package guessNumber;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

/**
 *
 * @author April-Angela Bala (aaibala)
 * @date   03-09-2018
 * ICS 321 - Database Systems I
 * Assignment 3: Web Application using JSF & JDBC
 */
@SessionScoped
@ManagedBean(name="Attempt")
public class AttemptBean implements Serializable{
    
    @Resource(name="jdbc:derby://localhost:1527/ics321")
    private DataSource ds;
       
    
    public List<Attempt> getAttemptList() throws SQLException, ClassNotFoundException {
        
        String url = "jdbc:derby://localhost:1527/ics321";
        String userName = "DBUSER";
        String password = "ics321";
        
        if (ds == null) {
            throw new SQLException("Can't get data source");
        }
        
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection (url, userName, password);
        
        List<Attempt> list = new ArrayList<Attempt>();
        String sqlquery;
        sqlquery = "SELECT ATIME, USERNUM, ACTUALNUM FROM DBUSER.ATTEMPTS ORDER BY ATIME DESC FETCH FIRST 20 ROWS ONLY";
        
        Statement s;                 
        s = conn.createStatement();
        ResultSet result = s.executeQuery(sqlquery);
                
        while (result.next()) {
            Attempt att = new Attempt();
            
            att.setAtime(result.getTimestamp("atime"));
            att.setUsernum(result.getInt("usernum"));
            att.setActualnum(result.getInt("actualnum"));
                        
            // Stores all data into a list.
            list.add(att);                        
        }       
                
        return list;        

    }
     
    /** Creates a new instance of AttemptBean */
    public AttemptBean() {
        
    }    
    
}
