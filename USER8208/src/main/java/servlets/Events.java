package servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.APIBridge;
import beans.Event;
import beans.Ticket;

/**
 * Servlet implementation class EventosAlta
 */
@WebServlet("/show_events")
@MultipartConfig
public class Events extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		APIBridge bridge = new APIBridge();
		bridge.setUrl(new URL("http://127.0.0.1:10801/eventos"));
		Event[] eventList = bridge.getEventList();

		request.setAttribute("eventList", Arrays.asList(eventList));
		getServletContext().getRequestDispatcher("/FilterEvents.jsp").forward(request, response);
		return;
	}
}
