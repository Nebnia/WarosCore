package fr.nebnia.waros;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nebnia.waros.PlayerListener;
import fr.nebnia.waros.commands.CommandAFK;
import fr.nebnia.waros.commands.CommandAH;
import fr.nebnia.waros.commands.CommandClaim;
import fr.nebnia.waros.commands.CommandClearlag;
import fr.nebnia.waros.commands.CommandCraft;
import fr.nebnia.waros.commands.CommandEnderchest;
import fr.nebnia.waros.commands.CommandGamemode;
import fr.nebnia.waros.commands.CommandHelp;
import fr.nebnia.waros.commands.CommandHome;
import fr.nebnia.waros.commands.CommandKit;
import fr.nebnia.waros.commands.CommandList;
import fr.nebnia.waros.commands.CommandMSG;
import fr.nebnia.waros.commands.CommandMoney;
import fr.nebnia.waros.commands.CommandPing;
import fr.nebnia.waros.commands.CommandPunishment;
import fr.nebnia.waros.commands.CommandShop;
import fr.nebnia.waros.commands.CommandSpawner;
import fr.nebnia.waros.commands.CommandTP;
import fr.nebnia.waros.cosmetics.CosmeticsListener;
import fr.nebnia.waros.regions.Region;

public class Main
        extends JavaPlugin
{

    public static Main main;
    private final Map<Player, Region> REGIONS = new HashMap<>();
    private final Map<Player, Rank> playerRanks = new HashMap<>();
    private final Set<Player> afkPlayers = new HashSet<>();
    private final Set<WarosPlayer> warosPlayers = new HashSet<>();
    private FileConfiguration playersFile;

    @Override
    public void onEnable()
    {
        main = this;
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable()
    {

    }

    private void registerCommands()
    {
        CommandTP cmdtp = new CommandTP();
        CommandMSG cmdmsg = new CommandMSG();
        CommandPunishment cmdp = new CommandPunishment();
        CommandMoney cmdm = new CommandMoney();
        CommandClaim cmdc = new CommandClaim();
        CommandHome cmdh = new CommandHome();
        CommandEnderchest cmdec = new CommandEnderchest();
        CommandKit cmdk = new CommandKit();

        getCommand("afk").setExecutor(new CommandAFK());
        getCommand("ah").setExecutor(new CommandAH());
        getCommand("back").setExecutor(cmdtp);
        getCommand("baltop").setExecutor(cmdm);
        getCommand("ban").setExecutor(cmdp);
        getCommand("broadcast").setExecutor(cmdmsg);
        getCommand("claim").setExecutor(cmdc);
        getCommand("clearlag").setExecutor(new CommandClearlag());
        getCommand("craft").setExecutor(new CommandCraft());
        getCommand("createhome").setExecutor(cmdh);
        getCommand("delhome").setExecutor(cmdh);
        getCommand("enderchest").setExecutor(cmdec);
        getCommand("endersee").setExecutor(cmdec);
        getCommand("gamemode").setExecutor(new CommandGamemode());
        getCommand("help").setExecutor(new CommandHelp());
        getCommand("home").setExecutor(cmdh);
        getCommand("ignore").setExecutor(cmdmsg);
        getCommand("ignoredlist").setExecutor(cmdmsg);
        getCommand("kick").setExecutor(cmdp);
        getCommand("kits").setExecutor(cmdk);
        getCommand("list").setExecutor(new CommandList());
        getCommand("money").setExecutor(cmdm);
        getCommand("msg").setExecutor(cmdmsg);
        getCommand("mute").setExecutor(cmdp);
        getCommand("pay").setExecutor(cmdm);
        getCommand("ping").setExecutor(new CommandPing());
        getCommand("reply").setExecutor(cmdmsg);
        getCommand("spawner").setExecutor(new CommandSpawner());
        getCommand("shop").setExecutor(new CommandShop());
        getCommand("tempban").setExecutor(cmdp);
        getCommand("tp").setExecutor(cmdtp);
        getCommand("tpa").setExecutor(cmdtp);
        getCommand("tpahere").setExecutor(cmdtp);
        getCommand("tpyes").setExecutor(cmdtp);
        getCommand("tpno").setExecutor(cmdtp);
        getCommand("unclaim").setExecutor(cmdc);
        getCommand("unignore").setExecutor(cmdmsg);
    }

    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new CosmeticsListener(), this);

    }
    public Map<Player, Region> getRegions()
    {
        return this.REGIONS;
    }
    public Map<Player, Rank> getPlayerRanks()
    {
        return this.playerRanks;
    }
    public Set<Player> getAFKPlayerList()
    {
        return this.afkPlayers;
    }

    public Set<WarosPlayer> getPlayers()
    {
        return warosPlayers;
    }

    public Player getPlayer(WarosPlayer wp)
    {
        Player p = null;
        for(WarosPlayer wps : getPlayers())
        {
            if(wps.equals(wp))
            {
                p = wp.getBukkitPlayer();
            }
        }
        return p;
    }

    public WarosPlayer getWarosPlayer(Player p)
    {
        WarosPlayer wp = null;
        for(WarosPlayer wps : getPlayers())
        {
            if(wps.getBukkitPlayer().equals(p))
            {
                wp = wps;
            }
        }

        return wp;
    }

    public void setPlayerFile(FileConfiguration fileConfig)
    {
        this.playersFile = fileConfig;
    }

    public FileConfiguration getPlayerFile()
    {
        return this.playersFile;
    }
}
