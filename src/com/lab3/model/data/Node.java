package com.lab3.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public class Node {
    int row;
    int column;
    int distance;
    boolean reachedFinish = false;
    boolean isInteractive = false;
    boolean isStartCell = false;
    boolean isFinishCell = false;


    List<Node> adjacencyList = new ArrayList<>();
    Node parent = null;

    public Node() {

    }

    public boolean isStartCell() {
        return isStartCell;
    }

    public void setStartCell(boolean startCell) {
        isStartCell = startCell;
    }

    public boolean isFinishCell() {
        return isFinishCell;
    }

    public void setFinishCell(boolean finishCell) {
        isFinishCell = finishCell;
    }

    public boolean isInteractive() {
        return isInteractive;
    }

    public void setInteractive(boolean interactive) {
        isInteractive = interactive;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasReachedFinish() {
        return reachedFinish;
    }

    public void setReachedFinish(boolean reachedFinish) {
        this.reachedFinish = reachedFinish;
    }

    public void addNode(Node node) {
        adjacencyList.add(node);
    }

    public boolean pathDoesntContain(Node newNode) {
        Node aux = new Node(this.getRow(), this.getColumn(), this.getDistance(), this.parent);
        while (aux != null) {
            if (aux.getRow() == newNode.getRow() && aux.getColumn() == newNode.getColumn()) return false;
            aux = aux.parent;
        }
        return true;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Node> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(List<Node> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public Node(int row, int column, int distance, Node parent) {
        this.row = row;
        this.column = column;
        this.distance = distance;
        this.parent = parent;
    }

    public Node(Node node) {
        this(node.getRow(), node.getColumn(), node.getDistance(), node.getParent());
    }

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

    public String toString() {
        String s = row + " " + column;
        return s;
    }
}
