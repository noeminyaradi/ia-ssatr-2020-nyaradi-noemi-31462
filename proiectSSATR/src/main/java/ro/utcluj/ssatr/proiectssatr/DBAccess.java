/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.proiectssatr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noemi
 */
public class DBAccess {
    private Connection conn;
    
    public DBAccess() throws ClassNotFoundException, SQLException {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ars_db;create=false","APP","APP");
    }
    
    public List<Booking> fetchAllBookings() throws SQLException {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM BOOKING");
        
        List<Booking> bookings = new ArrayList<>();
        
        while(rs.next()){
            bookings.add(new Booking(rs.getString("cnp"), rs.getString("name"), 
                    rs.getString("destination"), rs.getString("class_type"), 
                    rs.getInt("seat_number")));
        }
        
        return bookings;
    }
    
    public void insertBooking(Booking booking) throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate(MessageFormat.format(
                "INSERT INTO BOOKING VALUES(''{0}'', ''{1}'', ''{2}'', ''{3}'', {4})", 
                booking.getCnp(), booking.getName(), booking.getDestinationName(), 
                booking.getClassType(), booking.getSeatNumber()));
        
        s.close();
    }
    
    public Booking getBookingByCnp(String cnp) throws SQLException {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM BOOKING WHERE CNP='"+cnp+"'");
        if(rs.next()){
            return new Booking(rs.getString("cnp"), rs.getString("name"), rs.getString("destination"), 
                    rs.getString("class_type"), rs.getInt("seat_number"));
        }else{
            return null;
        } 
    }
}
