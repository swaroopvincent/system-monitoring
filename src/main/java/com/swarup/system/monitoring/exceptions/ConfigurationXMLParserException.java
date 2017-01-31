package com.swarup.system.monitoring.exceptions;

/**
 * Created by eswavin on 6/25/16.
 */
public class ConfigurationXMLParserException  extends SystemMonitoringException {

    private String fileName;

    public ConfigurationXMLParserException(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFailureCause() {
        return "Failed while parsing the configuration file " + fileName;
    }

    @Override
    public String getSuggestionMessage() {
        return "Check the configuration file is proper!";
    }
}
