package org.example.Controller;

import org.example.Gui.Gui;
import org.example.Memento.IMemento;
import org.example.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Model model;
    private final Gui gui;

    // undoList: top of stack == current state. Bottom == oldest available state.
    private final List<IMemento> undoList;
    private final List<IMemento> redoList;
    // history: every state the model has been in, in order; last entry == current state.
    private final List<IMemento> history;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoList = new ArrayList<>();
        this.redoList = new ArrayList<>();
        this.history  = new ArrayList<>();

        // Seed with the initial state so the first undo/redo and history row are meaningful.
        IMemento initial = model.createMemento();
        undoList.add(initial);
        history.add(initial);
    }

    // ---------- Model accessors ----------

    public void setOption(int optionNumber, int choice) {
        model.setOption(optionNumber, choice);
        saveToHistory();                // snapshot AFTER the change
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        model.setIsSelected(isSelected);
        saveToHistory();                // snapshot AFTER the change
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    // ---------- Undo / Redo ----------

    public void undo() {
        if (undoList.size() < 2) {
            System.out.println("Nothing to undo");
            return;
        }
        // Move the current state to the redo stack; restore the new top of undo.
        IMemento current = undoList.remove(undoList.size() - 1);
        redoList.add(current);

        IMemento previous = undoList.get(undoList.size() - 1);
        model.restoreState(previous);
        gui.updateGui();
    }

    public void redo() {
        if (redoList.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        IMemento next = redoList.remove(redoList.size() - 1);
        undoList.add(next);             // it becomes the new current state
        model.restoreState(next);
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
        // Keep redo working: push whatever we're on now into redo.
        redoList.add(undoList.get(undoList.size() - 1));

        IMemento selected = history.get(index);
        undoList.add(selected);         // selected becomes the new current state
        model.restoreState(selected);
        gui.updateGui();
    }

    // ---------- Internals ----------

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        undoList.add(currentState);     // current state is now top of undo
        history.add(currentState);      // and the latest entry in the log
        redoList.clear();               // a new change invalidates redo
    }
}

