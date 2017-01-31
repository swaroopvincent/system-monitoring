package com.swarup.system.monitoring.parsers;

import com.swarup.system.monitoring.exceptions.IllegalArgumentForParsingException;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by eswavin on 6/25/16.
 */
public class LineParserTest {

    private LineParser lineParser;

    @Test
    public void testgetValueGiveResult() {
        String input = "Swaroop\nVincent";
        String expectedResult = "Vincent";
        lineParser = new LineParser(input, 1, 0);
        String value = lineParser.getValue();
        assertTrue(expectedResult.equals(value));
    }

    @Test(expected = IllegalArgumentForParsingException.class)
    public void testgetValueWithIllegalArgumentThrowsException() {
        lineParser =new LineParser("blah", -1, -1);
        lineParser.getValue();
    }
    
//    @Test
//    public void testWndows() {
//    	Command command = new Command("wmic os get freephysicalmemory");
////    	Command command = new Command("hostname");
//    	String result = command.execute();
//    	lineParser = new LineParser(result, 1, 0);
//    	
//    	System.out.println(lineParser.getValue());
//    }
}