package com.example.notesapp;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataSource {

    private static InMemoryDataSource instance;
    private List<Object> items;

    private InMemoryDataSource() {
        items = new ArrayList<>();
        // Add some dummy data for testing
        items.add(new Note("This is a sample note."));
        items.add(new Todo("This is a sample to-do."));
        Todo completedTodo = new Todo("This is a completed to-do.");
        completedTodo.setDone(true);
        items.add(completedTodo);
    }

    public static synchronized InMemoryDataSource getInstance() {
        if (instance == null) {
            instance = new InMemoryDataSource();
        }
        return instance;
    }

    public List<Object> getItems() {
        return items;
    }

    public void addItem(Object item) {
        items.add(0, item); // Add new items to the top of the list
    }
}
