package com.swarup.system.monitoring.exceptions;

/**
 * Created by eswavin on 6/25/16.
 */
public class CommandTimedOutException extends SystemMonitoringException {

    private String command;

    public CommandTimedOutException(final String command) {
        this.command = command;
    }

    @Override
    public String getFailureCause() {
        return "command [ "+ command+" ] timed out.";
    }

    @Override
    public String getSuggestionMessage() {
        return "The application currently doesn't support metric collection which takes more than 20 seconds to execute.";
    }
}
