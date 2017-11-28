package com.cards.shvedko.Helpers;

import javax.sound.sampled.*;
import java.io.File;
import java.util.UUID;

public class AudioCapturing {
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;

    public static final String defaultFolder = "C:/cards/audio/";
    public String name;

    public AudioCapturing() {
    }

    //This method captures audio input from a
    // microphone and saves it in an audio file.
    public void captureAudio() {
        try {
            //Get things set up for capture
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

            //Create a thread to capture the microphone
            // data into an audio file and start the
            // thread running.  It will run until the
            // Stop button is clicked.  This method
            // will return after starting the thread.
            new CaptureThread().start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void stopCapture() {
        targetDataLine.stop();
        targetDataLine.close();

    }

    /**
     * This method creates and returns an
     * AudioFormat object for a given set of format
     * parameters.  If these parameters don't work
     * well for you, try some of the other
     * allowable parameter values, which are shown
     * in comments following the declarations.
     *
     * @return
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = false;
        //true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    /**
     * Inner class to capture data from microphone
     * and write it to an output audio file.
     */

    class CaptureThread extends Thread {
        public void run() {
            AudioFileFormat.Type fileType;
            File audioFile;

            //Set the file type and the file extension
            // based on the selected radio button.
            fileType = AudioFileFormat.Type.WAVE;
            name = UUID.randomUUID().toString();
            audioFile = new File( defaultFolder + name +".wav");

            try {
                targetDataLine.open(audioFormat);
                targetDataLine.start();
                AudioSystem.write(new AudioInputStream(targetDataLine), fileType, audioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}