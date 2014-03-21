import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.applet.*;

class brain extends JFrame implements ActionListener
{
JButton[][] spots = new JButton[5][5];
ImageIcon alive =new ImageIcon("who.png");
ImageIcon title=new ImageIcon("catch.png");
JLabel score=new JLabel();
JButton image=new JButton(title);
T runner=null;
double hits=0;
double turns=0;
double maxturn=0;
URL eng=null,eng1=null;
AudioClip sound=null,back=null;
JButton onoff=new JButton("Music On/Off");
boolean check1;
	public brain()
	{
	super("BrainVita");
	setSize(650,750);
	setVisible(true);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	onoff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			check1=!check1;
				if(check1)
				{
					try
					{
					eng1=this.getClass().getResource("2hell.wav");
					back=JApplet.newAudioClip(eng1);
					back.loop();
					}
					catch(Exception e){}
				}
				else
					back.stop();
			}
		});
	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
		setVisible(false);
		}
	});
	maxturn=Double.parseDouble(JOptionPane.showInputDialog("How many times you want to catch the \"Who I am\" ?"));
	Container cont=getContentPane();
	cont.setLayout(new FlowLayout());
	cont.add(image);
	image.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
		
		JOptionPane.showMessageDialog(null,"Game Details","Game Details",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		for(int i=0;i<spots.length;i++)
		{
				for(int j=0;j<spots[0].length;j++)
				{
				spots[i][j]=new JButton(alive);
				cont.add(spots[i][j]);
				spots[i][j].setEnabled(false);
				spots[i][j].addActionListener(this);
				}
		}
	score.setText("Turn :"+turns +"/"+maxturn+"  Current Score :" + (int)((hits/maxturn)*100));
	cont.add(score);
	setContentPane(cont);
	cont.add(onoff);
	runner=new T();
	runner.start();
	}
	
	private class T extends Thread
	{
		public void run()
		{
			while(true)
			{
			//maxturn=Double.parseDouble(JOptionPane.showInputDialog("How many times you want to catch the \"Who I am\" ?"));
				if(turns>=maxturn)
				{
				JOptionPane.showMessageDialog(null,"The game is over.\n\n"+"You hit "+ hits+" WhoIAm in "+turns+" turns.\n"+"Your score is "+	((int)(((hits*10000)/turns))),"Game Over",JOptionPane.INFORMATION_MESSAGE);
				break;
				}
			turns++;
			//int timedelay=(int)(Math.random()*1500);
				try
				{
				Thread.sleep(500);
				}
				catch(Exception e){}
			int row=(int)(Math.random()*5);
			int col=(int)(Math.random()*5);
			spots[row][col].setEnabled(true);
				try
				{
				Thread.sleep(1000);
				}
				catch(Exception e){}
			spots[row][col].setEnabled(false);
			score.setText("Turn :"+turns +"/"+maxturn+"  Current Score :" + (int)((hits/maxturn)*100));
			}
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
	hits=hits+1;
		try
		{
		runner.sleep(500);
		Thread.sleep(500);
		}
		catch(Exception e){}
		try
		{
		eng=this.getClass().getResource("laser.wav");
		sound=JApplet.newAudioClip(eng);
		sound.play();
		}
		catch(Exception e){}
	}
	
	public static void main(String args[])
	{
	new brain();
	}
}

// things to add - 
/*
play again
music on/off - done but orientation left
highscores - (table in which scores will be put up)
instructions 
*/