/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.proiectssatr;

/**
 *
 * @author NoemiNyaradi
 */
public class Booking {
    private String cnp;
    private String name;
    private String destinationName;
    private String classType;
    private int seatNumber;
    
    Booking(String cnp, String name, String destinationName,String classType, int seatNumber ) {
        this.cnp = cnp;
        this.name = name;
        this.destinationName = destinationName;
        this.classType = classType;
        this.seatNumber = seatNumber;
    }

    public String getCnp() {
        return cnp;
    }

    public String getName() {
        return name;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getClassType() {
        return classType;
    }

    public int getSeatNumber() {
        return seatNumber;
    } 
}
