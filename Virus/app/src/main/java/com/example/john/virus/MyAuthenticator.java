package com.example.john.virus;



import javax.mail.*;
/**
 * Created by john on 18/03/17.
 */

/**
 * 身份认证
 */
public class MyAuthenticator extends Authenticator {

    protected PasswordAuthentication getPasswordAuthentication(){
        String username = "baoshengbin_cumt@163.com";
        String password = "baoshengbin1996";
        return new PasswordAuthentication(username, password);
    }

}

