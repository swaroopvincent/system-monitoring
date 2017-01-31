package com.swarup.system.monitoring.exceptions;

/**
 * Created by eswavin on 6/25/16.
 */
public class CommandNotFoundException extends SystemMonitoringException {

    private String command;

    public CommandNotFoundException(final String command) {
        this.command = command;
    }

    @Override
    public String getFailureCause() {
        return "The command [ "+command+" ] not found.";
    }

    @Override
    public String getSuggestionMessage() {
        return "Check the command separately and try again. The application continues by ommiting this metric.";
    }
}
