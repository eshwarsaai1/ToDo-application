package org.WM.ToDoApplication.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.WM.ToDoApplication.Services.InDatabaseTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

@WebServlet(urlPatterns = "/deleteTask")
public class DeleteTask extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeleteTask.class);
    private static final InDatabaseTaskService IN_DATABASE_TASK_SERVICE = InDatabaseTaskService.getInstance();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow only this origin
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json");
        try {
            int userId = Authorization.GetUserId(request);
            if(userId==0){
                logger.debug("User didn't login!!");
                return;
            }
            int taskId=Integer.parseInt(request.getParameter("taskId"));
            IN_DATABASE_TASK_SERVICE.deleteTask(taskId, userId);
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
