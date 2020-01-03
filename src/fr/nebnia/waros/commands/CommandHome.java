package homes.homes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class home implements CommandExecutor {

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

    public home(Homes test) {
        this.test = test;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("home")) {
            if (args.length == 0) {

                if (sender instanceof Player) {
                    final Player player = (Player) sender;
                    final File file = new File(test.getDataFolder(), "Homes/homes.yml");
                    final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

                    final String key = "players." + player.getUniqueId();
                    final ConfigurationSection ConfigurationSection = configuration.getConfigurationSection(key);

                    if (ConfigurationSection == null) {
                        player.sendMessage(ChatColor.RED + "Aucun home, veuillez definir un home avec /sethome");
                    } else {
                        final World world = Bukkit.getWorld(ConfigurationSection.getString("world"));
                        final double x = ConfigurationSection.getDouble("x");
                        final double y = ConfigurationSection.getDouble("y");
                        final double z = ConfigurationSection.getDouble("z");
                        final float yaw = (float) ConfigurationSection.getDouble("yaw");
                        final float pitch = (float) ConfigurationSection.getDouble("pitch");

                        final Location homeLocation = new Location(world, x, y, z, yaw, pitch);

                        if(cooldowns.containsKey(player.getName())){

                            int seconds = 10;
                            long timeleft = ((cooldowns.get(player.getName()) / 1000) + seconds) - (System.currentTimeMillis() / 1000);
                            if(timeleft > 0){
                                player.sendMessage(ChatColor.RED + "Veuillez attendre " + timeleft + " secondes avant de refaire cela !");
                                return true;
                            }

                        }

                        cooldowns.put(player.getName(), System.currentTimeMillis());


                        player.teleport(homeLocation);
                        player.sendMessage(ChatColor.GREEN + "Teleportation chez vous avec succes");
                    }
                }
                return true;
            }
        }
        return false;
    }
}
