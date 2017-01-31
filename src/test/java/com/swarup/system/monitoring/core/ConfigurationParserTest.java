package com.swarup.system.monitoring.core;

import com.swarup.system.monitoring.exceptions.ConfigurationXMLParserException;
import com.swarup.system.monitoring.model.Configuration;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Created by eswavin on 6/26/16.
 */
public class ConfigurationParserTest {

    private ConfigurationParser configurationParser;

    @Test
    public void testParseAndBuildModelForLinux() {
        configurationParser = new ConfigurationParser("linux.xml");
        Configuration configuration = configurationParser.parseAndBuildModel();
        assertTrue(configuration.getMachine().getCommand().equals("hostname"));
        assertTrue(configuration.getMetrics().size() == 2);
    }

    @Test (expected = ConfigurationXMLParserException.class)
    public void testParseAndBuildModelForBlahThrowsConfigurationXMLParserException() {
        configurationParser = new ConfigurationParser("blah.xml");
        Configuration configuration = configurationParser.parseAndBuildModel();
    }

}