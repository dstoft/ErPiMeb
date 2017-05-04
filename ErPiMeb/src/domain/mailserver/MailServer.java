/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.mailserver;

/**
 *
 * @author AKT
 */
public class MailServer {
    
    public boolean validateEmail() {
        boolean emailValidated = false;
        // Send an email to the registered email.
        // If the sending-process creates an error, return false.
        return emailValidated;
    }
    
    public boolean emailOrderReceipt(String emailAddress) {
        // Send an email to emailAddress. If it fails, return false, otherwise return true.
        boolean emailSent = false;
        return emailSent;
    }
    
}
