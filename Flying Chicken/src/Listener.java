package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Listener implements ActionListener {

        boolean firstLevelPressed = false;
        boolean secondLevelPressed = false;
        boolean thirdLevelPressed = false;
        boolean restartPressed = false;
        boolean exitPressed = false;


        public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();

            if (buttonName.equals("level 1")) {
                firstLevelPressed = true;
            }

            else if(buttonName.equals("level 2")){
                secondLevelPressed = true;
            }
            else if(buttonName.equals("level 3")){
                thirdLevelPressed = true;
            }
            else if(buttonName.equals("exit")){
                exitPressed = true;
            }
            else if(buttonName.equals("restart")){
                restartPressed = true;
            }
        }

        

}
