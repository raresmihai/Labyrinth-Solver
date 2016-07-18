package com.lab3.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public class LabyrinthListImpl implements LabyrinthModel {
    List<Node> labyrinth = new ArrayList<>();
    List<Node> copy;
    List<Node> pathCells = new ArrayList<>();
    Node startCell;
    Node finishCell;

    int rowCount;
    int columnCount;

    public void setStartCell(Node startCell) {
        this.startCell = startCell;
    }

    public void setFinishCell(Node finishCell) {
        this.finishCell = finishCell;
    }

    boolean isPathCell(int x, int y) {
        for (Node node : pathCells) {
            if (node.getRow() == x && node.getColumn() == y) {
                return true;
            }
        }
        return false;
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
        for (Node node : labyrinth) {
            if (node.getRow() == x && node.getColumn() == y) return false;
        }
        return true;
    }

    @Override
    public boolean isWallAt(int x, int y) {
        return !isFreeAt(x, y);
    }

    @Override
    public Node getStartCell() {
        return startCell;
    }

    @Override
    public Node getFinishCell() {
        return finishCell;
    }

    @Override
    public int[][] getLabyrinthAsMatrix() {
        int[][] matrix = new int[rowCount][columnCount];
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                if (isWallAt(i, j)) {
                    matrix[i][j] = 1;
                } else {
                    if (startCell.getRow() == i && startCell.getColumn() == j) {
                        matrix[i][j] = -1;
                    } else {
                        if (finishCell.getRow() == i && finishCell.getColumn() == j) {
                            matrix[i][j] = 2;
                        } else {
                            if (isPathCell(i, j)) {
                                matrix[i][j] = 3;
                            } else {
                                matrix[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
        return matrix;
    }

    @Override
    public void reinitialize() {
        if (copy == null) {
            copy = new ArrayList<>();
            for (Node node : labyrinth) {
                Node aux = new Node(node.getRow(), node.getColumn(), 0, null);
                copy.add(aux);
            }
        }
        labyrinth.clear();
        for (Node node : copy) {
            Node aux = new Node(node.getRow(), node.getColumn(), 0, null);
            labyrinth.add(aux);
        }
        pathCells = new ArrayList<>();
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    @Override
    public void setValue(int x, int y, int value) {
        if (value == 1) {
            Node node = new Node(x, y, 0, null);
            labyrinth.add(node);
        }
        if (value == 2) {
            finishCell = new Node(x, y, 0, null);
        }
        if (value == -1) {
            startCell = new Node(x, y, 0, null);
        }
        if(value == 3)  {
            pathCells.add(new Node(x,y,3,null));
        }
    }

    @Override
    public void initialize(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

}
