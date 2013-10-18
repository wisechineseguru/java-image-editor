
import java.io.File;

import java.io.FilenameFilter;

class FileFilter implements FilenameFilter {

   public boolean accept(File f, String fileName) {
      return fileName.endsWith(".jpg") || fileName.endsWith(".JPG");
   }
}
