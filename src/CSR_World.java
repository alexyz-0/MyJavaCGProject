import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CSR_World extends A_World {
    protected void init()
    {
        //Initialize all objects here
        enemyList = new ArrayList<>();
        bg = new Backgound_Image();
        gos = new CSR_GameOverScreen();
        avatar = new CSR_Avatar();

    }
    protected int maxMovementSize;
    protected void processUserInput(A_UserInput userInput, double diffSeconds)
    {
        //if(userInput.isMouseMoved){
        //if(userInput.isMouseEvent)
        //if(userInput.isKeyEvent)
        if(userInput.isKeyEvent) {
            if(!gos.isInitialized()) {
                if(userInput.keyWasPressed()){
                    if (userInput.keyPressed == 32) {
                        System.out.println("pressed");
                        avatar.ascend();
                    }
                    if(userInput.keyPressed == 'm' || userInput.keyPressed == 'M') bg.setMode();

                }
                if(userInput.keyWasReleased())
                    if (userInput.keyReleased == 32)
                        avatar.descend();
            } else {
                if(gos.canContinue()) {
                    init();
                    userInput.keyWasPressed = true;
                }
            }
        }
    }
}
