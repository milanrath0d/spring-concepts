package org.milan.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Class responsible for intercepting incoming requests
 *
 * @author Milan Rathod
 */
@Component
public class MaintenanceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceInterceptor.class);

    /**
     * If Current Day is Sunday then make App unavailable & show message
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Calendar calender = Calendar.getInstance();
        int dayOfWeek = calender.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            LOG.debug("Received Intercept Request for maintenance.");
            response.getWriter().print("Web Site under maintenance today");
            return false;
        }
        return true;
    }

    /**
     * To Perform something post handle
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        LOG.debug("Post Handle Method called: {}", request.getRequestURI());
    }

    /**
     * To perform something after completion
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        LOG.debug("After Completion Method called: {}", request.getRequestURI());
    }
}
