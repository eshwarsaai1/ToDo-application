package org.WM.ToDoApplication.Controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.WM.ToDoApplication.Models.Task;
import org.WM.ToDoApplication.Services.InDatabaseTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@WebServlet(urlPatterns = "/addTask")
public class AddTask extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddTask.class);
    private static final InDatabaseTaskService IN_DATABASE_TASK_SERVICE = InDatabaseTaskService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow only this origin
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        Gson gson = new Gson();
        try {
            Task task = gson.fromJson(request.getReader(),Task.class);
            int userId = Authorization.GetUserId(request);
            if(userId==0){
                logger.debug("User didn't login!!");
                return;
            }
            int taskId = IN_DATABASE_TASK_SERVICE.addTask(task,userId);
            response.getWriter().println(gson.toJsonTree(Map.of("taskId",taskId)));
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
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
