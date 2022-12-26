
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread 
{
    
    private GameArea ga;
    private GameForm gf;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    
    private int pause = 1000;
    private int speedupPerLevel = 100;
    
    public GameThread(GameArea ga, GameForm gf)
    {
        this.ga = ga;
        this.gf = gf;
        
        gf.updateLevel(level);
        gf.updateScore(score);
    }
   @Override
   public void run()
   {
       
       while(true)
       {
           
           ga.spawnBlock();
           
            while(ga.moveBlockDown())  
            {
                try 
                {
                    Thread.sleep(pause); // blok aşşağı hareket ederken duraksayarak inmesi
                } 
                catch (InterruptedException ex)  //threadlerin yarıda kesilmesi
                {
                    return;
                }
            }
            
            if(ga.isBlockOutOfBounds())
            {
                Tetris.gameOver(score);
                break;
            }
            
            ga.moveBlockToBackground();
            score += ga.clearLines();      //satır tamamlandığı zaman skor arttırılması
            gf.updateScore(score);
            
            int lvl = score / scorePerLevel +1;  //level arttırılması ve aynı zamanda 3 levelden sonra hızlanmasıs
            if (lvl > level)
            {
                level = lvl;
                gf.updateLevel(level);
                pause -= speedupPerLevel;
            }
            
       }
   }   
   
}
