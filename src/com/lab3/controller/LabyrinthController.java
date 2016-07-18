package com.lab3.controller;

import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.data.Node;
import com.lab3.model.observers.StoreSolutionObserver;
import com.lab3.model.solvers.LabyrinthSolver;
import com.lab3.model.observers.Observer;
import com.lab3.view.LabyrinthView;

import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public class LabyrinthController {
    LabyrinthModel model;
    LabyrinthView view;
    LabyrinthSolver solver;
    List<Observer> observers;

    public LabyrinthModel getModel() {
        return model;
    }

    public void addObserver(Observer observer) {
        solver.addObserver(observer);
    }

    public void setModel(LabyrinthModel model) {
        this.model = model;
    }

    public LabyrinthView getView() {
        return view;
    }

    public void setView(LabyrinthView view) {
        this.view = view;
    }

    public LabyrinthSolver getSolver() {
        return solver;
    }

    public void setSolver(LabyrinthSolver solver) {
        this.solver = solver;
        this.solver.setLabyrinth(model.getLabyrinthAsMatrix());
    }

    public void showView() {
        System.out.println(view.getLabyrinth());
    }

    public void setLabyrinth() {
        view.setLabyrinth();
    }

    public void solve() {
        solver.solve();
    }

    public void printSolutions(StoreSolutionObserver observer) {
        List<List<Node>> solutions = observer.getSolutions();
        view.printSolutions(solutions);
    }
}
