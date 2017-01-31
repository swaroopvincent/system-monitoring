package com.swarup.system.monitoring.core;

import com.swarup.system.monitoring.exceptions.OperatingSystemNotSupportedException;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Created by eswavin on 6/26/16.
 */
public class OperatingSystemFinderTest {

    private OperatingSystemFinder operatingSystemFinder;

    @Test (expected = OperatingSystemNotSupportedException.class)
    public void testGetOperatingSystemTypeThrowsOperatingSystemNotSupportedException() {
        System.setProperty("os.name", "test");
        operatingSystemFinder = new OperatingSystemFinder();
        operatingSystemFinder.getOperatingSystemType();
    }

    @Test
    public void testGetOperatingSystemTypeGivesLinux() {
        System.setProperty("os.name", "Linux");
        operatingSystemFinder = new OperatingSystemFinder();
        OperatingSystem operatingSystem = operatingSystemFinder.getOperatingSystemType();
        System.out.println(operatingSystem.getType());
        assertTrue(operatingSystem.getType().equals("Linux"));
    }



}