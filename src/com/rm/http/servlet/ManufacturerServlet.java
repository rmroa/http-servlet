package com.rm.http.servlet;

import com.rm.http.service.ManufacturerService;
import com.rm.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manufacturers")
public class ManufacturerServlet extends HttpServlet {

    private final ManufacturerService manufacturerService = ManufacturerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("manufacturers", manufacturerService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("manufacturer"))
                .forward(req, resp);
    }
}
