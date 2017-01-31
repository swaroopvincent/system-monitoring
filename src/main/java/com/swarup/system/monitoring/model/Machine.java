package com.swarup.system.monitoring.model;

/**
 * Created by eswavin on 6/25/16.
 */
public class Machine {

    private String command;
    private Parser parser;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "command='" + command + '\'' +
                ", parser=" + parser +
                '}';
    }
}
