package org.example.Memento;

import java.time.LocalDateTime;

public interface IMemento {
    // memento metadata methods
    LocalDateTime getTimestamp();

    // human-readable description shown in the history window
    String getLabel();
}