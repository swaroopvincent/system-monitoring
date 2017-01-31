package com.swarup.system.monitoring.exceptions;

import com.swarup.system.monitoring.util.Constants;

/**
 * Created by eswavin on 6/24/16.
 * Exception thrown when failed to identify the machine type for the system monitoring.
 * This is a fatal error and application exit.
 */
public class MachineTypeCannotFindException extends SystemMonitoringException {


    @Override
    public String getFailureCause() {
        return "Failed to detect the operating system type.";
    }

    @Override
    public String getSuggestionMessage() {
        return Constants.EXCPETION_FATAL_MESSAGE;
    }
}
