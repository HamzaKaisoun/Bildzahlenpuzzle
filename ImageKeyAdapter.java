import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Icon;
/**
 * Klasse ImageKeyAdapter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class ImageKeyAdapter extends KeyAdapter
{
    ZeichenPanel panel;
    ImageGame image;

    private static int  [] zahlen;
    private JLabel []  imageLabel;
    private static int n;
    private int blank;
    private int newBlank;
    private static int movesCounter;

    public ImageKeyAdapter(ZeichenPanel panel)
    {
        this.panel=panel;
        image=panel.getImage();
        zahlen=image.getZahlen();
        imageLabel=image.getJLabel();
        n= image.getN();
        blank= image.getBlank();
        newBlank=0;
        movesCounter=image.getMovesCounter();
    }

    public void keyPressed(KeyEvent e) 
    {
        movesCounter++;
        if (e.getKeyCode()== KeyEvent.VK_DOWN)
        {
            newBlank=blank-n;
            if (newBlank<(n*n) && newBlank>=0)
            {
                Icon temp = imageLabel[newBlank].getIcon();
                imageLabel[blank].setIcon(temp);
                imageLabel[newBlank].setIcon(null);

                int tempZahl = zahlen[newBlank];
                zahlen[blank]= tempZahl;
                zahlen[newBlank]=-1;

                blank=newBlank;
            }
        }
        if (e.getKeyCode()== KeyEvent.VK_UP)
        {
            newBlank=blank+n;
            if (newBlank<(n*n) && newBlank>=0)
            {
                Icon temp = imageLabel[newBlank].getIcon();
                imageLabel[blank].setIcon(temp);
                imageLabel[newBlank].setIcon(null);

                int tempZahl = zahlen[newBlank];
                zahlen[blank]= tempZahl;
                zahlen[newBlank]=-1;

                blank=newBlank;
            }
        }
        if (e.getKeyCode()== KeyEvent.VK_RIGHT)
        {
            newBlank=blank-1;
            int r=blank/n;
            int nr=newBlank/n;

            if (newBlank<(n*n) && newBlank>=0 && nr==r)
            {
                Icon temp = imageLabel[newBlank].getIcon();
                imageLabel[blank].setIcon(temp);
                imageLabel[newBlank].setIcon(null);

                int tempZahl = zahlen[newBlank];
                zahlen[blank]= tempZahl;
                zahlen[newBlank]=-1;

                blank=newBlank;
            }
        }
        if (e.getKeyCode()== KeyEvent.VK_LEFT)
        {
            newBlank=blank+1;

            int r=blank/n;
            int nr=newBlank/n;
            if (newBlank<(n*n) && newBlank>=0&& nr==r) // nr==r hier schaut man, ob der Tuasch innerhalb der Reihe stattfindet. Falls nicht wird nichts gemacht
            {
                Icon temp = imageLabel[newBlank].getIcon();
                imageLabel[blank].setIcon(temp);
                imageLabel[newBlank].setIcon(null);

                int tempZahl = zahlen[newBlank];
                zahlen[blank]= tempZahl;
                zahlen[newBlank]=-1;

                blank=newBlank;
            }

        }
        if (GameStatus.isSolved(zahlen))
        {
            panel.removeAll();
            panel.validate();
            panel.repaint();
            JLabel win= new JLabel(" ",SwingConstants.CENTER);
            JLabel moves= new JLabel(" ",SwingConstants.CENTER);
            win.setText("Sie haben Gewonnen. Wählen Sie einen Komplexitätsgrad, um ein neues Spiel zu starten.");
            win.setForeground(Color.blue);
            win.setFont(new Font("XALAS",Font.PLAIN,18));
            moves.setText("Gebrauchte Bewegungen: "+movesCounter);
            moves.setForeground(Color.red);
            moves.setFont(new Font("XALAS",Font.PLAIN,25));
            win.setOpaque(true);
            panel.add(win);
            panel.add(moves);
        }
    }

    static int [] getZahlenCopy()
    {
        int [] zahlenCopy= new int[n*n];
        for (int i=0; i<n*n ; i++)
        {
            zahlenCopy[i]= zahlen[i];
        }
        return zahlenCopy;
    }

    static int getMovesCounter()
    {
        return movesCounter;
    }
}
