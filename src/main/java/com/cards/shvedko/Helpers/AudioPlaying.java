package com.cards.shvedko.Helpers;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;
import java.util.UUID;

public class AudioPlaying {
    public static synchronized void playSound(final String fileName) {
        while(!Thread.interrupted()){
            Thread.currentThread().interrupt();
        }

        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    File file = new File(AudioCapturing.defaultFolder + fileName);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }


            }
        }).start();
    }
}