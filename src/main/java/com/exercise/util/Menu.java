package com.exercise.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(String description, Runnable action) {
        menuItems.add(new MenuItem(description, action));
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nMENU OPTIONS:");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i).description());
            }
            System.out.println("0. Exit");

            int choice = InputReader.inputInt("Choose an option: ");

            if (choice == 0) {
                break;
            }

            if (choice > 0 && choice <= menuItems.size()) {
                menuItems.get(choice - 1).action().run();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private record MenuItem(String description, Runnable action) {
    }
}
