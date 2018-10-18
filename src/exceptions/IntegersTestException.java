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
public class IntegersTestException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public IntegersTestException(String label) {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + label + " contains integers!", "Integers Error", JOptionPane.ERROR_MESSAGE);
    }
}
