package com.example.hw2q8;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //the size of the board
    private final int SIZE = 9;

    //game
    private Game game;

    //app interface
    private AppInterface appInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        int screenSize = getWindowManager().getCurrentWindowMetrics().getBounds().width();
        int width = screenSize/SIZE;


        //create game
        game = new Game();

        //create app interface
        appInterface = new AppInterface(this,SIZE,width);

        //set content view of the screen
        setContentView(appInterface);

        //get initial board and display in appInterface
        int[][] board = game.getBoard();
        appInterface.drawInitialBoard(board);

        //attach event handler to all edit text
        for(int x = 0 ; x< board.length ; x++)
        {
            for(int y = 0 ; y < board.length ; y++)
            {
                TextChangeHandler temp = new TextChangeHandler(x,y);
                appInterface.setTextChangeHandler(temp,x,y);
            }
        }
    }

    public class TextChangeHandler implements TextWatcher
    {
        private int x;
        private int y;

        public TextChangeHandler(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s) //here
        {
            String input = appInterface.getInput(x,y);

            //if input (edit text) is equal to ""
            //set the value on the board to a zero
            if(input.equals(""))
            {
                game.setValue(0,x,y);
            }
            //if input (edit text) is equal to "0"
            //clear x,y on the interface
            else if(input.equals("0"))
            {
                game.setValue(0,x,y);
                appInterface.clear(x,y);
            }
            //if the input length is greater than 1
            //set 0 at x,y on board
            //clear x,y on interface
            else if(input.length() > 1)
            {
                game.setValue(0,x,y);
                appInterface.clear(x,y);

            }
            else
            {
                //value = convert input to integer
                int value=0;
                //convert input to integer
                try
                {
                    value = Integer.parseInt(input);
                }
                catch (NumberFormatException e)
                {}

                //if value can be placed at x ,y on game board
                if(!game.check(value,x,y))
                {
                    //set value at x,y on board
                    game.setValue(value,x,y);
                }
                else
                {
                    //set 0 at x,y on board
                    game.setValue(0,x,y);

                    //clear x,y on interface
                    appInterface.clear(x,y);

                }


            }

        }
    }
}