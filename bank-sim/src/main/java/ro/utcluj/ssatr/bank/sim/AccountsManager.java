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
public class AccountsManager {
    private BankAccount[] bankAccounts = new BankAccount[20];
    
    public void addAcount(BankAccount account) {
        for (int i = 0; i < bankAccounts.length; i++) {
            
            if (bankAccounts[i] == null) {
                bankAccounts[i] = account;
                System.out.println("New account has been added successfully!");
                return;
            }
        }
    }
    
    public int getTotalBalance() {
        int sum = 0;
        for (BankAccount account : bankAccounts) {
            
            if(account != null) {
                sum += account.getBalance();
            } else {
                break;
            }
            
        }
        return sum;
    }
    
    public String getAllAccountsDetails(){
       String all = "";
       for (BankAccount account : bankAccounts) { 
           
           if(account != null){
            String line = "Account Name = "+account.getName()+"  Owner = "+account.getOwner()+"  Balance = "+account.getBalance()+"\n";
            all = all + line;
           }
       }
       return all;
    }
    
    public boolean transfer(String fromOwnerName, String toOwnerName, int amount) {
        boolean transferStatus = false;
        BankAccount fromAccount = getAccountForOwner(fromOwnerName);
        
        if (fromAccount != null) {
            int actualBalance = fromAccount.getBalance();
            
            if (actualBalance >= amount) {
                fromAccount.subtractAmount(amount);
                BankAccount toAccount = getAccountForOwner(toOwnerName);
                
                if (toAccount != null) {
                    toAccount.addAmount(amount);
                    transferStatus = true;
                }
            }
        }
        return transferStatus;
    }
    
    private BankAccount getAccountForOwner(String ownerName) {
        for (BankAccount account : bankAccounts) {
            
            if (account != null && account.getOwner().equals(ownerName)){
                return account;
            }
        }
        return null;
    }
}
