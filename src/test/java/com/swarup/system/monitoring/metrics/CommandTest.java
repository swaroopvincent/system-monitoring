package com.swarup.system.monitoring.metrics;

import com.swarup.system.monitoring.exceptions.CommandNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eswavin on 6/25/16.
 */
public class CommandTest {

    private Command command;

    @Test
    public void testExecuteGivesResult() {
        command = new Command("hostname");
        assertTrue(command.execute().length() > 0);
    }

    @Test (expected = CommandNotFoundException.class)
    public void testExecuteThrowsCommandNotFoundException() {
        command = new Command("hostnamessss");
        command.execute();
    }


}