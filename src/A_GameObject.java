import java.awt.*;

abstract class A_GameObject {
    // The Icon used to visualize that unit in the world. Currently just placeholder code.
    protected Image icon;

    protected double  x,y;
    protected double width, height;
    protected Color   color;
    protected int unitSide;

    // GameObjects sometimes call physics methods
    protected static A_World world;

    // This is old constructor
    // construct GameObject
    public A_GameObject(double x, double y, double width, double height, Image icon)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = icon;
    }

    // Change the color of the unit
    public void changeColor(Color color){
        this.color = color;
    }

    // Unit or Obstacle
    abstract int object_type();

    static void setWorld(A_World w) {world=w;}

    boolean checkCollider(A_GameObject obj){
        return (this.x < obj.x + obj.width &&
                this.x + this.width > obj.x &&
                this.y < obj.y + obj.height &&
                this.y + this.height > obj.y);
    }


}
