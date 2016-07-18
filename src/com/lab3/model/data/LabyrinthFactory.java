package com.lab3.model.data;

/**
 * Created by rares on 08.03.2016.
 */
public class LabyrinthFactory {
    static public LabyrinthModel createLabyrinth(String labyrinth) {
        switch (labyrinth) {
            case "matrix":
                return new LabyrinthMatrixImpl();
            case "list":
                return new LabyrinthListImpl();
            default:
                return null;
        }
    }
}
