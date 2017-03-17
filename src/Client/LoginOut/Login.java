package Client.LoginOut;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		PrintWriter out = response.getWriter();
		Connection con=null;	//connection to the DB is declared
		Statement st=null;	//statement to be sent to query the DB is declared
		ResultSet rs=null;	//resultset to store values returned by the DB is declared
		try{
			String email=request.getParameter("email");	//email entered by user on the page is stored in a string
			String pwd=request.getParameter("pwd");	//password entered by user on the page is stored in a string
			Class.forName("com.mysql.jdbc.Driver");	//returns com.mysql.jdbc.driver class needed to establish connection with DB
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seo","root","MySQL");	//connection is initialized, port is given, DB name,password are given
			st= con.createStatement();	//statement is initialized to be queried with the DB
			rs=st.executeQuery("select * from client_m where email='"+email+"'");	//query is executed and the results are stored in the resultset, which includes the entry which has the email entered by user
			if(rs.next()){	//if an entry is present in the resultset
                if (rs.getString(1).equals(pwd)) {	//if the password in the entry is the same as the one entered by user
                	if(rs.getString(2).equals("T")){	//if the column 3 return "T", user has been blocked by admin
                		out.println("blocked by admin, contact admin at admin@dash.com");
                	}
                	else{	//column returned "F", user is not blocked
                		HttpSession session = request.getSession();	//session for the user will be created that will keep the user logged in
                		session.setAttribute("client",rs.getInt(3));	//an attribute for the session is created and is given the value of the ID of the user from the DB
                		if(rs.getString(4).equals("F")){	//if the column 4 return "F", user has not authenticated email ID
                			out.println("please authenticate email address to receive upadates on email");
                		}
                		response.sendRedirect("index-1.html");	//user is directed to the next page
                	}
                  }
                else {
                    out.println( "Password does not match with the password during registeration... Please re-login with correct password");
                }
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
