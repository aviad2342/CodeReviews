/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;
//***************************************** Imports *********************************************
import javax.swing.JOptionPane;
import utils.*;

/**
 *
 * @author Aviad
 */
@SuppressWarnings("serial")
public class PassNotMatchException extends Exception {
    //***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public PassNotMatchException() {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); //sound error
        JOptionPane.showMessageDialog(null, "The UserName and Passwords do not matched!", "Login Failed Error", JOptionPane.ERROR_MESSAGE);
    }
}
