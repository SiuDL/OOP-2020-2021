package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet {

    int size = 720;
    float cellSize;
    boolean[][] board = new boolean[size][size];
    boolean[][] next = new boolean[size][size];
    boolean pause = true;

    public int countNeighbours(int row, int col)
    {
        int count = 0;
        
        for(int r = row -1 ; r <= row + 1; r ++)
        {
            for(int c = col -1 ; c <= col + 1; c ++)
            {
                if (! (r == row && c == col))
                {
                    if (getCell(board, r, c))
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

    public void setCell(boolean[][] board, int row, int col, boolean b)
    {
        if (row >= 0 && row <= size -1 && col >= 0 && col <= size -1)
        {
            board[row][col] = b;
        }
    }

    public boolean getCell(boolean[][] board, int row, int col)
    {
        if (row >= 0 && row < size -1 && col >= 0 && col < size -1)
        {
            return board[row][col];
        }
        else
        {
            return false;
        }        
    }

    public void ragnarok(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                setCell(board, i, j, false);
            }
        }
    }

    public void cross(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if((i>=(size*0.4f) && i<=(size*0.6f)) || (j>=(size*0.4f) && j<=(size*0.6f))){
                    board[i][j] = true;
                }
            }
        }
    }

    public void drawBoard(boolean[][] board)
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
                if (board[row][col])
                {
                    //fill(colour, 255, 255);
                    rect(x, y, cellSize, cellSize);
                }
            }
        }

    }

    private void printBoard(boolean[][] board)
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for (int col = 0 ; col < size ; col ++)
            {
                print(board[row][col] ? 1 : 0);
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
                board[row][col] = (dice < 0.5f) ? true : false;
            }
        }
    }

    public void settings()
    {
        size(1600, 900);
    }
    
    int mode = 0;
    boolean paused = false;
    public void keyPressed() {
        if (keyCode == ' ')
        {
            if(pause == true){
                pause = false;
            }else{
                pause = true;
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
                if(getCell(board, i, j) == true){
                    if(count == 2 || count == 3){
                        setCell(next, i, j, true);
                    }else{
                        setCell(next, i, j, false);
                    }
                }else{
                    if(count == 3){
                        setCell(next, i, j, true);
                    }else{
                        setCell(next, i, j, false);
                    }
                }
            }
        }
        
        // Swap board and next
        boolean[][] temp = board;
        board = next;
        next = temp;
    }

    public void mouseDragged()
    {
        // This method gets called automatically when the mouse is dragged across the screen
    }

    public void draw() {
        if(pause){
            background(0);
            drawBoard(board);        
            updateBoard();
        }
    }
}
