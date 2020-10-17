/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

import static com.mycompany.eshop.registerForm.VALID_EMAIL_ADDRESS_REGEX;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Thanasis
 */
public class PasswordUtils {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RAND = new SecureRandom();
    String saltRET=null;
    
    //hashing methods will move them to seperate class
    public static Optional<String> generateSalt (final int length) {

    if (length < 1) {
      System.err.println("error in generateSalt: length must be > 0");
      return Optional.empty();
    }

    byte[] salt = new byte[length];
    RAND.nextBytes(salt);

    return Optional.of(Base64.getEncoder().encodeToString(salt));
  }
    
    public static Optional<String> hashPassword (String password, String salt) throws InvalidKeySpecException {

    char[] chars = password.toCharArray();
    byte[] bytes = salt.getBytes();

    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

    Arrays.fill(chars, Character.MIN_VALUE);

    try {
      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] securePassword = fac.generateSecret(spec).getEncoded();
      return Optional.of(Base64.getEncoder().encodeToString(securePassword));

    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      System.err.println("Exception encountered in hashPassword()");
      return Optional.empty();

    } finally {
      spec.clearPassword();
    }
  }
   public static boolean verifyPassword (String password, String key, String salt) throws InvalidKeySpecException {
    Optional<String> optEncrypted = hashPassword(password, salt);
    if (!optEncrypted.isPresent()) return false;
    return optEncrypted.get().equals(key);
  }
    
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
}
    
public static void GenerateSaltFile (){
    File f = new File("salt.txt");
        if(f.exists() && !f.isDirectory()) { 
       System.out.println("File already exists!");
    }else{
            try {
          FileWriter myWriter = new FileWriter("salt.txt");
          String salt=generateSalt(64).get();
          myWriter.write(salt);
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
           }catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }      
    }
     
}
    
    public String ReadSaltFile(){
          try {
            File myObj = new File("salt.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              saltRET=data;
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
          return saltRET;
    }
}
