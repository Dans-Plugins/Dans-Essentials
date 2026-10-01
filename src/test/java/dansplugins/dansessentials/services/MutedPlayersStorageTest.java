package dansplugins.dansessentials.services;

import dansplugins.dansessentials.data.EphemeralData;
import dansplugins.dansessentials.utils.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class MutedPlayersStorageTest {
    @TempDir
    File dataFolder;

    private Logger logger;

    @BeforeEach
    void setUp() {
        logger = mock(Logger.class);
    }

    @Test
    void mutesSurviveASaveAndALoadIntoFreshData() {
        EphemeralData before = new EphemeralData();
        before.getMutedPlayers().addAll(Arrays.asList("Steve", "Alex"));
        new MutedPlayersStorage(dataFolder, before, logger).save();

        EphemeralData after = new EphemeralData();
        new MutedPlayersStorage(dataFolder, after, logger).load();

        assertEquals(Arrays.asList("Steve", "Alex"), after.getMutedPlayers());
        verify(logger, never()).warn(anyString());
    }

    @Test
    void load_withNoFileLeavesNobodyMuted() {
        EphemeralData data = new EphemeralData();
        new MutedPlayersStorage(dataFolder, data, logger).load();

        assertTrue(data.getMutedPlayers().isEmpty());
        verify(logger, never()).warn(anyString());
    }

    @Test
    void load_skipsBlankLinesAndDuplicatesAndTrimsNames() throws IOException {
        Files.write(new File(dataFolder, MutedPlayersStorage.FILE_NAME).toPath(),
                Arrays.asList("Steve", "", "  Alex  ", "Steve"), StandardCharsets.UTF_8);
        EphemeralData data = new EphemeralData();
        new MutedPlayersStorage(dataFolder, data, logger).load();

        assertEquals(Arrays.asList("Steve", "Alex"), data.getMutedPlayers());
    }

    @Test
    void save_afterAnUnmuteDropsThePlayerFromTheFile() throws IOException {
        EphemeralData data = new EphemeralData();
        data.setMutedPlayers(new ArrayList<>(Arrays.asList("Steve", "Alex")));
        MutedPlayersStorage storage = new MutedPlayersStorage(dataFolder, data, logger);
        storage.save();

        data.getMutedPlayers().remove("Steve");
        storage.save();

        assertEquals(Arrays.asList("Alex"),
                Files.readAllLines(new File(dataFolder, MutedPlayersStorage.FILE_NAME).toPath(), StandardCharsets.UTF_8));
    }

    @Test
    void save_createsAMissingDataFolder() {
        File missing = new File(dataFolder, "DansEssentials");
        EphemeralData data = new EphemeralData();
        data.getMutedPlayers().add("Steve");
        new MutedPlayersStorage(missing, data, logger).save();

        assertTrue(new File(missing, MutedPlayersStorage.FILE_NAME).isFile());
        verify(logger, never()).warn(anyString());
    }

    @Test
    void load_warnsAndLeavesNobodyMutedWhenTheFileCannotBeRead() {
        // A directory in place of the file makes the read fail.
        assertTrue(new File(dataFolder, MutedPlayersStorage.FILE_NAME).mkdir());
        EphemeralData data = new EphemeralData();
        new MutedPlayersStorage(dataFolder, data, logger).load();

        assertTrue(data.getMutedPlayers().isEmpty());
        verify(logger).warn(anyString());
    }

    @Test
    void save_warnsWhenTheFileCannotBeWritten() {
        assertTrue(new File(dataFolder, MutedPlayersStorage.FILE_NAME).mkdir());
        EphemeralData data = new EphemeralData();
        data.getMutedPlayers().add("Steve");
        new MutedPlayersStorage(dataFolder, data, logger).save();

        verify(logger).warn(anyString());
    }
}
