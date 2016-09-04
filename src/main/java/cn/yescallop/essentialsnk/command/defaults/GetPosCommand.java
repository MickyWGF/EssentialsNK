package cn.yescallop.essentialsnk.command.defaults;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;
import cn.yescallop.essentialsnk.EssentialsAPI;
import cn.yescallop.essentialsnk.command.CommandBase;

public class GetPosCommand extends CommandBase {

    public GetPosCommand(EssentialsAPI api) {
        super("getpos", api);
        this.setAliases(new String[]{"coords", "position", "whereami", "getlocation", "getloc"});
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }
        if (args.length > 1) {
            this.sendUsage(sender);
            return false;
        }
        Player player;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(TextFormat.RED + lang.translateString("commands.generic.ingame"));
                return false;
            }
            player = (Player) sender;
        } else {
            if (!sender.hasPermission("essentialsnk.getpos.other")) {
                sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.permission"));
                return false;
            }
            player = api.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(TextFormat.RED + lang.translateString("commands.generic.player.notfound", args[0]));
                return false;
            }
        }
        sender.sendMessage(sender == player ? lang.translateString("commands.getpos.success", new String[]{player.getLevel().getName(), String.valueOf(player.getFloorX()), String.valueOf(player.getFloorY()), String.valueOf(player.getFloorZ())}) : lang.translateString("commands.getpos.success.other", new String[]{player.getName(), player.getLevel().getName(), String.valueOf(player.getFloorX()), String.valueOf(player.getFloorY()), String.valueOf(player.getFloorZ())}));
        return true;
    }
}
