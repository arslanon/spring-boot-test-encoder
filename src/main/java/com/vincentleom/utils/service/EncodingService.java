package com.vincentleom.utils.service;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodingService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EncodingService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Encode a password as bcrypt
     *
     * @param password String - password
     * @return String - encoded password via Bcrypt
     */
    public String encode(String password) {
        return this.passwordEncoder.encode(password);
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
        String AlphaNumericString =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
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

    /**
     * Generate password with given length parameter
     * -link https://www.baeldung.com/java-generate-secure-password
     *
     * @param length int - default is 32
     * @return String -
     */
    public String generatePassayPassword(int length) {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(1);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "100";
            }

            public String getCharacters() {
                return "!#$%^&*_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(length, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    }
}
