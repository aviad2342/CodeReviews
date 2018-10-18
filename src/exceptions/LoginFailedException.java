/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

//***************************************** Imports *********************************************
import utils.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Aviad
 */
@SuppressWarnings("serial")
public class LoginFailedException extends Exception{
    //***************************************** Constructor *********************************************
    
     @SuppressWarnings("unused")
	public LoginFailedException(){
         
         SoundClass sound = new SoundClass("/sounds/errorSound.wav"); //sound error
        JOptionPane.showMessageDialog(null, "Login failed", "Login Failed Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
}
