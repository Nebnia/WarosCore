package fr.nebnia.waros.cosmetics;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.nebnia.waros.Utils;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class CosmeticsUtils
    extends Utils
{
    public void sendTablist(Player p)
    {
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = ChatSerializer.a(
                "{\"text\": \"§e§m======§e{ §6§lWaros§e }§m====== "
                        + "§n\"}");
        IChatBaseComponent footer = ChatSerializer.a(
                "{\"text\": \"§n "+
                        "§7Joueurs: §a" + Bukkit.getOnlinePlayers().size() + " §f/ §c" + Bukkit.getMaxPlayers() + " §f| §6Staff: §e " + getStaff().size()
                        + "§n"
                        + "§7Boutique: §6waros.buycraft.net\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try
        {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        connection.sendPacket(packet);
    }
}
