
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
    private Color [][] background;
    
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder, int columns)
    {
        placeholder.setVisible(false);
        this.setBounds( placeholder.getBounds() );
        this.setBackground( placeholder.getBackground() );
        this.setBorder( placeholder.getBorder() );
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];
      
       
    }
    
    public void spawnBlock()
    {
        block = new TetrisBlock( new int [][]{ {1, 0}, {1, 0}, {1, 1}}, Color.blue   );
        block.spawn(gridColumns);
    }
    
    public boolean moveBlockDown()
    {
        if(checkBottom() == false ) 
        {
            moveBlockToBackground();
            return false;
        }
      block.moveDown();
      repaint();
      
      return true;
    }
    
    private boolean checkBottom()
    {
        if(block.getBottomEdge() == gridRows)
        {
            return false;
        }
        return true;
        
    }
    
    private void moveBlockToBackground()
    {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int yPos = block.getY();
        int xPos = block.getX();
        
        Color color = block.getColor();
        
        for (int r = 0; r < h; r++)
        {
            for (int c = 0; c < w; c++)
            {
                if(shape[r][c] == 1)
                {
                    background[r + yPos][c+ xPos] = color;
                }
            }
        }
    }
    
    private void drawBlock(Graphics g)
    {
        
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int [][] shape = block.getShape();
                
        for(int row = 0; row < h; row++)
        {
            for(int col = 0; col < w; col++)
            {
                if(shape[row][col] == 1)
                {
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g)
    {
        Color color;
         for (int r = 0; r < gridRows; r++)
         {
             for (int c = 0; c < gridColumns; c++)
             {
                 color = background[r][c];
                 
                 if (color != null)
                 {
                     int x = c * gridCellSize;
                     int y = r * gridCellSize;
                     drawGridSquare(g, color, x, y);
                 }
             }
         }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y)
    {
         g.setColor(color);
         g.fillRect(x, y, gridCellSize, gridCellSize );
         g.setColor(Color.black);
         g.drawRect(x ,y, gridCellSize, gridCellSize );
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBackground(g);
        drawBlock(g);
    }
}
