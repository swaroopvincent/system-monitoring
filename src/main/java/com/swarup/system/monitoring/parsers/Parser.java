package com.swarup.system.monitoring.parsers;

/**
 * Created by eswavin on 6/25/16.
 * The parent class for all parser that can be built in future.
 */
public abstract class Parser {

    /**
     * Gives the positional value from the executed result set.
     * @return result of the execution.
     */
    public abstract String getValue();
}
