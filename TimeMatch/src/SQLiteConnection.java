import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SQLiteConnection  {

	Connection conn=null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:TimeMatch.sqlite");			
			
			//JOptionPane.showMessageDialog(null, "Connected");
			
			return conn;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Wrong Entry, Try Again");
			return null;
		}
	}
}
