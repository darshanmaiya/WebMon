package webmon;

import javax.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  resp.getWriter().println("Hello World!");
  }
}
