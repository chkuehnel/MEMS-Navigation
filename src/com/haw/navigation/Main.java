package com.haw.navigation;

public class Main{


    public static void main(String[] args) {

        // we have to use an extra class to launch the program, because static main class
        // cant be used for callbacks
        Organiser organiser = new Organiser();

        System.out.println("Program started");
    }
}
