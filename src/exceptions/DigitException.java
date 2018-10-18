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
public class DigitException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public DigitException() {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); // error sound
        JOptionPane.showMessageDialog(null, "The Programmer's Id number is less then 9 digits", "Digit Error", JOptionPane.ERROR_MESSAGE);
    }
}