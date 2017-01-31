package com.swarup.system.monitoring.core;

import com.swarup.system.monitoring.metrics.Command;
import com.swarup.system.monitoring.model.Configuration;
import com.swarup.system.monitoring.model.Machine;
import com.swarup.system.monitoring.model.Metric;
import com.swarup.system.monitoring.parsers.LineParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by eswavin on 6/25/16.
 * This class prints the system monitoring metrics to console.
 */
public class MetricsPrinter {

    private Configuration configuration;

    public MetricsPrinter(final Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * start the metrics printing job.
     */
    public void start() {
        System.out.println("********************************************************************************");
        System.out.println(getMachineNameForHeader());
        System.out.println("********************************************************************************");
        startMetricCollection();
    }

    private void startMetricCollection() {
        Collector collector = new Collector();
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(collector, 0, 30*1000);
    }

    class Collector extends TimerTask {

        @Override
        public void run() {
            System.out.print(getCurrentTimeStamp() + "\t");
            List<Metric> metrics = configuration.getMetrics();
            for(Metric metric : metrics) {
                final Command command = new Command(metric.getCommand());
                final String result = command.execute();
                String value = new LineParser(result, metric.getParser().getRow(), metric.getParser().getColumn()).getValue();
                System.out.print(metric.getName() + " : " + value+"\t");
            }
            System.out.println();
        }

        public String getCurrentTimeStamp() {
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            return strDate;
        }
    }

    private String getMachineNameForHeader() {
        Machine machine = configuration.getMachine();
        final Command command = new Command(machine.getCommand());
        final String result = command.execute();
        return new LineParser(result, machine.getParser().getRow(), machine.getParser().getColumn()).getValue();
    }

}
