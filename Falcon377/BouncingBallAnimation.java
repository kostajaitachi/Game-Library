/*
Break All the bricks and wait for exciting prizes to come
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;


public class BouncingBallAnimation extends JFrame implements KeyListener
{
    int x = 200, y = 200, dirX = 1, dirY = 1;
	int count=0;
    int height, width;
	Rectangle rect=new Rectangle(10,500,70,30);
	Rectangle rect1;
	Rectangle[][] r=new Rectangle[6][5];
	
	public BouncingBallAnimation()
	{
	super("BouncingBallAnimation");
		setResizable(false);
        setSize(400, 600);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		createBricks();
        addKeyListener(this);
        while(true)
        {
            try
            {
                Thread.sleep(5);
                moveBall();
                repaint();
            }
            catch(InterruptedException ie)
            {

            }
        }
	}
   
   public void paint(Graphics g)
    {
        super.paint(g);
		g.setColor(Color.BLUE);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 30, 30);
		
		for(int i=0;i<r.length;i++)
		{
			for(int j=0;j<r[0].length;j++)
			{
				if(i%2!=0 && j%2==0)
				{
				g.setColor(Color.red);
				g.fillRect(r[i][j].x,r[i][j].y,80,20);
				}
				else if(i%2 == 0 && j%2!=0)
				{
				g.setColor(Color.blue);
				g.fillRect(r[i][j].x,r[i][j].y,80,20);
				
				}
				
				else
				{
				g.setColor(Color.green);
				g.fillRect(r[i][j].x,r[i][j].y,80,20);
				}
			}
		}
		
    }

    public void moveBall()
    {
	
		rect1=new Rectangle(x,y,30,30);
		
		if(rect1.intersects(rect))
			collideBack();
			
		for(int i=0;i<r.length;i++)
		{
			for(int j=0;j<r[0].length;j++)
			{
			
				/*if(count == 30)
				{
				JOptionPane.showMessageDialog(null,"You finished all of them ,Congratulations ");
				break;
				}*/
				
				if(rect1.intersects(r[i][j]))
				{
				r[i][j].reshape(900,900,0,0);
				collideBack();
				}
			}
		}
		
        if(x + dirX < 0 || x + dirX > 355)
        {
            dirX = -dirX;
            
        }

        if(y + dirY < 0 || y + dirY > 550)
        {
            dirY = -dirY;
        }

        x += dirX;
        y += dirY;
    }
	
	public void keyPressed(KeyEvent e){}
	
	public void keyReleased(KeyEvent e){}
	
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar()=='d')
			rect.x += 5;
		if(e.getKeyChar()=='a')
			rect.x-=5;

	repaint();
	}
	
	public void collideBack()
	{
	dirX=-dirX;
	dirY=-dirY;
	x+=dirX;
	y+=dirY;
	}
	
	public void createBricks()
	{
	for(int i=0;i<r.length;i++)
	{
		for(int j=0;j<r[0].length;j++)
		{
		r[i][j]=new Rectangle(j*80,i*20,80,20);
		}
	}
	
	}
	

    public static void main(String args[])
    {
        new BouncingBallAnimation();
    }
}
