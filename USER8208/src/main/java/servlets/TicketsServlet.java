package servlets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.APIBridge;
import beans.Event;
import beans.Ticket;
import beans.User;

/**
 * Servlet implementation class TicketsServlet
 */
@WebServlet({"/viewTickets", "/sellTickets"})
public class TicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public TicketsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		APIBridge bridge = new APIBridge();
		Ticket[] ticketArray = null;
		URL url = null;
		
		if (request.getHttpServletMapping().getMatchValue().equals("viewTickets")) {
			
			if (request.getParameter("user") != null) {
				url = new URL("http://127.0.0.1:10803/users/tickets");
				bridge.setUrl(url);
				ticketArray = bridge.getTicketList((User) session.getAttribute("user"));
				if (ticketArray.length == 0)
					request.setAttribute("noTicketsMsg", "Aún no tienes entradas a la venta, ¡pero puedes comprarlas!");
				
			} else if (request.getParameter("Event_id") != null) {
				url = new URL("http://127.0.0.1:10801/eventos/" + request.getParameter("Event_id"));
				bridge.setUrl(url);
				Event event = bridge.getEvent();
				if (event.getId() == null) {    // something went wrong with the request
					return;
				}
				url = new URL("http://127.0.0.1:10803/eventos/tickets");
				bridge.setUrl(url);
				ticketArray = bridge.getTicketList(event);
				if (ticketArray.length == 0)
					request.setAttribute("noTicketsMsg", "Aún no hay entradas a la venta, ¡pero pronto habrá!");

			} else {
				return;
			}
			
			if (ticketArray.length == 0) {
				request.setAttribute("ticketList", new ArrayList<Ticket>());
			} else {
				ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
				for (Ticket t: ticketArray) {
					ticketList.add(t);
				}
				request.setAttribute("ticketList", ticketList);
			}
			getServletContext().getRequestDispatcher("/BuyTicket.jsp").forward(request, response);
			return;
			
		}
		
		if (request.getHttpServletMapping().getMatchValue().equals("sellTickets") && session != null) {
			url = new URL("http://127.0.0.1:10801/eventos/" + request.getParameter("Event_id"));
			bridge.setUrl(url);
			Event event = bridge.getEvent();
			if (event.getId() == null) {    // something went wrong with the request
				return;
			}
			session.setAttribute("selectedEventForSelling", event);
			getServletContext().getRequestDispatcher("/SellTicket.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		APIBridge bridge = new APIBridge();
		URL url = null;
		
		if (session == null) { return; }
		
		if (request.getHttpServletMapping().getMatchValue().equals("sellTickets")) {
			Ticket new_ticket = new Ticket();
			
			// fijamos el precio
			if (request.getParameter("price").length() > 9)
				new_ticket.setPrice(Integer.parseInt(request.getParameter("price").substring(0, 9)));
			else
				new_ticket.setPrice(Integer.parseInt(request.getParameter("price")));
			
			// fijamos el tipo de evento
			if (request.getParameter("type").length() > 150)
				new_ticket.setType(request.getParameter("type").substring(0, 150));
			else
				new_ticket.setType(request.getParameter("type"));

			// fijamos el código
			if (request.getParameter("code").length() > 150)
				new_ticket.setCode(request.getParameter("code").substring(0, 150));
			else
				new_ticket.setCode(request.getParameter("code"));

			// fijamos el id del evento
			new_ticket.setEvent((Event) session.getAttribute("selectedEventForSelling"));
			// fijamos el id del usuario
			new_ticket.setUsers((User) session.getAttribute("user"));
			
			bridge.setUrl(new URL("http://127.0.0.1:10803/tickets"));
			if (bridge.uploadTicket(new_ticket).equals("success")) {
				request.setAttribute("statusMsg", "Entrada subida");
			} else {
				request.setAttribute("statusMsg", "Error: entrada no subida");
			}
			getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
			return;
		}
	}
}
