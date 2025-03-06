package com.es2.memento;

import java.util.ArrayList;

// Classe Memento: Armazena o estado do servidor
public class Memento extends Object {
    private ArrayList<String> students = new ArrayList<>();

    public Memento(ArrayList<String> studentNames) {
        students.addAll(studentNames); // Faz uma cópia real da lista
    }

    public ArrayList<String> getState() {
        return students; 
    }
}
