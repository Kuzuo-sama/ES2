package com.es2.memento;

import java.util.ArrayList;

public class Server extends Object{
    private ArrayList<String> studentNames = new ArrayList<>();

    public Server() {
    }

    public void addStudent(String studentName) throws ExistingStudentException {
        if (studentNames.contains(studentName)) {
            throw new ExistingStudentException();
        }else{
            studentNames.add(studentName);
        }
    }

    public Memento backup() {
        Memento m = new Memento(studentNames);
        return m;
    }

    public void restore(Memento state) {
        studentNames = state.getState();
    }

    public ArrayList<String> getStudentNames() {
        return studentNames;
    }
}