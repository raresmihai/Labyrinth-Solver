package com.lab3.app;

import com.lab3.controller.LabyrinthController;
import com.lab3.model.data.LabyrinthFactory;
import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.observers.Observer;
import com.lab3.model.observers.PrintScreenObserver;
import com.lab3.model.observers.StoreSolutionObserver;
import com.lab3.model.solvers.AutomatedSolver;
import com.lab3.model.solvers.InteractiveSolver;
import com.lab3.model.solvers.LabyrinthSolver;
import com.lab3.view.LabyrinthConsoleView;
import com.lab3.view.LabyrinthView;

/**
 * Created by rares on 08.03.2016.
 */
public class Main {
    public static void main(String args[]) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}
