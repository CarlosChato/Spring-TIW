package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

import beans.User;

/**
 * Servlet implementation class EnterServlet
 */
@WebServlet({ "/register", "/login"})
public class EnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getHttpServletMapping().getMatchValue().equals("register")) {
			getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
		}
		if (request.getHttpServletMapping().getMatchValue().equals("login")) {
			getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		HttpSession session = request.getSession();


		/* ----- REGISTER ----- */
		if (request.getHttpServletMapping().getMatchValue().equals("register")) {
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			
			if (username.length() > 150
					|| name.length() > 150
					|| surname.length() > 150
					|| password.length() > 150
					|| email.length() > 150
					|| phone.length() != 9
					|| !phone.matches("[0-9]+")) {
				
				request.setAttribute("errorMsg", (String) "Algún campo tiene mucho texto o el número de teléfono no tiene formato correcto");
				getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
			}

			URL url = new URL("http://127.0.0.1:10802/users");
			String content = "{\"admin\":\"false\",\"username\":\"" + username + "\",\"password_user\":\"" + password + 
					"\",\"name_user\":\"" + name + "\",\"surname\":\"" + surname + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\"}";
			HttpURLConnection urlConn = null;
			StringBuffer buffer = null;
			
			try {
				urlConn = (HttpURLConnection) url.openConnection();
				urlConn.setRequestMethod("POST");
				urlConn.setRequestProperty("Content-Type", "application/json");
				urlConn.setDoOutput(true);
				OutputStream outStream = urlConn.getOutputStream();
				outStream.write(content.getBytes());
				outStream.flush();
				outStream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", (String) "Error conectando con el servicio, inténtelo de nuevo más tarde.");
				getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
			}
						
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				buffer = new StringBuffer();
				String line = null;
				while((line = bufferReader.readLine()) != null) {
					buffer.append(line);
				}
				bufferReader.close();
				urlConn.disconnect();
			} else {
				System.out.println("Connection unsuccessful, resource not created");
				request.setAttribute("errorMsg", (String) "Error en el registro, inténtelo de nuevo en unos instantes.");
				getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
			}

			User userFromDB = gson.fromJson(buffer.toString(), User.class);
			
			if (userFromDB.getId() < 0) {
				request.setAttribute("errorMsg", (String) "Este usuario ya existe");
				getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
			}
			
	        session.setAttribute("user", userFromDB);
	        response.sendRedirect("Home");
		}
		
		/* ----- LOGIN ----- */
		if (request.getHttpServletMapping().getMatchValue().equals("login")) {
			URL url = new URL("http://127.0.0.1:10802/users/login/" + username);
			StringBuilder content = new StringBuilder();
			String line;
			
			try {
				URLConnection urlConn = url.openConnection();
				BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

				while ((line = buffer.readLine()) != null) {
					content.append(line);
				}
				buffer.close();
			} catch (IOException e) {
				System.out.println("IOException handled: " + e.getMessage());
				content = new StringBuilder();
				content.append("{}");
			} catch (Exception e) {
				e.printStackTrace();
			}

			User userFromDB = gson.fromJson(content.toString(), User.class);
			
			if (userFromDB == null) {
				request.setAttribute("errorMsg", (String) "Este usuario no existe");
				getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
			}
			
			if (!password.equals(userFromDB.getPassword_user())) {
				request.setAttribute("errorMsg", (String) "La contraseña es incorrecta");
				getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);				
			}

			System.out.println("Usuario logeado con éxito");
			
	        session.setAttribute("user", userFromDB);
	        response.sendRedirect("Home");
		}
	}
}
