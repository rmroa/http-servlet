package com.rm.http.servlet;

import com.rm.http.dto.CreateUserDto;
import com.rm.http.entity.Gender;
import com.rm.http.entity.Role;
import com.rm.http.exception.ValidationException;
import com.rm.http.service.UserService;
import com.rm.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.setAttribute("genders", Gender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserDto customerDto = CreateUserDto.builder()
                .firstName(req.getParameter("name"))
                .lastName(req.getParameter("lastName"))
                .birthday(req.getParameter("birthday"))
                .image(req.getPart("image"))
                .country(req.getParameter("country"))
                .city(req.getParameter("city"))
                .phone(req.getParameter("phone"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .gender(req.getParameter("gender"))
                .build();

        try {
            userService.create(customerDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
