package com.company;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class Calc {

    public void menu(HashMap<String, Double> data){                                                                     //menu z opcjami do wyboru
        Scanner inputMenu = new Scanner(System.in);
        System.out.println("Witaj w kalkulatorze walut.\n" +
                "Aby wyświetlić aktualne przeliczniki względem EUR wpisz 'p'.\n" +
                "Jeśli chcesz przejść do kalkulatora wpisz 'k'.\n" +
                "Jeśli chcesz wyjść z programu wpisz 'w'.");
        System.out.println("Co chcesz zrobić?");
        String inputMenuString = inputMenu.nextLine();
        while(!(inputMenuString.equals("p") || inputMenuString.equals("k") || inputMenuString.equals("w"))){
            System.out.println("Brak wskazanej opcji, wprowadź informację ponownie.");
            inputMenuString = inputMenu.nextLine();
        }

        switch (inputMenuString) {
            case "p":
                converters(data);
                break;
            case "k":
                calculate(data);
                break;
            case "w":
                System.exit(0);
                break;
        }
    }

    public void converters(HashMap<String, Double> data){                                                               //wyświtelenie listy walut wraz z przelicznikami
        Scanner inputConverters = new Scanner(System.in);
        for(String i : data.keySet()) {
            System.out.println(i + " " + data.get(i));
        }
        System.out.println("Powrót do menu? (tak/nie)");
        String inputConvertersString = inputConverters.nextLine();
        switch (inputConvertersString){
            case "tak":
                menu(data);
                break;
            case "nie":
                System.out.println("Program wyłączy się");
                System.exit(0);
        }
    }

    public void calculate(HashMap<String, Double> data){
        double result = 0;
        Scanner currencyNameScanner = new Scanner(System.in);
        Scanner valueScanner = new Scanner(System.in);

        System.out.println("Podaj nazwę waluty którą chcesz przeliczyć " +
                "(zgodnie z nazwą z listy przeliczników w menu).");
        String currencyName = currencyNameScanner.nextLine();
        currencyName = currencyName.toUpperCase();

        boolean ifExist = data.containsKey(currencyName);                                                               //sprawdzenie czy dana waluta istnieje
        if(!ifExist){
            System.out.println("Nie ma takiej waluty");
            calculate(data);
        }

        System.out.println("Podaj ilość pieniędzy w EUR.");                                                             //zapytanie o kwote i sprawdzenie czy format jest prawidłowy
        String valueString = valueScanner.nextLine();
        if(valueString.matches(".*[a-z].*") || valueString.matches(".*[A-Z].*")){
            System.out.println("Wpisana dana musi być liczbą.");
            calculate(data);
        }

        double value = parseDouble(valueString);                                                                        //przeliczenie wartości
        for (String i : data.keySet()) {
            if (currencyName.equals(i)) {
                result = value * data.get(i);
                break;
            }
        }


        System.out.format("Kwota po przeliczeniu: %.2f",result);                                                        //wyświetlenie wyniku i zapytanie o ponowne przeliczenie
        System.out.println("\nCzy chcesz przeliczyć jeszcze raz? (tak/nie)");
        Scanner inputChoice = new Scanner(System.in);
        String inputChoiceString = inputChoice.nextLine();

        switch (inputChoiceString){
            case "tak":
                calculate(data);
                break;
            case "nie":
                menu(data);
            default:
                System.out.println("Nie ma takiej odpowiedzi, program wyłączy się.");
                System.exit(0);
        }
    }
}
