
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

class ImageScroller extends JScrollPane {

   public ImageScroller(MyFrame var1, MyCanvas var2) {
      JPanel var3 = new JPanel(new BorderLayout());
      var3.add(var2, "Center");
      this.add(var3);
      JScrollBar var4 = this.getVerticalScrollBar();
      JScrollBar var5 = this.getHorizontalScrollBar();
      var4.setValue(1500);
      var5.setValue(1500);
      var2.repaint();
   }
}
