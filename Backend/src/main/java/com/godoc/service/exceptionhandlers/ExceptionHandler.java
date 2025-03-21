package com.godoc.service.exceptionhandlers;

import com.godoc.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, Object handler, @NonNull Exception ex) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.addObject("url", request.getRequestURL());

        if (ex instanceof ResourceNotFoundException) {
            mav.setStatus(HttpStatus.NOT_FOUND);
        }

        mav.setView(new MappingJackson2JsonView());
        return mav;
    }
}
