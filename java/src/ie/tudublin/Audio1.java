package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {

    Minim minim; // Connect to minim library
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples

    float[] lerpedBuffer;

    public void settings()
    {
        size(512, 512);
    }

    float y = 200;
    float lerpedY = y;

    public void setup()
    {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("kkk.mp3", width);
        ap.play();
        //ab = ai.mix; // Connect the buffer to mic
        ab = ap.mix; // Connect the buffer to file
        colorMode(HSB);
        lerpedBuffer = new float[width];
    }

    float lerpedAverage = 0;

    public void draw()
    {
        background(0);
        stroke(255);
        float halfHeight = height/2;
        float average = 0;
        float sum = 0;

        for(int i=0;i<ab.size();i++){ // .size() gets the size on an array buffer
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

            line(i, halfHeight - (lerpedBuffer[i] * halfHeight * 2), halfHeight + (lerpedBuffer[i] * halfHeight * 2), i);

            sum += abs(ab.get(i));
        }

        average = sum / (float) ab.size();
        // calculate the average amplitude
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);
        //ellipse(width/4, 100, average * 400, average * 400);
        //ellipse(width/2, 100, 50 + (lerpedAverage * 400), 50 + (lerpedAverage * 400));

        // lerp(); example
        /*
        ellipse(200, y, 30, 30);
        ellipse(300, lerpedY, 30, 30);
        y += random(-10, 10);
        lerpedY = lerp(lerpedY, y, 0.1f);
        */
    }   
}