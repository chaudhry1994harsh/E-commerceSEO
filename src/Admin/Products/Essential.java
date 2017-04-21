package Admin.Products;

import java.io.IOException;
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
 * Servlet implementation class Essential
 */
@WebServlet("/Essential")
public class Essential extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Essential() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();	//active sessions are checked
		if(session.getAttribute("admin")==null){	//if admin session does not exist
												//link admin login page
		}
		else{	//if admin session exists
			Connection con=null;	//connection to the DB is declared
			Statement st=null;	//statement to be sent to query the DB is declared
			ResultSet rs=null;	//resultset to store values returned by the DB is declared
			try{
				String descrip=request.getParameter("descrip");	//description is entered by user on the page is stored in a string
				String name=request.getParameter("name");	//name of product entered by user on the page is stored in a string
				String brand=request.getParameter("brand");	//brand entered by user on the page is stored in a string
				String mtitle=request.getParameter("mtitle");	//meta title for SEO is entered by user on the page is stored in a string
				String mdescrip=request.getParameter("mdescrip");	//meta description for SEO is entered by user on the page is stored in a string
				String mkeyword=request.getParameter("mkeyword");	//meta keyword for SEO is entered by user on the page is stored in a string
				String catg=request.getParameter("catg");	//category of the product is entered by user on the page is stored in a string
				Class.forName("com.mysql.jdbc.Driver");	//returns com.mysql.jdbc.driver class needed to establish connection with DB
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seo","root","MySQL");	//connection is initialized, port is given, DB name,password are given
				st= con.createStatement();	//statement is initialized to be queried with the DB
			
				int i=st.executeUpdate("insert into client_m(name,brand,descrip,mtitle,mdescrip,mkeyword,catg) values ('"+name+"','"+brand+"','"+descrip+"','"+mtitle+"','"+mdescrip+"','"+mkeyword+"','"+catg+"')");	//int i is used to execute statement, uploading email, name and password of the new user who is registering to the DB 
				if(i>0)	//int i will be greater then 0 if the update to the DB was successful 
				{
					//need to send product data to next page to enter details somehow 
					//rs=st.executeQuery("select * from client_m where email='"+email+"'");	//query is executed and the results are stored in the resultset, which includes the all the data about the newly registered user account
					//rs.next();	//the first entry in the resultset is selected
					response.sendRedirect("index-1.html");	//user is directed to the next page
				}
			}
			catch(Exception se){
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
