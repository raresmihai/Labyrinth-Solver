package com.lab3.model.observers;

import com.lab3.model.data.Node;
import com.lab3.view.LabyrinthConsoleView;
import com.lab3.view.LabyrinthView;

/**
 * Created by rares on 08.03.2016.
 */
public class PrintScreenObserver implements Observer {
    LabyrinthView view;

    public PrintScreenObserver(LabyrinthView view) {
        this.view = view;
    }

    @Override
    public void processSolution(Node node) {
        if (node.isInteractive()) {
            System.out.println("Hurray! You reached the destination!");
        } else {
            System.out.println("New solution found!");
        }
    }

    @Override
    public void processCell(Node cell) {
        if (cell.isInteractive()) {
            System.out.println(view.getLabyrinth());
        }
    }
}
