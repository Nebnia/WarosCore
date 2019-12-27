package fr.nebnia.waros.cosmetics;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CosmeticsListener
    implements Listener
{
    CosmeticsUtils cu = new CosmeticsUtils();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        if(p.hasPermission("waros.name.joueur"))
        {
            p.setPlayerListName("§7[Joueur] " + p.getName());
        } else if(p.hasPermission("waros.name.vip"))
        {
            p.setPlayerListName("§a[VIP] " + p.getName());
        } else if(p.hasPermission("waros.name.streamer"))
        {
            p.setPlayerListName("§5[Streamer] " + p.getName());
        } else if(p.hasPermission("waros.name.youtuber"))
        {
            p.setPlayerListName("§c[Youtuber] " + p.getName());
        } else if(p.hasPermission("waros.name.mod"))
        {
            p.setPlayerListName("§6[Modérateur] " + p.getName());
        } else if(p.hasPermission("waros.name.builder"))
        {
            p.setPlayerListName("§b[Constructeur] " + p.getName());
        } else if(p.hasPermission("waros.name.admin"))
        {
            p.setPlayerListName("§4[Administrateur] " + p.getName());
        }

        for(Player player : Bukkit.getOnlinePlayers())
        {
            cu.sendTablist(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            cu.sendTablist(p);
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void chatFormating(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();

        if(p.hasPermission("waros.name.joueur"))
        {
            e.setFormat("§7[Joueur] " + p.getName() + " : " + e.getMessage());
        } else if(p.hasPermission("waros.name.vip"))
        {
            e.setFormat("§a[VIP] " + p.getName() + "§f : " + e.getMessage());
        } else if(p.hasPermission("waros.name.streamer"))
        {
            e.setFormat("§5[Streamer] " + p.getName() + "§f : " + e.getMessage());
        } else if(p.hasPermission("waros.name.youtuber"))
        {
            e.setFormat("§C[Youtuber] " + p.getName() + "§f : " + e.getMessage());
        } else if(p.hasPermission("waros.name.mod"))
        {
            e.setFormat("§6[Modérateur] " + p.getName() + "§f : " + e.getMessage());
        } else if(p.hasPermission("waros.name.builder"))
        {
            e.setFormat("§b[Constructeur] " + p.getName() + "§f : " + e.getMessage());
        }  else if(p.hasPermission("waros.name.admin"))
        {
            e.setFormat("§4[Administrateur] " + p.getName() + "§f : " + e.getMessage());
        }
    }
}
