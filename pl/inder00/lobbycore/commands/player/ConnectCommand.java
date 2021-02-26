package pl.inder00.lobbycore.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.inder00.lobbycore.Core;
import pl.inder00.lobbycore.baisc.BungeeServer;
import pl.inder00.lobbycore.bungeecord.Channel;
import pl.inder00.lobbycore.commands.PlayerCommand;
import pl.inder00.lobbycore.storage.Config;

import java.util.Arrays;

public class ConnectCommand extends PlayerCommand {

    public ConnectCommand() {
        super("connect", "Laczenie z innym serwerem", "/connect <server>", Arrays.asList("polacz","serwer"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(sender.hasPermission("core.connect")){

            Config cfg = Core.getConfiguration();

            if(args.length == 0){
                if(sender instanceof Player){
                    ((Player) sender).openInventory(Core.getServerGui());
                    return false;
                }
                sender.sendMessage("§8» §cPoprawne uzycie §7/polacz <serwer>");
                return false;
            } else if(args.length == 1 && sender instanceof Player){

                Player p = (Player) sender;
                String server = args[0];
                BungeeServer bungeeServer = BungeeServer.get(server);
                if(bungeeServer == null){
                    sender.sendMessage("§cNie znaleziono podanego serwera!");
                    return false;
                }
                Channel.connect(p,bungeeServer.getName());
                p.sendTitle(cfg.connecting$title.replace("{SERVER}",bungeeServer.getName().toUpperCase()),cfg.connecting$subtitle.replace("{SERVER}",bungeeServer.getName().toUpperCase()));
                p.sendMessage(cfg.connecting$chat.replace("{SERVER}",bungeeServer.getName().toUpperCase()));
                return true;

            } else if(args.length == 2){

                if(sender.hasPermission("core.connect.other")){

                    String server = args[0];
                    BungeeServer bungeeServer = BungeeServer.get(server);
                    if(bungeeServer == null){
                        sender.sendMessage("§cNie znaleziono podanego serwera!");
                        return false;
                    }

                    Player p = Bukkit.getPlayer(args[1]);
                    if(p == null){
                        sender.sendMessage("§cPodany gracz jest aktualnie offline");
                        return false;
                    }

                    Channel.connect(p,bungeeServer.getName());
                    p.sendTitle(cfg.connecting$title.replace("{SERVER}",bungeeServer.getName().toUpperCase()),cfg.connecting$subtitle.replace("{SERVER}",bungeeServer.getName().toUpperCase()));
                    p.sendMessage(cfg.connecting$chat.replace("{SERVER}",bungeeServer.getName().toUpperCase()));
                    return true;

                } else {
                    sender.sendMessage("§cNie posiadasz uprawnien do wykonania tej komendy (core.connect.other)");
                    return false;
                }

            } else {
                if(sender instanceof Player){
                    ((Player) sender).openInventory(Core.getServerGui());
                    return false;
                }
                sender.sendMessage("§8» §cPoprawne uzycie §7/polacz <serwer>");
                return false;
            }

        } else {
            sender.sendMessage("§cNie posiadasz uprawnien do wykonania tej komendy (core.connect)");
            return false;
        }
    }
}
