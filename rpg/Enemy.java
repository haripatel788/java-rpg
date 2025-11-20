public class Enemy {
    private final String name;
    private int health;
    private final int attackPower;

    public Enemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " took " + damage + " damage! Remaining health: " + health);
    }

    public void attack(Player player) {
        System.out.println(name + " attacks " + player.getName() + " for " + attackPower + " damage!");
        player.takeDamage(attackPower);
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    // Override the toString() method to return the enemy's name
    @Override
    public String toString() {
        return name + " - Health: " + health + ", Attack Power: " + attackPower;
    }
}