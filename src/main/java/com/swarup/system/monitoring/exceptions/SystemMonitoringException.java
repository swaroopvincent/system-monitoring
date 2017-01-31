package com.swarup.system.monitoring.exceptions;

/**
 * Created by eswavin on 6/24/16.
 * The root class of the applications exception hierarchy.
 */
public abstract class SystemMonitoringException extends RuntimeException {

    public abstract String getFailureCause();

    public abstract String getSuggestionMessage();
}
