/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.helpers;

import java.util.Random;

/**
 *
 * @author Przemo
 */
public class CodeGenerator {
    
       private static final Random RANDOM = new Random();

       private static final int LENGTH = 128;

       private static final int MODULO_FACTOR = 123;

       private final static String CHARSET_STRING = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz0123456789";

       private static final char[] CHARSET = CHARSET_STRING.toCharArray();

 

       public static String generateCode(final String username) {

              StringBuilder s = new StringBuilder();

              String mod = String.valueOf(moduloOfUsername(username));

              s.append(mod);

              for (int i = 0; i < LENGTH - mod.length() - 1; i++) {

                     s.append(CHARSET[RANDOM.nextInt(CHARSET.length)]);

              }

              s.append(mod.length());

              return s.toString();

       }

 

       public static boolean verifyCode(final String code, final String username) {

              String m = String.valueOf(moduloOfUsername(username));

              int l = Integer.parseInt(code.substring(code.length() - 1,

                           code.length()));

              return code.length() == LENGTH

                           && code.substring(0, l).equals(m);

       }

 
       public static String sanitiseCode(final String code){
           for(char c: code.toCharArray()){
               if(!CHARSET_STRING.contains(String.valueOf(c))){
                   return "";
               }
           }
           return code;
       }

       private static int moduloOfUsername(final String username) {

              int s = 0;

              for (int i = 0; i < username.length(); i++) {

                     if (CHARSET_STRING.contains(String.valueOf(username.charAt(i)))) {

                           s += username.charAt(i);

                     }

              }

              return s % MODULO_FACTOR;

       }
    
}
