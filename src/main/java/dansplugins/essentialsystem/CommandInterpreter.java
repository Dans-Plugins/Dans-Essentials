package dansplugins.essentialsystem;

import dansplugins.essentialsystem.Commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static dansplugins.essentialsystem.MedievalEssentials.createStringFromArgs;

public class CommandInterpreter {

    private static CommandInterpreter instance;

    private CommandInterpreter() {

    }

    public static CommandInterpreter getInstance() {
        if (instance == null) {
            instance = new CommandInterpreter();
        }
        return instance;
    }

    public void interpretCommand(CommandSender sender, String label, String[] args) {
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
            }
        }

        if (label.equalsIgnoreCase("motd")) {
            MOTDCommand command = new MOTDCommand();
            command.showMOTD(sender);
        }

        if (label.equalsIgnoreCase("setmotd")) {
            SetMOTDCommand command = new SetMOTDCommand();
            command.setMOTD(sender, args);
        }

        if (label.equalsIgnoreCase("seen")) {
            SeenCommand command = new SeenCommand();
            command.showLastLogout(sender, args);
        }

        if (label.equalsIgnoreCase("vanish")) {
            VanishCommand command = new VanishCommand();
            command.toggleVisibility(sender);
        }

        if (label.equalsIgnoreCase("mute")) {
            MuteCommand command = new MuteCommand();
            command.mutePlayer(sender, args);
        }

        if (label.equalsIgnoreCase("unmute")) {
            UnmuteCommand command = new UnmuteCommand();
            command.unmutePlayer(sender, args);
        }

        if (label.equalsIgnoreCase("nick")) {
            NickCommand command = new NickCommand();
            command.changeDisplayName(sender, args);
        }
/*
        if (label.equalsIgnoreCase("whois")) {
            WhoIsCommand command = new WhoIsCommand();
            command.showIGNToPlayer(sender, args);
        }
*/

        if (label.equalsIgnoreCase("getpos")) {
            GetPosCommand command = new GetPosCommand();
            command.sendCoordinates(sender);
        }

        if (label.equalsIgnoreCase("flyspeed")) {
            FlySpeedCommand command = new FlySpeedCommand();
            command.setFlySpeed(sender, args);
        }

        if (label.equalsIgnoreCase("gm")) {
            GamemodeCommand command = new GamemodeCommand();
            command.setGamemode(sender, args);
        }

        if (label.equalsIgnoreCase("back")) {
            BackCommand command = new BackCommand();
            command.teleportToLastLocation(sender);
        }

        if (label.equalsIgnoreCase("logins")) {
            LoginsCommand command = new LoginsCommand();
            command.showLogins(sender);
        }

        if (label.equalsIgnoreCase("label")) {
            LabelCommand command = new LabelCommand();
            command.renameItemInMainHand(sender, args);
        }

        if (label.equalsIgnoreCase("invsee"))  {
            InvseeCommand command = new InvseeCommand();
            command.invseePlayer(sender, args);
        }

        if (label.equalsIgnoreCase("clearinv"))  {
            ClearInvCommand command = new ClearInvCommand();
            command.clearInv(sender, args);
        }
    }

}
