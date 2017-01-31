package com.swarup.system.monitoring.exceptions;

import static com.swarup.system.monitoring.util.Constants.EXCPETION_FATAL_MESSAGE;

/**
 * Created by eswavin on 6/24/16.
 * Exception thrown if the application identifies an operating system that is not supported.
 * Fatal exception and application stop.
 */
public class OperatingSystemNotSupportedException extends SystemMonitoringException {

    private String identifiedType;

    public OperatingSystemNotSupportedException(final String identifiedType) {
        this.identifiedType = identifiedType;
    }

    @Override
    public String getFailureCause() {
        return "The operating system " + identifiedType + " not supported.";
    }

    @Override
    public String getSuggestionMessage() {
        return EXCPETION_FATAL_MESSAGE;
    }
}
