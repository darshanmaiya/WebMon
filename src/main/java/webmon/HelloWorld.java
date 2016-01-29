package webmon;

import javax.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html");
      resp.getWriter().println("<html><body>");
      resp.getWriter().println("<h2>Hello World</h2>"); //remove this line

      //Add your code here

      resp.getWriter().println("</body></html>");
  }
}
