package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Guards the permission node of every registered sub-command against the two
 * places it must also appear: the permissions block of plugin.yml, which gives
 * the node its default, and the permissions table of USER_GUIDE.md.
 * @author Daniel McCoy Stephenson
 */
public class SubCommandsTest {
    private List<AbstractPluginCommand> subCommands;

    @BeforeEach
    public void setUp() {
        subCommands = SubCommands.create(new EphemeralData());
    }

    @Test
    public void testCreate_registersThirteenSubCommands() {
        assertEquals(13, subCommands.size());
    }

    @Test
    public void testCreate_eachCallReturnsNewInstances() {
        List<AbstractPluginCommand> other = SubCommands.create(new EphemeralData());

        for (int i = 0; i < subCommands.size(); i++) {
            assertFalse(subCommands.get(i) == other.get(i),
                    "sub-command " + i + " is shared between calls");
        }
    }

    @Test
    public void testCreate_noTwoSubCommandsShareAName() {
        Set<String> seen = new HashSet<>();

        for (AbstractPluginCommand command : subCommands) {
            for (String name : command.getNames()) {
                assertTrue(seen.add(name), "'" + name + "' is registered by more than one sub-command");
            }
        }
    }

    @Test
    public void testCreate_everyPermissionIsDeclaredInPluginYml() throws Exception {
        ConfigurationSection declared = loadPluginYml().getConfigurationSection("permissions");
        assertNotNull(declared, "plugin.yml has no permissions block");

        for (AbstractPluginCommand command : subCommands) {
            for (String permission : command.getPermissions()) {
                assertTrue(declared.contains(permission),
                        "'" + permission + "' (/de " + command.getNames().get(0) + ") is not declared in plugin.yml");
            }
        }
    }

    @Test
    public void testCreate_everyPermissionIsInTheUserGuidePermissionsTable() throws Exception {
        String userGuide = new String(Files.readAllBytes(Paths.get("USER_GUIDE.md")), StandardCharsets.UTF_8);

        for (AbstractPluginCommand command : subCommands) {
            for (String permission : command.getPermissions()) {
                assertTrue(userGuide.contains("| `" + permission + "`"),
                        "'" + permission + "' (/de " + command.getNames().get(0) + ") has no row in the USER_GUIDE.md permissions table");
            }
        }
    }

    private YamlConfiguration loadPluginYml() throws Exception {
        try (InputStream in = SubCommandsTest.class.getResourceAsStream("/plugin.yml")) {
            assertNotNull(in, "plugin.yml is not on the classpath");
            try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.load(reader);
                return yaml;
            }
        }
    }
}
