
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

class Negative extends RGBImageFilter {

   MyFrame f;


   public Image filter(Image var1, MyFrame var2) {
      this.f = var2;
      return var2.createImage(new FilteredImageSource(var1.getSource(), this));
   }

   public int filterRGB(int var1, int var2, int var3) {
      int var7 = var3 >> 24 & 255;
      int var4;
      int var5;
      int var6;
      if(this.f.w > 0 && this.f.h > 0) {
         if(var2 >= this.f.c.y && var2 <= this.f.c.y + this.f.h - 1 && var1 >= this.f.c.x && var1 <= this.f.c.x + this.f.w - 1 && this.f.w > 0 && this.f.h > 0) {
            var4 = 255 - (var3 >> 16) & 255;
            var5 = 255 - (var3 >> 8) & 255;
            var6 = 255 - var3 & 255;
            return var7 << 24 | var4 << 16 | var5 << 8 | var6;
         } else {
            var4 = var3 >> 16 & 255;
            var5 = var3 >> 8 & 255;
            var6 = var3 & 255;
            return var7 << 24 | var4 << 16 | var5 << 8 | var6;
         }
      } else {
         var4 = 255 - (var3 >> 16) & 255;
         var5 = 255 - (var3 >> 8) & 255;
         var6 = 255 - var3 & 255;
         return var7 << 24 | var4 << 16 | var5 << 8 | var6;
      }
   }
}
