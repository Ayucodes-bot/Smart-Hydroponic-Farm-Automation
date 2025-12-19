package com.hydroponic.controller;

import com.hydroponic.service.HydroponicService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/sensor")
public class SensorServlet extends HttpServlet {

    private HydroponicService service;

    @Override
    public void init() {
        service = new HydroponicService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        double ph = Double.parseDouble(req.getParameter("ph"));
        String analysis = service.analyzePH(ph);

        req.setAttribute("analysis", analysis);
        req.getRequestDispatcher("viewSensor.jsp").forward(req, resp);
    }
}
