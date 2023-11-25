import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Klasse ZeichenFrame.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class ZeichenFrame extends JFrame implements ActionListener
{
    ZeichenPanel panel;
    JFrame frame=this;
    private int n;
    private JMenuItem n3,n4,n5,exit, dialog,numbers,image;
    private boolean lastGameIsImageGame;
    File file=null;
    NumberKeyAdapter k ;
    ImageKeyAdapter kI ;
    public ZeichenFrame()
    {
        starter();

        JMenuBar menuZeile= new JMenuBar();
        setJMenuBar(menuZeile);

        JMenu complexity= new JMenu("Complexity");
        JMenu file= new JMenu("File");
        JMenu help= new JMenu("help");

        menuZeile.add(file);
        menuZeile.add(complexity);
        menuZeile.add(help);

        numbers= new JMenuItem ("Numbers");
        image= new JMenuItem ("Image");

        n3= new JMenuItem ("3x3");
        n4= new JMenuItem ("4x4");
        n5= new JMenuItem ("5x5");
        exit= new JMenuItem("Exit");
        dialog= new JMenuItem("pick a picture");
        JMenuItem h1= new JMenuItem("1. comlexity ermöglicht eine Änderung des Schwierigkeitsgrades");
        JMenuItem h2= new JMenuItem("2. Unter File kann ein beliebiges Bild hochgeladen werden oder das Spiel verlassen");

        complexity.add(n3);
        complexity.add(n4);
        complexity.add(n5);

        file.add(dialog);
        file.add(exit);

        help.add(h1);
        help.add(h2);

        n3.addActionListener(this);
        n4.addActionListener(this);
        n5.addActionListener(this);
        exit.addActionListener(this);
        dialog.addActionListener(this);

        setTitle("Sliding Puzzle");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==n3)
        {
            if (kI!=null)
            {
                removeKeyListener(kI); 
            }
            this.n=3;
            getContentPane().remove(panel);
            panel=new ZeichenPanel(this,3);
            panel.setLayout(new GridLayout(3,3,5,5));
            getContentPane().add(panel);
            k=new NumberKeyAdapter( panel);
            addKeyListener(k);
            lastGameIsImageGame=false;
            setVisible(true);
        }
        if (e.getSource()==n4)
        {
            if (kI!=null)
            {
                removeKeyListener(kI); 
            }
            this.n=4;
            getContentPane().remove(panel);
            panel=new ZeichenPanel(this,4);
            panel.setLayout(new GridLayout(4,4,5,5));
            getContentPane().add(panel);
            k=new NumberKeyAdapter( panel);
            addKeyListener(k);
            lastGameIsImageGame=false;
            setVisible(true);
        }
        if (e.getSource()==n5)
        {
            if (kI!=null)
            {
                removeKeyListener(kI); 
            }
            this.n=5;
            getContentPane().remove(panel);
            panel=new ZeichenPanel(this,5);
            panel.setLayout(new GridLayout(5,5,5,5));
            getContentPane().add(panel);
            k=new NumberKeyAdapter( panel);
            addKeyListener(k);
            lastGameIsImageGame=false;
            setVisible(true);
        }
        if (e.getSource()==dialog)
        {
            JFileChooser fc= new JFileChooser();
            FileFilter filter= new FileNameExtensionFilter("JPEG file","jpg","jpeg");
            fc.setFileFilter(filter);
            int response= fc.showOpenDialog(this);
            if (response==JFileChooser.APPROVE_OPTION)
            {
                file= fc.getSelectedFile();
            }
            if (file!=null)
            {
                getContentPane().remove(panel);
                
                if (kI!=null)
                {
                    removeKeyListener(kI); 
                }
                if (k!=null)
                {
                    removeKeyListener(k); 
                }
                panel=new ZeichenPanel(this,n,file, lastGameIsImageGame);
                panel.setLayout(new GridLayout(n,n,5,5));
                getContentPane().add(panel);
                kI= new ImageKeyAdapter(panel);
                addKeyListener(kI);
                lastGameIsImageGame=true;
                file=null;
                setVisible(true);
            }
            else
            {
                panel.removeAll();
                panel.validate();
                panel.repaint();

                JLabel error= new JLabel(" ",SwingConstants.CENTER);
                error.setText("Die Bildeingabe wurde abgebrochen! Bitte laden Sie ein neues Bild hoch");
                error.setForeground(Color.RED);
                error.setFont(new Font("XALAS",Font.PLAIN,20));
                error.setOpaque(true);
                panel.add(error);
                setVisible(true);
            }
        }
        if (e.getSource()==exit)
        {
            System.exit(0);
        }
    }

    public void starter ()
    {
        this.n=3;
        panel=new ZeichenPanel(this,3);
        panel.setLayout(new GridLayout(3,3,5,5));
        getContentPane().add(panel);
        k=new NumberKeyAdapter( panel);
        addKeyListener(k);
    }
}

