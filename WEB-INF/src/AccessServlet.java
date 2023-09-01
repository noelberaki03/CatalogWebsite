import java.security.InvalidParameterException;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessServlet extends HttpServlet {
    private static final long serialVersionUID = 100L;
 
    private UserManager userManager;
    private DatabaseManager db;

    @Override
    public void init() {
        db = new DatabaseManager();
        userManager = new UserManager(db.getUsers()); // Initialize userManager
    }

    @Override
    public void destroy() {
        db.writeUsers(userManager.getUsers()); // Save the user data to the database
        super.destroy();
    }

    public void loginAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false); // Get existing session if it exists

        if (session != null) {
            String errorResponse = "Failure to login";
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse);
            session.removeAttribute("action");
        }

        User userToLogin = new User(email, password);

        try {
            userManager.loginUser(userToLogin);

            session = request.getSession(true); // Create a new session
            session.setAttribute("email", email);
            response.sendRedirect(request.getContextPath() + "/catalog.html");
        } 
        catch (InvalidParameterException e) {
            String errorResponse = "ERROR: Failure to login";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
        } 
        catch (IllegalStateException e) {
            String errorResponse = "Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse);
        }
    }

    public void registerAction(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");

            HttpSession session = request.getSession(false);

            if (session != null) {
                String errorResponse = "Error: try registering again";
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse);
                return;
            }
            else {
                User registeringUser = new User(firstName, lastName, email, password);
                // InvalidParameterException, IllegalStateException 
                try {
                    userManager.registerUser(registeringUser);
                }
                catch(InvalidParameterException e) {
                    System.out.println(e);
                }
                catch(IllegalStateException e) {
                    System.out.println(e);
                }
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
    }

    public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
        else {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //HttpSession session = request.getSession();

        if (action == null || action.equals("")) {
            String errorResponse = "Your request caused an ERROR";
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse);
            return;
        }  
        else if (action.equals("login")) {
            loginAction(request, response);
        }
        else if (action.equals("register")) {
            registerAction(request, response);
        }
        else if (action.equals("logout")) {
            logoutAction(request, response);
        }
        else if (action.equals("forgot")) {
            response.sendRedirect(request.getContextPath() + "/find");
        }
        else {
            String errorResponse = "Your request caused an ERROR";
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse);
            return;
        }
    }
}