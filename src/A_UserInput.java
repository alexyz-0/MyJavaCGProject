final class A_UserInput
{
    // everything a user can press on keyboard or mouse
    int mousePressedX, mousePressedY,
            mouseMovedX,   mouseMovedY, mouseButton;

    boolean keyWasPressed = false;
    char keyPressed;
    boolean keyWasReleased = false;
    char keyReleased;

    // if Mouse was clicked, Key was pressed or Mouse is still hold down
    boolean isMouseEvent, isKeyEvent, isMousePressed;

    // ... is returned as a data set
    A_UserInput()
    { this.clear();
    }

    boolean keyWasPressed(){
        boolean temp = keyWasPressed;
        keyWasPressed = false;
        return temp;
    }
    boolean keyWasReleased(){
        boolean temp = keyWasReleased;
        keyWasReleased = false;
        return temp;
    }

    final void clear()
    { isMouseEvent=false; isKeyEvent=false;
    }
}
