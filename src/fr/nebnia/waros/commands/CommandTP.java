package fr.nebnia.waros.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_14_R1.ChatMessageType;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;

public class CommandTP
    implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("back"))
        {

        }
        if(cmd.getName().equalsIgnoreCase("tp"))
        {

        }
        if(cmd.getName().equalsIgnoreCase("tpa"))
        {
            if(!(sender instanceof Player))
            {
                sender.sendMessage("[!] Commande inutilisable par la console.");
                return false;
            } else
            {
                Player p = (Player) sender;
                if(args.length == 1)
                {
                    try
                    {
                        Player target = Bukkit.getPlayer(args[0]);

                        p.sendMessage("§7[§2§l!§7] Demande de téléportation envoyée à §f" + target.getName() + "§7.");
                        requestTPA(p, target);



                    } catch (Exception e)
                    {
                        p.sendMessage("§7[§4§l!§7] Le joueur §f" + args[0] + "§7 est introuvable.");
                        return false;
                    }
                } else
                {

                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("tpahere"))
        {

        }
        if(cmd.getName().equalsIgnoreCase("tpyes") || cmd.getName().equalsIgnoreCase("tpno"))
        {

        }
        return false;
    }

    private void requestTPA(Player player, Player target)
    {
        IChatBaseComponent comp = ChatSerializer
                .a("{\"text\":\"§7" + player.getName() + " souhaite se téléporter à toi, accepter ?\",\"extra\":[{\"text\":\" §a[OUI]\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§aAccepter la demande\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tpyes\"},\"extra\":[{\"text\":\"§4[NON]\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§4Refuser la demande\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tpno\"}}]}");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp);
        ((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
    }

}
