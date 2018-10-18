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
public class SpaceStartException extends Exception {
//***************************************** Constructor *********************************************

    @SuppressWarnings("unused")
	public SpaceStartException(String label) {
        SoundClass sound = new SoundClass("/sounds/errorSound.wav"); // sound error
        JOptionPane.showMessageDialog(null, "The " + "'" + label + "'" + " starts with spaces!", "Space Error", JOptionPane.ERROR_MESSAGE);
    }
}
