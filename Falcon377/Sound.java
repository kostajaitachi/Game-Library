import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import org.jfugue.*;

class Sound extends JFrame 
{
JButton[] buttons = new JButton[17];
JButton[] black = new JButton[12];
Player player =new Player();
JLayeredPane panel =new JLayeredPane();
	public Sound()
	{
	super("Piano");
	setSize(1140,200);
	setLocation(10,200);
	setVisible(true);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	//setLayout(null);
	add(panel);
	setButtons();
	addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyChar() =='c')
				{
				buttons[0].setBackground(Color.BLUE);
				Pattern p1=new Pattern("C");
				player.play(p1);
				}
				
				
				if(e.getKeyChar() =='d')
				{
				buttons[1].setBackground(Color.BLUE);
				Pattern p1=new Pattern("D");
				player.play(p1);
				}
				
				if(e.getKeyChar() =='e')
				{
				buttons[2].setBackground(Color.BLUE);
				Pattern p1=new Pattern("E");
				player.play(p1);
				}
				
				if(e.getKeyChar() =='f')
				{
				buttons[3].setBackground(Color.BLUE);
				Pattern p1=new Pattern("F");
				player.play(p1);
				}
				
				if(e.getKeyChar() =='g')
				{
				buttons[4].setBackground(Color.BLUE);
				Pattern p1=new Pattern("G");
				player.play(p1);
				}
				
				if(e.getKeyChar() =='a')
				{
				buttons[5].setBackground(Color.BLUE);
				Pattern p1=new Pattern("A");
				player.play(p1);
				}
				
				if(e.getKeyChar() =='b')
				{
				buttons[6].setBackground(Color.BLUE);
				Pattern p1=new Pattern("B");
				player.play(p1);
				}
			}
			
			public void keyReleased(KeyEvent e)
			{
				for(int i=0;i<buttons.length;i++)
					buttons[i].setBackground(Color.WHITE);
			}
		});
	setFocusable(true);
	}
	
	public void setButtons()
	{
		for(int i=0;i<buttons.length;i++)
		{
		buttons[i] = new JButton();
		buttons[i].setVerticalAlignment( SwingConstants.BOTTOM );
		buttons[i].setForeground(Color.BLUE);
		buttons[i].setBackground(Color.WHITE);
		buttons[i].setBounds((i+1)*60,20,60,120);
			if(i%7 == 0)
			{
				buttons[i].setText("C");
				
				
				
			}
			else if(i%7 == 1)
			{
				buttons[i].setText("D");
				
			}
			else if(i%7 == 2)
			{
				buttons[i].setText("E");
				
			}
			else if(i%7 == 3)
			{
				buttons[i].setText("F");
				
			}
			else if(i%7 == 4)
			{
				buttons[i].setText("G");
				
			}
			else if(i%7 == 5)
			{
				buttons[i].setText("A");
				
			}
			else if(i%7 == 6)
			{
				buttons[i].setText("B");
				
			}
		panel.add(buttons[i],0,-1);
		
		
					
		}
		
		for(int i=0;i<black.length;i++)
		{
		black[i] = new JButton();
		black[i].setForeground(Color.RED);
		black[i].setBackground(Color.BLACK);
		black[i].setVerticalAlignment( SwingConstants.BOTTOM );
		panel.add(black[i],1,-1);
		}
		
		black[0].setBounds(88,20,50,70);
		black[0].setText("C#");
		
		black[1].setBounds(148,20,50,70);
		black[1].setText("D#");
		
		black[2].setBounds(268,20,50,70);
		black[2].setText("F#");
		
		black[3].setBounds(328,20,50,70);
		black[3].setText("G#");
		
		black[4].setBounds(388,20,50,70);
		black[4].setText("A#");
		
		black[5].setBounds(508,20,50,70);
		black[5].setText("C#");
		
		black[6].setBounds(568,20,50,70);
		black[6].setText("D#");
		
		black[7].setBounds(688,20,50,70);
		black[7].setText("F#");
		
		black[8].setBounds(748,20,50,70);
		black[8].setText("G#");
		
		black[9].setBounds(808,20,50,70);
		black[9].setText("A#");
		
		black[10].setBounds(928,20,50,70);
		black[10].setText("C#");
		
		black[11].setBounds(988,20,50,70);
		black[11].setText("D#");
	
	}
	

	public static void main(String args[])
	{
	new Sound();
	}
}