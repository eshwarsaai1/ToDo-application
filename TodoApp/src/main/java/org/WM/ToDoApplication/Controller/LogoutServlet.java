package org.WM.ToDoApplication.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow only this origin
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("AuthCookie")){
                cookie.setMaxAge(0);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setSecure(false);
                cookie.setHttpOnly(true);
                response.setHeader("Set-Cookie", "AuthCookie=" + "something" + "; Path=/; Domain=localhost; Secure; HttpOnly; SameSite=None");
                logger.info("user logged out");
            }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        // Set CORS headers for the preflight request
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
