import java.util.Random;

public class Map {
    private final int rows, cols;
    private final String[][] grid;
    private final boolean[][] visited;
    private int playerRow, playerCol;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new String[rows][cols];
        this.visited = new boolean[rows][cols];
        this.playerRow = 0;
        this.playerCol = 0;
        populateMap();
    }

    private void populateMap() {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextInt(10) < 2) {
                    grid[i][j] = "T";
                } else if (random.nextInt(10) < 2) {
                    grid[i][j] = "H";
                } else if (random.nextInt(10) < 1) {
                    grid[i][j] = "B";
                } else {
                    grid[i][j] = ".";
                }
            }
        }
        grid[rows - 1][cols - 1] = "E";
    }

        public void movePlayer(String direction) {
        if (direction.equals("north") && playerRow > 0) {
            playerRow--;
        } else if (direction.equals("south") && playerRow < rows - 1) {
            playerRow++;
        } else if (direction.equals("east") && playerCol < cols - 1) {
            playerCol++;
        } else if (direction.equals("west") && playerCol > 0) {
            playerCol--;
        } else {
            System.out.println("Invalid direction. You can't move that way.");
        }
    }

    public boolean triggerEvent(Player player) {
        String room = grid[playerRow][playerCol];

        if (visited[playerRow][playerCol]) {
            System.out.println("You already explored this area. Nothing happens.");
            return false;
        }

        visited[playerRow][playerCol] = true;

        if (room.equals("T")) {
            System.out.println("You found a treasure chest! Opening...");
            String[] treasures = {"Gold", "Diamond", "Magic Sword", "Health Potion"};
            String foundTreasure = treasures[new Random().nextInt(treasures.length)];
            player.addItem(new Item(foundTreasure, 50));
        } else if (room.equals("H")) {
            System.out.println("You found a Healing Fountain! Restoring all health...");
            player.heal(100);
        } else if (room.equals("B")) {
            System.out.println("You encounter a Boss Enemy!");
            Enemy boss = new Enemy("Dark Knight", 150, 25);
            CombatSystem combat = new CombatSystem(player, boss);
            combat.startCombat();
        } else if (room.equals("E")) {
            System.out.println("You have discovered the exit to the dungeon! Well done!");
            return true;
        } else {
            System.out.println("This room is empty. Nothing interesting here.");
        }

        return false;
    }

    public void printMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("P ");
                } else if (visited[i][j]) {
                    System.out.print(grid[i][j] + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public String getPlayerPosition() {
        return "(" + playerRow + ", " + playerCol + ")";
    }

    public boolean hasReachedExit() {
        return grid[playerRow][playerCol].equals("E");
    }
}