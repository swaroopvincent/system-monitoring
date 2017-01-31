package com.swarup.system.monitoring.main;

import static com.swarup.system.monitoring.util.Constants.CONFIG_FILE_NAME_FOR_WINDOWS;
import static com.swarup.system.monitoring.util.Constants.CONFIG_FILE_NAME_FOR_LINUX;

import com.swarup.system.monitoring.core.ConfigurationParser;
import com.swarup.system.monitoring.core.MetricsPrinter;
import com.swarup.system.monitoring.core.OperatingSystemFinder;
import com.swarup.system.monitoring.exceptions.SystemMonitoringException;
import com.swarup.system.monitoring.model.Configuration;
import com.swarup.system.monitoring.core.OperatingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eswavin on 6/24/16.
 * Launch the system monitor application.
 */
public class SystemMonitor {

    private static Logger logger = LoggerFactory.getLogger(SystemMonitor.class);

    public static void main(String[] args) {
        try {
            OperatingSystemFinder operatingSystemFinder = new OperatingSystemFinder();
            OperatingSystem operatingSystem = operatingSystemFinder.getOperatingSystemType();
            logger.trace("Operating system detected as {}", operatingSystem.getType());
            String configFileName = getConfigFileName(operatingSystem);
            logger.trace("Configuration file in use is {}", configFileName);
            Configuration configuration = new ConfigurationParser(configFileName).parseAndBuildModel();
            MetricsPrinter metricsPrinter = new MetricsPrinter(configuration);
            metricsPrinter.start();
        } catch (final SystemMonitoringException systemMonitoringException) {
            System.err.println(systemMonitoringException.getFailureCause());
            System.err.println(systemMonitoringException.getSuggestionMessage());
        }
    }

    private static String getConfigFileName(final OperatingSystem operatingSystem) {
        if(operatingSystem.equals(OperatingSystem.LINUX)) {
            return CONFIG_FILE_NAME_FOR_LINUX;
        } else {
            return CONFIG_FILE_NAME_FOR_WINDOWS;
        }
    }

}
