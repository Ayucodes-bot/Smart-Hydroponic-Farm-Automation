package com.hydroponic.controller;

import com.hydroponic.service.HydroponicService;
import com.hydroponic.model.Schedule;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    private HydroponicService service;

    @Override
    public void init() {
        service = new HydroponicService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int plantId = Integer.parseInt(req.getParameter("plantId"));
            int duration = Integer.parseInt(req.getParameter("duration"));

            Schedule s = new Schedule(plantId, duration);
            service.applySchedule(s);

            resp.sendRedirect("success.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
