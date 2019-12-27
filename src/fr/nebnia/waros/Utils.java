package fr.nebnia.waros;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils
{

    private Main main = Main.main;

    public Set<Player> getStaff()
    {
        Set<Player> staff = new HashSet<Player>();
        for (Player p : Bukkit.getOnlinePlayers())
        {
            if(p.hasPermission("staff"))
            {
                staff.add(p);
            }
        }
        return staff;
    }
}
