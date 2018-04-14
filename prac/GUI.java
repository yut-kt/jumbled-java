import javax.swing.*;
import java.awt.event.*;

public class GUI extends JPanel {
    JButton iii;
    JLabel uuu;
    public static void main(String[] args) {
        JFrame aaa = new JFrame("Hello");
        aaa.getContentPane().add(new GUI());        
        aaa.pack();
        aaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aaa.setVisible(true);
    }
    GUI() {
        iii = new JButton("click me");
        uuu = new JLabel("0 clicks");
        add(iii);
        add(uuu);
        iii.addActionListener(new Count(this));
    }
}

class Count implements ActionListener {
    private GUI ggg;
    private int i = 0;
    public Count(GUI eee) {
        ggg = eee;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ggg.iii) {
            i++;
            ggg.uuu.setText(i + " clicks");
        }
    }
}
