package dansplugins.dansessentials;

import dansplugins.dansessentials.Commands.*;
import dansplugins.dansessentials.bStats.Metrics;
import dansplugins.dansessentials.eventhandlers.*;
import dansplugins.dansessentials.services.LocalConfigService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;
import preponderous.ponder.minecraft.abs.PonderPlugin;
import preponderous.ponder.minecraft.spigot.misc.PonderAPI_Integrator;
import preponderous.ponder.minecraft.spigot.tools.EventHandlerRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class DansEssentials extends PonderPlugin implements Listener {
    private boolean debug = false;

    private static DansEssentials instance;

    private final String pluginVersion = "v" + getDescription().getVersion();

    // public methods -------------------------------------------------------------------------

    public static DansEssentials getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.ponderAPI_integrator = new PonderAPI_Integrator(this);

        // create/load config
        if (!(new File("./plugins/DansEssentials/config.yml").exists())) {
            LocalConfigService.getInstance().saveMissingConfigDefaultsIfNotPresent();
        }
        else {
            // pre load compatibility checks
            if (isVersionMismatched()) {
                LocalConfigService.getInstance().saveMissingConfigDefaultsIfNotPresent();
            }
            reloadConfig();
        }

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
        EventHandlerRegistry eventHandlerRegistry = new EventHandlerRegistry(getPonderAPI());
        eventHandlerRegistry.registerEventHandlers(listeners, this);
    }

    /**
     * Method to initialize the command service with commands.
     *
     */
    private void initializeCommandService() {
        ArrayList<AbstractPluginCommand> commands = new ArrayList<>(Arrays.asList(
                new BackCommand(), new BroadcastCommand(), new ClearInvCommand(),
                new FlyCommand(), new FlySpeedCommand(), new GamemodeCommand(),
                new GetPosCommand(), new HelpCommand(), new InvseeCommand(),
                new LabelCommand(), new MuteCommand(), new UnmuteCommand(),
                new VanishCommand()
        ));
        getPonderAPI().getCommandService().initialize(commands, "That command wasn't found.");
    }

    public boolean isVersionMismatched() {
        String versionInConfig = this.getConfig().getString("version");
        if (versionInConfig == null) {
            return true;
        }
        return !versionInConfig.equalsIgnoreCase(getVersion());
    }

    public String getVersion() {
        return pluginVersion;
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    // end of helper methods -------------------------------------------------------------------------

}