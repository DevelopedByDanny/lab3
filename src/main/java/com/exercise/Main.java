package com.exercise;

import com.exercise.database.MoonDao;
import com.exercise.database.PlanetDao;
import com.exercise.util.InputReader;
import com.exercise.util.Menu;

public class Main {

    public static void main(String[] args) {

        // Creating database access objects
        var planetDao = new PlanetDao();
        var moonDao = new MoonDao();

        // Creating menus
        var mainMenu = new Menu("Main Menu");
        var planetMenu = new Menu("planets menu");
        var moonMenu = new Menu("moon menu");

        // Adding the menu options
        mainMenu.addMenuItem("Planet Menu", planetMenu::displayMenu);
        mainMenu.addMenuItem("Moon Menu", moonMenu::displayMenu);

        planetMenu.addMenuItem("Show all planets", planetDao::selectAllPlanets);
        planetMenu.addMenuItem("Add a planet", () -> planetDao.planetInsert(
                InputReader.inputString("Enter the name of the new planet: "),
                InputReader.inputDouble("Enter the planets mass: "),
                InputReader.inputInt("Enter the radius: ")
        ));
        planetMenu.addMenuItem("Delete a planet", () -> planetDao.delete(InputReader.inputString("Enter the name of the planet to remove: ")));
        planetMenu.addMenuItem("Show the numbers of planets", planetDao::countPlanets);
        planetMenu.addMenuItem("Search planet by id", () -> planetDao.find(InputReader.inputInt("Enter planet ID: ")));
        planetMenu.addMenuItem("List Planets with Moon Count", planetDao::listPlanetsWithMoonCount);
        planetMenu.addMenuItem("List Moons of a Planet", () -> planetDao.listMoonsOfPlanet(InputReader.inputString("Enter a name of a planet: ")));

        moonMenu.addMenuItem("List All Moons", moonDao::listAllMoons);
        moonMenu.addMenuItem("Add a Moon", () -> {
            moonDao.addMoon(
                    InputReader.inputInt("Enter the planet ID: "),
                    InputReader.inputString("Enter the name of the new moon: "),
                    InputReader.inputDouble("Enter the mass of the new moon: "));
        });
        moonMenu.addMenuItem("Update a Moon", () -> {
            var moon = moonDao.findMoonByName(InputReader.inputString("Enter the name of the moon: "));
            if (moon != null) {
                System.out.println(moon.toString());
                moonDao.updateMoon(
                        moon.moonId(),
                        InputReader.inputInt("Enter the new planet id for this moon: "),
                        InputReader.inputString("Enter the new name: "),
                        InputReader.inputDouble("Enter new mass: ")
                );
            } else System.out.println("That moon does not exist!");

        });
        moonMenu.addMenuItem("Delete a Moon", () -> {
            moonDao.deleteMoon(InputReader.inputString("Enter the name of the moon you want to delete: "));
        });

        mainMenu.displayMenu();
    }
}