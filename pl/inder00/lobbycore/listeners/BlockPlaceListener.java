package pl.inder00.lobbycore.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.storage.Config;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent e){
        Player p = e.getPlayer();
        Config cfg = Core.getConfiguration();
        if(!p.getGameMode().equals(GameMode.CREATIVE)){
            p.sendMessage(cfg.cannot$build);
            e.setCancelled(true);
            return;
        }
    }

}
