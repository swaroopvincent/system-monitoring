package com.swarup.system.monitoring.core;

import com.swarup.system.monitoring.exceptions.MachineTypeCannotFindException;
import com.swarup.system.monitoring.exceptions.OperatingSystemNotSupportedException;

import static com.swarup.system.monitoring.util.Constants.LINUX_MACHINE_TYPE_AS_PROPERTY_VALUE;
import static com.swarup.system.monitoring.util.Constants.OS_NAME_AS_KEY;
import static com.swarup.system.monitoring.util.Constants.WINDOWS_MACHINE_TYPE_AS_PROPERTY_VALUE;

/**
 * Created by eswavin on 6/26/16.
 * This class helps to identify the operating system in which the application is running.
 */
public class OperatingSystemFinder {

    public OperatingSystem getOperatingSystemType() {
        String osTypeValue = null;
        osTypeValue = System.getProperty(OS_NAME_AS_KEY);
        if (osTypeValue == null) {
            throw new MachineTypeCannotFindException();
        } else {
            if(osTypeValue.startsWith(LINUX_MACHINE_TYPE_AS_PROPERTY_VALUE)) {
                return OperatingSystem.LINUX;
            } else if (osTypeValue.startsWith(WINDOWS_MACHINE_TYPE_AS_PROPERTY_VALUE)) {
                return OperatingSystem.WINDOWS;
            } else {
                throw new OperatingSystemNotSupportedException(osTypeValue);
            }
        }
    }
}
