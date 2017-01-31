package com.swarup.system.monitoring.exceptions;

/**
 * Created by eswavin on 6/25/16.
 */
public class IllegalArgumentForParsingException extends SystemMonitoringException {

    private int row;
    private int column;
    private String result;

    public IllegalArgumentForParsingException(final int row, final int column, final String result) {
        this.row = row;
        this.column = column;
        this.result = result;
    }

    @Override
    public String getFailureCause() {
        return "The row ["+row+"] and column ["+column+"] combination fails to give parsing result for "+result;
    }

    @Override
    public String getSuggestionMessage() {
        return "Check the command output and slection values for the parsing.";
    }
}
