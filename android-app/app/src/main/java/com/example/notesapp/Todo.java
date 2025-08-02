package com.example.notesapp;

public class Todo {
    private String content;
    private boolean isDone;

    public Todo(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
