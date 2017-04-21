package Client.Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
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
		
		HttpSession session = request.getSession();	//active sessions are checked
		if(session.getAttribute("admin")==null){	//if client session does not exist 
												//link client login page
		}
		else{	//if client session exists
			PrintWriter out = response.getWriter();	
			Connection con=null;	//connection to the DB is declared 
			Statement st=null;	//statement to be sent to query the DB is declared
			ResultSet rs=null;	//resultset to store values returned by the DB is declared
			try{
				String phone=request.getParameter("phone");	//phone no. entered by user on the page is stored in a string
				String add_lin1=request.getParameter("add_lin1"); //address line 1 entered by user on the page is stored in a string
				String add_lin2=request.getParameter("add_lin2"); //address line 2 entered by user on the page is stored in a string
				String state=request.getParameter("state"); //state entered by user on the page is stored in a string
				String pin=request.getParameter("pin"); //PIN no entered by user on the page is stored in a string
				Class.forName("com.mysql.jdbc.Driver");	//returns com.mysql.jdbc.driver class needed to establish connection with DB
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seo","root","MySQL");	//connection is initialized, port is given, DB name,password are given
				st= con.createStatement();	//statement is initialized to be queried with the DB
				rs = st.executeQuery("select IFNULL(max(pid),0) from client_d where id='"+session.getAttribute("client")+"'");	//based on the ID of the client who has logged in, MAX(pid) taken, if there is no pID available 0 is taken
				rs.next();	//the fisrt row of the resultset is selected
				int pid=rs.getInt(0)+1;	//whatver the PID is found, 1 is added to it to send in with the new record
				int i=st.executeUpdate("insert into client_d(id,pid,phone,add_lin1,add_lin2,state,pin) values ('"+session.getAttribute("client")+"','"+pid+"','"+phone+"','"+add_lin1+"','"+add_lin2+"','"+state+"','"+pin+"')");	//int i is used to execute statement, uploading client details to the DB 
				if(i>0)	//int i will be greater then 0 if the update to the DB was successful
				{
					out.println("client details registered.");
				}
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
			finally{
				try { if (rs != null) rs.close(); } catch (Exception e) {};	//resultset is closed
				try { if (st != null) st.close(); } catch (Exception e) {};	//statement is closed
				try { if (con != null) con.close(); } catch (Exception e) {};	//connection is closed
				
			}
		}
	}

}
