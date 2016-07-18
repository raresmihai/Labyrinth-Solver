package com.lab3.view;

import com.lab3.model.data.LabyrinthModel;
import com.lab3.model.data.Node;

import java.util.List;

/**
 * Created by rares on 08.03.2016.
 */
public interface LabyrinthView {
    void setLabyrinth();

    String getLabyrinth();

    void printSolutions(List<List<Node>> solutions);

    void outputMessage(String msg);

    void setModel(LabyrinthModel model);
}

