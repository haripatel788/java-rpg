import java.util.Scanner;

public class RPGGame {
    private final Player player;
    private final Map map;

    public RPGGame() {
        player = new Player("Hero", 100, 10);
        map = new Map(10, 10); 
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("Welcome to the Enhanced Dungeon RPG!");
        System.out.println("Explore the dungeon, defeat enemies, collect treasures, and complete quests.");
        System.out.println("Find the exit to win!");

        while (!gameOver) {
            System.out.println("\nLocation: " + map.getPlayerPosition());
            map.printMap();
            System.out.println(player);

            System.out.println("Choose an action: move (north, south, east, west), inventory, quests, or quit:");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("north") || action.equals("south") || action.equals("east") || action.equals("west")) {
                map.movePlayer(action);
                gameOver = map.triggerEvent(player);
            } else if (action.equals("inventory")) {
                player.showInventory();
            } else if (action.equals("quests")) {
                player.showQuests();
            } else if (action.equals("quit")) {
                gameOver = true;
                System.out.println("You have quit the game.");
            } else {
                System.out.println("Invalid action. Try again.");
            }

            if (player.getHealth() <= 0) {
                System.out.println("You have died. Game over!");
                gameOver = true;
            }

            if (map.hasReachedExit()) {
                System.out.println("Congratulations! You've found the exit and completed the game!");
                gameOver = true;
            }
        }

        scanner.close();
    }
}