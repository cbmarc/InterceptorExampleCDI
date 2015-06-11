package org.smallpawn.example;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageServlet extends HttpServlet {

    @Inject
    MessageManager messageManager;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * Entry point to test Interceptor works
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/html");

        // Prepare three messages. PrepareMessage just turns the given message to lower case. Interceptor should intercept
        // the method and stop method execution if the message is more than 75 characters long. Stupid example, just for testing
        String msg1 = messageManager.prepareMessage("This is a message that should be printed, because it is 75 characters long");
        String msg2 = messageManager.prepareMessage("This is a message that should NOT be printed, because it is more than 75 characters long");
        String msg3 = messageManager.prepareMessage("This is a message that should be printed");
        PrintWriter out = resp.getWriter();
        out.println("<p>" + msg1 + "</p>");
        out.println("<p>" + msg2 + "</p>");
        out.println("<p>" + msg3 + "</p>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
