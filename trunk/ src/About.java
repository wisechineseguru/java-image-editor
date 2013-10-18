import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class About extends JFrame implements ActionListener {

   JLabel l;
   JButton bExit;
   MyFrame f;


   About(MyFrame myFrame) {
      super("Devloper");
      this.f = myFrame;
      this.setSize(2000, 300);
      this.setLocation(10, 200);
      this.display();
      this.setBackground(Color.white);
      this.setUndecorated(true);
      this.addWindowListener(new MyAboutAdapter(this));
   }

   public void display() {
      this.setLayout(new BorderLayout(10, 10));
      JPanel myFrame = new JPanel();
      this.bExit = new JButton(this.f.createImageIcon("/icons/exit.jpg"));
      myFrame.setLayout(new GridLayout(3, 1, 10, 10));
      this.add(new JLabel("Well!! I am Alok Mishra, third year student of Govt. Engineering College Bikaner, India. This is my first Java application. Its basic and very intuitive image editor. Thanks. If anybody need any help regarding this project, Pls. feel free to contact. My Id-  hayalok@gmail.com"), "Center");
      myFrame.setLayout(new BorderLayout(5, 5));
      this.add(myFrame, "North");
      myFrame.add(this.bExit, "East");
      this.bExit.addActionListener(this);
   }

   public void actionPerformed(ActionEvent myFrame) {
      this.dispose();
   }
}
