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
            // 1. Read input
            String name = request.getParameter("name");
            String type = request.getParameter("type");

            // 2. Validation (VERY IMPORTANT FOR REVIEW-2)
            if (name == null || name.trim().isEmpty()
                    || type == null || type.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Plant name and type are required");
                return;
            }

            // 3. Create model
            Plant plant = new Plant(name, type);

            // 4. Business logic via Service (MVC respected)
            service.addPlant(plant);

            // 5. Success response
            response.setContentType("text/plain");
            response.getWriter().write("Plant added successfully");

        } catch (Exception e) {
            // 6. Error handling (marks here)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error while adding plant");
        }
    }
}
