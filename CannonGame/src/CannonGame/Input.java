package CannonGame;

import static CannonGame.CannonGame.applet;

public class Input {
    // instead of having all input in main this class is used just to make the main
    // class cleaner
    public static void keyPressed() {
        if (applet.key == ' ') {// space
            applet.t.shoot();
        }
        if (applet.key == 'w') {
            applet.t.barrelDirection.rotate(-applet.PI / 60);
        }
        if (applet.key == 's') {
            applet.t.barrelDirection.rotate(applet.PI / 60);
        }
    }

    public static void mouseClicked() {
        // applet.println("mouse clicked");
    }

}