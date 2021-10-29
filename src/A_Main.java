// This is a main class
// This class is of type 'A', since it's independent from implementation details and independent from kind of the game
// More information about 'A' class you will find in lecture notes on page 84,  12.1 A-classes

final class A_Main
{
    private A_World world;

    public A_Main()
    {
        A_Frame frame = new A_Frame();
        frame.displayOnScreen();

        world = new CSR_World();

        world.setGraphicSystem(frame.getGraphicSystem());
        world.setInputSystem(frame.getInputSystem());

        A_GameObject.setWorld(world);
        frame.getGraphicSystem().setWorld(world);

        world.init();
        world.run();
    }

    public static void main(String[] args) {
        new A_Main();
    }
}
