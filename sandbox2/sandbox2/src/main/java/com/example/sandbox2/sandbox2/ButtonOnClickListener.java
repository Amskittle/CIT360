/*
* File created May/2014 by Amy Norman, William Bowen, and Curtis Lange.
*/

package com.example.sandbox2.sandbox2;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Amy Norman on 5/9/2014.
 */
public class ButtonOnClickListener implements View.OnClickListener {
    int count = 0;
    CharSequence[][] buttons = new CharSequence[3][3];
//    ImageView image = (ImageView) View.findViewById(R.id.imageView);

    @Override
    public void onClick(View clickedView) {
        ImageButton clickedButton = (ImageButton) clickedView;
            if (count % 2 == 0) {
                clickedButton.setImageResource(R.drawable.x);
                clickedButton.setContentDescription("x");
            } else {
                clickedButton.setImageResource(R.drawable.o);
                clickedButton.setContentDescription("o");
            }
            count++;
            clickedButton.setClickable(false);
        System.out.println(clickedButton.getId()+": "+clickedButton.getContentDescription());
             checkForWin(clickedButton);
    }

    public void resetCounter(){
        count = 0;
    }

    public void checkForWin(View clickedButton){
        switch(clickedButton.getId()){
            case 2131165245: buttons[0][0] = clickedButton.getContentDescription();
                if (buttons[0][0] == buttons[0][1]){
                    if (buttons[0][1] == buttons[0][2]){
                        winner(buttons, buttons[0][0]);
                    }
                }else if (buttons[0][0] == buttons[1][0]){
                    if (buttons[1][0] == buttons[2][0]){
                        winner(buttons, buttons[0][0]);
                    }
                }else if (buttons[0][0] == buttons[1][1]){
                    if (buttons[1][1] == buttons[2][2]){
                        winner(buttons, buttons[0][0]);
                    }
                }
                break;
            case 2131165246: buttons[0][1] = clickedButton.getContentDescription();
                if (buttons[0][1] == buttons[0][0]){
                    if (buttons[0][0] == buttons[0][2]){
                        winner(buttons, buttons[0][1]);
                    }
                }else if (buttons[0][1] == buttons[1][1]){
                    if (buttons[1][1] == buttons[2][1]){
                        winner(buttons, buttons[0][1]);
                    }
                }
                break;
            case 2131165247: buttons[0][2] = clickedButton.getContentDescription();
                if (buttons[0][2] == buttons[0][1]){
                    if (buttons[0][1] == buttons[0][0]){
                        winner(buttons, buttons[0][2]);
                    }
                }else if (buttons[0][2] == buttons[1][2]){
                    if (buttons[1][2] == buttons[2][2]){
                        winner(buttons, buttons[0][2]);
                    }
                }else if (buttons[0][2] == buttons[1][1]){
                    if (buttons[1][1] == buttons[2][0]){
                        winner(buttons, buttons[0][2]);
                    }
                }
                break;
            case 2131165248: buttons[1][0] = clickedButton.getContentDescription();
                if (buttons[1][0] == buttons[0][0]){
                    if (buttons[0][0] == buttons[2][0]){
                        winner(buttons, buttons[1][0]);
                    }
                }else if (buttons[1][0] == buttons[1][1]){
                    if (buttons[1][1] == buttons[1][2]){
                        winner(buttons, buttons[1][0]);
                    }
                }
                break;
            case 2131165249: buttons[1][1] = clickedButton.getContentDescription();
                if (buttons[1][1] == buttons[0][0]){
                    if (buttons[0][0] == buttons[2][2]){
                        winner(buttons, buttons[1][1]);
                    }
                }else if (buttons[1][1] == buttons[0][1]){
                    if (buttons[0][1] == buttons[2][1]){
                        winner(buttons, buttons[1][1]);
                    }
                }else if (buttons[1][1] == buttons[0][2]){
                    if (buttons[0][2] == buttons[2][0]){
                        winner(buttons, buttons[1][1]);
                    }
                }else if (buttons[1][1] == buttons[1][0]){
                    if (buttons[1][0] == buttons[1][2]){
                        winner(buttons, buttons[1][1]);
                    }
                }
                break;
            case 2131165250: buttons[1][2] = clickedButton.getContentDescription();
                if (buttons[1][2] == buttons[0][2]){
                    if (buttons[0][2] == buttons[2][2]){
                        winner(buttons, buttons[1][2]);
                    }
                }else if (buttons[1][2] == buttons[1][1]){
                    if (buttons[1][1] == buttons[1][0]){
                        winner(buttons, buttons[1][2]);
                    }
                }
                break;
            case 2131165251: buttons[2][0] = clickedButton.getContentDescription();
                if (buttons[2][0] == buttons[1][0]){
                    if (buttons[1][0] == buttons[0][0]){
                        winner(buttons, buttons[2][0]);
                    }
                }else if (buttons[2][0] == buttons[1][1]){
                    if (buttons[1][1] == buttons[0][2]){
                        winner(buttons, buttons[2][0]);
                    }
                }else if (buttons[2][0] == buttons[2][1]){
                    if (buttons[2][1] == buttons[2][2]){
                        winner(buttons, buttons[2][0]);
                    }
                }
                break;
            case 2131165252: buttons[2][1] = clickedButton.getContentDescription();
                if (buttons[2][1] == buttons[2][0]){
                    if (buttons[2][0] == buttons[2][2]){
                        winner(buttons, buttons[2][1]);

                    }
                }else if (buttons[2][1] == buttons[1][1]){
                    if (buttons[1][1] == buttons[0][1]){
                        winner(buttons, buttons[2][1]);
                    }
                }
                break;
            case 2131165253: buttons[2][2] = clickedButton.getContentDescription();
                if (buttons[2][2] == buttons[2][1]){
                    if (buttons[2][1] == buttons[2][0]){
                        winner(buttons, buttons[2][2]);
                    }
                }else if (buttons[2][2] == buttons[1][1]){
                    if (buttons[1][1] == buttons[0][0]){
                        winner(buttons, buttons[2][2]);
                    }
                }else if (buttons[2][2] == buttons[1][2]){
                    if (buttons[1][2] == buttons[0][2]){
                        winner(buttons, buttons[2][2]);
                    }
                }
                break;
        }


    }
    public void winner(CharSequence[][] buttons, CharSequence xo){
        if (xo == "x"){
            System.out.println("X wins!");
        } else{
            System.out.println("O wins!");
        }
        for (int i = 0; i < 3; i++){
           buttons[i][0] = null;
           buttons[i][1] = null;
           buttons[i][2] = null;
        }

        // How do I make the board unclickable and draw a line?
    }

}
