package Admin.ManageUsers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClientB
 */
@WebServlet("/BlockC")
public class BlockC extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockC() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session = request.getSession();	//active sessions are checked
		if(session.getAttribute("admin")==null){	//if admin session does not exist 
												//link admin login page
		}
		else{	//if admin session exists
			PrintWriter out = response.getWriter();
			Connection con=null;	//connection to the DB is declared
			Statement st=null;	//statement to be sent to query the DB is declared
			try{
				String email=request.getParameter("email");	//email entered by user on the page is stored in a string 
				Class.forName("com.mysql.jdbc.Driver");	//returns com.mysql.jdbc.driver class needed to establish connection with DB
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seo","root","MySQL");	//connection is initialized, port is given, DB name,password are given
				st= con.createStatement();	//statement is initialized to be queried with the DB
				int i=st.executeUpdate("update client_m set block = 'T' where email = '"+email+"'");	//int i is used to execute statement, changing the block status from false to true i.e., "T" in the DB
				if(i>0)	//int i will be greater then 0 if the update to the DB was successful
				{
					out.println(" client blocked.");
				}
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
			finally{
				try { if (st != null) st.close(); } catch (Exception e) {};	//statement is closed
				try { if (con != null) con.close(); } catch (Exception e) {};	//connection is closed
			}
		}
		
	}

}
