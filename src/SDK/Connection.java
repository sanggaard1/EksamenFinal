package SDK;

import Encrypters.Cryptor;
import Model.User;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.deploy.net.HttpResponse;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.json.simple.JSONObject;

import javax.ws.rs.client.Entity;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Connection {

    // Login request
    public static String authorizeLogin(String username, String password) {
        String enrPassowrd = Cryptor.encryptDecryptXOR(password);
        UserLogin userLogin = new UserLogin(username, password);
        //  System.out.println(Cryptor.encryptDecryptXOR(new Gson().toJson(userLogin)));

        ClientResponse clientResponse = HttpRequest.post("/user/login", Cryptor.encryptDecryptXOR(new Gson().toJson(userLogin)));
        String token = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {

            String json = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                token = json;
            } else {
                System.out.println("Not working");
            }
        }
        clientResponse.close();
        return token;
    }

    //getting all books
    public static ArrayList<Book> getBooks() {
        ClientResponse clientResponse = HttpRequest.get("/book");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Cryptor.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return books;
    }

    // getting a specific book
    public static Book getBook(int id) {
        ClientResponse clientResponse = HttpRequest.get("/book" + id);
        Book book = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Cryptor.encryptDecryptXOR(encryptedJson);
                book = new Gson().fromJson(decryptedJson, Book.class);
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return book;
    }

    // getting all stored curriculums
    public static ArrayList<Curriculum> getCurriculums() {
        ClientResponse clientResponse = HttpRequest.get("/curriculum");
        ArrayList<Curriculum> curriculums = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Cryptor.encryptDecryptXOR(encryptedJson);
                curriculums = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Curriculum>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return curriculums;
    }

    // Get Books on specific curriculum
    public static ArrayList<Book> getCurriculumBooks(int curriculumId) {
        ClientResponse clientResponse = HttpRequest.get("/curriculum/" + curriculumId + "/books");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Cryptor.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return books;
    }

    //Create User
    public static String postUser(JsonObject data) {
        ClientResponse clientResponse = HttpRequest.post("/user/", Cryptor.encryptDecryptXOR(new Gson().toJson(data)));

        String response = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.out.println(response);
            } else {
                System.out.println("Error");
            }
        }
        clientResponse.close();
        return response;
    }

    //Update User
    public static String putUser(String token, JsonObject data, int userID) {

        ArrayList<String> info = new ArrayList<>();
        info.add("authorization");
        info.add(token);
        ClientResponse clientResponse = HttpRequest.put("/user/" + userID, Cryptor.encryptDecryptXOR(new Gson().toJson(data)), info);
        System.out.println(clientResponse.toString());
        String response = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.out.println(response);
            } else {
                System.out.println("Error");
            }
        }


        clientResponse.close();
        return response;

    }

    public static String deleteUser(String token, JsonObject data, int userID) {

        ArrayList<String> info = new ArrayList<>();
        info.add("authorization");
        info.add(token);
        ClientResponse clientResponse = HttpRequest.delete("/user/" + userID, Cryptor.encryptDecryptXOR(new Gson().toJson(data)), info);
        System.out.println(clientResponse.toString());
        String response = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                System.out.println(response);
            } else {
                System.out.println("Error");
            }

        }
        clientResponse.close();
        return response;

    }


    // Logout

}

