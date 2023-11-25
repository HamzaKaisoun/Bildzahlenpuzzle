import javax.swing.JLabel;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.util.Random;

/**
 * Klasse ImageGame.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class ImageGame
{
    ZeichenPanel panel;
    private static int n;
    private static int blank;
    private JLabel imageLabel[];
    private  int [] zahlen;
    private boolean lastGameIsImageGame;
    private File file;
    private int movesCounter;
    public ImageGame(ZeichenPanel panel, int n,File file, boolean lastGameIsImageGame)
    {
        this.panel=panel;
        this.n=n;
        this.file=file;
        this.lastGameIsImageGame=lastGameIsImageGame;
        createGame();
    }

    public void createGame()
    {
        imageLabel = new JLabel[n*n] ;
        BufferedImage ausschnitte[]=getSubimage(read(file));
        if (lastGameIsImageGame)
        {
            zahlen=new int[n*n];
            int [] zahlen1= ImageKeyAdapter.getZahlenCopy();
            movesCounter = ImageKeyAdapter.getMovesCounter();
            for (int i=0; i<n*n;i++)
            {
                zahlen[i]=zahlen1[i];
            }
        }
        else
        {
            zahlen=new int[n*n];
            int [] zahlen1= NumberKeyAdapter.getZahlenCopy();
            movesCounter = NumberKeyAdapter.getMovesCounter();
            for (int i=0; i<n*n;i++)
            {
                zahlen[i]=zahlen1[i];
            }
        }

        for (int i=0; i<ausschnitte.length; i++)
        {
            if (zahlen[i]==-1)
            {
                imageLabel[i]= new JLabel();
                this.blank=i;
            }
            else
            {
                imageLabel[i]= new JLabel(new ImageIcon(ausschnitte[zahlen[i]]));
            }
            panel.add(imageLabel[i]);
        }
    }

    static BufferedImage [] getSubimage(BufferedImage scaledImage)
    {
        BufferedImage ausschnitte[] = new BufferedImage[n*n];
        int arrayAdapter=0;
        int diff=0;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                ausschnitte[diff+j]=scaledImage.getSubimage((800/n)*j,(800/n)*i, (800/n), (800/n));
                arrayAdapter++;
            }
            diff=arrayAdapter;
        }
        return ausschnitte;
    }

    static BufferedImage read(File input)
    {
        BufferedImage  image= new BufferedImage (400,400,BufferedImage.TYPE_INT_RGB);
        try
        {
            image= ImageIO.read(input);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        BufferedImage scaledImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        scaledImage.getGraphics().drawImage(image,0,0, 800, 800, null);

        return scaledImage;

    }

    public  int[] getZahlen()
    {
        return zahlen;
    }

    public  JLabel[] getJLabel()
    {
        return imageLabel;
    }

    public  int getN()
    {
        return n;
    }

    public  int getBlank()
    {
        return blank;
    }
    
    public int getMovesCounter()
    {
        return movesCounter;
    }
}
