
import javax.swing.*;
import java.awt.event.ActionEvent;

public class SampleAction extends AbstractAction {

    public SampleAction(String text, Icon icon) {
        super(text, icon);
    }

    public SampleAction(String text) {
        super(text);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action ->" + e.getActionCommand() + " complated!");
    }
}
