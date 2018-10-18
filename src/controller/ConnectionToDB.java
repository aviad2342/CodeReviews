package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionToDB {
	
	private final static String cl = "org.gjt.mm.mysql.Driver";
	private final static String URL = "jdbc:mysql://db4free.net:3306/codesocietyshare";
	private final static String user="boaz100";
	private final static String pass="1lachman2";
	private static Connection conn=null;
	
	private static ConnectionToDB singleton = new ConnectionToDB( );
	   
	   /* A private Constructor prevents any other 
	    * class from instantiating.
	    */
	   private ConnectionToDB(){
	   
	   }
	   
	   /* Static 'instance' method */
	   public static ConnectionToDB getInstance() {
	      return singleton;
	   }
               
               public static Connection getConect()
               {
                   if(conn!=null)
                   {
                       return conn;
                   }
                   else
                   {
                    try {
//					new com.mysql.jdbc.Driver();
					Class.forName(cl);
	
					String connectionUrl = URL;
					String connectionUser = user;
					String connectionPassword = pass;
					conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                                            						
				} catch (Exception e) {
					e.printStackTrace();
				} 
                return conn;
                   }
               }
               
               public static void connectionClose()
               {
                try {
                    conn.close();
                    conn=null;
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionToDB.class.getName()).log(Level.SEVERE, null, ex);
                }
               }

}
