package ie.tudublin;
import processing.core.PApplet;

public class BugZap extends PApplet{
    
    public void settings(){
        size(1200, 720);
    }

    float playerX, playerY, playerWidth, playerHeight, bugX, bugY, bugWidth, bugHeight, lazerX, lazerY;
    int lazerCD;
    boolean check = false;
    
    public void setup() {
        playerX = width / 2;
        playerY = 670;
        playerWidth = 80;
        playerHeight = 80;
        bugX = width / 2;
        bugY = 60;
        bugWidth = 100;
        bugHeight = 100;
        smooth(); // ANTIALIASING
    }

    public void drawPlayer(float x, float y, float w, float h){	
        if(lazerCD != 20 && check == true){
            stroke(255, 0, 0);
            line(playerX, 650, playerX, 0);
            ++lazerCD;
        }else{
            check = false;
        }
        fill(0);
        stroke(0, 255, 0);
        ellipse(x, y, w, h);
    }

    public void drawBug(float x, float y, float w, float h){
        ellipse(x, y, w, h);
    }

    public void draw(){
        background(0);
        drawPlayer(playerX, playerY, playerWidth, playerHeight);
        drawBug(bugX, bugY, bugWidth, bugWidth);
    }

    public void keyPressed(){
		if(keyCode == LEFT){
            // System.out.println("Left arrow pressed");
            playerX -= 20;
            while(playerX <= 30){
                playerX += 20;
            }
		}
		if(keyCode == RIGHT){
            // System.out.println("Right arrow pressed");
            playerX += 20;
            while(playerX >= 1170){
                playerX -= 20;
            }
        }
		if(key == ' '){
            // System.out.println("SPACE key pressed");
            lazerCD = 0;
            check = true;
        }
	}
}
