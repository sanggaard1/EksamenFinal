import Controllers.Controller;
import SDK.Config;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Main {

    public static void main(String[] args) {
        Config.initConfig();

        new Controller().menu();

    }
}
