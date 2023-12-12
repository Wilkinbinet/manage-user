package com.manage.user.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.manage.user.util.ConstantsUtil.INTERNAL_SERVICE_ERROR;

public class ErrorResponseUtil {

    public static void handleException(HttpServletResponse httpResponse, Exception ex) throws IOException {
        Throwable nestedException = ex.getCause();

        if (nestedException instanceof IllegalArgumentException) {
            sendErrorResponse(httpResponse, HttpServletResponse.SC_BAD_REQUEST, nestedException.getMessage());
        } else {
            sendErrorResponse(httpResponse, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVICE_ERROR);
        }
    }

    private static void sendErrorResponse(HttpServletResponse httpResponse, int statusCode, String message) throws IOException {
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(statusCode);
        String jsonErrorResponse = "{\"mensaje\": \"" + message + "\"}";
        httpResponse.getWriter().write(jsonErrorResponse);
    }
}
