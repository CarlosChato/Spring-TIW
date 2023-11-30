package servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.APIBridge;
import beans.Chat;
import beans.Message;
import beans.User;

import com.google.gson.*;


/**
 * Servlet implementation class ChatServlet
 */
@WebServlet({"/chat","/chatCreate"})
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	APIBridge bridge = new APIBridge();
	
    public ChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		HttpSession session = request.getSession(false);
		if (request.getHttpServletMapping().getMatchValue().equals("chat")) {
			String id = request.getParameter("id");
			if (session == null) {
				getServletContext().getRequestDispatcher("/login").forward(request, response);
				return;
				}
			
			bridge.setUrl(new URL("http://127.0.0.1:10804/chats"));
			Chat[] chatList = bridge.getChatList((User)session.getAttribute("user"));
			if (chatList == null) {
				System.out.println("chat list null");
				response.sendRedirect("/Home");
				return;
			}
			
			if (id == null) {
				request.setAttribute("chatList", Arrays.asList(chatList));
				getServletContext().getRequestDispatcher("/ShowChats.jsp").forward(request, response);
			} else {
				
				// ¿Alguien intenta acceder a un chat en el que no participa?
				boolean ok = false;
				for (int i = 0; i < chatList.length; i++) {
					if (chatList[i].getId().toString().equals(id)) {
						ok = true;
					}
				}
				if (!ok) {
					response.sendRedirect("/Home");
					return;
				}
				// El usuario está en el chat solicitado, démosle sus mensajes
				bridge.setUrl(new URL("http://127.0.0.1:10804/chats/view/" + id));
				Message[] messageList = bridge.getMessages();
				if (messageList == null) {
					System.out.println("message list null");
					response.sendRedirect("/Home");
					return;
				}
				for(Message m: messageList) {
					System.out.println(m.getId());
					System.out.println(m.getSendBy());
					System.out.println(m.getChat());
					System.out.println(m.getChat()==null);
					}
					request.setAttribute("messageList", Arrays.asList(messageList));
					getServletContext().getRequestDispatcher("/ShowMessages.jsp").forward(request, response);
				}
				
			}
		else {
			// Get user's id that sell the ticket 
			String id_user2 = request.getParameter("User_id");
			if (session == null) {
				getServletContext().getRequestDispatcher("/login").forward(request, response);
				return;
				}
						
			bridge.setUrl(new URL("http://127.0.0.1:10802/users/"+id_user2));
			User User2 = bridge.getUser();
			if (User2 == null) {
				System.out.println("User not found");
				response.sendRedirect("/Home");
				return;
			}
			
			System.out.println(User2);
			
			Chat new_chat = new Chat();
			
			new_chat.setUser1((User)session.getAttribute("user"));
			new_chat.setUser2(User2);
			
			// Set the URL to create new chat
			bridge.setUrl(new URL("http://127.0.0.1:10804/chats/newChat"));
			
			// Create new chat
			Chat chat_created = gson.fromJson(bridge.postChat(new_chat), Chat.class);
			
			System.out.println(chat_created);
			
			response.sendRedirect("/ticketsell8208/chat?id="+chat_created.getId());		
			
		}
		
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
		
		bridge.setUrl(new URL("http://127.0.0.1:10804/chats"));
		Chat[] chatList = bridge.getChatList((User)session.getAttribute("user"));
		if (chatList == null) {
			System.out.println("chat list null");
			response.sendRedirect("/Home");
			return;
		}
		// ¿Alguien intenta publicar un mensaje en un chat en el que no participa?
		boolean ok = false;
		Chat chat = null;
		for (int i = 0; i < chatList.length; i++) {
			if (chatList[i].getId().toString().equals(id)) {
				ok = true;
				chat = chatList[i];
			}
		}
		if (!ok) {
			response.sendRedirect("/Home");
			return;
		}
		
		
		bridge.setUrl(new URL("http://127.0.0.1:10804/chats/newMessage"));
		Message msg = new Message();
			
		msg.setMessage(request.getParameter("msg"));
		msg.setSendBy((User) session.getAttribute("user"));
		msg.setChat(chat);
		bridge.postMessage(msg);
		doGet(request, response);
	}

}
