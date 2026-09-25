package dansplugins.dansessentials.utils;

import dansplugins.dansessentials.DansEssentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class LoggerTest {
    private DansEssentials dansEssentials;
    private java.util.logging.Logger pluginLogger;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        dansEssentials = mock(DansEssentials.class);
        pluginLogger = mock(java.util.logging.Logger.class);
        when(dansEssentials.getLogger()).thenReturn(pluginLogger);
        logger = new Logger(dansEssentials);
    }

    @Test
    public void testLog_debugDisabled_logsNothing() {
        when(dansEssentials.isDebugEnabled()).thenReturn(false);

        logger.log("message");

        verify(pluginLogger, never()).log(Level.INFO, "[Dan's Essentials] message");
    }

    @Test
    public void testWarn_debugDisabled_stillLogsWarning() {
        when(dansEssentials.isDebugEnabled()).thenReturn(false);

        logger.warn("message");

        verify(pluginLogger).log(Level.WARNING, "[Dan's Essentials] message");
    }
}
