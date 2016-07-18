package com.lab3.model.observers;

import com.lab3.model.data.Node;

/**
 * Created by rares on 08.03.2016.
 */
public interface Observer {
    void processCell(Node cell);

    void processSolution(Node node);
}
