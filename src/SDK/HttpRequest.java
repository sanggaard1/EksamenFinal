package SDK;

import com.sun.jersey.api.client.*;
import java.util.ArrayList;


/**
 * Created by Sanggaard on 24/11/2016.
 */
public class HttpRequest {

    static Client client = Client.create();

    /**
     * Used to get requests to the server
     *
     * @param path the specific path
     * @return
     */
    public static ClientResponse get(String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path); //book

            clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
    // Post http request. Sender data objekt, der indeholder bruger information
    public static ClientResponse post(String path, String data) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path); //book

            clientResponse = webResource.accept("application/json").post(ClientResponse.class, data);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
    // Put http request - Data objekt og headerinfo sendes
    public static ClientResponse put(String path, String data, ArrayList<String> headerinfo) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client

                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path); //book

            clientResponse = webResource.accept("application/json").header(headerinfo.get(0), headerinfo.get(1)).put(ClientResponse.class, data);
            System.out.println(headerinfo.get(0) + "--" + headerinfo.get(1));//this line exceutes no error


        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
    // Delete http request
    public static ClientResponse delete(String path, String data, ArrayList<String> headerinfo) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client

                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path);
            clientResponse = webResource.accept("application/json").header(headerinfo.get(0), headerinfo.get(1)).delete(ClientResponse.class);



        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
    // Logout - sletter token på serveren
    public static ClientResponse logout(String path, String data) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client

                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path);
            clientResponse = webResource.accept("application/json").post(ClientResponse.class, data);


        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }


}

