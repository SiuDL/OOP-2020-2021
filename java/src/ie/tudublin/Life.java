package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet {

    int size = 720;
    float cellSize;
    float[][] board = new float[size][size];
    float[][] next = new float[size][size];

    public int countNeighbours(int row, int col)
    {
        int count = 0;
        
        for(int r = row -1 ; r <= row + 1; r ++)
        {
            for(int c = col -1 ; c <= col + 1; c ++)
            {
                if (! (r == row && c == col))
                {
                    if (getCell(board, r, c) > 0)
                    {
                        count ++;
                    }
                }
            }
        }

        // OR Use 8 if statements
        /*
        if (getCell(board, row-1, col-1))
        {
            count ++;
        }
        if (getCell(board, row-1, col))
        {
            count ++;
        }
        if (getCell(board, row-1, col+1))
        {
            count ++;
        }
        if (getCell(board, row, col-1))
        {
            count ++;
        }
        if (getCell(board, row, col+1))
        {
            count ++;
        }
        if (getCell(board, row+1, col-1))
        {
            count ++;
        }
        if (getCell(board, row+1, col))
        {
            count ++;
        }
        if (getCell(board, row+1, col+1))
        {
            count ++;
        }
        */
        
        return count;
    }

    public void setCell(float[][] board, int row, int col, float b)
    {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            board[row][col] = b;
        }
    }

    public float getCell(float[][] board, int row, int col)
    {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            return board[row][col];
        }
        else
        {
            return 0;
        }        
    }

    public void ragnarok(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                setCell(board, i, j, 0);
            }
        }
    }

    public void cross(){
        for(int i=0; i<size; i++){
            setCell(board, size/2, i, random(255));
            setCell(board, i, size/2, random(255));
        }
    }

    public void drawBoard(float[][] board)
    {
        // Use a nested loop
        // Use map to calculate x and y
        // Rect draw the cell
        // Use the cell size variable
        // Use some colours!
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float x = map(col, 0, size, 0, width);
                float y = map(row, 0, size, 0, height);
                //float colour = map((x * y), 0, size, 0, 255) % 255;
                if (board[row][col] > 0)
                {
                    //fill(colour, 255, 255);
                    rect(x, y, cellSize, cellSize);
                }
            }
        }

    }

    private void printBoard(float[][] board)
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                //print(board[row][col] ? random(255) : 0);
            }
            println();
        }        
    }

    public void randomize()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                float dice = random(0.0f, 1.0f);
                /*
                if (dice < 0.5)
                {
                    board[row][col] = true;
                }
                else
                {
                    board[row][col] = false;
                }
                */
                board[row][col] = (dice < 0.5f) ? random(255) : 0;
            }
        }
    }

    public void settings()
    {
        size(1600, 900);
    }
    
    int mode = 0;
    boolean paused = true;
    public void keyPressed() {
        if (keyCode == ' ')
        {
            if(paused == true){
                paused = false;
            }else{
                paused = true;
            }
        }
        
        if (keyCode == '1')
        {
            randomize();
        }
        if (keyCode == '2')
        {
            ragnarok();
        }
        if (keyCode == '3')
        {
            cross();
        }
        if (keyCode == '4')
        {
            mouseDragged();
        }
            
    }

    public void setup() {
        colorMode(HSB);
        randomize();
        
        /*
        board[0][1] = true;
        board[1][2] = true;
        board[3][2] = true;
        */
        
        println(countNeighbours(0, 2));

        cellSize = width / (size);
        //printBoard(board);        
    }

    private void updateBoard()
    {
        // Put code here to apply the rules!!
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                int count = countNeighbours(i, j);
                if(getCell(board, i, j) > 0){
                    if(count == 2 || count == 3){
                        setCell(next, i, j, random(255));
                    }else{
                        setCell(next, i, j, 0);
                    }
                }else{
                    if(count == 3){
                        setCell(next, i, j, random(255));
                    }else{
                        setCell(next, i, j, 0);
                    }
                }
            }
        }
        
        // Swap board and next
        float[][] temp = board;
        board = next;
        next = temp;
    }

    public void mouseDragged()
    {
        // This method gets called automatically when the mouse is dragged across the screen
        int row = (int)map(mouseY, 0, height, 0, size);
        int col = (int)map(mouseX, 0, width, 0, size);
        setCell(board, row, col, random(255));
    }

    public void draw() {
        if(paused){
            background(0);
            drawBoard(board);        
            updateBoard();
        }
    }
}
