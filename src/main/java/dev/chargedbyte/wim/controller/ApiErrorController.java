package dev.chargedbyte.wim.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiErrorController implements ErrorController {
    @RequestMapping("/api/error")
    public String handleError(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);

        if (status != null) {
            return status.getReasonPhrase();
        }

        return "Unknown Error Occurred";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
