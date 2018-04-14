import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PictureChanger extends JFrame {
    JPanel panel;
    JLabel pictLabel;
    JButton pict1;
    JButton pict2;
    JButton pict3;
    JButton next;
    String image1 = new String("library.jpg"); //Pict1ボタンで表示
    String image2 = new String("library2.jpg");//Pict2ボタンで表示
    String image3 = new String("Courtyard.jpg");//Pict3ボタンで表示
    int gesCount = 0;

    //JLabelPictureのコンストラクタ
    public PictureChanger (String title) {
        super(title); //ウィンドウの名前
        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());

        //ImageIconクラスのオブジェクトとしてイメージをJLabelに取り込む
        pictLabel = new JLabel(new ImageIcon(image1));
        //JLabelに画像イメージを取り込んだJLabelを乗せる
        panel.add(pictLabel,BorderLayout.CENTER);

        pict1 =new JButton("Pcit1");
        panel.add(pict1, BorderLayout.NORTH);
        pict1.addActionListener(this.new Pict1Listener());

        pict2 =new JButton("Pcit2");
        panel.add(pict2, BorderLayout.WEST);
        pict2.addActionListener(this.new Pict2Listener());

        pict3 =new JButton("Pcit3");
        panel.add(pict3, BorderLayout.EAST);
        pict3.addActionListener(this.new Pict3Listener());

        next =new JButton("Next");
        panel.add(next, BorderLayout.SOUTH);
        next.addActionListener(this.new NextListener());
      }


    public static void main (String[] args) {
        PictureChanger pc = new PictureChanger ("PictureChanger");
        pc.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        //ilabel.pack(); //サブコンポーネントの推奨サイズに合わせて表示サイズを調整する
        pc.setSize(800, 600);
        pc.setVisible(true);
    }

    //Pict1が押されたらimage1を表示する
    class Pict1Listener implements ActionListener { //ActionListenerを実装する
        //イベントが発生したら呼び出される
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pict1) {
                //発生したイベントの対象がボタンであったときに処理をする
                pictLabel.setIcon(new ImageIcon(image1));
                System.out.println("clickPit1");
                panel.repaint();

            }
        }
    }

    //Pict2が押されたらimage2を表示する
    class Pict2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pict2) {
                pictLabel.setIcon(new ImageIcon(image2));
                System.out.println("clickPit2");
                panel.repaint();

            }
        }
    }

    //Pict3が押されたらimage3を表示する
    class Pict3Listener implements ActionListener { //ActionListenerを実装する
        //イベントが発生したら呼び出される
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == pict3) {
                //発生したイベントの対象がボタンであったときに処理をする
                pictLabel.setIcon(new ImageIcon(image3));
                System.out.println("clickPit3");
                panel.repaint();

            }
        }
    }

    //nextが押されたら画像を順番に表示する
    class NextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == next) {
                System.out.println("NEXT");
                gesCount++;
                if (gesCount%3==0) {
                    pictLabel.setIcon(new ImageIcon(image3));
                } else if (gesCount%3 ==1) {
                    pictLabel.setIcon(new ImageIcon(image1));
                } else if (gesCount%3==2) {
                    pictLabel.setIcon(new ImageIcon(image2));
                }
                panel.repaint();

            }
        }
    }

}




