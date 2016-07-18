package com.lab3.view;

import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.data.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by rares on 08.03.2016.
 */
public class LabyrinthConsoleView implements LabyrinthView {
    private LabyrinthModel model;

    public LabyrinthConsoleView(LabyrinthModel model) {
        this.model = model;
    }

    public void setLabyrinth() {
        try {
            Scanner sc = new Scanner(new File("input"));
            int rowCount = sc.nextInt();
            int columnCount = sc.nextInt();
            model.initialize(rowCount, columnCount);
            int value;
            for (int i = 0; i < rowCount; ++i) {
                for (int j = 0; j < columnCount; ++j) {
                    value = sc.nextInt();
                    model.setValue(i, j, value);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String getChar(int value) {
        switch (value) {
            case -1:
                return "S";
            case 1:
                return "*";
            case 2:
                return "F";
            case 3:
                return "X";
            default:
                return " ";
        }
    }

    @Override
    public void outputMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public String getLabyrinth() {
        return this.toString();
    }

    public String toString() {
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        int matrix[][] = model.getLabyrinthAsMatrix();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowCount; ++i) {
            sb.append("|");
            for (int j = 0; j < columnCount; ++j) {
                sb.append(getChar(matrix[i][j])).append("|");
            }
            sb.append("\n");
        }
        return String.valueOf(sb);
    }

    @Override
    public void setModel(LabyrinthModel model) {
        this.model = model;
    }

    @Override
    public void printSolutions(List<List<Node>> solutions) {
        for (List<Node> list : solutions) {
            ListIterator li = list.listIterator(list.size());
            while (li.hasPrevious()) {
                System.out.print("(" + li.previous() + ") -> ");
            }
            System.out.println(" Finish\n-------------------------------------------");
        }
    }

}
