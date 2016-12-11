package de.edgecube.dogwastebagdispenserscologne;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DispenserLocationParserTest {
    @Test
    public void parse_withSampleInput_ReturnsListOfDispensers() throws Exception {
        DispenserLocationParser parser = new DispenserLocationParser();
        assertEquals(4, 2 + 2);
    }
}