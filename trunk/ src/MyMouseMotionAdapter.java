
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class MyMouseMotionAdapter extends MouseMotionAdapter {

   MyFrame f;


   MyMouseMotionAdapter(MyFrame var1) {
      this.f = var1;
   }

   public void mouseDragged(MouseEvent var1) {
      this.f.ta = var1.getX();
      this.f.tb = var1.getY();
      this.f.c.action = "crop";
      this.f.c.cropImg = this.f.create(this.f.x, this.f.y, this.f.ta, this.f.tb);
      if(this.f.x > this.f.ta) {
         if(this.f.y > this.f.tb) {
            this.f.c.x = this.f.ta;
            this.f.c.y = this.f.tb;
         } else {
            this.f.c.x = this.f.ta;
         }
      } else if(this.f.y > this.f.tb) {
         this.f.c.y = this.f.tb;
      }

      this.f.c.repaint();
   }
}
