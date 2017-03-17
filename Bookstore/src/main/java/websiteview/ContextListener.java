/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview;

import Facade.Session;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author abdelrhman galal
 */
public class ContextListener implements ServletContextListener {

    Session session = new Session();

    public ContextListener() {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        session.clearAll();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        session.clearAll();
    }
}
