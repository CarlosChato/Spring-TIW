package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.sql.Connection;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * Servlet implementation class scriptSQL
 */
@WebServlet("/scriptSQL")
public class scriptSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(mappedName = "TIWDS")
	DataSource ds;
	Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scriptSQL() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // users data
		 String[] names = new String[] {"Pepe", "Alberto", "Ben", "Jose", "Peppo", "David", "Elena", "Fred", "Frank", "Jorge", "Hal"};
		 String[] usernames	= new String[]  {"Aspect","Kraken","Bender","Lynch","Big Papa","Mad Dog","Bowser","ODoyle","Bruise","Psycho","Cannon"};       
		 String[] letters = new String[] {"q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
		 String named, passd, usernamed, surnamed, emaild;
		 int phoned;
			
		 Random ran = new Random();
		 int min = 600000000;
		 int max = 699999999;
		 
		 // events data
		 String typed = "fest";
		 String dated, photod;
		 String[] events_name = new String[] {"mad cool","arenal sound","resurrection fest"};
		 String[] url_events = new String[] {"mad-cool.png","arenal-sound.png","resurrection.jpg"};
		 String[] cityd = new String[] {"Madrid","Valencia","Ourense"};
		 String[] salad = new String[] {"Ifema","Playa Valencia","La Poza"};
		 String[] messages = new String[] {"hola, estoy interesado en la entrada","hola, que le gustaria saber?","En que parte del escensario es??"};
		 
		 // tickets data
		 int priced, user_id, event_id;
		 String type = "ticket";
		 Integer code;
		 
		 try {
			conn = ds.getConnection();
			Statement st = conn.createStatement();
			conn.setAutoCommit(false);
			
			if(conn != null){
				System.out.println("Connection was successful");
			} else {
				System.out.println("Conecction Error");
			}

			st.execute( "DROP TABLE IF EXISTS event_tickets;");
			
			st.execute( "DROP TABLE IF EXISTS tickets;");
			
			st.execute("DROP TABLE IF EXISTS message ;");

			st.execute("DROP TABLE IF EXISTS chats ;");
			
			st.execute("DROP TABLE IF EXISTS users;");
			
			st.execute("DROP TABLE IF EXISTS event ;");
			
			st.execute("CREATE TABLE IF NOT EXISTS users(\r\n"
					+ "  idUsers INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  isAdmin BOOLEAN NOT NULL,\r\n"
					+ "  name_user VARCHAR(150) NOT NULL,\r\n"
					+ "  password_user VARCHAR(150) NOT NULL,\r\n"
					+ "  username VARCHAR(150) NOT NULL,\r\n"
					+ "  surname VARCHAR(150) NOT NULL,\r\n"
					+ "  email VARCHAR(150) NOT NULL,\r\n"
					+ "  phone INT NOT NULL,\r\n"
					+ "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "  PRIMARY KEY (idUsers),\r\n"
					+ "  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE,\r\n"
					+ "  UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE,\r\n"
					+ "  UNIQUE INDEX phone_UNIQUE (phone ASC) VISIBLE);");
			
			st.execute("\r\n"
					+ "CREATE TABLE IF NOT EXISTS event(\r\n"
					+ "  idEvent INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  category VARCHAR(150) NOT NULL,\r\n"
					+ "  fecha DATE NOT NULL,\r\n"
					+ "	 name_event VARCHAR(150) NOT NULL,\r\n"
					+ "  city VARCHAR(150) NOT NULL,\r\n"
					+ "  sala VARCHAR(150) NOT NULL,\r\n"
					+ "  photo_path TEXT,\r\n"
					+ "  photo LONGTEXT,\r\n"
					+ "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "  PRIMARY KEY (idEvent));");
			
			st.execute("CREATE TABLE IF NOT EXISTS tickets(\r\n"
					+ "  idTickets INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  type VARCHAR(150) NOT NULL,\r\n"
					+ "  code VARCHAR(150) NOT NULL,\r\n"
					+ "  price INT NOT NULL,\r\n"
					+ "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "  Users_id INT NOT NULL,\r\n"
					+ "  Event_id INT NOT NULL,\r\n"
					+ "  PRIMARY KEY (idTickets),\r\n"
					+ "    FOREIGN KEY (Users_id)\r\n"
					+ "    REFERENCES users (idUsers)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE,\r\n"
					+ "    FOREIGN KEY (Event_id)\r\n"
					+ "    REFERENCES event (idEvent)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE);");
			
			st.execute("CREATE TABLE IF NOT EXISTS chats(\r\n"
					+ "  idChat INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  user1_id INT NOT NULL,\r\n"
					+ "  user2_id INT NOT NULL,\r\n"
					+ "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "  PRIMARY KEY (idChat),\r\n"
					+ "  FOREIGN KEY (user1_id)\r\n"
					+ "    REFERENCES users (idUsers)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE,\r\n"
					+ "  FOREIGN KEY (user2_id)\r\n"
					+ "    REFERENCES users (idUsers)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE);");
			
			st.execute("CREATE TABLE IF NOT EXISTS message(\r\n"
					+ "  id INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  message TEXT NOT NULL,\r\n"
					+ "  chat INT NOT NULL,\r\n"
					+ "  sendBy INT NOT NULL, \r\n"
					+ "  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
					+ "  PRIMARY KEY (id),\r\n"
					+ "  FOREIGN KEY (chat)\r\n"
					+ "    REFERENCES chats (idChat)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE,\r\n"
					+ "  FOREIGN KEY (sendBy)\r\n"
					+ "    REFERENCES users (idUsers)\r\n"
					+ "    ON DELETE CASCADE \r\n"
					+ "    ON UPDATE CASCADE);");
			
			// Fill users table
			st.execute("INSERT INTO users (isAdmin,name_user,password_user,username,surname,email,phone) VALUES (true,'root','password','root','leaf','root@root.com','600000000');");
			
			for(int i = 0; i < 20; i++) {
				
				//Generate a new user and add it to db
				named = names[ran.nextInt(10)] + letters[ran.nextInt(letters.length)];
				passd = new String();
				usernamed = usernames[ran.nextInt(usernames.length)]
						  + letters[ran.nextInt(letters.length)]
						  + letters[ran.nextInt(letters.length)]
						  + letters[ran.nextInt(letters.length)];
				surnamed = usernames[ran.nextInt(usernames.length)] + names[ran.nextInt(10)];
				emaild = new String();
				phoned = ran.nextInt(max-min) + min;
				
				for(int n = 0; n < 5; n++) {
					passd = passd + letters[ran.nextInt(letters.length)];
				}
				
				for(int n = 0; n < 5; n++) {
					emaild = emaild + letters[ran.nextInt(letters.length)];
				}
				
				emaild = emaild +"@gmail.com";
				
				st.execute(
						"INSERT INTO users (isAdmin,name_user,password_user,username,surname,email,phone) VALUES (false,'"+named+"','"+passd+"','"+usernamed+"','"+surnamed+"','"+emaild+"','"+phoned+"');"
						);
			}
			
			// Insert data in event's table
			
			for(int i = 0; i < 3; i++) {
				dated = "2023-"+(Integer.toString(ran.nextInt(12)+1))+"-"+(Integer.toString(ran.nextInt(27)+1));
				photod = "assets/public/events/"+url_events[i];
				st.execute(
						"INSERT INTO event (category,fecha,city,name_event,sala,photo_path) VALUES ('"+typed+"','"+dated+"','"+cityd[i]+"','"+events_name[i]+"','"+salad[i]+"','"+photod+"');"
						);
			}
			
			// Create ticket script...
			for(int i = 0; i < 20; i++) {
				priced = ran.nextInt(80)+20;
				user_id = ran.nextInt(20)+1;
				event_id = ran.nextInt(3)+1;
				code = ran.nextInt(100);
				
				st.execute(
						"INSERT INTO tickets (type,price, code,Users_id,Event_id) VALUES ('"+type+"','"+priced+"','"+code+"','"+user_id+"','"+event_id+"');"
						);
			}
			
			st.execute("INSERT INTO chats (user1_id, user2_id) VALUES ('"+1+"','"+2+"');");
			
			for(int i = 0; i < messages.length; i++) {
				st.execute(	"INSERT INTO message (message, chat, sendBy) VALUES ('"+messages[i]+"','"+1+"','"+(1+i%2)+"');");
			}

			conn.commit();
			System.out.println("query was successfull");
			conn.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
