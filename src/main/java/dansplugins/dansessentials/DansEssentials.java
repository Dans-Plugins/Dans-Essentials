package dansplugins.dansessentials;

import dansplugins.dansessentials.Commands.*;
import dansplugins.dansessentials.bStats.Metrics;
import dansplugins.dansessentials.eventhandlers.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import preponderous.ponder.AbstractPonderPlugin;
import preponderous.ponder.misc.PonderAPI_Integrator;
import preponderous.ponder.misc.specification.ICommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Daniel Stephenson
 */
public class DansEssentials extends AbstractPonderPlugin implements Listener {

    private static DansEssentials instance;

    private String version = "v2.1-alpha-2";

    // public methods -------------------------------------------------------------------------

    public static DansEssentials getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ponderAPI_integrator = new PonderAPI_Integrator(this);
        toolbox = getPonderAPI().getToolbox();

        // TODO: handle config stuff

        registerEventHandlers();
        initializeCommandService();
        int pluginId = 9527;
        Metrics metrics = new Metrics(this, pluginId);
        getPonderAPI().setDebug(false);
    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            DefaultCommand defaultCommand = new DefaultCommand();
            return defaultCommand.execute(sender);
        }

        return getPonderAPI().getCommandService().interpretCommand(sender, label, args);
    }

    // end of public methods -------------------------------------------------------------------------


    // helper methods -------------------------------------------------------------------------

    /**
     * Method to create and register the event handlers.
     *
     */
    private void registerEventHandlers() {
        ArrayList<Listener> listeners = new ArrayList<>();
        listeners.add(new ChatHandler());
        listeners.add(new InteractionHandler());
        listeners.add(new JoinHandler());
        listeners.add(new SignHandler());
        listeners.add(new TeleportHandler());
        getToolbox().getEventHandlerRegistry().registerEventHandlers(listeners, this);
    }

    /**
     * Method to initialize the command service with commands.
     *
     */
    private void initializeCommandService() {
        ArrayList<ICommand> commands = new ArrayList<>(Arrays.asList(
                new BackCommand(), new BroadcastCommand(), new ClearInvCommand(),
                new FlyCommand(), new FlySpeedCommand(), new GamemodeCommand(),
                new GetPosCommand(), new HelpCommand(), new InvseeCommand(),
                new LabelCommand(), new MuteCommand(), new UnmuteCommand(),
                new VanishCommand()
        ));
        getPonderAPI().getCommandService().initialize(commands, "That command wasn't found.");
    }

    @Override
    public boolean isVersionMismatched() {
        String versionInConfig = this.getConfig().getString("version");
        if (versionInConfig == null) {
            return true;
        }
        return !versionInConfig.equalsIgnoreCase(getVersion());
    }

    @Override
    public String getVersion() {
        return version;
    }

    // end of helper methods -------------------------------------------------------------------------

}
