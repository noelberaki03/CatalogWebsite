import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class FindPasswordServlet extends HttpServlet {
    // instance variables
    private static final long serialVersionUID = 100L;

    private DatabaseManager db = new DatabaseManager();
    private UserManager userManager = new UserManager(db.getUsers());;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = request.getSession(false);
    
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
    
        String action = (String) session.getAttribute("action");
        if (action == null || action.equals("")) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        } 
        else if (action.equals("forgot")) {*/
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>Forgot Password</title><link rel=\"stylesheet\" href=\"styles.css\"></head>");
            out.println("<body>");

            out.println("<h2>Forgot Password</h2>");
            out.println("<p>Please enter your registered email:</p>");
            
            // Create a form that submits to the same URL using GET method
            out.println("<form method=\"get\">");
            out.println("<input type=\"email\" name=\"email\" placeholder=\"Enter your email\" required>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            
            String email = request.getParameter("email"); // Retrieve the email from the submitted form

            if (email != null && !email.isEmpty()) {
                out.println("<h4>Email: " + email + "</h4>");

                if (userManager.getUsers().containsKey(email)) {
                    String password = userManager.getUsers().get(email).getPassword();
                    out.println("<h4>Password: " + password + "</h4>");
                } else {
                    out.println("<h4>Password not found!</h4>");
                    out.println("<h5>Email may not be registered</h5>");
                }
            }
            // Add a button to redirect to login.html
            out.println("<form action=\"login.html\">");
            out.println("<input type=\"submit\" value=\"Go to Login\">");
            out.println("</form>");
            out.println("</body></html>");
        
    }
}