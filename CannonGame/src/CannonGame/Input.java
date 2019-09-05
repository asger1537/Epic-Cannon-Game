package CannonGame;

import static CannonGame.CannonGame.applet;

public class Input{
    //instead of having all input in main this class is used just to make the main class cleaner
    public static void keyPressed(){
        if (applet.key == ' '){//space
            applet.c.shoot();
        }
    }
    
    public static void mouseClicked(){
        //applet.println("mouse clicked");
    }    

}