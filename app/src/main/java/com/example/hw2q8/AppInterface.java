package com.example.hw2q8;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class AppInterface extends GridLayout
{
    //the size of the sudoku board
    private final int BORDSIZE = 9;

    private EditText[][] board;
    private GridLayout myGrid;

    public AppInterface(Context context,int size,int width)
    {
        super(context);

        //setting the number of rows and columns
        setRowCount(BORDSIZE);
        setColumnCount(BORDSIZE);

        //create a board in grid layout
        board = new EditText[BORDSIZE][BORDSIZE];

        for(int x  = 0 ; x < BORDSIZE ; x++)
        {
            for(int y = 0 ; y < BORDSIZE ; y++ )
            {
                board[x][y] = new EditText(context);
                board[x][y].setBackgroundColor(Color.parseColor("#787878"));
                board[x][y].setText("");
                board[x][y].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                board[x][y].setGravity(Gravity.CENTER);
                board[x][y].setTextSize((int)(width*0.2));
                board[x][y].setTextColor(Color.parseColor("#FF000000"));
                board[x][y].setInputType(InputType.TYPE_CLASS_NUMBER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams( );
                params.width = width;
                params.height = width;
                params.rowSpec = GridLayout.spec(x, 1);
                params.columnSpec = GridLayout.spec(y, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                if (x == 0) params.topMargin = 80;
                board[x][y].setLayoutParams(params);
                addView(board[x][y]);
            }
        }

    } //end of constructor



}
