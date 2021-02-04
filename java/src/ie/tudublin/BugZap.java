package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet{
    
    public void settings(){
        size(1200, 720);
    }

    float playerX, playerY, playerWidth, playerHeight, bugX, bugY, bugWidth, bugHeight, lazerX, lazerY;
    int lazerCD, lazerBeam, score = 0;
    boolean check = false;
    boolean menuCheck = false;
    
    public void setup() {
        playerX = width / 2;
        playerY = 670;
        playerWidth = 80;
        playerHeight = 80;
        bugX = random(1170);
        bugY = 60;
        bugWidth = 100;
        bugHeight = 100;
        smooth(); // ANTIALIASING
    }

    public void drawPlayer(float x, float y, float w, float h){	
        if(lazerCD != 20 && check == true){
            stroke(255, 0, 0);
            strokeWeight(2);
            line(playerX, playerY, mouseX, mouseY);
            ++lazerCD;
        }else if(lazerCD == 20){
            check = false;
        }
        fill(0);
        stroke(0, 255, 0);
        strokeWeight(1);
        ellipse(x, y, w, h);
    }

    public void drawBug(float x, float y, float w, float h){
        noStroke();
        fill(255, 255, 255);
        ellipse(x, y, w, h);
        fill(255, 0, 0);
        ellipse(x, y, w = random(25, 45), h = random(65, 85));
        fill(0);
        ellipse(x, y, w = random(5, 25), h = random(25, 35));
        hitDetection();
    }

    public void hitDetection(){
        if(((bugX <= (playerX + 80)) && (bugX >= (playerX - 80))) && ((bugY <= (playerY + 80)) && (bugY >= (playerY - 80)))){
            menuCheck = false;
            bugX = random(1170);
            bugY = 60;
        }
    }

    public void hitScan(){
       if(((mouseX <= (bugX + 10)) && (mouseX >= (bugX - 10))) && ((mouseY <= (bugY + 10)) && (mouseY >= (bugY - 10)))){
            score++;
            bugX = random(1170);
            bugY = 60;
       }
    }

    public void draw(){
        background(0);
        fill(255,255,255);
        textSize(18);
        text("Press q to quit", 50, 50);
        if(menuCheck == true){
            drawPlayer(playerX, playerY, playerWidth, playerHeight);
            if((frameCount % 3) == 0){
                if(bugX < playerX){
                    bugX += 10;
                }
                if(bugX > playerX){
                    bugX -= 10;
                }
                if(bugY < playerY){
                    bugY += 10;
                }
                if(bugY > playerY){
                    bugY -= 10;
                }
            }
            drawBug(bugX, bugY, bugWidth, bugWidth);
        }else{
            background(0);
            textSize(32);
            text("BUGZAP", (width/2)-(6*8), 150);
            text("Score = "+ score, (width/2)-(8*8), 250);
            text("Press SPACE to play", (width/2)-(19*8), 450);
        }
    }

    public void keyPressed(){
        switch(key){
            case 'a':
                playerX -= 30;
                while(playerX <= 30){
                    playerX += 20;
                }
                break;
            case 'd':
                playerX += 30;
                while(playerX >= 1170){
                    playerX -= 20;
                }
                break;
            case 'w':
                playerY -= 30;
                while(playerY <= 30){
                    playerY += 20;
                }
                break;
            case 's':
                playerY += 30;
                while(playerY >= 690){
                    playerY -= 20;
                }
                break;
            case ' ':
                menuCheck = true;
                break;
            case 'q':
                menuCheck = false;
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    public void mousePressed(){
        lazerCD = 0;
        check = true;
        hitScan();
    }
}

