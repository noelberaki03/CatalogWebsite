//import java.security.InvalidParameterException;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {
    // instance variables
    private static final long serialVersionUID = 100L;

    private CartManager cartManager;
    private DatabaseManager db;

    @Override
    public void init() {
        db = new DatabaseManager();
        cartManager = new CartManager(db.getUserCarts());
    }

    @Override
    public void destroy() {
        db.writeUserCarts(cartManager.getUserCarts());
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        if (email == null || email.equals("")) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        else {
            Map<CartItem, Integer> userCart = cartManager.getUserCart(email);
            PrintWriter out = response.getWriter();

            if (userCart == null || userCart.size() == 0) {
                out.println("<html>");
                out.println("<head><title>My Cart</title><link rel=\\\"stylesheet\\\" href=\\\"styles.css\\\"></head>");
                out.println("<body>");
                out.println("<h1>Your Cart:</h1>");
                out.println("<hr><br><h3>It seems like your cart is empty</h3>");
                out.println("<h3>Click below to go back to the catalog");
                out.println("<a href='/catalog/catalog.html'>Back To Catalog!</a>");
                out.println("</body></html>");
            }
            else {
                out.println(CartSummaryHtmlGenerator.getCartSummaryPage(userCart));
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        String email = (String) session.getAttribute("email");
        if (email == null || email.equals("")) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        else {
            String imgAddress = request.getParameter("imgAddress");
            String itemName = request.getParameter("itemName");
            int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
            
            CartItem cartItem = new CartItem(imgAddress, itemName, itemPrice);
            cartManager.addToCart(email, cartItem);
            response.sendRedirect(request.getContextPath() + "/catalog.html");
        }
    }
}