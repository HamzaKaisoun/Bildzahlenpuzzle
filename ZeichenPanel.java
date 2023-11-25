import javax.swing.JPanel;
import java.io.File;

/**
 * Beschreiben Sie hier die Klasse ZeichenPanel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ZeichenPanel extends JPanel
{

    ZeichenFrame frame ;
    NumberGame numbers;
    ImageGame image;
    ZeichenPanel()
    {

    }

    ZeichenPanel(ZeichenFrame frame,int n)
    {
        this.frame= frame;

        numbers= new NumberGame(this,n);
    }
    ZeichenPanel(ZeichenFrame frame, int n,File file,boolean lastGameIsImageGame )
    {
        this.frame= frame;

        image= new ImageGame(this,n,file,lastGameIsImageGame);

    }
    public NumberGame getNumbers()
    {
        return numbers;
    }
    public ImageGame getImage()
    {
    return image;
    }
}
