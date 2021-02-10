package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

    public void settings() {
        size(500, 500);
        cx = width / 2;
        cy = height / 2;        
    }

    int mode = 0;

    float cx;
    float cy;

    public void keyPressed() {
        // the value of mode will be the number of the 
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(HSB);
    }

    public void draw() {
        background(0);
        noStroke();
        switch (mode)
        {
            case 0:
                fill(50, 255, 255);                    
                if (mouseX < cx)
                {
                    rect(0, 0, cx, height);
                }
                else
                {
                    rect(cx, 0, cx, height);
                }
                break;
            case 1:
                fill(50, 255, 255);                                    
                if (mouseX < cx && mouseY < cy)
                {
                    rect(0, 0, cx, cy);
                }
                else if (mouseX > cx && mouseY < cy)
                {
                    rect(cx, 0, cx, cy);
                }
                else if (mouseX < cx && mouseY > cy)
                {
                    rect(0, cy, cx, cy);
                }
                else
                {
                    rect(cx, cy, cx, cy);
                }
                break;
            case 2:
            {
                int numRects = (int)(mouseX / 10.0f);
                float w = width / (float) numRects;
                float cgap = 255 / (float) numRects;
                for(int i = 0 ; i < numRects ; i ++)
                {
                    fill(i * cgap, 255, 255);
                    rect(i * w, 0, w, height);
                }
                break;
            }
            case 3:
            {
                int numCircles = (int)(mouseX / 10.0f);
                float w = width / (float) numCircles;
                float cgap = 255 / (float) numCircles;
                for(int i = 0 ; i < numCircles ; i ++)
                {
                    fill(cgap * i, 255, 255);
                    ellipse(w / 2 + (i * w), cy, w, w);
                }
                break;
            }
            case 4:{
                int i, shiftHeight = 0;
                int numRects = 10;
                float w = width / (float) numRects;
                float cgap = 255 / (float) numRects;

                for(i = 0; i < numRects; i++){
                    fill(cgap * i, 255, 255);
                    rect(i * w, shiftHeight, w, height/10);
                    shiftHeight += 50;
                }
                break;
            }
            case 5:{
                int i;
                int shiftHeight = 0;
                int numRects = 10;
                float w = width / (float) numRects;
                float cgap = 255 / (float) numRects;

                for(i = 0; i < numRects; i++){
                    fill(cgap * i, 255, 255);
                    rect(i * w, shiftHeight, w, height/10);
                    rect(width-(i*w)-w, shiftHeight, w, height/10);
                    shiftHeight += 50;
                }
                break;
            }
            case 6:{
                int numCircles = 10;
                int shiftHeight = 0;
                float cgap = 255 / (float) numCircles;

                for(int i=0; i<numCircles; i++){
                    fill(cgap * i, 255, 255);
                    ellipse(width/2, height/2, width+shiftHeight, height+shiftHeight);
                    shiftHeight -=50;
                }
                break;
            }
            case 7:{
                int line=5;
                float r=100, a, inc = TWO_PI/(float)line;                
                for(int i=0;i<line;i++){
                    a = inc*i;
                    stroke(100,255,255);
                    line(cx,cy,cx+(sin(a)*r),cy+(cos(a)*r));
                }
                break;
            }
        }
    }
}
