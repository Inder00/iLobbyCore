package pl.inder00.lobbycore.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.inder00.lobbycore.baisc.BungeeServer;
import pl.inder00.lobbycore.bungeecord.Channel;

public class BungeePlayersTask extends BukkitRunnable {

    @Override
    public void run() {

        if(Bukkit.getOnlinePlayers().size() > 0){
            for(BungeeServer server : BungeeServer.getServers()){
                Channel.playerCount(server.getName());
            }
        }

    }
}
