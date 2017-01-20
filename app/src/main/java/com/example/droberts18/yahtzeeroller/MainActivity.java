package com.example.droberts18.yahtzeeroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /**
     * Creates the basic screen
     * @param savedInstanceState    Necessary to create screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Rolls each of the dices when "ROLL" button is clicked
     * @param v     The View in which the button was clicked
     */
    public void rollDice(View v){
        int[] ivs = {R.id.diceSlot1, R.id.diceSlot2, R.id.diceSlot3,
                            R.id.diceSlot4, R.id.diceSlot5};
        // Creates 6 new rolls
        Roll[] rolls = new Roll[6];
        for(int i = 0; i < 5; i++){
            ImageView imgV = (ImageView)findViewById(ivs[i]);
            // Assigns an ImageView to each roll
            rolls[i] = new Roll(imgV, this);
        }

        // Starts threads
        for(int i = 0; i < 6; i++){
            Thread t = new Thread(rolls[i]);
            t.start();

            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex) {;}
        }
    }
}
