package com.company;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Testing Functionality of BCrypt
 * @author Carter Du
 */
public class TestBCrypt {

    public static void main(String[] args) {
        String  originalPassword = "password";

        //String generatedSecuredPasswordHash = BCrypt.hashpw(newPass, BCrypt.gensalt(12));
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        String hash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));;
        System.out.println(generatedSecuredPasswordHash);
        System.out.println(hash);


        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);

        System.out.println(matched);


    }
}
