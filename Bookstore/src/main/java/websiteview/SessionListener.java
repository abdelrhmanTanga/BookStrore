/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview;

import Facade.Session;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author abdelrhman galal
 */
public class SessionListener implements HttpSessionListener {
    Session session;
    public SessionListener(){
        session = new Session();
    }
    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println("session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession info = hse.getSession();
        if (info != null){
            String email = (String) info.getAttribute("loggedIn");
            if (email != null){
                session.logout(email);
            }
        }
    }
}
