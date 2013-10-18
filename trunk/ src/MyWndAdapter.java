
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyWndAdapter extends WindowAdapter {

   MyFrame f;


   MyWndAdapter(MyFrame var1) {
      this.f = var1;
   }

   public void windowClosing(WindowEvent var1) {
      this.f.dispose();
   }
}
