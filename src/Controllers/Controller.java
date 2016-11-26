package Controllers;

import Model.Book;
import Model.User;
import Model.Curriculum;
import SDK.Connection;
import com.google.gson.Gson;

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
                System.out.println("___________________________");
                System.out.println("| BookIT - Login - BookIT |");
                System.out.println("|_________________________|");
                System.out.println("");
                System.out.println("Du har nu følgende valgmuligheder:");
                System.out.println("1. Login som eksisterende bruger");
                System.out.println("2. Opret ny bruger");

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        mainMenu();
                        break;
                    case 2:
                        createUser();
                        break;
                    default:
                        System.out.println("Du tastede forkert - prøv igen.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Indtast venligst et tal");
                input.next();
            }
        } while (true);

    }

    // Menu der møder bruger - efter man er logget ind med gyldigt username og password
    public void mainMenu() {

        String username, password;
        System.out.println("Login");
        System.out.println("Indtast brugernavn");
        username = input.nextLine();
        System.out.println("Indtast kodeord");
        password = input.nextLine();

        String token = Connection.authorizeLogin(username, password);
        if (token != null) {
            do {
                try {
                    System.out.println("");
                    System.out.println("Book & Curriculum service");
                    System.out.println("1. Print alle bøger");
                    System.out.println("2. Print en bog med unikke oplysninger og priser");
                    System.out.println("3. Print et pensum");
                    System.out.println("4. Ændre i personlige brugeroplysninger");
                    System.out.println("5. Slet bruger");
                    System.out.println("6. Log ud");
                    switch (input.nextInt()) {
                        case 1:
                            printBooks();
                            break;
                        case 2:
                            printBook();
                            break;
                        case 3:
                            printCurriculum();
                            break;
                        case 4:
                            changeUser();
                            break;
                        case 5:
                            deleteUser();
                            break;
                        case 6:
                            System.out.println(" ---------------");
                            System.out.println("  Tak for i dag  ");
                            System.out.println(" ---------------");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Du tastede forkert - prøv igen.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Indtast venligst et tal");
                    input.next();
                }
            } while (true);

        }

    }

    public void printBooks() {
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("Her er alle bøgerne:");
        for (Book book : books) {
            System.out.println("Bog - ID: " + book.getBookID() + " - Titlen: " + book.getTitle());
        }
    }

    public void printBook() {
        Scanner input2 = new Scanner(System.in);
        ArrayList<Book> books = Connection.getBooks();
        ArrayList<Book> foundBooks = new ArrayList<>();
        boolean check = false;

        System.out.println("Indtast navn på den ønskede bog");
        String searchTitle = input2.nextLine();

        try {
            for (Book book : books) {
                if (book.getTitle() != null && book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                    foundBooks.add(book);
                    check = true;
                    System.out.println(foundBooks.indexOf(book) + ". " + book.getTitle());
                }
            }
            } catch (InputMismatchException e) {
                check = true;
                System.out.println("Indtast venligt et tal?");
                input.next();


            }
             System.out.println("Indtast nummer på bog, som du ønsker info på?");
            int foundBook = input2.nextInt();
            Book book = foundBooks.get(foundBook);
            System.out.println("Title: " + book.getTitle() + "\n" + "Author(s): " + book.getAuthor() + "\n" + "Version: " + book.getVersion()
                    + "\n" + "ISBN/Stregkode Nr.: " + book.getISBN() + "\n" + "Forlag: " + book.getPublisher() + "\n" + "Pris på AB: " + book.getPriceAB()
                    + "\n" + "Pris på CDON: " + book.getPriceCDON() + "\n" + "Pris på SAXO: " + book.getPriceSAXO() + "\n" );

/*
        } catch (InputMismatchException e) {
            check = true;
            System.out.println("Indtast venligt et tal?");
            input.next();
*/
        }


    public void createUser() {
        System.out.println("Not implementet");

    }

    public void printCurriculum() {
        System.out.println("Not implementet");

    }

    public void changeUser() {
        System.out.println("Not implementet");

    }

    public void deleteUser() {
        System.out.println("Not implementet");
    }
    public void logout() {
        System.out.println("Not implementet");
}
}