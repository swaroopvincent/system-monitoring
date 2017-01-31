package com.swarup.system.monitoring.metrics;

import com.swarup.system.monitoring.exceptions.CommandNotFoundException;
import com.swarup.system.monitoring.exceptions.CommandTimedOutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by eswavin on 6/25/16.
 * The class that runs the command.
 */
public class Command {

    private static Logger logger = LoggerFactory.getLogger(Command.class);

    private String commandToExecute;
    private char[] buffer = new char[65536];
    private static final long MAXIMUM_ALLOWED_TIME_FOR_COMMAND = 20*1000;

    public Command(final String commandToExecute) {
        this.commandToExecute = commandToExecute;
    }

    public String execute() {
        String result = new String();
        String error = new String();
        boolean isCommandExecutionFinished = false;
        long commandStartTime = 0;
        try {
            Process process = Runtime.getRuntime().exec(commandToExecute);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            commandStartTime = System.currentTimeMillis();
            while (!isCommandExecutionFinished && MAXIMUM_ALLOWED_TIME_FOR_COMMAND + commandStartTime > System.currentTimeMillis()) {
                if(inputReader.ready()) {
                    int length = inputReader.read(buffer, 0, buffer.length);
                    if(length > 0) {
                        result = result + new String(buffer, 0, length);
                    }
                } else {
                    try {
                        process.exitValue();
                        isCommandExecutionFinished = true;
                    } catch (IllegalThreadStateException illegalThreadStateException){}
                }
            }
            if(!isCommandExecutionFinished) {
                logger.warn("Command failed to run in allowed time limit, command is {}", commandToExecute);
                throw new CommandTimedOutException(commandToExecute);
            }
            int length;
            while ((length = inputReader.read(buffer, 0, buffer.length)) != -1) {
                if (length > 0) {
                    result = result + new String(buffer, 0, length);
                }
            }
            while ((length = errorReader.read(buffer, 0, buffer.length)) != -1) {
                if (length > 0) {
                    error = error + new String(buffer, 0, length);
                }
            }
            if(error.length() > 0) {
                logger.warn("command processed with error, command is {}", commandToExecute);
                logger.warn("error message is {}", error);
            }
            inputReader.close();
            errorReader.close();
        } catch (IOException e) {
            logger.error("Running command not found and command is {}", commandToExecute);
            throw new CommandNotFoundException(commandToExecute);
        }
        return result;
    }

}
