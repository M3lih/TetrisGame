
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

public class GameArea extends JPanel
{
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    
    private int[][] block = { {1,0}, {1,0}, {1,1} };
    
    public GameArea(JPanel placeholder, int columns)
    {
        placeholder.setVisible(false);
        this.setBounds( placeholder.getBounds() );
        this.setBackground( placeholder.getBackground() );
        this.setBorder( placeholder.getBorder() );
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
    }
    
    private void drawBlock(Graphics g)
    {
        for(int row = 0; row < block.length; row++)
        {
            for(int col = 0; col < block[0].length; col++)
            {
                if(block[row][col] == 1)
                {
                    g.setColor(Color.red);
                    g.fillRect(col * gridCellSize , row * gridCellSize, gridCellSize, gridCellSize );
                    g.setColor(Color.black);
                    g.drawRect(col * gridCellSize , row * gridCellSize, gridCellSize, gridCellSize );
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        drawBlock(g);
    }
}
