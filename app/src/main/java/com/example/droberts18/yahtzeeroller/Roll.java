package com.example.droberts18.yahtzeeroller;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by droberts18 on 1/19/2017.
 */

public class Roll implements Runnable {
    private ImageView iv;
    private Activity MainActivity;

    /**
     * Creates a roll
     * @param iv    The corresponding ImageView for the roll
     * @param MainActivity      The Activity in which the roll takes place
     */
    public Roll(ImageView iv, Activity MainActivity) {
        this.iv = iv;
        this.MainActivity = MainActivity;
    }

    /**
     * Checks to see if a dice should stop rolling (when a random number is equal to 50)
     * @return  If the dice should stop rolling or not
     */
    private boolean stop() {
        Random rand = new Random();
        int stopNum = rand.nextInt(50) + 1;

        if (stopNum == 50)
            return true;

        return false;
    }

    // Each thread runs this, generates a random dice side image and displays it in dice slots
    public void run() {
        final int[] imageNames = {R.drawable.one, R.drawable.two, R.drawable.three,
                R.drawable.four, R.drawable.five, R.drawable.six};

        do {
            Random rand = new Random();
            final int randRollNum = rand.nextInt(6);

            try {
                // Runs on main thread to refresh UI
                MainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        iv.setImageResource(imageNames[randRollNum]);
                    }
                });

                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ;
            }
        } while (stop() == false);
    }
}

