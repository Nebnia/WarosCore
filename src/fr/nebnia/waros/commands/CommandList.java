package fr.nebnia.waros.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandList
    implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("list"))
        {
            sender.sendMessage("§7Il y a actuellement §6" + Bukkit.getOnlinePlayers().size() + "§7 joueurs connectés sur §6" + Bukkit.getMaxPlayers() + "§7.");

        }

        return false;
    }

}