/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.proiectssatr;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author NoemiNyaradi
 */
public class BookingService {
    private DBAccess db;

    public BookingService(DBAccess db) {
        this.db = db;
    }
    
    public List<Booking> getAllBookings() throws SQLException {
        return db.fetchAllBookings();
    }
    
    public String submitBooking(Booking booking) throws SQLException {
        Booking existingBooking = db.getBookingByCnp(booking.getCnp());
        String responseMessage;
        
        if (existingBooking != null) {
            responseMessage = "User already has a destination!";
        } else {
            db.insertBooking(booking);  
            responseMessage = "Booking created!";
        }
        
        return responseMessage;
    }
    
    
}
