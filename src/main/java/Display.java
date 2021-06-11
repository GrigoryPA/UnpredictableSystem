import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {
    public Color[][] display;
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
        widthImage=image.getWidth();
        heightImage=image.getHeight();
        display=new Color[widthImage][heightImage];
        for (int i = 0; i < widthImage; i++)
            for (int j = 0; j < heightImage; j++)
                display[i][j] = new Color(image.getRGB(i,j));

        return;
    }

    public void AddPointOnDisplay(int x, int y, Color color) {
        display[x][y] = color;
    }

    public void CreateAndOpenImage() {
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, display[x][y].getRGB());
            }
        CreateFrameForImage(image);
    }

    public void UpdateImage() {
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, display[x][y].getRGB());
            }
        //imageicon.setImage(image);
        label.updateUI();
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
