import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Iterator;

public class CSR_World extends A_World {
    protected void init()
    {
        //Initialize all objects here
        bg = new Backgound_Image();
        avatar = new CSR_Avatar();

    }
    protected int maxMovementSize;
    protected void processUserInput(A_UserInput userInput, double diffSeconds)
    {
        //if(userInput.isMouseMoved){
        //if(userInput.isMouseEvent)
        //if(userInput.isKeyEvent)
        if(userInput.isKeyEvent){
            if(userInput.keyWasPressed())
                if (userInput.keyPressed == 32)
                    avatar.ascend();
            if(userInput.keyWasReleased())
                if (userInput.keyReleased == 32)
                    avatar.descend();
        }
    }
}
