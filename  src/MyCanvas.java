
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemColor;
import javax.swing.JViewport;

public class MyCanvas extends JViewport {

   public Image newImg = null;
   public Image cropImg = null;
   public Image backimg = null;
   int x = 0;
   int y = 0;
   int w = 0;
   int h = 0;
   String action = "image";
   Boolean load = Boolean.valueOf(true);
   Color c = new Color(150, 255, 255, 100);
   public MyFrame f;


   MyCanvas(Image var1, int var2, int var3, MyFrame var4) {
      this.f = var4;
      this.newImg = var1;
      this.w = var2;
      this.h = var3;
      this.setBackground(SystemColor.window);
      this.setForeground(new Color(80, 80, 255));
      this.backimg = var4.createImageObj("nice.jpg");
      MediaTracker var5 = new MediaTracker(this);
      var5.addImage(this.backimg, 0);

      try {
         var5.waitForID(0);
      } catch (InterruptedException var7) {
         ;
      }

   }

   public void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      if(this.action != "crop") {
         this.cropImg = null;
      }

      var1.drawImage(this.backimg, 400, 100, this);
      if(this.load.booleanValue()) {
         MediaTracker var2 = new MediaTracker(this);
         var2.addImage(this.newImg, 0);

         try {
            var2.waitForID(0);
         } catch (InterruptedException var4) {
            ;
         }

         this.w = this.newImg.getWidth(this);
         this.h = this.newImg.getHeight(this);
         this.setViewSize(new Dimension(this.w, this.h));
         this.setSize(this.w, this.h);
         this.load = Boolean.valueOf(false);
      }

      var1.drawImage(this.newImg, 0, 0, this);
      if(this.action == "crop") {
         var1.drawImage(this.cropImg, this.x, this.y, this);
         var1.drawRect(this.x, this.y, this.f.w, this.f.h);
         var1.drawRect(this.x + 1, this.y + 1, this.f.w - 2, this.f.h - 2);
      }

   }

   public void update(Graphics var1) {
      this.paint(var1);
   }

   public Dimension getPreferredSize() {
      return new Dimension(this.w - 50, this.h - 50);
   }
}
