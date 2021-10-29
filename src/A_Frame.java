import javax.swing.*;
import java.io.Serial;

public class A_Frame extends JFrame {
    @Serial
    private static final long serialVersionUID = 2L;

    private B_Panel panel;

    public A_Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(A_Const.WIDTH,A_Const.HEIGHT);
        this.setResizable(false);

        panel = new B_Panel();

        // needed for Keyboard input !!!
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        this.setContentPane(panel);
    }

    public void displayOnScreen() { validate(); setVisible(true); }

    public A_GraphicSystem getGraphicSystem() { return panel; }
    public A_InputSystem   getInputSystem()   { return panel.getInputSystem(); }
}
