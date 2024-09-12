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

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/getTasks")
public class GetTasks extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(GetTasks.class);
    private static final InDatabaseTaskService IN_DATABASE_TASK_SERVICE = InDatabaseTaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow only this origin
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        try {
            Gson gson = new Gson();
            PrintWriter printWriter = response.getWriter();
            int userId = Authorization.GetUserId(request);
            logger.info("{}",userId);
            if(userId == 0) {
//                response.setStatus(404);
                //response.sendRedirect("/index.html");
                logger.info("Unauthorized");
                printWriter.println(gson.toJsonTree(Map.of("status", "Unauthorized")));
                return;
            }
//            printWriter.println(gson.toJsonTree(Map.of("status", "Unauthorized")));
            List<Task> tasks = IN_DATABASE_TASK_SERVICE.getAllTasksOfUser(userId);
            printWriter.println(gson.toJsonTree(Map.of("status", "authorized", "list", tasks)));
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
