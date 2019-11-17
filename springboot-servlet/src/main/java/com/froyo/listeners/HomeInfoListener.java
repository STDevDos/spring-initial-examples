package com.froyo.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Froy
 */
@WebListener
public class HomeInfoListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextEvent initialized.");
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("user", "[USUARIO]");
        sc.setAttribute("name", "[NOMBRE DEL USUARIO]");
        sc.setAttribute("dato", "éste dato es obtenido desde la clase @WebListener (HomeInfoListener) ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.removeAttribute("user");
        sc.removeAttribute("name");
        sc.removeAttribute("dato");
        System.out.println("ServletContextEvent destroyed.");
    }
}
