package Controllers;

import Model.Book;
import SDK.Connection;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Controller {

    Scanner input;

    public Controller() {
        input = new Scanner(System.in);
    }

    // Første menu brugeren møder
    public void preMenu() {

        Scanner input = new Scanner(System.in);

        int choice = 0;

        do {
            try {
                System.out.println("BookIT - Login - BookIT");
                System.out.println("");
                System.out.println("Du har nu følgende valgmuligheder:");
                System.out.println("1. Login som eksisterende bruger");
                System.out.println("2. Opret ny bruger");

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        loginMenu();
                        break;
                    case 2:
                        createUser();
                        break;
                    default:
                        System.out.println("Du tastede forkert - pr�v igen.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Indtast venligt et tal (1-9)");
                input.next();
            }
        } while (true);

    }
    // Menu der møder bruger - efter man er logget ind med gyldigt username og password
    public void loginMenu() {

        String username, password;
        System.out.println("BookIT - Login - BookIT");
        System.out.println("Indtast Brugernavn");
        username = input.nextLine();
        System.out.println("Indtast kodeord");
        password = input.nextLine();

        String token = Connection.authorizeLogin(username, password);
        if (token != null) {
            do {
                System.out.println("Welcome - Du har nu følgende valgmuligheder");
                System.out.println("1) Print en bog");
                System.out.println("2) Print alle bøger");
                switch (input.nextInt()) {
                    case 1:
                        printBook();
                        break;
                    case 2:
                        printBooks();
                        break;
                    default:
                        System.out.println("Indtast enten 1 eller 2");
                }
            } while (true);//Brug noget andet en true. CurrentUser != null
        } else {
            System.out.println("user pas fejl");
        }

    }

    public void printBooks() {
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("Bøger i Biancas bogklub");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }

    public void printBook() {
        System.out.println("Indast id på den ønskede bog");
        Book book = Connection.getBook(input.nextInt());
        System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
    }

    public void createUser() {
        System.out.println("Not implementet");

    }

}