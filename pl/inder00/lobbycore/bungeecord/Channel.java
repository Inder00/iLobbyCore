package pl.inder00.lobbycore.bungeecord;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.baisc.BungeeServer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Channel implements PluginMessageListener {

    public static final String PLUGIN_CHANNEL = "BungeeCord";

    public static void connect(Player player, String target) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("Connect");
            output.writeUTF(target);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Nie mozna polaczyc sie do serwera "+target+" ("+e.getCause().getMessage()+")");
        }

        player.sendPluginMessage(Core.getInstance(), PLUGIN_CHANNEL, array.toByteArray());
    }
    public static void playerCount(String target) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("PlayerCount");
            output.writeUTF(target);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Nie mozna okreslic ilosci graczy na serwerze "+target+" ("+e.getCause().getMessage()+")");
        }

        Bukkit.getOnlinePlayers().iterator().next().sendPluginMessage(Core.getInstance(), PLUGIN_CHANNEL, array.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        if (subchannel != null && subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            int playerCount = in.readInt();
            try {
                BungeeServer.get(server).setPlayers(playerCount);
            } catch(Exception e) {

            }
        }
    }
}
