package webmon;

import javax.servlet.http.*;
import java.io.*;

/**
 * The Class HelloWorldServlet.
 */
@SuppressWarnings("serial")
public class HelloWorldServlet extends HttpServlet {

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  resp.getWriter().println("Hello World!");
  }
}