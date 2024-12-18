package com.dailycodebuffer.filemngt;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servletmodel", value = "/Servletmodel")
//@WebServlet: Annotation to define a servlet and map it to a specific URL.
public class model extends HttpServlet {
    //(HttpServlet provides methods to handle HTTP requests.)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}