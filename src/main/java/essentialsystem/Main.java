package essentialsystem;

import essentialsystem.Commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public MOTD motd = new MOTD();

    @Override
    public void onEnable() {
        System.out.println("Medieval Essentials in enabling...");

        motd.load();

        System.out.println("Medieval Essentials is enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Medieval Essentials in disabling...");

        motd.save();

        System.out.println("Medieval Essentials is disabled!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("medievalessentials")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
                HelpCommand command = new HelpCommand();
                command.sendHelpInfo(sender);
            }
        }

        if (label.equalsIgnoreCase("fly")) {
            FlyCommand command = new FlyCommand();
            command.toggleFlight(sender, args);
        }

        if (label.equalsIgnoreCase("broadcast")) {
            BroadcastCommand command = new BroadcastCommand();
            if (args.length != 0) {
                command.broadcast(sender, createStringFromArgs(0, args.length, args));
            }
            else {
                sender.sendMessage(ChatColor.RED + "Usage: /broadcast (message)");
                return false;
            }
        }

        if (label.equalsIgnoreCase("motd")) {
            MOTDCommand command = new MOTDCommand(this);
            command.showMOTD(sender);
        }

        if (label.equalsIgnoreCase("setmotd")) {
            SetMOTDCommand command = new SetMOTDCommand(this);
            command.setMOTD(sender, args);
        }

        return false;
    }

    public static String createStringFromArgs(int start, int end, String[] args) {
        String toReturn = "";
        for (int i = start; i < end; i++) {
            toReturn = toReturn + args[i];
            if (i < end - 1) {
                toReturn = toReturn + " ";
            }
        }
        return toReturn;
    }


}
