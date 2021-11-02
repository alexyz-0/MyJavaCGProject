import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CSR_Avatar extends A_GameObject {

    BufferedImage AvatarIdle;
    BufferedImage AvatarJump;
    BufferedImage AvatarJump2;
    BufferedImage AvatarFloat;
    BufferedImage AvatarDead;

    private double animationTimer = 0;
    private boolean isAscending = false;
    private boolean onTheGround = true;

    private double speed;
    private final double accConst = 1.005;                  //Acceleration Constant : How much will the avatar be accelerated
    private final double stoppingThreshold = 0.05;          //Stopping Threshold    : If we get slower ([de]acceleration while going down[up]), this is the min speed, til the direction is switched
    private final double accelerationThreshold = 1.0;       //Acceleration Threshold: The max acceleration
    private final double startSpeed = 0.01;                 //Starting Speed        : As the speed gets bigger with v^a, it can't be 0 at start.

    public CSR_Avatar(){
        super(A_Const.AVATAR_X_POSITION, A_Const.AVATAR_MAX_Y_POSITION, 256, 256, null);
        catchImages();
        icon = AvatarIdle;
    }

    public CSR_Avatar(double x, double y, double width, double height) {
        super(x, y, width, height, null);
        catchImages();
        icon = AvatarIdle;
    }

    void catchImages() {
        try{
            this.AvatarIdle = ImageIO.read(this.getClass().getResource("/Images/FrogIdle.png"));
            this.AvatarJump = ImageIO.read(this.getClass().getResource("/Images/FrogJump.png"));
            this.AvatarJump2 = ImageIO.read(this.getClass().getResource("/Images/FrogJump2.png"));
            this.AvatarFloat = ImageIO.read(this.getClass().getResource("/Images/FrogFloat.png"));
            this.AvatarDead = ImageIO.read(this.getClass().getResource("/Images/FrogDead.png"));
        }catch (IOException e) { e.printStackTrace(); }
    }
            ////Side note: Possible problems when pressing space bar (isAscending = true), but the momentum still drags you down and you hit the ground
    void ascend() {  //on press key
        if(!isAscending){
            onTheGround = false;
            isAscending = true;
            icon = AvatarFloat;
        }
    }
    void descend() { //on release key
        isAscending = false;
    }
            ////Side note: Possible problems when pressing space bar (isAscending = true), but the momentum still drags you down and you hit the ground
    void move(double diffSec) {
        if(!onTheGround) {
            if (isAscending) {                                                                          //If we hold space
                if (speed >= -accelerationThreshold) {                                                  //and if we aren't already at full speed ...
                    if (speed == 0) speed = -startSpeed;
                    if (speed <= 0)                                                                     //... and if we are already going up,
                        speed *= Math.pow(accConst, diffSec);                                           //      speed is increased (accelerate)
                    else if (speed > stoppingThreshold)                                                 //... and if we are still going down: if speed is still above the threshold
                        speed *= Math.pow(accConst, -diffSec);                                          //      get slower
                    else
                        speed = 0;                                                                      //      if it's under the threshold, stop moving down
                    if (Math.abs(speed) >= accelerationThreshold) speed = -accelerationThreshold;
                }
            } else {                                                                                    //If we don't hold space
                if (speed < accelerationThreshold) {                                                    //and if we aren't going down at full speed...
                    if (speed == 0) speed = startSpeed;
                    if (speed >= 0)                                                                     //... and if we are already going down
                        speed *= Math.pow(accConst, diffSec);                                           //      speed is increased (accelerate)
                    else if (Math.abs(speed) > stoppingThreshold && y > A_Const.AVATAR_MIN_Y_POSITION)  //... and if we are still going up: if speed is still above the threshold
                        speed *= Math.pow(accConst, -diffSec);                                          //      get slower
                    else
                        speed = 0;                                                                      //      if it's under the threshold, stop moving up
                    if (speed >= accelerationThreshold) speed = accelerationThreshold;
                }
            }
            //System.out.println("Hier: " + speed + " . . " + diffSec);

            if (isAscending && y > A_Const.AVATAR_MIN_Y_POSITION || !isAscending && y < A_Const.AVATAR_MAX_Y_POSITION) {
                y += diffSec * speed;
                if(y >= A_Const.AVATAR_MAX_Y_POSITION) {
                    y = A_Const.AVATAR_MAX_Y_POSITION;
                    onTheGround = true;
                    speed = 0;
                    animationTimer = 0;
                }
                if(y <= A_Const.AVATAR_MIN_Y_POSITION) {
                    y = A_Const.AVATAR_MIN_Y_POSITION;
                    speed = 0;
                }
            }
        }
    }

    void animate(double diffSec) {
        if(onTheGround){
            animationTimer += diffSec;
            //Jump2 is 250 millis; Jump is 750 millis; Idle is 500 millis
            if(animationTimer > 1500){
                icon = AvatarIdle;
                animationTimer = 0;
            } else if(animationTimer > 1250){
                icon = AvatarJump2;
            } else if(animationTimer > 500){
                icon = AvatarJump;
            } else {
                icon = AvatarIdle;
            }
        }
    }

    protected void draw(Graphics2D g2D) {
        g2D.drawImage(icon, (int) x, (int) y, (int) width, (int) height, null);
        //g2D.drawImage(this.image, 0, 0, null);
    }

    void triggerDeath(){
        icon = AvatarDead;
    }

    @Override
    int object_type() {
        return A_Const.TYPE_AVATAR;
    }
}
