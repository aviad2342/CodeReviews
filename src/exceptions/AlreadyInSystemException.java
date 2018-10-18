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
public class AlreadyInSystemException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public AlreadyInSystemException() {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); // error sound
        JOptionPane.showMessageDialog(null, "This user is already in system", "Login Failed Error", JOptionPane.ERROR_MESSAGE);
    }
}
