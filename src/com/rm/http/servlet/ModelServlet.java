package com.rm.http.servlet;

import com.rm.http.service.ModelService;
import com.rm.http.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/models")
public class ModelServlet extends HttpServlet {

    private final ModelService modelService = ModelService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long manufacturerId = Long.valueOf(req.getParameter("manufacturerId"));
        req.setAttribute("models", modelService.findByManufacturerId(manufacturerId));

        req.getRequestDispatcher(JspHelper.getPath("model"))
                .forward(req, resp);
    }
}
