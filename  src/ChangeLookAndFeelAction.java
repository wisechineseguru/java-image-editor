
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

class ChangeLookAndFeelAction extends AbstractAction {

   MyFrame f;
   String type;


   protected ChangeLookAndFeelAction(MyFrame var1, String var2) {
      super("ChangeTheme");
      this.f = var1;
      this.type = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      this.f.setLookAndFeel(this.type);
   }
}
