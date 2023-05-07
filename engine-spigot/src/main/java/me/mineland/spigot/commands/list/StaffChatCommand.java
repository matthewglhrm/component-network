package me.mineland.spigot.commands.list;

import me.mineland.core.Fields;
import me.mineland.core.player.roles.RoleController;
import me.mineland.core.utils.StringUtils;
import me.mineland.spigot.commands.SpigotCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffChatCommand extends SpigotCommands {

    public static List<Player> STAFFERS_IN_CHAT = new ArrayList<>();

    public StaffChatCommand(String name, String... aliases) {
        super("staffchat", "sc");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando é somente para jogadores.");
            return;
        }
        Player player = (Player) sender;
        RoleController roleController = RoleController.getRolePlayer(player);
        if (!player.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.staffchat")) {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
            return;
        }

        if (args.length == 0) {
            if (STAFFERS_IN_CHAT.contains(player)) {
                player.sendMessage("§cVocê saiu do staffchat.");
                STAFFERS_IN_CHAT.remove(player);
            } else {
                player.sendMessage("§aVocê entrou no staffchat");
                STAFFERS_IN_CHAT.add(player);
            }
            return;
        }

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> players.hasPermission(Fields.PREFIX_PERMISSION + ".cmd.staffchat"))
                .forEach(players -> {
                    players.sendMessage("§e§l[SC] {playerGroup}{playerName}§f: {message}"
                            .replace("{playerGroup}", roleController.getPrefix())
                            .replace("{playerName}", player.getName())
                            .replace("{message}", StringUtils.join(args, " ")
                            .replace("&", "§")
                    ));
                });

    }
}
