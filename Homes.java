package homes.homes;

import org.bukkit.plugin.java.JavaPlugin;

public final class Homes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("sethome").setExecutor(new sethome(this));
        getCommand("home").setExecutor(new home(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
