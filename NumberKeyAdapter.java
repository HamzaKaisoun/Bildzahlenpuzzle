
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Klasse NumberKeyAdapter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class NumberKeyAdapter extends KeyAdapter
{
    ZeichenPanel panel;
    NumberGame numbers;

    private static int [] zahlen;
    private JLabel []  label;
    private static int n;
    private int blank;
    private int newBlank;
    private static int movesCounter; 
    // private static int zahlenCopy[];
    public NumberKeyAdapter(ZeichenPanel panel)
    {
        this.panel=panel;
        numbers=panel.getNumbers();
        zahlen=numbers.getZahlen();
        label=numbers.getJLabel();
        n= numbers.getN();
        blank= n*n-1;
        newBlank=0;
        movesCounter=0;
    }

    public void keyPressed(KeyEvent e) 
    {
        movesCounter++;
        if (e.getKeyCode()== KeyEvent.VK_DOWN)
        {
            newBlank=blank-n;
            if (newBlank<(n*n) && newBlank>=0)
            {
                String temp = label[newBlank].getText();
                label[blank].setText(temp);
                label[newBlank].setText("");
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
                String temp = label[newBlank].getText();
                label[blank].setText(temp);
                label[newBlank].setText("");
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
                String temp = label[newBlank].getText();
                label[blank].setText(temp);
                label[newBlank].setText("");
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
                String temp = label[newBlank].getText();
                label[blank].setText(temp);
                label[newBlank].setText("");
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
        int zahlenCopy[]= new int[n*n];
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

