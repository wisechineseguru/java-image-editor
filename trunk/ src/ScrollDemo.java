
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollDemo extends JPanel {

   MyCanvas c;
   MyFrame f;
   int t;
   int tgl = 0;
   Insets ins;
   JScrollPane sp;


   public ScrollDemo(Image var1, int var2, MyFrame var3) {
      this.f = var3;
      this.tgl = var2;
      this.setLayout(new BoxLayout(this, 2));
      this.c = new MyCanvas(var1, 300, 200, var3);
      this.setBackground(Color.gray.brighter().brighter());
      this.sp = new JScrollPane(this.c);
      this.sp.setViewportBorder(BorderFactory.createLineBorder(Color.black));
      this.add(this.sp);
      this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      this.sp.repaint();
   }

   public void update(Image var1, int var2) {
      this.t = var2;
      int var3;
      int var4;
      if(var2 == 0) {
         var3 = this.getHeight();
         var4 = this.getWidth();
      } else {
         var3 = var1.getHeight(this);
         var4 = var1.getWidth(this);
      }

      this.c.h = var3;
      this.c.w = var4;
      this.c.newImg = var1;
      this.c.repaint();
      this.sp.getParent().validate();
   }

   protected void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      int var2;
      int var3;
      if(this.t == 0) {
         var2 = this.getHeight();
         var3 = this.getWidth();
      } else {
         var2 = this.c.newImg.getHeight(this);
         var3 = this.c.newImg.getWidth(this);
      }

      this.c.repaint();
      this.sp.getParent().validate();
   }
}
