import java.util.ArrayList;

public class Player {
    private final String name;
    private int health;
    private int attackPower;
    private final ArrayList<Item> inventory;
    private final ArrayList<String> quests;

    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.inventory = new ArrayList<>();
        this.quests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }


    public int getAttackPower() {
        return attackPower;
    }


    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " took " + damage + " damage! Remaining health: " + health);
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " healed for " + amount + " health! Current health: " + health);
    }


    public void addItem(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }


    public Item getItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }


    public void useItem(Item item) {
        if (item != null) {
            if (item.getName().equalsIgnoreCase("Health Potion")) {
                heal(30); 
            } else if (item.getName().equalsIgnoreCase("Attack Potion")) {
                attackPower += 5;
                System.out.println("Your attack power increased to " + attackPower + "!");
            }
            inventory.remove(item);
            System.out.println("You used " + item.getName() + ".");
        } else {
            System.out.println("Item not found in your inventory.");
        }
    }


    public void showInventory() {
        System.out.println("Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }


    public void addQuest(String quest) {
        quests.add(quest);
        System.out.println("New Quest: " + quest);
    }


    public void showQuests() {
        System.out.println("Active Quests:");
        if (quests.isEmpty()) {
            System.out.println("No active quests at the moment.");
        } else {
            for (String quest : quests) {
                System.out.println("- " + quest);
            }
        }
    }

    public void attack(Enemy enemy) {
        System.out.println(name + " attacks " + enemy.getName() + " for " + attackPower + " damage!");
        enemy.takeDamage(attackPower);
    }

    @Override
    public String toString() {
        return name + " - Health: " + health + ", Attack Power: " + attackPower;
    }
}