
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MenuAction extends JPanel {
    //http://www.java2s.com/Code/Java/Swing-JFC/ThisexamplecreateamenubarandtoolbarbothpopulatedwithAction.htm
    public JMenuBar menuBar;

    public JToolBar toolBar;

    private boolean fullScreen = false;

    public MenuAction(JFrame frame) {
        super(true);

        menuBar = new JMenuBar();
        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        toolBar = new JToolBar();
        toolBar.setBorder(new EtchedBorder());

        SampleAction fullscreen = new SampleAction(
                "fullscreen",
                new ImageIcon("C:\\Users\\latek\\Desktop\\Programowanie obiektowe\\Projekt\\src\\main\\java\\icons\\fullscreenIcon.png")
        );
        JMenuItem fullscreenItem = new JMenuItem(fullscreen);
        JButton fullscreenButton = new JButton(fullscreen);
        menu.add(fullscreenItem);
        toolBar.add(fullscreenButton);


        SampleAction exit = new SampleAction(
                "exit",
                new ImageIcon("C:\\Users\\latek\\Desktop\\Programowanie obiektowe\\Projekt\\src\\main\\java\\icons\\exitIcon.png")
        );
        JMenuItem exitItem = new JMenuItem(exit);
        JButton exitButton = new JButton(exit);
        menu.add(exitItem);
        toolBar.add(exitButton);


        fullscreenItem.addActionListener(e -> { // fullscreen resize screen
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow( !fullScreen ? frame : null );

            fullScreen = !fullScreen;
        });

        exitItem.addActionListener(e -> System.exit(0)); // close aplication

    }

    public boolean isFullScreen() {
        return fullScreen;
    }

}
