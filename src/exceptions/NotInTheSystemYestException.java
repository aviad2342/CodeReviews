/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class NotInTheSystemYestException extends Exception{
        //***************************************** Constructor *********************************************
    
     @SuppressWarnings("unused")
	public NotInTheSystemYestException(String label){
         SoundClass sound = new SoundClass("/sounds/errorSound.wav"); //sound error
        JOptionPane.showMessageDialog(null, "Sorry no " + label  + " in the system yet!", "Spaces Error", JOptionPane.ERROR_MESSAGE);
    }
}
