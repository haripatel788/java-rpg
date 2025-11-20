public class Quest {
    private final String description;
    private final String reward;

    public Quest(String description, String reward) {
        this.description = description;
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public String getReward() {
        return reward;
    }

    @Override
    public String toString() {
        return "Quest: " + description + " | Reward: " + reward;
    }
}