/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.proiectssatr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author NoemiNyaradi
 */
public class AirlineReservationSystem {
    private final BookingService bookingService;

    public AirlineReservationSystem(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    public void startServer(){
        
        try{
        
            ServerSocket ss = new ServerSocket(4050);

            while(true){
                System.out.println("Astept conexiune de la client...");
                Socket s = ss.accept(); //metoda blocanta
                System.out.println("Clientul s-a conectat!");
                //...... 
                BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
                //......
                String line = fluxIn.readLine();
                
                if (line.equals("GET_RESERVATIONS")) {
                    List<Booking> allBookings = bookingService.getAllBookings();
                    allBookings.stream()
                            .map(Booking::toString)
                            .forEach(fluxOut::println);
                } else {
                    List<String> bookingParams = new ArrayList();
                            
                    while (!line.equals("END")) {
                        bookingParams.add(line);
                        line = fluxIn.readLine();
                    }
                    Booking booking = new Booking(bookingParams.get(0),bookingParams.get(1),
                            bookingParams.get(2),bookingParams.get(3),Integer.valueOf(bookingParams.get(4)));

                    String responseMessage = bookingService.submitBooking(booking);
                    fluxOut.println(responseMessage);
                }
                

                s.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AirlineReservationSystem netCon = new AirlineReservationSystem(new BookingService(new DBAccess()));
        netCon.startServer();
    }
}
