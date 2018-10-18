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
public class MissingDataException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public MissingDataException() {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); //sound error
        JOptionPane.showMessageDialog(null, "You must fill all fields!", "Missing Fields Error", JOptionPane.ERROR_MESSAGE);
    }
}
