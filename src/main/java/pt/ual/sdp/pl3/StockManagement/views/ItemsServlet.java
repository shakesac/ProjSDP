package pt.ual.sdp.pl3.StockManagement.views;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "itemsServlet", value = "/items/*")
public class ItemsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1></h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}