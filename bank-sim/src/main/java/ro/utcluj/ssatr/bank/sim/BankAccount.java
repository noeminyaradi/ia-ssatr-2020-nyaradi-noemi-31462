/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.bank.sim;

/**
 *
 * @author Noemi
 */
public class BankAccount {
    private String owner;
    private int balance;
    private String name;
    
    BankAccount(String owner, int balance, String name) {
        this.owner = owner;
        this.balance = balance;
        this.name = name;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public String getName() {
        return name;
    }
    
    public void addAmount(int amount) {
        balance += amount;
    }
    
    public void subtractAmount(int amount) {
        balance -= amount;
    }
}
