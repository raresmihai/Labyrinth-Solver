package com.lab3.model.solvers;

import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.data.Node;
import com.lab3.model.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public class AutomatedSolver implements LabyrinthSolver {
    List<Observer> observers = new ArrayList<>();
    int[][] labyrinth;
    List<Node> solutionQueue = new ArrayList<>();
    LabyrinthModel model;

    int currentRow;
    int currentColumn;
    int rowCount;
    int columnCount;

    int currentIndex = 0;
    int lastIndex = 0;

    public AutomatedSolver(LabyrinthModel model) {
        this.model = model;
        this.currentRow = model.getStartCell().getRow();
        this.currentColumn = model.getStartCell().getColumn();
        this.rowCount = model.getRowCount();
        this.columnCount = model.getColumnCount();
    }

    @Override
    public void setLabyrinth(int[][] labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public int[][] getLabyrinth() {
        return labyrinth;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers(Node node) {
        for (Observer observer : observers) {
            observer.processCell(null);
        }
    }

    @Override
    public void processSolutionByObservers(Node n) {
        for (Observer observer : observers) {
            observer.processSolution(n);
        }
    }


    List<Node> addNeighbours(Node node) {
        List<Node> list = new ArrayList<>();
        int currentRow = node.getRow();
        int currentColumn = node.getColumn();
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int nextRow = currentRow + dx[i];
            int nextColumn = currentColumn + dy[i];
            Node newNode = new Node(nextRow, nextColumn, node.getDistance() + 1, node);
            if (model.isFreeAt(nextRow, nextColumn) && node.pathDoesntContain(newNode)) {
                list.add(newNode);
                if (labyrinth[nextRow][nextColumn] == 2) {
                    processSolutionByObservers(newNode);
                    node.setReachedFinish(true);
                }
            }
        }
        return list;
    }

    void addNodesToQueue(List<Node> list) {
        for (Node node : list) {
            solutionQueue.add(node);
            lastIndex++;
        }
    }

    @Override
    public void solve() {
        Node root = new Node(model.getStartCell());
        solutionQueue.add(root);
        while (currentIndex <= lastIndex) {
            Node currentNode = solutionQueue.get(currentIndex++);
            if (!currentNode.hasReachedFinish()) {
                List<Node> adjacencyList = addNeighbours(currentNode);
                if (adjacencyList.size() > 0) {
                    currentNode.setAdjacencyList(adjacencyList);
                    addNodesToQueue(adjacencyList);
                }
            }
        }
    }
}
