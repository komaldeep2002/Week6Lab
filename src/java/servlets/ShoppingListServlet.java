package servlets;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Komaldeep Kaur Virk
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        session.setAttribute("username", username);

        String logout = request.getParameter("action");

        if (logout != null) {
            session.invalidate();
            request.setAttribute("res", "You have successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else if (username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }

    }
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        HttpSession session = request.getSession();
        String name = request.getParameter("username");
        String status = request.getParameter("action");
        String item = request.getParameter("item");
 
        if (status.equals("register")) {

            if (name == null || name.equals("")) {
                request.setAttribute("res", "Please enter username.");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
                return;
            }
            session.setAttribute("username", name);
        } else if (status.equals("add")) {
            list.add(item);
            session.setAttribute("list", list);
        } else if (status.equals("delete")) {
            String selected = request.getParameter("item");
            list = (ArrayList) session.getAttribute("list");
            list.remove(selected);
            session.setAttribute("list", list);
            if (list.isEmpty()) {
                session.removeAttribute("list");
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
    }

}