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
public class SpacesException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public SpacesException(String label) {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); //sound error
        JOptionPane.showMessageDialog(null, "The " + "'" + label + "'" + " contains spaces!", "Spaces Error", JOptionPane.ERROR_MESSAGE);
    }
}
