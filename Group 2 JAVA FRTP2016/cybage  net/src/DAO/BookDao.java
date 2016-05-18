package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;



import DatabaseConnection.MyConnection;


public class BookDao {
		
		
		
		//method to add new book from database
		public void addNewBook(int id,String bookname,String authorname)
		{
			MyConnection c=new MyConnection();
			Connection con;
	
			try 
			{
				con = c.connect();
			
			
			PreparedStatement pst = null;
			
		
			
			String insertQuery = "INSERT INTO BOOK"	+ "(BookId,BookName,AuthorName) VALUES" + "(?,?,?)";
			pst = con.prepareStatement(insertQuery);
			
			pst.setInt(1, id);
			pst.setString( 2, bookname);
			pst.setString( 3, authorname);
			
			System.out.println(pst.executeUpdate());
			System.out.println("Book Inserted...");

			
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//method to delete book from database
		public void deleteBook(int n)
		{
			try
			{
			MyConnection c=new MyConnection();
			Connection con;
		con = c.connect();
       
        
      
        PreparedStatement pst = null;
        String insertQuery = "delete from book where BookId= ?";
       	pst = con.prepareStatement(insertQuery);
       	
       	pst.setInt(1,n);
       	System.out.println(pst.executeUpdate());
		System.out.println("Book Deleted..");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
}
