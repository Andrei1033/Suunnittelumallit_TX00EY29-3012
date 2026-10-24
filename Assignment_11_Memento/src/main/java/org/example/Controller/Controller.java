package org.example.Controller;

import org.example.Gui.Gui;
import org.example.Memento.IMemento;
import org.example.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private Gui gui;

    private final List<IMemento> undoList;// Memento history
    private final List<IMemento> redoList; // Memento history
    private List<IMemento> history; // Memento history

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoList = new ArrayList<>();
        this.redoList = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    // ---------- Model accessors ----------

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        model.setOption(optionNumber, choice);
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    // ---------- Undo / Redo ---------

    public void undo() {
        if (undoList.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        redoList.add(model.createMemento());

        IMemento previousState = undoList.remove(undoList.size() - 1);
        model.restoreState(previousState);
        gui.updateGui();
    }

    public void redo() {
        if (redoList.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }

        undoList.add(model.createMemento());

        IMemento nextState = redoList.remove(redoList.size() - 1);
        model.restoreState(nextState);
        gui.updateGui();
    }

    // ---------- History window ----------

    public List<IMemento> getHistory() {
        return history;
    }

    public void restoreFromHistory(int index) {
        if (index < 0 || index >= history.size()) {
            return;
        }
        // Preserve ability to redo: save current state
        redoList.add(model.createMemento());

        IMemento selected = history.get(index);
        model.restoreState(selected);
        gui.updateGui();
    }


    // ---------- Internals ----------

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        undoList.add(currentState);
        history.add(currentState);
        redoList.clear();
    }
}
