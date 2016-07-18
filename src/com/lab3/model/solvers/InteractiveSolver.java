package com.lab3.model.solvers;

import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.data.Node;
import com.lab3.model.observers.Observer;
import com.lab3.view.LabyrinthView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rares on 08.03.2016.
 */
public class InteractiveSolver implements LabyrinthSolver {
    List<Observer> observers = new ArrayList<>();
    int[][] labyrinth;
    boolean solutionFound = false;
    LabyrinthModel model;

    int currentRow;
    int currentColumn;
    int rowCount;
    int columnCount;

    public InteractiveSolver(LabyrinthModel model) {
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
            observer.processCell(node);
        }
    }

    @Override
    public void processSolutionByObservers(Node n) {
        for (Observer observer : observers) {
            observer.processSolution(n);
        }
    }

    boolean validCommand(String command) {
        switch (command) {
            case "D":
                this.currentRow++;
                return true;
            case "U":
                this.currentRow--;
                return true;
            case "L":
                this.currentColumn--;
                return true;
            case "R":
                this.currentColumn++;
                return true;
            default:
                System.out.println("Invalid Command! Use U,D,R,L to move around.");
                return false;
        }
    }

    void updateLabyrinth() {
        if (currentRow < 0 || currentRow >= rowCount || currentColumn < 0 || currentColumn >= columnCount) {
            System.out.println("Out of bounds!");
            Node node = new Node(currentRow, currentColumn, -1, null);
            node.setInteractive(true);
            updateObservers(node);
            this.solutionFound = true;
        } else {
            if (labyrinth[currentRow][currentColumn] == 2) {
                //labyrinth[currentRow][currentColumn] = 3;
                model.setValue(currentRow, currentColumn, 3);
                this.solutionFound = true;
                Node node = new Node(currentRow, currentColumn, 0, null);
                node.setInteractive(true);
                updateObservers(node);
                processSolutionByObservers(node);
            } else {
                if (labyrinth[currentRow][currentColumn] == 1) {
                    System.out.println("You just hit a wall!");
                    Node node = new Node(currentRow, currentColumn, -1, null);
                    node.setInteractive(true);
                    updateObservers(node);
                    this.solutionFound = true;
                }
                if (labyrinth[currentRow][currentColumn] == 0) {
                    //labyrinth[currentRow][currentColumn] = 3;
                    model.setValue(currentRow, currentColumn, 3);
                    Node node = new Node(currentRow, currentColumn, 0, null);
                    node.setInteractive(true);
                    updateObservers(node);
                }
            }


        }
    }

    @Override
    public void solve() {
        Scanner sc = new Scanner(System.in);
        String command;
        int option;
        while (true) {
            System.out.println("1 - Start game.\n2 - Exit");
            option = sc.nextInt();
            if (option != 1 && option != 2) {
                System.out.println("Invalid command!");
                continue;
            }
            if (option == 2) {
                break;
            } else {
                System.out.println("Game started! Use U,R,D,L to move around.");
                model.reinitialize();
                this.currentRow = model.getStartCell().getRow();
                this.currentColumn = model.getStartCell().getColumn();
                Node node = new Node(currentRow, currentColumn, 0, null);
                node.setInteractive(true);
                this.solutionFound = false;
                updateObservers(node);
                while (!solutionFound) {
                    command = sc.next();
                    if (validCommand(command)) {
                        updateLabyrinth();
                    }
                }
            }
        }

    }

}
