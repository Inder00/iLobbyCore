package pl.inder00.lobbycore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.storage.Config;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        Config cfg = Core.getConfiguration();

        if(!p.hasPermission("chat.bypass")){
            e.setCancelled(true);
            p.sendMessage(cfg.cannot$chat);
            return;

        }
    }

}
