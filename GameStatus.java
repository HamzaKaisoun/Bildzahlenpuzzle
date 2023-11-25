import java.util.Random;

/**
 * Klasse shuffle.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class GameStatus
{
    static boolean isSolvable(int [] zahlen) {
        int countInversions = 0;

        for (int i = 0; i < zahlen.length-1; i++) {
            for (int j = 0; j < i; j++) {
                if (zahlen[j] > zahlen[i])
                {
                    countInversions++;
                }
            }
        }

        return countInversions % 2 == 0;
    }

    static boolean isSolved(int [] zahlen) {
        if (zahlen[zahlen.length - 1] != -1) 
        {
            return false;
        }
        if (zahlen[0] != 0)
        {
            return false;
        }

        for (int i =0; i <zahlen.length-1; i++) {
            if (zahlen[i] != i)
            {
                return false;  
            }
        }

        return true;
    }
}
