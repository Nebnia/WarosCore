package fr.nebnia.waros;

import org.bukkit.entity.Player;

public class WarosPlayer
{
    private Player player;
    private Rank rank;
    private boolean AFKStatus;
    private int balance = 0;


    public WarosPlayer(Main main, Player p)
    {
        this.player = p;
        this.rank = main.getPlayerRanks().get(p);
        this.AFKStatus = false;
        this.balance = main.getPlayerFile().getInt(p.getUniqueId() + ".money");
    }

    public Player getBukkitPlayer()
    {
        return this.player;
    }

    public Rank getRank()
    {
        return this.rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }

    public void setAFKStatus(boolean status)
    {
        this.AFKStatus = status;
    }

    public boolean getAFKStatus()
    {
        return this.AFKStatus;
    }

    public int getBalance()
    {
        return this.balance;
    }

    public void setBalance(int bal)
    {
        this.balance = bal;
    }
}
