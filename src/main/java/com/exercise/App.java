package com.exercise;

import com.exercise.database.PlanetDao;
import com.exercise.util.InputReader;
import com.exercise.util.Menu;

public class App {

    public static void main(String[] args) {

        var planetDao = new PlanetDao();

        var mainMenu = new Menu();
        mainMenu.addMenuItem("Show all planets", planetDao::selectAllPlanets);
        mainMenu.addMenuItem("Add a planet", () -> planetDao.planetInsert("Mercury", 1, 1));
        mainMenu.addMenuItem("Delete a planet", () -> planetDao.delete(InputReader.inputString("Enter the name of the planet to remove: ")));
        mainMenu.addMenuItem("Show the numbers of planets", planetDao::countPlanets);
        mainMenu.addMenuItem("Search planet by id", () -> planetDao.find(InputReader.inputInt("Enter planet ID: ")));

        mainMenu.displayMenu();

    }

}




