
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyFrame extends JFrame implements ActionListener, ChangeListener {

   public Image img;
   public int max = 2;
   public Image[] uImg;
   public int index;
   public int num;
   public int w;
   public int h;
   public int x;
   public int y;
   public int a;
   public int b;
   public int ta;
   public int tb;
   public int[] pix;
   public String[] fnames;
   String cdir;
   Boolean chng;
   public ScrollDemo sp;
   public JScrollBar vsb;
   public JScrollBar hsb;
   public JSlider jsl;
   public JTextField jtf;
   public MyCanvas c;
   public JMenuBar mb;
   public JToolBar jtb;
   public JMenu mFile;
   public JMenu mEdit;
   public JMenu mAbout;
   public JMenu mRotation;
   public JMenu mAction;
   public JMenu mLook;
   public JMenu mPlaf;
   public JMenuItem mOpen;
   public JMenuItem mSave;
   public JMenuItem mSaveAs;
   public JMenuItem mExit;
   public JMenuItem mReset;
   public JMenuItem mInvert;
   public JMenuItem mLeft;
   public JMenuItem mRight;
   public JMenuItem mNegative;
   public JMenuItem mGray;
   public JMenuItem mContrast;
   public JMenuItem mCrop;
   public JMenuItem mDevloper;
   public JMenuItem mRedo;
   public JMenuItem mUndo;
   public JMenuItem mMac;
   public JMenuItem mMetal;
   public JMenuItem mMulti;
   public JMenuItem mSynth;
   public JMenuItem mBasic;
   public JMenuItem mMotif;
   public JMenuItem mWindow;
   public JMenuItem mWindowClassic;
   public JMenuItem mGtk;
   public JMenuItem mNimbus;
   public JMenuItem mCurrent;
   public JButton open;
   public JButton invert;
   public JButton left;
   public JButton right;
   public JButton negative;
   public JButton gray;
   public JButton contrast;
   public JButton quit;
   public JButton bPrev;
   public JButton bNext;
   public JButton crop;
   public JButton redo;
   public JButton undo;
   public JButton reset;
   public FileDialog fdl;
   public FileDialog fds;
   private static final String mac = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
   private static final String metal = "javax.swing.plaf.metal.MetalLookAndFeel";
   private static final String multi = "javax.swing.plaf.multi.MultiLookAndFeel";
   private static final String synth = "javax.swing.plaf.synth.SynthLookAndFeel";
   private static final String basic = "javax.swing.plaf.basic.BasicLookAndFeel";
   private static final String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
   private static final String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
   private static final String windowsClassic = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
   private static final String gtk = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
   private static final String nimbus = "sun.swing.plaf.nimbus.NimbusLookAndFeel";
   private static final String current = UIManager.getSystemLookAndFeelClassName();


   MyFrame() {
      super("Image Editor");
      this.uImg = new Image[this.max + 1];
      this.index = 0;
      this.num = 0;
      this.w = 0;
      this.h = 0;
      this.fnames = new String[]{"01.jpg", "07.jpg", "12.jpg", "24.jpg", "02.jpg", "last.jpg"};
      this.cdir = "";
      this.chng = Boolean.valueOf(true);
      this.c = null;

      try {
         SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
               MyFrame.this.display();
            }
         });
         UIManager.setLookAndFeel("sun.swing.plaf.nimbus.NimbusLookAndFeel");
         SwingUtilities.updateComponentTreeUI(this);
         LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();

         for(int var2 = 0; var2 < var1.length; ++var2) {
            String var3 = var1[var2].getClassName();
            System.out.println(var3);
         }
      } catch (Exception var4) {
         ;
      }

      this.addWindowListener(new MyWndAdapter(this));
   }

   public ImageIcon createImageIcon(String var1) {
      String var2 = "/resources/images" + var1;
      System.out.println(this.getClass().getResource(var2));
      return new ImageIcon(this.getClass().getResource(var2));
   }

   public Image createImageObj(String var1) {
      String var2 = "/resources/images/sampleimages/" + var1;
      System.out.println(this.getClass().getResource(var2));
      return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(var2));
   }

   public void display() {
      Dimension var1 = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(var1.width, var1.height - 30);
      File var2 = new File(".");
      System.out.println(var2.getAbsolutePath());
      this.setLayout(new BorderLayout(10, 10));
      this.mb = new JMenuBar();
      this.jtb = new JToolBar();

      try {
         String var3 = "/icons/redo.jpg";
         this.redo = new JButton(this.createImageIcon(var3));
         this.redo.setToolTipText("redo");
         this.redo.addActionListener(this);
         this.redo.setEnabled(false);
         var3 = "/icons/undo.jpg";
         this.undo = new JButton(this.createImageIcon(var3));
         this.undo.setToolTipText("Undo");
         this.undo.addActionListener(this);
         this.undo.setEnabled(false);
         var3 = "/icons/reset.jpg";
         this.reset = new JButton(this.createImageIcon(var3));
         this.reset.setToolTipText("Undo");
         this.reset.addActionListener(this);
         this.reset.setEnabled(true);
         var3 = "/icons/openimage.jpg";
         this.open = new JButton(this.createImageIcon(var3));
         this.open.setToolTipText("Open Image");
         this.open.addActionListener(this);
         var3 = "/icons/exit.jpg";
         this.quit = new JButton(this.createImageIcon(var3));
         this.quit.setToolTipText("exit");
         this.quit.addActionListener(this);
         var3 = "/icons/prev.jpg";
         this.bPrev = new JButton(this.createImageIcon(var3));
         this.bPrev.setToolTipText("prev-image");
         this.bPrev.addActionListener(this);
         var3 = "/icons/next.jpg";
         this.bNext = new JButton(this.createImageIcon(var3));
         this.bNext.setToolTipText("next-image");
         this.bNext.addActionListener(this);
         var3 = "/icons/right.jpg";
         this.right = new JButton(this.createImageIcon(var3));
         this.right.setToolTipText("right-rotation");
         this.right.addActionListener(this);
         var3 = "/icons/left.jpg";
         this.left = new JButton(this.createImageIcon(var3));
         this.left.setToolTipText("left-rotation");
         this.left.addActionListener(this);
         var3 = "/icons/invert1.jpg";
         this.invert = new JButton(this.createImageIcon(var3));
         this.invert.setToolTipText("left-rotation");
         this.invert.addActionListener(this);
         var3 = "/icons/crop.jpg";
         this.crop = new JButton(this.createImageIcon(var3));
         this.crop.setToolTipText("crop-selected");
         this.crop.addActionListener(this);
         var3 = "/icons/neg.jpg";
         this.negative = new JButton(this.createImageIcon(var3));
         this.negative.setToolTipText("negative");
         this.negative.addActionListener(this);
         var3 = "/icons/cont.jpg";
         this.contrast = new JButton(this.createImageIcon(var3));
         this.contrast.setToolTipText("contrast");
         this.contrast.addActionListener(this);
         var3 = "/icons/gray.jpg";
         this.gray = new JButton(this.createImageIcon(var3));
         this.gray.setToolTipText("black-white");
         this.gray.addActionListener(this);
      } catch (Exception var6) {
         System.err.println(var6);
      }

      JPanel var7 = new JPanel();
      var7.setLayout(new BorderLayout(0, 0));
      JPanel var4 = new JPanel();
      var4.setLayout(new GridLayout(1, 3, 20, 0));
      this.jsl = new JSlider(0, 0, 255, 255);
      this.jsl.putClientProperty("JSlider.isFilled", Boolean.FALSE);
      this.jsl.setPaintTicks(false);
      this.jsl.setMajorTickSpacing(25);
      this.jsl.setMinorTickSpacing(5);
      this.jsl.setPaintLabels(true);
      JLabel var5 = new JLabel("      Transparency");
      this.jtf = new JTextField();
      this.jtf.setText("255");
      this.jtf.setFont(new Font("lucida console", 1, 22));
      var4.add(var5);
      var4.add(new JLabel());
      var4.add(this.jtf);
      var7.add(var4, "North");
      var7.add(this.jsl, "Center");
      this.jtb.add(this.open);
      this.jtb.addSeparator();
      this.jtb.add(this.left);
      this.jtb.add(this.right);
      this.jtb.addSeparator();
      this.jtb.add(this.invert);
      this.jtb.addSeparator();
      this.jtb.add(this.bPrev);
      this.jtb.add(this.bNext);
      this.jtb.addSeparator();
      this.jtb.add(this.undo);
      this.jtb.add(this.redo);
      this.jtb.addSeparator();
      this.jtb.add(this.crop);
      this.jtb.add(this.negative);
      this.jtb.add(this.contrast);
      this.jtb.add(this.gray);
      this.jtb.addSeparator();
      this.jtb.add(this.reset);
      this.jtb.addSeparator();
      this.jtb.add(var7);
      this.jtb.addSeparator();
      this.jtb.add(this.quit);
      this.jtb.addSeparator();
      this.mFile = new JMenu("File");
      this.mEdit = new JMenu("Edit");
      this.mRotation = new JMenu("Rotation");
      this.mAction = new JMenu("Action");
      this.mLook = new JMenu("Look & Feel");
      this.mPlaf = new JMenu("Platfrm");
      this.mAbout = new JMenu("About");
      this.mOpen = new JMenuItem("open..");
      this.mSave = new JMenuItem("save");
      this.mSaveAs = new JMenuItem("saveAs");
      this.mExit = new JMenuItem("Exit");
      this.mFile.add(this.mOpen);
      this.mFile.add(this.mSave);
      this.mFile.add(this.mSaveAs);
      this.mFile.addSeparator();
      this.mFile.add(this.mExit);
      this.mLeft = new JMenuItem("left");
      this.mRight = new JMenuItem("right");
      this.mCrop = new JMenuItem("crop");
      this.mNegative = new JMenuItem("negative");
      this.mInvert = new JMenuItem("invert");
      this.mGray = new JMenuItem("gray");
      this.mContrast = new JMenuItem("contrast");
      this.mReset = new JMenuItem("reset");
      this.mRedo = new JMenuItem("redo");
      this.mRedo.setEnabled(false);
      this.mUndo = new JMenuItem("undo");
      this.mUndo.setEnabled(false);
      this.mEdit.add(this.mReset);
      this.mEdit.add(this.mRedo);
      this.mEdit.add(this.mUndo);
      this.mEdit.add(this.mAction);
      this.mEdit.add(this.mRotation);
      this.mAction.add(this.mCrop);
      this.mAction.add(this.mGray);
      this.mAction.add(this.mNegative);
      this.mAction.add(this.mContrast);
      this.mRotation.add(this.mInvert);
      this.mRotation.add(this.mLeft);
      this.mRotation.add(this.mRight);
      this.mDevloper = new JMenuItem("lets see who is he ?");
      this.mDevloper.addActionListener(this);
      this.mAbout.add(this.mDevloper);
      this.mMac = new JMenuItem("Mac Look & feel");
      this.mMetal = new JMenuItem("Metal look & feel");
      this.mMulti = new JMenuItem("Multi look & feel");
      this.mSynth = new JMenuItem("Synth look & feel");
      this.mBasic = new JMenuItem("Basic look & feel");
      this.mMotif = new JMenuItem("Motif look & feel");
      this.mWindow = new JMenuItem("Window look & feel");
      this.mWindowClassic = new JMenuItem("WindowClassic look & feel");
      this.mGtk = new JMenuItem("GTK look and feel");
      this.mNimbus = new JMenuItem("Nimbus look and feel");
      this.mCurrent = new JMenuItem("Current system look and feel");
      this.mLook.add(this.mMac);
      this.mLook.add(this.mMotif);
      this.mLook.add(this.mMetal);
      this.mLook.add(this.mWindow);
      this.mLook.add(this.mWindowClassic);
      this.mLook.add(this.mPlaf);
      this.mLook.add(this.mGtk);
      this.mLook.add(this.mNimbus);
      this.mLook.add(this.mCurrent);
      this.mPlaf.add(this.mSynth);
      this.mPlaf.add(this.mBasic);
      this.mPlaf.add(this.mMulti);
      this.mPlaf.setEnabled(false);
      this.mMac.setEnabled(this.isAvailableLook("com.sun.java.swing.plaf.mac.MacLookAndFeel").booleanValue());
      this.mMetal.setEnabled(this.isAvailableLook("javax.swing.plaf.metal.MetalLookAndFeel").booleanValue());
      this.mMulti.setEnabled(this.isAvailableLook("javax.swing.plaf.multi.MultiLookAndFeel").booleanValue());
      this.mSynth.setEnabled(this.isAvailableLook("javax.swing.plaf.synth.SynthLookAndFeel").booleanValue());
      this.mBasic.setEnabled(this.isAvailableLook("javax.swing.plaf.basic.BasicLookAndFeel").booleanValue());
      this.mMotif.setEnabled(this.isAvailableLook("com.sun.java.swing.plaf.motif.MotifLookAndFeel").booleanValue());
      this.mWindow.setEnabled(this.isAvailableLook("com.sun.java.swing.plaf.windows.WindowsLookAndFeel").booleanValue());
      this.mWindowClassic.setEnabled(this.isAvailableLook("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel").booleanValue());
      this.mGtk.setEnabled(this.isAvailableLook("com.sun.java.swing.plaf.gtk.GTKLookAndFeel").booleanValue());
      this.mNimbus.setEnabled(this.isAvailableLook("sun.swing.plaf.nimbus.NimbusLookAndFeel").booleanValue());
      this.mCurrent.setEnabled(this.isAvailableLook(current).booleanValue());
      this.setIconImage(this.createImageObj("icon.jpg"));
      this.img = this.createImageObj("24.jpg");
      this.sp = new ScrollDemo(this.img, 1, this);
      this.c = this.sp.c;
      this.c.load = Boolean.valueOf(true);
      this.setTitle("24.jpg-Image Editer");
      this.mOpen.addActionListener(this);
      this.mSave.addActionListener(this);
      this.mSaveAs.addActionListener(this);
      this.mExit.addActionListener(this);
      this.mInvert.addActionListener(this);
      this.mLeft.addActionListener(this);
      this.mRight.addActionListener(this);
      this.mCrop.addActionListener(this);
      this.mGray.addActionListener(this);
      this.mNegative.addActionListener(this);
      this.mContrast.addActionListener(this);
      this.mReset.addActionListener(this);
      this.mAbout.addActionListener(this);
      this.mRedo.addActionListener(this);
      this.mUndo.addActionListener(this);
      this.mMac.addActionListener(new ChangeLookAndFeelAction(this, "com.sun.java.swing.plaf.mac.MacLookAndFeel"));
      this.mMetal.addActionListener(new ChangeLookAndFeelAction(this, "javax.swing.plaf.metal.MetalLookAndFeel"));
      this.mMulti.addActionListener(new ChangeLookAndFeelAction(this, "javax.swing.plaf.multi.MultiLookAndFeel"));
      this.mSynth.addActionListener(new ChangeLookAndFeelAction(this, "javax.swing.plaf.synth.SynthLookAndFeel"));
      this.mBasic.addActionListener(new ChangeLookAndFeelAction(this, "javax.swing.plaf.basic.BasicLookAndFeel"));
      this.mMotif.addActionListener(new ChangeLookAndFeelAction(this, "com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
      this.mWindow.addActionListener(new ChangeLookAndFeelAction(this, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
      this.mWindowClassic.addActionListener(new ChangeLookAndFeelAction(this, "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"));
      this.mGtk.addActionListener(new ChangeLookAndFeelAction(this, "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"));
      this.mNimbus.addActionListener(new ChangeLookAndFeelAction(this, "sun.swing.plaf.nimbus.NimbusLookAndFeel"));
      this.mCurrent.addActionListener(new ChangeLookAndFeelAction(this, current));
      this.jsl.addChangeListener(this);
      this.mb.add(this.mFile);
      this.mb.add(this.mEdit);
      this.mb.add(this.mLook);
      this.mb.add(this.mAbout);
      this.setJMenuBar(this.mb);
      this.add(this.jtb, "North");
      this.add(this.sp, "Center");
      this.c.addMouseListener(new MyMouseAdapter(this));
      this.c.addMouseMotionListener(new MyMouseMotionAdapter(this));
   }

   public void stateChanged(ChangeEvent var1) {
      this.jtf.setText(String.valueOf(this.jsl.getValue()));
      int var2 = this.c.newImg.getWidth((ImageObserver)null);
      int var3 = this.c.newImg.getHeight((ImageObserver)null);
      this.c.cropImg = null;
      int[] var4 = new int[var2 * var3];
      PixelGrabber var5 = new PixelGrabber(this.c.newImg, 0, 0, var2, var3, var4, 0, var2);

      try {
         var5.grabPixels();
      } catch (InterruptedException var11) {
         ;
      }

      this.chng = Boolean.valueOf(false);
      this.c.newImg = null;
      int[] var6 = new int[var2 * var3];

      for(int var7 = 0; var7 < var2 * var3; ++var7) {
         int var8 = var4[var7] >> 16 & 255;
         int var9 = var4[var7] >> 8 & 255;
         int var10 = var4[var7] & 255;
         var6[var7] = this.jsl.getValue() << 24 | var8 << 16 | var9 << 8 | var10;
      }

      this.c.newImg = this.createImage(new MemoryImageSource(var2, var3, var6, 0, var2));
      this.c.repaint();
      this.c.setSize(1500, 1000);
   }

   public Boolean isAvailableLook(String var1) {
      try {
         Class var2 = Class.forName(var1);
         LookAndFeel var3 = (LookAndFeel)((LookAndFeel)var2.newInstance());
         return Boolean.valueOf(var3.isSupportedLookAndFeel());
      } catch (Exception var4) {
         return Boolean.valueOf(false);
      }
   }

   public void putImage(Image var1) {
      if(var1 == null) {
         this.mUndo.setEnabled(false);
         this.undo.setEnabled(false);
         this.mRedo.setEnabled(false);
         this.redo.setEnabled(false);
      } else {
         this.mUndo.setEnabled(true);
         this.undo.setEnabled(true);
         int var2;
         if(this.num >= this.max) {
            if(this.uImg[this.max] != null) {
               for(var2 = 0; var2 < this.max; ++var2) {
                  this.uImg[var2] = this.uImg[var2 + 1];
               }
            }

            this.uImg[this.max] = var1;
            if(this.num < this.max + 1) {
               ++this.num;
            }

            this.mRedo.setEnabled(false);
            this.redo.setEnabled(false);
         } else {
            if(this.uImg[this.num] != null) {
               for(var2 = this.num + 1; var2 <= this.max; ++var2) {
                  this.uImg[var2] = null;
               }

               this.mRedo.setEnabled(false);
               this.redo.setEnabled(false);
            }

            this.uImg[this.num] = var1;
            ++this.num;
         }
      }

   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2;
      PixelGrabber var3;
      if(var1.getSource() != this.crop && var1.getSource() != this.mCrop) {
         if(var1.getSource() == this.mDevloper) {
            About var25 = new About(this);
            var25.setVisible(true);
         } else {
            MediaTracker var26;
            if(var1.getSource() != this.undo && var1.getSource() != this.mUndo) {
               if(var1.getSource() != this.redo && var1.getSource() != this.mRedo) {
                  int var27;
                  if(var1.getSource() == this.bPrev) {
                     for(var27 = 0; var27 <= this.max; ++var27) {
                        this.uImg[var27] = null;
                     }

                     this.num = 0;
                     this.img = null;
                     this.c.newImg = null;
                     this.c.cropImg = null;
                     this.index = this.index > 0?this.index - 1:this.fnames.length - 1;
                     if(this.cdir != "") {
                        this.img = Toolkit.getDefaultToolkit().getImage(this.cdir + this.fnames[this.index]);
                     } else {
                        this.img = this.createImageObj(this.fnames[this.index]);
                     }

                     var26 = new MediaTracker(this);
                     var26.addImage(this.img, 0);

                     try {
                        var26.waitForID(0);
                     } catch (InterruptedException var21) {
                        ;
                     }

                     this.c.newImg = this.img;
                     this.c.action = "image";
                     this.c.load = Boolean.valueOf(true);
                     this.num = 0;
                     this.putImage((Image)null);
                     this.chng = Boolean.valueOf(true);
                     this.w = this.h = this.c.w = this.c.h = 0;
                     this.setTitle(this.fnames[this.index] + "-Image Editer");
                  } else if(var1.getSource() == this.bNext) {
                     for(var27 = 1; var27 <= this.max; ++var27) {
                        this.uImg[var27] = null;
                     }

                     this.num = 0;
                     this.img = null;
                     this.c.newImg = null;
                     this.c.cropImg = null;
                     this.index = this.index < this.fnames.length - 1?this.index + 1:0;
                     if(this.cdir != "") {
                        this.img = Toolkit.getDefaultToolkit().getImage(this.cdir + this.fnames[this.index]);
                     } else {
                        this.img = this.createImageObj(this.fnames[this.index]);
                     }

                     var26 = new MediaTracker(this);
                     var26.addImage(this.img, 0);

                     try {
                        var26.waitForID(0);
                     } catch (InterruptedException var20) {
                        ;
                     }

                     this.c.newImg = this.img;
                     this.c.load = Boolean.valueOf(true);
                     this.chng = Boolean.valueOf(true);
                     this.num = 0;
                     this.putImage((Image)null);
                     this.c.action = "image";
                     this.w = this.h = this.c.w = this.c.h = 0;
                     this.setTitle(this.fnames[this.index] + "-Image Editer");
                  } else if(var1.getSource() != this.reset && var1.getSource() != this.mReset) {
                     int[] var4;
                     int[] var6;
                     int var7;
                     if(var1.getSource() != this.invert && var1.getSource() != this.mInvert) {
                        int var30;
                        int var36;
                        if(var1.getSource() != this.left && var1.getSource() != this.mLeft) {
                           if(var1.getSource() != this.right && var1.getSource() != this.mRight) {
                              MediaTracker var29;
                              int var33;
                              PixelGrabber var42;
                              if(var1.getSource() != this.gray && var1.getSource() != this.mGray) {
                                 if(var1.getSource() != this.negative && var1.getSource() != this.mNegative) {
                                    if(var1.getSource() != this.contrast && var1.getSource() != this.mContrast) {
                                       if(var1.getSource() != this.quit && var1.getSource() != this.mExit) {
                                          if(var1.getSource() != this.mOpen && var1.getSource() != this.open) {
                                             if(var1.getSource() == this.mSave) {
                                                this.c.cropImg = null;
                                             } else if(var1.getSource() == this.mSaveAs) {
                                                this.c.cropImg = null;
                                                FileDialog var39 = new FileDialog(this, "Save File", 1);
                                                var39.setVisible(true);
                                                this.w = this.c.newImg.getWidth((ImageObserver)null);
                                                this.h = this.c.newImg.getHeight((ImageObserver)null);
                                                BufferedImage var35 = new BufferedImage(this.w, this.h, 4);
                                                var35.createGraphics().drawImage(this.c.newImg, 0, 0, this);

                                                try {
                                                   ImageIO.write(var35, "JPEG", new File(var39.getDirectory() + var39.getFile()));
                                                } catch (IOException var10) {
                                                   ;
                                                }
                                             }
                                          } else {
                                             for(var27 = 0; var27 <= this.max; ++var27) {
                                                this.uImg[var27] = null;
                                             }

                                             this.num = 0;
                                             this.putImage((Image)null);
                                             this.c.cropImg = null;
                                             this.fdl = new FileDialog(this, "Open file");
                                             this.fdl.setVisible(true);
                                             this.cdir = this.fdl.getDirectory();
                                             File var40 = new File(this.fdl.getDirectory());
                                             FileFilter var31 = new FileFilter();
                                             this.fnames = var40.list(var31);
                                             this.img = Toolkit.getDefaultToolkit().getImage(this.fdl.getDirectory() + this.fdl.getFile());

                                             for(var33 = 0; var33 < this.fnames.length; ++var33) {
                                                if(this.fnames[var33].equals(this.fdl.getFile())) {
                                                   this.index = var33;
                                                   break;
                                                }
                                             }

                                             this.c.newImg = this.img;
                                             this.c.load = Boolean.valueOf(true);
                                             this.chng = Boolean.valueOf(true);
                                          }
                                       } else {
                                          this.dispose();
                                       }
                                    } else {
                                       this.c.action = "image";
                                       this.c.cropImg = null;
                                       Contrast var32 = new Contrast();
                                       this.c.newImg = var32.filter(this.c.newImg, this);
                                       MediaTracker var34 = new MediaTracker(this);
                                       var34.addImage(this.c.newImg, 0);

                                       try {
                                          var34.waitForID(0);
                                       } catch (InterruptedException var12) {
                                          ;
                                       }

                                       var30 = this.c.newImg.getWidth((ImageObserver)null);
                                       var36 = this.c.newImg.getHeight((ImageObserver)null);
                                       int[] var43 = new int[var30 * var36];
                                       PixelGrabber var41 = new PixelGrabber(this.c.newImg, 0, 0, var30, var36, var43, 0, var30);

                                       try {
                                          var41.grabPixels();
                                       } catch (InterruptedException var11) {
                                          ;
                                       }

                                       this.c.newImg = this.createImage(new MemoryImageSource(var30, var36, var43, 0, var30));
                                       this.putImage(this.c.newImg);
                                       this.chng = Boolean.valueOf(true);
                                    }
                                 } else {
                                    this.c.action = "image";
                                    this.c.cropImg = null;
                                    Negative var37 = new Negative();
                                    this.c.newImg = var37.filter(this.c.newImg, this);
                                    var29 = new MediaTracker(this);
                                    var29.addImage(this.c.newImg, 0);

                                    try {
                                       var29.waitForID(0);
                                    } catch (InterruptedException var14) {
                                       ;
                                    }

                                    var33 = this.c.newImg.getWidth((ImageObserver)null);
                                    var30 = this.c.newImg.getHeight((ImageObserver)null);
                                    var6 = new int[var33 * var30];
                                    var42 = new PixelGrabber(this.c.newImg, 0, 0, var33, var30, var6, 0, var33);

                                    try {
                                       var42.grabPixels();
                                    } catch (InterruptedException var13) {
                                       ;
                                    }

                                    this.c.newImg = this.createImage(new MemoryImageSource(var33, var30, var6, 0, var33));
                                    this.putImage(this.c.newImg);
                                    this.chng = Boolean.valueOf(true);
                                 }
                              } else {
                                 this.c.cropImg = null;
                                 this.c.action = "image";
                                 GrayScale var38 = new GrayScale();
                                 this.c.newImg = var38.filter(this.c.newImg, this);
                                 var29 = new MediaTracker(this);
                                 var29.addImage(this.c.newImg, 0);

                                 try {
                                    var29.waitForID(0);
                                 } catch (InterruptedException var16) {
                                    ;
                                 }

                                 var33 = this.c.newImg.getWidth((ImageObserver)null);
                                 var30 = this.c.newImg.getHeight((ImageObserver)null);
                                 var6 = new int[var33 * var30];
                                 var42 = new PixelGrabber(this.c.newImg, 0, 0, var33, var30, var6, 0, var33);

                                 try {
                                    var42.grabPixels();
                                 } catch (InterruptedException var15) {
                                    ;
                                 }

                                 this.c.newImg = this.createImage(new MemoryImageSource(var33, var30, var6, 0, var33));
                                 this.putImage(this.c.newImg);
                                 this.chng = Boolean.valueOf(true);
                              }
                           } else {
                              this.c.action = "image";
                              this.c.cropImg = null;
                              this.w = this.c.newImg.getWidth((ImageObserver)null);
                              this.h = this.c.newImg.getHeight((ImageObserver)null);
                              var2 = new int[this.w * this.h];
                              var3 = new PixelGrabber(this.c.newImg, 0, 0, this.w, this.h, var2, 0, this.w);

                              try {
                                 var3.grabPixels();
                              } catch (InterruptedException var17) {
                                 ;
                              }

                              this.c.newImg = null;
                              var4 = new int[this.w * this.h];

                              for(var30 = 0; var30 < this.h; ++var30) {
                                 for(var36 = 0; var36 < this.w; ++var36) {
                                    var4[var36 * this.h + this.h - var30 - 1] = var2[var30 * this.w + var36];
                                 }
                              }

                              this.c.newImg = this.createImage(new MemoryImageSource(this.h, this.w, var4, 0, this.h));
                              this.putImage(this.c.newImg);
                              this.chng = Boolean.valueOf(true);
                           }
                        } else {
                           this.c.action = "image";
                           this.c.cropImg = null;
                           this.w = this.c.newImg.getWidth((ImageObserver)null);
                           this.h = this.c.newImg.getHeight((ImageObserver)null);
                           var2 = new int[this.w * this.h];
                           var3 = new PixelGrabber(this.c.newImg, 0, 0, this.w, this.h, var2, 0, this.w);

                           try {
                              var3.grabPixels();
                           } catch (InterruptedException var18) {
                              ;
                           }

                           this.c.newImg = null;
                           var4 = new int[this.w * this.h];
                           var30 = 0;

                           for(var36 = this.h; var36 > 0; --var36) {
                              for(var7 = this.w; var7 > 0; --var7) {
                                 var4[var7 * this.h - var36] = var2[var30++];
                              }
                           }

                           this.c.newImg = this.createImage(new MemoryImageSource(this.h, this.w, var4, 0, this.h));
                           this.putImage(this.c.newImg);
                           this.c.load = Boolean.valueOf(true);
                           this.chng = Boolean.valueOf(true);
                        }
                     } else {
                        this.c.action = "image";
                        this.c.cropImg = null;
                        var27 = this.c.newImg.getWidth((ImageObserver)null);
                        int var28 = this.c.newImg.getHeight((ImageObserver)null);
                        var4 = new int[var27 * var28];
                        PixelGrabber var5 = new PixelGrabber(this.c.newImg, 0, 0, var27, var28, var4, 0, var27);

                        try {
                           var5.grabPixels();
                        } catch (InterruptedException var19) {
                           ;
                        }

                        this.c.newImg = null;
                        var6 = new int[var27 * var28];
                        int var8;
                        int var9;
                        if(this.w * this.h > 0) {
                           var7 = this.b;

                           for(var8 = 0; var8 < var28; ++var8) {
                              for(var9 = 0; var9 < var27; ++var9) {
                                 if(var8 >= this.c.y && var8 <= this.b && var9 >= this.c.x && var9 <= this.a) {
                                    var6[var8 * var27 + var9 + (var7 - var8) * var27] = var4[var8 * var27 + var9];
                                 } else {
                                    var6[var8 * var27 + var9] = var4[var8 * var27 + var9];
                                 }
                              }

                              if(var8 >= this.c.y && var8 <= this.b) {
                                 --var7;
                              }
                           }
                        } else {
                           var7 = 0;

                           for(var8 = var28 - 1; var7 < var28; --var8) {
                              for(var9 = 0; var9 < var27; ++var9) {
                                 var6[var7 * var27 + var9 + (var8 - var7) * var27] = var4[var7 * var27 + var9];
                              }

                              ++var7;
                           }
                        }

                        this.c.newImg = this.createImage(new MemoryImageSource(var27, var28, var6, 0, var27));
                        this.c.load = Boolean.valueOf(true);
                        this.putImage(this.c.newImg);
                        this.chng = Boolean.valueOf(true);
                        this.c.load = Boolean.valueOf(true);
                        this.chng = Boolean.valueOf(true);
                     }
                  } else {
                     for(var27 = 0; var27 <= this.max; ++var27) {
                        this.uImg[var27] = null;
                     }

                     this.num = 0;
                     this.putImage((Image)null);
                     this.c.action = "image";
                     this.c.cropImg = null;
                     this.c.newImg = this.img;
                     this.c.load = Boolean.valueOf(true);
                     this.chng = Boolean.valueOf(true);
                  }
               } else {
                  this.c.cropImg = null;
                  System.out.println("num =" + this.num);
                  if(this.uImg[this.num] != null) {
                     ++this.num;
                     this.mUndo.setEnabled(true);
                     this.undo.setEnabled(true);
                     if(this.num - 1 == this.max) {
                        this.mRedo.setEnabled(false);
                        this.redo.setEnabled(false);
                     }
                  } else {
                     this.mRedo.setEnabled(false);
                     this.redo.setEnabled(false);
                  }

                  var26 = new MediaTracker(this);
                  var26.addImage(this.uImg[this.num - 1], 0);

                  try {
                     var26.waitForID(0);
                  } catch (InterruptedException var22) {
                     ;
                  }

                  this.c.newImg = this.uImg[this.num - 1];
                  this.c.load = Boolean.valueOf(true);
                  this.chng = Boolean.valueOf(true);
                  this.c.action = "image";
                  System.out.println("num =" + this.num);
               }
            } else {
               this.c.cropImg = null;
               System.out.println("num =" + this.num);
               if(this.uImg[this.num - 1] != null) {
                  --this.num;
                  this.mRedo.setEnabled(true);
                  this.redo.setEnabled(true);
                  if(this.num == 0) {
                     this.mUndo.setEnabled(false);
                     this.undo.setEnabled(false);
                  }
               } else {
                  this.mUndo.setEnabled(false);
                  this.undo.setEnabled(false);
               }

               var26 = new MediaTracker(this);
               if(this.num != 0) {
                  var26.addImage(this.uImg[this.num - 1], 0);

                  try {
                     var26.waitForID(0);
                  } catch (InterruptedException var23) {
                     ;
                  }

                  this.c.newImg = this.uImg[this.num - 1];
               } else {
                  this.c.newImg = this.uImg[this.num];
               }

               this.chng = Boolean.valueOf(true);
               this.c.action = "image";
               System.out.println("num =" + this.num);
            }
         }
      } else {
         this.c.cropImg = null;
         if(this.w * this.h > 0) {
            var2 = new int[this.w * this.h];
            var3 = new PixelGrabber(this.c.newImg, this.c.x, this.c.y, this.w, this.h, var2, 0, this.w);

            try {
               var3.grabPixels();
            } catch (InterruptedException var24) {
               ;
            }

            this.c.newImg = this.createImage(new MemoryImageSource(this.w, this.h, var2, 0, this.w));
            this.c.load = Boolean.valueOf(true);
            this.putImage(this.c.newImg);
            this.chng = Boolean.valueOf(true);
            this.c.action = "image";
         }
      }

      this.c.repaint();
   }

   public Image create(int var1, int var2, int var3, int var4) {
      this.w = var3 - var1;
      this.h = var4 - var2;
      if(this.w < 0) {
         this.w = -1 * this.w;
      }

      if(this.h < 0) {
         this.h = -1 * this.h;
      }

      int[] var5 = new int[this.w * this.h];

      for(int var6 = 0; var6 < var5.length; ++var6) {
         var5[var6] = -1857007361;
      }

      return this.createImage(new MemoryImageSource(this.w, this.h, var5, 0, this.w));
   }

   public Insets getInsets() {
      return new Insets(30, 0, 0, 0);
   }

   public void setLookAndFeel(String var1) {
      try {
         UIManager.setLookAndFeel(var1);
         SwingUtilities.updateComponentTreeUI(this);
      } catch (Exception var3) {
         System.out.println(var3);
      }

   }

}
