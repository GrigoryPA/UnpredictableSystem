import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class Display {
    public Color[][] display;
    public Color[][] new_display;
    public int widthImage;
    public int heightImage;
    public JFrame frame;
    public JLabel label;
    public BufferedImage image;
    public ImageIcon imageicon;
    public File file_in;
    public FileDialog openJPG;

    public Display() {
        try{
            openJPG = new FileDialog(frame, "Load image" ,FileDialog.LOAD);
            openJPG.setFile("*.jpg");
            openJPG.setVisible(true);
            file_in = new File(openJPG.getDirectory() + openJPG.getFile());
            image = ImageIO.read(file_in);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to open image !");
        }
        widthImage=image.getWidth()/2;
        heightImage=image.getHeight()/2;
        display=new Color[widthImage][heightImage];
        new_display=new Color[widthImage][heightImage];
        for (int i = 0; i < widthImage; i++)
            for (int j = 0; j < heightImage; j++) {
                new_display[i][j] = BigPixel(i, j);
            }
        return;
    }

    public Color BigPixel(int i, int j){
        int I=i*2, J=j*2;
        Color p1 = new Color(image.getRGB(I,J));
        Color p2 = new Color(image.getRGB(I+1,J));
        Color p3 = new Color(image.getRGB(I,J+1));
        Color p4 = new Color(image.getRGB(I+1,J+1));
        if((p1.getRed()+p2.getRed()+p3.getRed()+p4.getRed())/4 < 128)
            return Color.BLACK;
        else
            return Color.WHITE;
    }

    public void CreateAndOpenImage() {
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, new_display[x/2][y/2].getRGB());
            }
        CreateFrameForImage(image);
        for (int i = 0; i < widthImage; i++)
            for (int j = 0; j < heightImage; j++) {
                display[i][j] = new_display[i][j];
            }
    }

    public void UpdateImage() {
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, new_display[x/2][y/2].getRGB());
            }
        label.updateUI();
        for (int i = 0; i < widthImage; i++)
            for (int j = 0; j < heightImage; j++) {
                display[i][j] = new_display[i][j];
            }
    }

    public void CreateFrameForImage(BufferedImage image) {
        if (frame == null) {
            frame = new JFrame();
            frame.setTitle("stained_image");
            frame.setSize(image.getWidth(), image.getHeight());
            frame.setLocation(500, 50);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            label = new JLabel();
            imageicon = new ImageIcon(image);
            label.setIcon(imageicon);
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        } else label.setIcon(new ImageIcon(image));
    }

}
