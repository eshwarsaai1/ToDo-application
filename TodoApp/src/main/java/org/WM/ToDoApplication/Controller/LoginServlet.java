package org.WM.ToDoApplication.Controller;


import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.WM.ToDoApplication.Models.User;
import org.WM.ToDoApplication.Repository.impl.Database;
import org.WM.ToDoApplication.Services.UserValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow only this origin
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Gson gson = new Gson();
        try {
            User user = gson.fromJson(request.getReader(), User.class);
            if(user == null){
                logger.debug("user not found");
                return;
            }
            int userId = UserValidationService.getInstance().validateUser(user);
            if(userId == 0){
                response.getWriter().println(gson.toJsonTree(Map.of("status", "fail")));
                logger.info("Invalid user");
            }
            else {
                Database.UserId=userId;
                String authToken = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("AuthCookie", authToken);
                Database.authenticationToken = authToken;
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setSecure(false);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60 * 60 * 2);
                response.setHeader("Set-Cookie", "AuthCookie=" + authToken + "; Path=/; Domain=localhost; Secure; HttpOnly; SameSite=None");
                response.getWriter().println(gson.toJsonTree(Map.of("status", "success")));
//                response.sendRedirect("\getTasks");
            }
        }catch (Exception e){
            logger.error("Exception: ", e);
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
