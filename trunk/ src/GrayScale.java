
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class GrayScale extends RGBImageFilter {

   MyFrame f;


   public Image filter(Image var1, MyFrame var2) {
      this.f = var2;
      return var2.createImage(new FilteredImageSource(var1.getSource(), this));
   }

   public int filterRGB(int var1, int var2, int var3) {
      int var7 = var3 >> 24 & 255;
      int var4 = var3 >> 16 & 255;
      int var5 = var3 >> 8 & 255;
      int var6 = var3 & 255;
      int var8;
      if(this.f.w > 0 && this.f.h > 0) {
         if(var2 >= this.f.c.y && var2 <= this.f.c.y + this.f.h - 1 && var1 >= this.f.c.x && var1 <= this.f.c.x + this.f.w - 1) {
            var8 = (int)(0.56D * (double)var5 + 0.33D * (double)var4 + 0.11D * (double)var6);
            return var7 << 24 | var8 << 16 | var8 << 8 | var8;
         } else {
            return var7 << 24 | var4 << 16 | var5 << 8 | var6;
         }
      } else {
         var8 = (int)(0.56D * (double)var5 + 0.33D * (double)var4 + 0.11D * (double)var6);
         return var7 << 24 | var8 << 16 | var8 << 8 | var8;
      }
   }
}
