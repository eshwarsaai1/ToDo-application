package org.WM.ToDoApplication.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.WM.ToDoApplication.Repository.impl.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Authorization {
    private static final Logger logger = LoggerFactory.getLogger(Authorization.class);

    public static int GetUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            logger.info("Cookies collected");
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("AuthCookie")){
                    logger.debug("{}",Database.authenticationToken);
                    if(cookie.getValue().equals(Database.authenticationToken)){
                        logger.info("User Authorization is Valid");
                        return Database.UserId;
                    }
                    logger.debug("User Not Authorized");
                }
            }
        }
        logger.debug("User didn't login yet!!");
        return 0;
    }
}
