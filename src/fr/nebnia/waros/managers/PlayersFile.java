package fr.nebnia.waros.managers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.nebnia.waros.Main;
import fr.nebnia.waros.Rank;
import fr.nebnia.waros.WarosPlayer;

public class PlayersFile
{
    private File playersFile;
    private FileConfiguration fileConfig;
    private Main main;

    public void createFile(Main main)
    {
        playersFile = new File(main.getDataFolder() + "/players.yml");
        fileConfig = YamlConfiguration.loadConfiguration(playersFile);
        this.main = main;

        if(!playersFile.exists())
        {
            saveCustomYml(fileConfig, playersFile);
        }

        main.setPlayerFile(fileConfig);

    }

    public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile)
    {
        try
        {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getPlayerFile()
    {
        return this.fileConfig;
    }
    public void createPlayerPath(Player p)
    {
        String uuid = p.getUniqueId().toString();
        fileConfig.createSection(uuid);
        ConfigurationSection cs = fileConfig.getConfigurationSection(uuid);

        cs.set("lastplayername", p.getName());
        cs.set("first-logged", new SimpleDateFormat("dd-MM-yyyy hh:mm"));
        cs.set("last-logged", "");
        cs.set("rank", Rank.DEFAULT.toString());
        cs.set("money", 0);

    }

    public void savePlayerInformations(Player p)
    {
        String uuid = p.getUniqueId().toString();
        ConfigurationSection cs = fileConfig.getConfigurationSection(uuid);
        WarosPlayer wp = main.getWarosPlayer(p);

        cs.set("lastplayername", p.getName());
        cs.set("last-logged", new SimpleDateFormat("dd-MM-yyyy hh:mm"));
        cs.set("rank", wp.getRank().toString());
        cs.set("money", wp.getBalance());

    }

    /*
     * UUID:
     *   lastplayername: jérome
     *   first-logged: [dd:mm:yyyy] [hh:mm]
     *   rank: admin
     *   money:
     *
     *
     */
}
