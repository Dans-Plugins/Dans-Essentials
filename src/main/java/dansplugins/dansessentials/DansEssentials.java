package dansplugins.dansessentials;

import dansplugins.dansessentials.Commands.*;
import dansplugins.dansessentials.bStats.Metrics;
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

    private String version = "v2.0-beta-1";

    // public methods -------------------------------------------------------------------------

    public static DansEssentials getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ponderAPI_integrator = new PonderAPI_Integrator(this);
        toolbox = getPonderAPI().getToolbox();
        initializeConfigService();
        initializeConfigFile();
        registerEventHandlers();
        initializeCommandService();
        int pluginId = 9527;
        Metrics metrics = new Metrics(this, pluginId);
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
     * Method to get initialize the config service with config options and values.
     *
     */
    private void initializeConfigService() {
        HashMap<String, Object> configOptions = new HashMap<>();
        configOptions.put("debugMode", false);
        getPonderAPI().getConfigService().initialize(configOptions);
    }

    /**
     * Method to initialize the actual config.yml file.
     *
     */
    private void initializeConfigFile() {
        if (!(new File("./plugins/DansEssentials/config.yml").exists())) {
            getPonderAPI().getConfigService().saveMissingConfigDefaultsIfNotPresent();
        }
        else {
            // pre load compatibility checks
            if (isVersionMismatched()) {
                getPonderAPI().getConfigService().saveMissingConfigDefaultsIfNotPresent();
            }
            reloadConfig();
        }
    }

    /**
     * Method to create and register the event handlers.
     *
     */
    private void registerEventHandlers() {
        ArrayList<Listener> listeners = new ArrayList<>();
        listeners.add(new EventHandlers());
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
                new LabelCommand(), new MuteCommand(), new UnmuteCommand()
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
