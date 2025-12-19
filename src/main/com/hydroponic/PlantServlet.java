package com.hydroponic.controller;

import com.hydroponic.model.Plant;
import com.hydroponic.service.HydroponicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plant")
public class PlantServlet extends HttpServlet {

    private HydroponicService service;

    @Override
    public void init() {
        service = new HydroponicService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            double ph = Double.parseDouble(request.getParameter("ph"));

            Plant plant = new Plant();
            plant.setName(name);
            plant.setPhLevel(ph);

            service.addOrUpdatePlant(plant);

            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
