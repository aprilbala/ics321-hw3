package guessNumber;

import java.sql.Timestamp;

/**
 *
 * @author April-Angela Bala (aaibala)
 * @date   03-09-2018
 * ICS 321 - Database Systems I
 * Assignment 3: Web Application using JSF & JDBC
 */
public class Attempt {
    
    public Timestamp atime;    
    public Integer usernum;
    public Integer actualnum;
    
    /** Creates a new instance of UserNumberBean */
    public Attempt() {
        
    }
    
    public Timestamp getAtime() {
        return atime;
    }

    public void setAtime(Timestamp atime) {
        this.atime = atime;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public int getActualnum() {
        return actualnum;
    }

    public void setActualnum(int actualnum) {
        this.actualnum = actualnum;
    }       
}
