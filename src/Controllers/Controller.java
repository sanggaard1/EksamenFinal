package Controllers;

import Model.Book;
import SDK.Connection;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Controller {

    Scanner input;

    public Controller(){
        input = new Scanner(System.in);
    }

    public void menu(){

        String username, password;
        System.out.println("BookIT - Login - BookIT");
        System.out.println("Indtast Brugernavn");
        username = input.nextLine();
        System.out.println("Indtast kodeord");
        password = input.nextLine();

        String token = Connection.authorizeLogin(username, password);
        if(token != null){
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
            }while(true);//Brug noget andet en true. CurrentUser != null
        }
        else {
            System.out.println("user pas fejl");
        }

    }

    public void printBooks(){
        ArrayList<Book> books = Connection.getBooks();
        System.out.println("Bøger i Biancas bogklub");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }

    public void printBook(){
        System.out.println("Indast id på den ønskede bog");
        Book book = Connection.getBook(input.nextInt());
        System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
    }
}


