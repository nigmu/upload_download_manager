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

/*
A Servlet is a Java program that runs on a server and
handles HTTP requests from a client (e.g., a web browser).
It can process GET, POST, or other HTTP methods and return responses (e.g., HTML, JSON).

How Does a Servlet Work?
Web Container:Servlets run in a web container like Tomcat, Jetty, or WildFly.
The container manages servlet lifecycle (loading, initializing, executing, and destroying).

Client Requests:When a client (browser) sends a request to http://localhost:8080/Servletmodel,
the web container:Finds the mapped servlet using the @WebServlet annotation.
Calls either doGet or doPost depending on the HTTP method (GET/POST).

Response:Inside the doGet or doPost methods, you can process the request
and send back a response using the HttpServletResponse object.

You typically write servlets to:
Process form submissions.
Serve dynamic web pages.
Interact with databases.


 */