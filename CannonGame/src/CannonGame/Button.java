package CannonGame;

import static CannonGame.CannonGame.applet;

class Button{
    float x, y, w, h;

    Button(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;        
    }

    void show(){
        applet.rectMode(3);//center mode
        applet.rect(x, y, w, h);
    }

    boolean isHovered(){
        return (applet.mouseX > this.x - w/2 && applet.mouseX < this.x + w/2 &&
        applet.mouseY > this.y + h/2 && applet.mouseY < this.y - h/2);
    }

    void onClick(){
        //do stuff    
    }
}
