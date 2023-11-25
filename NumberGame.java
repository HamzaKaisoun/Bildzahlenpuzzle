import javax.swing.border.BevelBorder;//Border
import javax.swing.BorderFactory;//Border
import javax.swing.border.Border; //Border
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.Random;
import java.awt.Color;
import java.awt.event.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasse NumberGame.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class NumberGame 
{
    private ZeichenPanel panel;
    private int n;
    private  JLabel label[];
    private  int [] zahlen;

    public NumberGame(ZeichenPanel panel,int n)
    {
        this.n=n;
        this.panel=panel;
        creatGame();

    }

    public void creatGame()
    {
        Border bevelBorder =BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Font font= new Font("XALAS",Font.PLAIN,100);

        label = new JLabel[n*n];
        zahlen = new int[n*n];
        for (int i=0 ; i<zahlen.length; i++)
        {
            if (zahlen.length-1==i)
            {
                zahlen[i]=-1;
            }
            else 
            {
                zahlen [i]=i;
            }
        }

        label = new JLabel[n*n];

        do {
            shuffle(zahlen);
        }while ( !GameStatus.isSolvable(zahlen));

        for (int i=0 ; i<zahlen.length; i++)
        {
            if (i==zahlen.length-1)
            {
                label[i]= new JLabel(" ",SwingConstants.CENTER);
            }
            else
            {
                label[i]= new JLabel(String.valueOf(zahlen[i]),SwingConstants.CENTER);
            }

            label[i].setBorder(bevelBorder);
            label[i].setFont(font);
            label[i].setForeground(Color.blue);
            label[i].setBackground(Color.WHITE);
            label[i].setOpaque(true);
            panel.add(label[i]);
        }
    }

    private void shuffle(int [] zahlen) {
        Random random= new Random();
        int n=zahlen.length-2;
        while (n > 0) {
            // hier werden die Zahlen gemischt
            int r = random.nextInt(n--);
            int tmp = zahlen[r];
            zahlen[r] = zahlen[n];
            zahlen[n] = tmp;
        }
    }

    public  int[] getZahlen()
    {
        return zahlen;
    }

    public  JLabel[] getJLabel()
    {
        return label;
    }

    public  int getN()
    {
        return n;
    }
}
