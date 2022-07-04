package dansplugins.dansessentials;

import dansplugins.dansessentials.bStats.Metrics;
import dansplugins.dansessentials.commands.*;
import dansplugins.dansessentials.data.EphemeralData;
import dansplugins.dansessentials.listeners.*;
import dansplugins.dansessentials.services.ConfigService;
import dansplugins.dansessentials.utils.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;
import preponderous.ponder.minecraft.bukkit.abs.PonderBukkitPlugin;
import preponderous.ponder.minecraft.bukkit.services.CommandService;
import preponderous.ponder.minecraft.bukkit.tools.EventHandlerRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class DansEssentials extends PonderBukkitPlugin implements Listener {
    private final String pluginVersion = "v" + getDescription().getVersion();

    private final CommandService commandService = new CommandService(getPonder());
    private final ConfigService configService = new ConfigService(this);
    private final EphemeralData ephemeralData = new EphemeralData();
    private final Logger logger = new Logger(this);

    /**
     * This runs when the server starts.
     */
    @Override
    public void onEnable() {
        initializeConfig();
        registerEventHandlers();
        initializeCommandService();
        handlebStatsIntegration();
    }

    private void handlebStatsIntegration() {
        int pluginId = 9527;
        new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {

    }

    /**
     * This method handles commands sent to the minecraft server and interprets them if the label matches one of the core commands.
     * @param sender The sender of the command.
     * @param cmd The command that was sent. This is unused.
     * @param label The core command that has been invoked.
     * @param args Arguments of the core command. Often sub-commands.
     * @return A boolean indicating whether the execution of the command was successful.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            DefaultCommand defaultCommand = new DefaultCommand(this);
            return defaultCommand.execute(sender);
        }

        return commandService.interpretAndExecuteCommand(sender, label, args);
    }

    private void initializeConfig() {
        if (configFileExists()) {
            performCompatibilityChecks();
        }
        else {
            configService.saveMissingConfigDefaultsIfNotPresent();
        }
    }

    private boolean configFileExists() {
        return new File("./plugins/" + getName() + "/config.yml").exists();
    }

    private void performCompatibilityChecks() {
        if (isVersionMismatched()) {
            configService.saveMissingConfigDefaultsIfNotPresent();
        }
        reloadConfig();
    }

    /**
     * Method to create and register the event handlers.
     *
     */
    private void registerEventHandlers() {
        ArrayList<Listener> listeners = new ArrayList<>();
        listeners.add(new ChatListener(ephemeralData));
        listeners.add(new InteractionListener(logger));
        listeners.add(new JoinListener(ephemeralData, this));
        listeners.add(new SignListener());
        listeners.add(new TeleportListener(ephemeralData));
        EventHandlerRegistry eventHandlerRegistry = new EventHandlerRegistry();
        eventHandlerRegistry.registerEventHandlers(listeners, this);
    }

    /**
     * Method to initialize the command service with commands.
     *
     */
    private void initializeCommandService() {
        ArrayList<AbstractPluginCommand> commands = new ArrayList<>(Arrays.asList(
                new BackCommand(ephemeralData), new BroadcastCommand(), new ClearInvCommand(),
                new FlyCommand(), new FlySpeedCommand(), new GamemodeCommand(),
                new GetPosCommand(), new HelpCommand(), new InvseeCommand(),
                new LabelCommand(), new MuteCommand(ephemeralData), new UnmuteCommand(ephemeralData)
        ));
        commandService.initialize(commands, "That command wasn't found.");
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

    /**
     * Checks if debug is enabled.
     * @return Whether debug is enabled.
     */
    public boolean isDebugEnabled() {
        return configService.getBoolean("debugMode");
    }
}