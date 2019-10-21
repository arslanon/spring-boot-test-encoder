package com.vincentleom.utils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodingService {

    final private PasswordEncoder passwordEncoder;

    @Autowired
    public EncodingService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Encode a text with bcrypt
     *
     * @param s String - text
     * @return String - encoded text via Bcrypt
     */
    public String encode(String s) {
        return this.passwordEncoder.encode(s);
    }

    /**
     * Generate a hex password with given length parameter
     * -link https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
     *
     * @param length int - default is 32
     * @return String - alphanumeric text
     */
    public String generateText(int length) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(length);

        while(sb.length() < length){

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
