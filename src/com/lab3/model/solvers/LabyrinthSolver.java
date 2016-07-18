package com.lab3.model.solvers;

import com.lab3.model.data.Node;
import com.lab3.model.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public interface LabyrinthSolver {
    void addObserver(Observer observer);

    void updateObservers(Node n);

    void processSolutionByObservers(Node n);

    void solve();

    void setLabyrinth(int labyrinth[][]);

    int[][] getLabyrinth();
}
