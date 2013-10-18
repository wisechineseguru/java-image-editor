
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyAboutAdapter extends WindowAdapter {

   About f;


   MyAboutAdapter(About var1) {
      this.f = var1;
   }

   public void windowClosing(WindowEvent var1) {
      this.f.dispose();
   }
}
