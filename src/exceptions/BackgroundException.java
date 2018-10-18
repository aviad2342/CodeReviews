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
public class BackgroundException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public BackgroundException() {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); // error sound
        JOptionPane.showMessageDialog(null, "Can't find the background file!!", "Background Error", JOptionPane.ERROR_MESSAGE);

    }
}
