
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyMouseAdapter extends MouseAdapter {

   MyFrame f;


   MyMouseAdapter(MyFrame var1) {
      this.f = var1;
   }

   public void mousePressed(MouseEvent var1) {
      this.f.c.action = "image";
      this.f.c.x = this.f.x = this.f.a = var1.getX();
      this.f.c.y = this.f.y = this.f.b = var1.getY();
      System.out.println(this.f.c.x + "," + this.f.c.y);
   }

   public void mouseReleased(MouseEvent var1) {
      this.f.a = var1.getX();
      this.f.b = var1.getY();
      System.out.println(this.f.a + "," + this.f.b);
      this.f.c.action = "crop";
      this.f.c.cropImg = this.f.create(this.f.x, this.f.y, this.f.a, this.f.b);
      if(this.f.a == this.f.x && this.f.b == this.f.y) {
         this.f.c.action = "image";
      }

      if(this.f.x > this.f.a) {
         if(this.f.y > this.f.b) {
            this.f.c.x = this.f.a;
            this.f.c.y = this.f.b;
         } else {
            this.f.c.x = this.f.a;
         }
      } else if(this.f.y > this.f.b) {
         this.f.c.y = this.f.b;
      }

      this.f.c.repaint();
   }
}
