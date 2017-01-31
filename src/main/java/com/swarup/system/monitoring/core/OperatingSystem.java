package com.swarup.system.monitoring.core;

/**
 * Created by eswavin on 6/24/16.
 * The enum to distinguish the operating system.
 */
public enum OperatingSystem {

    LINUX("Linux"),
    WINDOWS("WIndows");

    private String type;

    OperatingSystem(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
