package cybagenetpackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import DatabaseConnection.MyConnection;

public class SessionCounter implements HttpSessionListener
{
        public static int count;
    
        public static int hitcount;
        
        static
        {
        	
            MyConnection c=new MyConnection();
    		Connection con;
    		try 
    		{
    			con = c.connect();
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
              
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from session");
                int c1=0,c2=0;
                while(rs.next())
                {
                	c1=rs.getInt(1);
                	c2=rs.getInt(2);
                }
                System.out.println(c1);
                System.out.println(c2);
                count=c1;
                hitcount=c2;
                
                System.out.println("inserted successfully hit count and session count");
                
    		}
    		catch(Exception e)
    		{
    			System.out.println(e);
    		}
        	
        }
        public SessionCounter()
        {
        }

        public void sessionCreated(HttpSessionEvent arg0)
        {
        		System.out.println(count);
        		System.out.println(hitcount);
                count++;
                hitcount++;
                System.out.println("Session Created sessions active :"+count+" hitcount :"+hitcount);
                ServletContext sContext = arg0.getSession().getServletContext();
                MyConnection c=new MyConnection();
        		Connection con;
                try {
					con = c.connect();
					String query="insert into session values(? ,?)";
					PreparedStatement pst=null;
					pst = con.prepareStatement(query);
					pst.setInt(1, count);
					pst.setInt(2, hitcount);
				
					  System.out.println("updated successfully hit count and session count ="+	pst.executeUpdate());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		
        		PreparedStatement pst = null;
                
                synchronized (sContext)
                {
                        sContext.setAttribute("sessionCount", new Integer(count));
                }
        }

        public void sessionDestroyed(HttpSessionEvent arg0)
        {
                count--;
                ServletContext sContext = arg0.getSession().getServletContext();
                
                
                MyConnection c=new MyConnection();
        		Connection con;
                try {
					con = c.connect();
					String query="update session set sessioncount= ?";
					PreparedStatement pst=null;
					pst = con.prepareStatement(query);
					System.out.println("Current session count after session destroyed"+count);
					pst.setInt(1, count);
					
					pst.executeUpdate();
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
                synchronized (sContext)
                {
                        sContext.setAttribute("sessionCount", new Integer(count));
                }
        }
}


