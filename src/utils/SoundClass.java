/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * allows to play sound files.
 * @author Aviad
 */
public class SoundClass {

    //******************************************** Constructor *******************************************
    public SoundClass(String fileName) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(fileName));  // create AudioInputStream variable
            Clip clip = AudioSystem.getClip();  // create Clip variable
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Sound File", JOptionPane.ERROR_MESSAGE);
        }

    }
}
