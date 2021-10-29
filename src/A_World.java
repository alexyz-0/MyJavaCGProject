import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

abstract class A_World {
    private A_GraphicSystem graphicSystem;
    private A_PhysicsSystem physicsSystem;
    private A_InputSystem   inputSystem;
    private A_UserInput     userInput;
    private ArrayList<CSR_Fly> enemyList;

    //Here are most global objects declared
    Backgound_Image bg;
    CSR_Avatar avatar;

    A_World() {
        physicsSystem = new CSR_PhysicsSystem(this);
        enemyList = new ArrayList<>();
    }
    final void run(){
        long lastTick =  System.currentTimeMillis();

        while(true){
            long currentTick = System.currentTimeMillis();
            long millisDiff  = currentTick-lastTick;
            if(millisDiff<A_Const.FRAME_MINIMUM_MILLIS)
            {
                try{ Thread.sleep(A_Const.FRAME_MINIMUM_MILLIS-millisDiff);} catch(Exception ex){}
                currentTick = System.currentTimeMillis();
                millisDiff  = currentTick-lastTick;
            }
            lastTick = currentTick;


            //Spawn Enemy 50:1 Chance
            if(ThreadLocalRandom.current().nextInt(0, 50 + 1) == 0)         //ThreadLocalRandom.current().nextInt(min, max + 1);
                enemyList.add(new CSR_Fly());

            //UserInput
            userInput = inputSystem.getUserInput();
            processUserInput(userInput,millisDiff/1000.0);
            userInput.clear();

            //Move
            avatar.move(millisDiff);
            for(CSR_Fly fly : enemyList)
                fly.move(millisDiff);

            //Check Collision
            Iterator<CSR_Fly> iter = enemyList.iterator();
            while (iter.hasNext()) {
                CSR_Fly fly = iter.next();

                if (fly.checkCollider(avatar)){
                    //System.out.println("AVATAR: " + avatar.x + " - " + avatar.x+avatar.width + " ::: " + avatar.y + " - " + avatar.y+avatar.height);
                    //System.out.println("FLY   : " + fly.x + " - " + fly.x+fly.width + " ::: " + fly.y + " - " + fly.y+fly.height);
                    iter.remove();
                }
            }

            iter = enemyList.iterator();
            while (iter.hasNext()) {
                CSR_Fly fly = iter.next();

                if (fly.checkOnScreen()){
                    System.out.println("checkOnScreen: " + fly.x);
                    iter.remove();
                }
            }

            //Animate
            bg.animate(millisDiff);
            avatar.animate(millisDiff);
            for(CSR_Fly fly : enemyList)
                fly.animate(millisDiff);

            //Draw
            bg.draw(graphicSystem.getGraphicsToDraw());
            avatar.draw(graphicSystem.getGraphicsToDraw());
            for(CSR_Fly fly : enemyList)
                fly.draw(graphicSystem.getGraphicsToDraw());
            graphicSystem.redraw();


        }
    }

    protected void setGraphicSystem(A_GraphicSystem p) { graphicSystem = p; }
    protected void setInputSystem(A_InputSystem p)     { inputSystem   = p; }

    protected A_PhysicsSystem getPhysicsSystem()       { return physicsSystem; }

    protected abstract void init();
    protected abstract void processUserInput(A_UserInput input, double diffSec);
}
