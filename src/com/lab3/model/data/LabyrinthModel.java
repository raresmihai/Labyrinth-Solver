package com.lab3.model.data;

/**
 * Created by rares on 08.03.2016.
 */
public interface LabyrinthModel {
    int getRowCount();

    int getColumnCount();

    boolean isFreeAt(int x, int y);

    boolean isWallAt(int x, int y);

    Node getStartCell();

    Node getFinishCell();

    int[][] getLabyrinthAsMatrix();

    void setRowCount(int rowCount);

    void setColumnCount(int columnCount);

    void setValue(int x, int y, int value);

    void initialize(int rowCount, int columnCount);

    void reinitialize();
}
