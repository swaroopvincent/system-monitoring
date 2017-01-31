package com.swarup.system.monitoring.model;

/**
 * Created by eswavin on 6/25/16.
 */
public class Metric {

    private String name;
    private String command;
    private Parser parser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "Metric{" +
                "name='" + name + '\'' +
                ", command='" + command + '\'' +
                ", parser=" + parser +
                '}';
    }
}
