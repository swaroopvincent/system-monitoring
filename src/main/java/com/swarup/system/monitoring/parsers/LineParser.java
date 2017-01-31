package com.swarup.system.monitoring.parsers;

import com.swarup.system.monitoring.exceptions.IllegalArgumentForParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eswavin on 6/25/16.
 */
public class LineParser extends Parser {

    private static Logger logger = LoggerFactory.getLogger(LineParser.class);

    private int rowPosition;
    private int columnPosition;
    private String result;

    public LineParser(final String result, final int rowPosition, final int columnPosition) {
        this.result = result;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    @Override
    public String getValue() {
        String[] splittedRowResult = null;
        String[] splittedColumnResult = null;
        String rowToBeParser = null;
        String result = null;
        if(rowPosition < 0 || columnPosition < 0) {
            logger.error("Failed while parsing the result");
            throw new IllegalArgumentForParsingException(rowPosition, columnPosition, result);
        }
        if(rowPosition > 0) {
            splittedRowResult = this.result.split("\\n+");
            rowToBeParser = splittedRowResult[rowPosition];
        } else if (rowPosition == 0) {
            rowToBeParser = this.result;
        }
        if(columnPosition > 0) {
            splittedColumnResult = rowToBeParser.split("\\s+");
            result = splittedColumnResult[columnPosition];
        } else if (columnPosition == 0) {
            result = rowToBeParser;
        }
        return result.trim();
    }

}
