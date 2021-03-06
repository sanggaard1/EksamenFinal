package Controllers;

import Encrypters.Digester;
import Model.Book;
import Model.Curriculum;
import SDK.Connection;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Controller {

    private int userID;

    Scanner input;
    private String tokenId;

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

    // Opret bruger
    public void createUser() {

        JsonObject data = new JsonObject();

        Scanner input = new Scanner(System.in);

        System.out.print("Indtast dit fornavn:");
        data.addProperty("firstName", input.nextLine());

        System.out.print("Indtast dit efternavn:");
        data.addProperty("lastName", input.nextLine());

        System.out.print("Indtast din email:");
        data.addProperty("email", input.nextLine());

        System.out.print("Indtast ønsket brugernavn:");
        data.addProperty("userName", input.nextLine());

        System.out.print("Indtast ønsket kodeord:");
        data.addProperty("password", Digester.hashWithSalt(input.nextLine()));

        data.addProperty("userType", "0");

        Connection.postUser(data);

        System.out.println("Bruger er nu oprettet - Du kan nu logge ind med informationer");

    }


    // Menu der møder bruger - efter man ner logget ind med gyldigt username og password
    public void mainMenu() {

        String username, password;
        System.out.println("___Login___ ");
        System.out.println("Indtast brugernavn:");
        username = input.next();
        System.out.println("Indtast kodeord:");
        password = input.next();

        String token = Connection.authorizeLogin(username, password);
        JsonParser parse = new JsonParser();

        // Henter userID og Token, der man bruges til at opdatere den rette bruger i PUT.
        JsonArray testm = (JsonArray) parse.parse(token);
        JsonObject user = (JsonObject) testm.get(0);
        userID = user.get("userID").getAsInt();

        String temp=testm.get(1).toString();
        tokenId = temp.substring(1,temp.length()-1);


        System.out.println(token);
        if (token != null) {
            do {
                try {
                    System.out.println("");
                    System.out.println("Book & Curriculum service");
                    System.out.println("1. Se alle bøger");
                    System.out.println("2. Find en bog med unikke oplysninger og priser");
                    System.out.println("3. Visning af pensumlister");
                    System.out.println("4. Ændre bruger info");
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
                            logout();
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
    // Printer alle bøger i databasen
    public void printBooks() {
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("Her er alle bøgerne:");
        for (Book book : books) {
            System.out.println("Bog - ID: " + book.getBookID() + " - Titlen: " + book.getTitle());
        }
    }
    // Søge funktion til print
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
    // Printer alle pensumlister - indeksere dem og tager user-input
    public void printCurriculum() {
        Scanner input = new Scanner(System.in);
        ArrayList<Curriculum> curriculums = Connection.getCurriculums();

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
    // Ændre bruger metode - bruger HTTP Put i httprequest
    public void changeUser() {

        Scanner input = new Scanner(System.in);


        System.out.println("Indtast i felter du ønsker at få ændret. Du kan undlade at udfylde parametre du ikke ønsker ændret");

        JsonObject data = new JsonObject();

        System.out.print("Indtast dit fornavn:");
        data.addProperty("firstName", input.nextLine());

        System.out.print("Indtast dit efternavn:");
        data.addProperty("lastName", input.nextLine());

        System.out.print("Indtast din email:");
        data.addProperty("email", input.nextLine());

        System.out.print("Indtast ønsket brugernavn:");
        data.addProperty("userName", input.nextLine());

        System.out.print("Indtast ønsket kodeord:");
        data.addProperty("password", input.nextLine());

        data.addProperty("userType", "0");
        Connection.putUser(tokenId,data, userID);

        System.out.println("Bruger nu ændret");


    }
    // Soft State delete of user - Sletter token
    public void deleteUser() {
        Connection.deleteUser(tokenId, new JsonObject(),userID);
        System.out.println("User now deleted");

        preMenu();

    }
    // logout - sletter token
    public void logout(){
        Connection.logout(tokenId);

        preMenu();
    }

}