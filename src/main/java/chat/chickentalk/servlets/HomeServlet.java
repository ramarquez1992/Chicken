package chat.chickentalk.servlets;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.pojos.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Person p = DaoImpl.getInstance().getPersonById(1);
//        System.out.println(p);

        System.out.println("hello, HomeServlet.java");
        response.sendRedirect("index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
