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

import java.util.Scanner;

/**
 * Created by rares on 10.03.2016.
 */
public class Menu {
    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int option;
        LabyrinthModel model = null;
        LabyrinthSolver solver = null;
        LabyrinthController controller = new LabyrinthController();
        System.out.println("----Maze Game----\n");
        System.out.println("Choose labyrinth type\n1.Matrix\n2.List");
        option = sc.nextInt();
        switch (option) {
            case 1:
                model = LabyrinthFactory.createLabyrinth("matrix");
                break;
            case 2:
                model = LabyrinthFactory.createLabyrinth("list");
                break;
            default:
                System.out.println("Invalid command!");
                System.exit(0);
        }
        LabyrinthView view = new LabyrinthConsoleView(model);
        controller.setModel(model);
        controller.setView(view);
        controller.setLabyrinth();

        System.out.println("Choose solver\n1.Interactive\n2.Automated");
        option = sc.nextInt();
        switch (option) {
            case 1:
                solver = new InteractiveSolver(model);
                break;
            case 2:
                solver = new AutomatedSolver(model);
                break;
            default:
                System.out.println("Invalid command!");
                System.exit(0);
        }

        controller.setSolver(solver);
        controller.addObserver(new PrintScreenObserver(view));
        StoreSolutionObserver observer = new StoreSolutionObserver();
        controller.addObserver(observer);

        controller.showView();
        controller.solve();
        System.out.println("Do you want to print all sollutions?\n2-Yes.\n0-No");
        option = sc.nextInt();
        if (option != 2) {
            System.out.println("Bye Bye!");
            System.exit(0);
        }
        controller.printSolutions(observer);
    }
}
