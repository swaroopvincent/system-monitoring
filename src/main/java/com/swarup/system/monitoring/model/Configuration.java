package com.swarup.system.monitoring.model;

import java.util.List;

/**
 * Created by eswavin on 6/25/16.
 */
public class Configuration {

    private Machine machine;
    private List<Metric> metrics;

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "machine=" + machine +
                ", metrics=" + metrics +
                '}';
    }
}
