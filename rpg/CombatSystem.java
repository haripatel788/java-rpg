import java.util.Scanner;

public class CombatSystem {
    private final Player player;
    private final Enemy enemy;

    public CombatSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startCombat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("A battle begins against " + enemy.getName() + "!");
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("\n" + player);
            System.out.println(enemy);

            System.out.println("Choose an action: attack, defend, or use item:");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("attack")) {
                player.attack(enemy);
                if (!enemy.isDefeated()) {
                    enemy.attack(player);
                }
            } else if (action.equals("defend")) {
                System.out.println(player.getName() + " defends, reducing incoming damage!");
                int reducedDamage = Math.max(0, enemy.getHealth() / 5);
                player.takeDamage(reducedDamage);
            } else if (action.equals("use item")) {
                player.showInventory();
                System.out.println("Choose an item to use:");
                String itemName = scanner.nextLine();
                Item itemToUse = player.getItemByName(itemName);
                if (itemToUse != null) {
                    player.useItem(itemToUse);
                } else {
                    System.out.println("Invalid item choice!");
                }
            } else {
                System.out.println("Invalid action. Try again.");
            }
        }

        if (player.getHealth() > 0) {
            System.out.println("You have defeated " + enemy.getName() + "!");
        } else {
            System.out.println("You were defeated by " + enemy.getName() + ". Game over.");
        }
    }
}