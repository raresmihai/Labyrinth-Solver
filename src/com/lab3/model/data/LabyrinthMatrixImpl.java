package com.lab3.model.data;

import java.util.ArrayList;

/**
 * Created by rares on 08.03.2016.
 */
public class LabyrinthMatrixImpl implements LabyrinthModel {
    int matrix[][];
    int matrixCopy[][];
    int rowCount;
    int columnCount;

    @Override
    public void initialize(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        matrix = new int[rowCount][columnCount];
    }

    @Override
    public void reinitialize() {
        if (matrixCopy == null) {
            matrixCopy = new int[rowCount][columnCount];
            for (int i = 0; i < rowCount; ++i) {
                for (int j = 0; j < columnCount; ++j) {
                    matrixCopy[i][j] = matrix[i][j];
                }
            }
        }
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                matrix[i][j] = matrixCopy[i][j];
            }
        }
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int[][] getLabyrinthAsMatrix() {
        return matrix;
    }

    @Override
    public void setValue(int x, int y, int value) {
        matrix[x][y] = value;
    }


    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public boolean isFreeAt(int x, int y) {
        if (x < 0 || x >= rowCount || y < 0 || y >= columnCount) return false;
        return (matrix[x][y] == 0 || matrix[x][y] == 2);
    }

    @Override
    public boolean isWallAt(int x, int y) {
        return matrix[x][y] == 1;
    }

    @Override
    public Node getStartCell() {
        Node node = new Node();
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                if (matrix[i][j] == -1) {
                    node.setRow(i);
                    node.setColumn(j);
                    node.setDistance(0);
                    break;
                }
            }
        }
        node.setParent(null);
        return node;
    }

    @Override
    public Node getFinishCell() {
        Node node = new Node();
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                if (matrix[i][j] == 2) {
                    node.setRow(i);
                    node.setColumn(j);
                    break;
                }
            }
        }
        return node;
    }
}
