package Roma;

public class MoneyManager {
    public final static int STARTING_MONEY = 0;

    private int[] playerMoney = new int[Roma.MAX_PLAYERS];

    public MoneyManager() {

    }

    public void gainMoney(int playerID, int amount) {
        playerMoney[playerID] += amount;
    }

    public void loseMoney(int playerID, int amount) {
        assert (playerMoney[playerID] > amount);
        playerMoney[playerID] -= amount;
    }

    public boolean enoughMoney(int playerID, int amount) {
        boolean enoughMoney = false;
        if (playerMoney[playerID] > amount){
            enoughMoney = true;
        } else {
            System.out.println("Not enough money!");
        }
        return enoughMoney;
    }

    public void transferMoney(int from, int to, int amount) {
        assert (playerMoney[from] > amount);
        playerMoney[from] -= amount;
        playerMoney[to] += amount;
    }

    public int getPlayerMoney(int player) {
        return playerMoney[player];
    }

    public String toString() {
        return "Player One has: $" + playerMoney[Roma.PLAYER_ONE] + ", and Player Two has: $" +
                playerMoney[Roma.PLAYER_TWO];
    }
}
