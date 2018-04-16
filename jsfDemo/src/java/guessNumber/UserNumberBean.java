package guessNumber;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Date;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author April-Angela Bala (aaibala)
 * @date   03-09-2018
 * ICS 321 - Database Systems I
 * Assignment 3: Web Application using JSF & JDBC
 */
@ManagedBean(name="UserNumberBean")
@SessionScoped
public class UserNumberBean implements Serializable {
    
    public Timestamp atime;
    Integer randomInt;
    Integer userNumber;
    String response;
    
    String sqlquery = "INSERT INTO DBUSER.ATTEMPTS VALUES (?, ?, ?)";
        
    public String getResponse() throws SQLException, ClassNotFoundException {
        
        String url = "jdbc:derby://localhost:1527/ics321";
        String userName = "DBUSER";
        String password = "ics321";
       
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.getConnection(url, userName, password); 
                
        try {
            PreparedStatement ps = conn.prepareStatement(sqlquery);
                                
            atime = new Timestamp(new Date().getTime());
            ps.setTimestamp(1, atime);
            ps.setInt(2, userNumber);
            ps.setInt(3, randomInt);
            ps.execute(); 
      
        } catch (SQLException e) {
            
        } finally {
            conn.close();
        }
                
        if ((userNumber != null) && (userNumber.compareTo(randomInt)) == 0) {
            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

            return "Yay! You got it!";        
        } else {
            return "<p>Sorry, " + userNumber + " isn't it.</p>" + "<p>Guess again...</p>";
        }            
    }
    

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    /** Creates a new instance of UserNumberBean */
    public UserNumberBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt(10);
        System.out.println("Duke's number: " + randomInt);
    }

}
