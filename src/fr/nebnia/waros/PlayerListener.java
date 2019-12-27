package fr.nebnia.waros;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nebnia.waros.regions.Region;

public class PlayerListener
        implements Listener
{

    private Main main = Main.main;
    private Map<Player, Region> regions = main.getRegions();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        main.getPlayers().add(new WarosPlayer(main, e.getPlayer()));
    }
    /*@EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if (event.getTo().getBlockX() == event.getFrom().getBlockX() &&
                event.getTo().getBlockY() == event.getFrom().getBlockY() &&
                event.getTo().getBlockZ() == event.getFrom().getBlockZ()) {
            return;
        }
        Player player = event.getPlayer();


        // user actually moved, check if they changed to a different region.
        Region newRegion = RegionManager.getRegion(event.getTo());
        Region oldRegion = regions.get(player);
        if (newRegion == oldRegion) {
            return; // user is still inside the same region as last time
        }

        // user has changed regions. send exit message of last region
        if (oldRegion != null && oldRegion.hasExitMessage()) {
            player.sendMessage(oldRegion.getExitMessage());
        }

        // check if user has left all regions
        if (newRegion == null) {
            // user has left all regions
        	regions.remove(player);
            return; // done.
        }

        // user is entering a new region
        regions.put(player, newRegion);
        if (newRegion.hasEnterMessage()) {
            player.sendMessage(newRegion.getEnterMessage());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
    	regions.remove(event.getPlayer());
    }*/
}
