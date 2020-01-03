package homes.homes;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class sethome implements CommandExecutor {

    private Map<String, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent PlayerInteractEvent) {

        Player player = PlayerInteractEvent.getPlayer();
        if (cooldowns.containsKey(player.getName())) {

            int seconds = 5;
            long timeleft = ((cooldowns.get(player.getName()) / 1000) + seconds) - (System.currentTimeMillis() / 1000);
            if (timeleft > 0) {
                player.sendMessage(ChatColor.RED + "Veuillez attendre " + timeleft + " secondes avant de refaire cela !");
                PlayerInteractEvent.setCancelled(true);
                return;
            }

        }

        cooldowns.put(player.getName(), System.currentTimeMillis());
    }

    private Homes test;

    public sethome(Homes test) {
        this.test = test;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("sethome")) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    final Player player = (Player) sender;
                    final UUID uuid = player.getUniqueId();
                    final Location location = player.getLocation();

                    final File file = new File(test.getDataFolder(), "Homes/homes.yml");
                    final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                    final String key = "players." + uuid.toString();

                        configuration.set(key + ".world", location.getWorld().getName());
                        configuration.set(key + ".x", location.getX());
                        configuration.set(key + ".y", location.getY());
                        configuration.set(key + ".z", location.getZ());
                        configuration.set(key + ".yaw", location.getYaw());
                        configuration.set(key + ".pitch", location.getPitch());

                        if (cooldowns.containsKey(player.getName())) {

                            int seconds = 30;
                            long timeleft = ((cooldowns.get(player.getName()) / 1000) + seconds) - (System.currentTimeMillis() / 1000);
                            if (timeleft > 0) {
                                player.sendMessage(ChatColor.RED + "Veuillez attendre " + timeleft + " secondes pour mettre un nouveau sethome !");
                                return true;
                            }

                        }

                        cooldowns.put(player.getName(), System.currentTimeMillis());

                        player.sendMessage(ChatColor.GREEN + "Home defini ! Faites /home pour vous y teleporter");

                        try {
                            configuration.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
                return true;
            }
        }
        return false;
    }
}
