package Controllers;

import Model.Book;
import Model.User;
import Model.Curriculum;
import SDK.Connection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Controller {

    Scanner input;

    public Controller() {
        input = new Scanner(System.in);
    }

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


    public void createUser() {

        String newUsername;
        String newPassword;
        boolean existingUser = false;

        Scanner input = new Scanner(System.in);

        System.out.print("Indtast ønsket brugernavn: ");
        newUsername = input.nextLine();
        System.out.print("Indtast ønsket password: ");
        newPassword = input.nextLine();
/*
        for (User user : userDatabase.getUsers()) {
            if (newUsername.equalsIgnoreCase(user.getUsername())) {
                existingUser = true;
                break;
            }
        }

        if (existingUser) {
            System.out.println("Dette brugernavn eksisterer allerede. Prøv igen");
        } else {
            User newUser = new User(newUsername, newPassword, 0);
            userDatabase.getUsers().add(newUser);
            System.out.println("Bruger " + newUsername + " er nu oprettet");
        }

        System.out.println("Not implementet");
*/


    }


    // Menu der møder bruger - efter man er logget ind med gyldigt username og password
    public void mainMenu() {

        String username, password;
        System.out.println("___Login___ ");
        System.out.println("Indtast brugernavn:");
        username = input.nextLine();
        System.out.println("Indtast kodeord:");
        password = input.nextLine();

        String token = Connection.authorizeLogin(username, password);
        if (token != null) {
            do {
                try {
                    System.out.println("");
                    System.out.println("Book & Curriculum service");
                    System.out.println("1. Se alle bøger");
                    System.out.println("2. Find en bog med unikke oplysninger og priser");
                    System.out.println("3. Visning af pensumlister");
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
                            System.out.println(" Du logges ud - Tak for i dag  ");
                            preMenu();
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

        System.out.println("Indtast navn på den ønskede bog");
        String searchTitle = input2.nextLine();

        try {
            for (Book book : books) {
                if (book.getTitle() != null && book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                    foundBooks.add(book);
                    System.out.println(foundBooks.indexOf(book) + ". " + book.getTitle());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Indtast venligst et tal?");
            input.next();


        }
        System.out.println("Indtast nummer på bog, som du ønsker info på?");
        int foundBook = input2.nextInt();
        Book book = foundBooks.get(foundBook);
        System.out.println("Title: " + book.getTitle() + "\n" + "Author(s): " + book.getAuthor() + "\n" + "Version: " + book.getVersion()
                + "\n" + "ISBN/Stregkode Nr.: " + book.getISBN() + "\n" + "Forlag: " + book.getPublisher() + "\n" + "Pris på AB: " + book.getPriceAB()
                + "\n" + "Pris på CDON: " + book.getPriceCDON() + "\n" + "Pris på SAXO: " + book.getPriceSAXO() + "\n");

    }

    public void printCurriculum() {
        Scanner input = new Scanner(System.in);
        ArrayList<Curriculum> curriculums = Connection.getCurriculums();
        ArrayList<Curriculum> foundCurriculumBooks = new ArrayList<>();

        System.out.println("Her er alle pensumlisterne - Du kan få vist bøgerne på semesteret ved at indtaste det unikke ID:");
        for (Curriculum curriculum : curriculums) {
            System.out.println("ID: " + curriculum.getCurriculumID() + " - " + curriculum.getSchool() + " " + curriculum.getSemester() + ". Semester  " + curriculum.getEducation());
        }


        int searchCurriculum;
        do {

            System.out.println("Indtast ID for Semester: ");
            while (!input.hasNextInt()) {
                System.out.println("Indtast venligst et nummer");
                input.next();
            }
            searchCurriculum = input.nextInt();

        } while (searchCurriculum <= 0 || searchCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = Connection.getCurriculumBooks(searchCurriculum);

        System.out.println("De følgende bøger skal bruges på det valgte semester: \n(Info på den enkelte bog kan findes i hovedmenuen) \n");

        for (Book book : curriculumBooks) {
            System.out.println(book.getTitle() + " - ISBN Nummer: " + book.getISBN());

        }

    }

    public void changeUser() {
        System.out.println("Not implementet");

    }

    public void deleteUser() {
        System.out.println("Not implementet");

    }

}