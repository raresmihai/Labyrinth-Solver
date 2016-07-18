package com.lab3.model.observers;

import com.lab3.model.data.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public class StoreSolutionObserver implements Observer {

    List<List<Node>> solutions = new ArrayList<>();
    List<Node> path = new ArrayList<>();


    List<Node> clonePath(List<Node> path) {
        List<Node> newPath = new ArrayList<>();
        for (Node node : path) {
            Node newNode = new Node(node.getRow(), node.getColumn(), 0, null);
            newPath.add(newNode);
        }
        Collections.reverse(newPath);
        return newPath;
    }

    @Override
    public void processSolution(Node node) {
        if (node.isInteractive()) {
            this.solutions.add(clonePath(path));
            Collections.sort(this.solutions, new Comparator<List<Node>>() {
                @Override
                public int compare(List<Node> o1, List<Node> o2) {
                    if (o1.size() < o2.size()) return -1;
                    if (o1.size() > o2.size()) return 1;
                    return 0;
                }
            });
            this.path.clear();
        } else {
            List<Node> newSolution = new ArrayList<>();
            while (node != null) {
                node = node.getParent();
                if (node != null) {
                    newSolution.add(node);
                }
            }
            solutions.add(newSolution);
        }
    }

    @Override
    public void processCell(Node cell) {
        if (cell.isInteractive()) {
            if (cell.getDistance() == -1) {
                this.path.clear();
            } else {
                this.path.add(cell);
            }
        }
    }

    public List<List<Node>> getSolutions() {
        return solutions;
    }
}
