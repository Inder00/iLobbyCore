package pl.inder00.lobbycore.tasks;

import me.hfox.spigboard.Spigboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.baisc.BungeeServer;
import pl.inder00.lobbycore.baisc.ScoreboardSlot;
import pl.inder00.lobbycore.storage.Config;

public class ScoreboardTask extends BukkitRunnable {

    private Spigboard spigboard;
    private Config cfg;
    private Runtime runtime;

    public ScoreboardTask(){

        this.runtime = Runtime.getRuntime();

        this.cfg = Core.getConfiguration();
        this.spigboard = new Spigboard(cfg.scoreboard$name);

        for(ScoreboardSlot slot : ScoreboardSlot.getSlots()){
            this.spigboard.add(slot.getKey(),variable(slot.getValue()),slot.getId());
        }
    }

    @Override
    public void run() {

        for(ScoreboardSlot slot : ScoreboardSlot.getUpdating()){
            this.spigboard.getEntry(slot.getKey()).update(variable(slot.getValue()));
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            p.setScoreboard(this.spigboard.getScoreboard());
        }

    }

    private String variable(String input){
        for(BungeeServer server : BungeeServer.getServers()){
            input = input.replace("{SERVER:"+server.getName()+"}", ""+server.getPlayers());
        }
        return input
                .replace("{USAGERAM}", ""+((this.runtime.totalMemory() / 1024L / 1024L)-(this.runtime.freeMemory() / 1024L / 1024L)))
                ;
    }
}
