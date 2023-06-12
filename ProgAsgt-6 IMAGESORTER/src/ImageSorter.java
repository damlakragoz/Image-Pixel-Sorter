import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class ImageSorter extends JFrame implements ActionListener,KeyListener{
    public static BufferedImage image;
    static JLabel label;


    static int delay=1;
    Timer timer;

    static double[][] ORbrightness;
    static int[][] ORRGB;
    static double[][] brightness;
    static int[][] RGB;



    public static void main(String[] args) throws IOException {
            ImageSorter is = new ImageSorter();
            is.startAnimatedBubbleSort();
            
    }

    ImageSorter() throws IOException {
        this.setTitle("Image Sorter!");
        this.loadImage("res/ss.png");
    }

    public void loadImage(String fileName) throws IOException {
        File file= new File(fileName);
        image = ImageIO.read(file);
        brightness = new double[image.getWidth()][image.getHeight()];
        RGB = new int[image.getWidth()][image.getHeight()];

        //stores ORiginal pixel values(rgb and brightness)
        ORbrightness = new double[image.getWidth()][image.getHeight()];
        ORRGB = new int[image.getWidth()][image.getHeight()];

        for (int i = 0 ; i < image.getWidth() ; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(i, j);
                Color pxlclr = new Color(rgb, true);
                int r = pxlclr.getRed();
                int g = pxlclr.getGreen();
                int b = pxlclr.getBlue();
                double brgthness = calcBrightness(r, g, b);
                ORbrightness[i][j] = brgthness;
                ORRGB[i][j] = pxlclr.getRGB();
            }
        }

    }

    public void displayImage(){
        this.setSize(image.getWidth(), image.getHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        label=new JLabel();
        label.setIcon(new ImageIcon(image));
        this.getContentPane().add(label,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public double calcBrightness(float R,float G, float B) {
        return (0.2126 * R + 0.7152 * G + 0.0722 * B);
    }

    public void horizontalStep(){
        //sort each horizontal line of the image based on color’s brightness value
        double[][] brightness = new double[image.getWidth()][image.getHeight()];
        int[][] RGB = new int[image.getWidth()][image.getHeight()];

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                int rgb = image.getRGB(i, j);
                Color pxlclr = new Color(rgb, true);
                int r = pxlclr.getRed();
                int g = pxlclr.getGreen();
                int b = pxlclr.getBlue();
                double brgthness = calcBrightness(r, g, b);
                brightness[i][j] = brgthness;
                RGB[i][j] = pxlclr.getRGB();

            }
        }


        for (int j = 0; j < image.getHeight(); j++){
            for( int i = 0; i < image.getWidth()-1 ; i++ ){
                if (brightness[i][j] < brightness[i+1][j]) {
                    // swapping of elements
                    double tempBr = brightness[i][j];
                    brightness[i][j] = brightness[i+1][j];
                    brightness[i+1][j] = tempBr;

                    int tempRGB = RGB[i][j];
                    RGB[i][j] = RGB[i+1][j];
                    RGB[i+1][j] = tempRGB;
                }
            }

            for( int i = 0; i < image.getWidth() ; i++ ){
                int rGbGb = (int) RGB[i][j];
                image.setRGB(i, j, rGbGb);
            }
        }
        displayImage();

    }

    void sortCols(double[][] brightness, int[][] RGB){

        for (int j = 0; j < image.getHeight(); j++) {

            for (int i = 0; i < brightness.length; i++) {

                for (int k = 0; k < brightness.length -i - 1; k++) {
                    if (brightness[k][j] < brightness[k+1][j]) {
                        // swapping of elements
                        double tempBr = brightness[k][j];
                        brightness[k][j] = brightness[k+1][j];
                        brightness[k+1][j] = tempBr;

                        int tempRGB = RGB[k][j];
                        RGB[k][j] = RGB[k+1][j];
                        RGB[k+1][j] = tempRGB;
                    }
                }
            }
        }
    }

    void sortRows(double[][] brightness, int[][] RGB) {

        // loop for rows of matrix
        for (int i = 0; i < brightness.length; i++) {
            // loop for column of matrix
            for (int j = 0; j < brightness[i].length; j++) {

                for (int k = 0; k < brightness[i].length - j - 1; k++) {
                    if (brightness[i][k] < brightness[i][k + 1]) {

                        // swapping of elements
                        double tempBr = brightness[i][k];
                        brightness[i][k] = brightness[i][k + 1];
                        brightness[i][k + 1] = tempBr;

                        int tempRGB = RGB[i][k];
                        RGB[i][k] = RGB[i][k+1];
                        RGB[i][k+1] = tempRGB;
                    }
                }
            }
        }
    }

    public void verticalStep(){
        //performs one pass of Bubble Sort in the horizontal direction, considering the image as a bunch of vertical arrays.

        for (int i = 0 ; i < image.getWidth() ; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(i, j);
                Color pxlclr = new Color(rgb, true);
                int r = pxlclr.getRed();
                int g = pxlclr.getGreen();
                int b = pxlclr.getBlue();
                double brgthness = calcBrightness(r, g, b);
                brightness[i][j] = brgthness;
                RGB[i][j] = pxlclr.getRGB();

            }
        }

        for (int i = 0 ; i < image.getWidth() ; i++) {
            for (int j = 0; j < image.getHeight()-1; j++) {
                if (brightness[i][j] < brightness[i][j + 1]) {

                    // swapping of elements
                    double tempBr = brightness[i][j];
                    brightness[i][j] = brightness[i][j + 1];
                    brightness[i][j + 1] = tempBr;

                    int tempRGB = RGB[i][j];
                    RGB[i][j] = RGB[i][j + 1];
                    RGB[i][j + 1] = tempRGB;
                }
            }

            for (int j = 0; j < image.getHeight(); j++){
               int rGbGb = (int) RGB[i][j];
               image.setRGB(i, j, rGbGb);
            }
        }
        displayImage();

    }

    public void diagonalStep(){
        //calls horizontalStep, verticalStep, and displayImage methods in it
        verticalStep();
        horizontalStep();
    }

    public void startAnimatedBubbleSort(){

       timer = new Timer (delay, this); //1000 millisecs = 1 second
       timer.start();
       this.addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        diagonalStep();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='d'){
            delay /=2;
            timer.setDelay(delay);
        } else if(e.getKeyChar()=='a'){
            delay *=2;
            timer.setDelay(delay);
        } else if(e.getKeyChar()=='r'){
            timer.stop();
            //this loop resets the image by setting the original RGB values to the picture
            for (int i = 0 ; i < image.getWidth() ; i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int rGbGb = (int) ORRGB[i][j];
                    image.setRGB(i, j, rGbGb);
                }
            }
            displayImage();
            startAnimatedBubbleSort();

        }
        else {
            System.out.println("wrong key");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
