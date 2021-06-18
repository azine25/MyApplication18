package com.geek.myapplication.ui.home;

public class ToDoModel {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ToDoModel(String text) {
        this.text = text;
    }
}
