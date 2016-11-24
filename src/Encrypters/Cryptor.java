package Encrypters;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Cryptor {

    public static String encryptDecryptXOR(String input) {
        char[] key = {'H', 'I', 'J'};
        StringBuilder output = new StringBuilder();

        /*
        Input-String scramples
         */
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }
}
