
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Contrast extends RGBImageFilter {

   MyFrame f;
   double gain = 1.1D;


   public Image filter(Image curImage, MyFrame curFrame) {
      this.f = curFrame;
      return curFrame.createImage(new FilteredImageSource(curImage.getSource(), this));
   }

   public int multclamp(int curImage, double curFrame) {
      curImage = (int)((double)curImage * curFrame);
      return curImage > 255?255:curImage;
   }

   public int cont(int curImage) {
      return curImage < 128?(int)((double)curImage / this.gain):this.multclamp(curImage, this.gain);
   }

   public int filterRGB(int curImage, int curFrame, int var3) {
      int var7 = var3 >> 24 & 255;
      int var4;
      int var5;
      int var6;
      if(this.f.w > 0 && this.f.h > 0) {
         if(curFrame >= this.f.c.y && curFrame <= this.f.c.y + this.f.h - 1 && curImage >= this.f.c.x && curImage <= this.f.c.x + this.f.w - 1 && this.f.w > 0 && this.f.h > 0) {
            var4 = this.cont(var3 >> 16 & 255);
            var5 = this.cont(var3 >> 8 & 255);
            var6 = this.cont(var3 & 255);
            return var7 << 24 | var4 << 16 | var5 << 8 | var6;
         } else {
            var4 = var3 >> 16 & 255;
            var5 = var3 >> 8 & 255;
            var6 = var3 & 255;
            return var7 << 24 | var4 << 16 | var5 << 8 | var6;
         }
      } else {
         var4 = this.cont(var3 >> 16 & 255);
         var5 = this.cont(var3 >> 8 & 255);
         var6 = this.cont(var3 & 255);
         return var7 << 24 | var4 << 16 | var5 << 8 | var6;
      }
   }
}
