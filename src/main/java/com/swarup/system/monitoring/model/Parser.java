package com.swarup.system.monitoring.model;

/**
 * Created by eswavin on 6/25/16.
 */
public class Parser {

    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Parser{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
