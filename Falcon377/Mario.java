import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;

public class Mario extends JFrame implements KeyListener
{
Container cont;
String arena[][]=
{{" "," "," "," "," "," "," "," "," "," "},
 {" "," "," "," "," "," "," "," "," "," "},
 {" "," "," "," "," "," ","|","#","#"," "},
 {" ","#","#","#"," "," ","|","#"," "," "},
 {" "," "," "," "," "," ","|","#"," "," "},
 {" ","#","#","#","#","#","#","#","#"," "},
 {" "," "," "," "," "," "," "," "," "," "},
 {"#","#","#","#"," "," ","#","#","#","#"},
 {" "," "," "," "," "," "," "," "," "," "},
 {"#","#","#","#","#","#","#","#","#","#"},
};
ArrayList stars =new ArrayList();
JLabel character=new JLabel(new ImageIcon("mario.png"));
boolean jumping=false;
boolean climbing=false;
Runner runner;
ArrayList enemies =new ArrayList();
	public Mario()
	{
	super("Mario");
	setResizable(false);
	setSize(500,500);
	setVisible(true);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cont=getContentPane();
	cont.setLayout(null);
	addKeyListener(this);
	cont.setBackground(Color.BLACK);
	cont.add(character);
	character.setBounds(0,400,50,50);
	generateStars();
	//generateEnemies();
		for(int i=0;i<arena.length;i++)
		{
			for(int j=0;j<arena[0].length;j++)
			{
			JLabel lbl=null;
				if(arena[j][i].equals(" "))
				lbl=new JLabel(new ImageIcon("air.png"));
				if(arena[j][i].equals("|"))
				lbl=new JLabel(new ImageIcon("ladder.png"));
				if(arena[j][i].equals("#"))
				lbl=new JLabel(new ImageIcon("ground.png"));
			cont.add(lbl);
			lbl.setBounds(i*50,j*50,50,50);
			}
		}
	repaint();
	cont.validate();
	runner=new Runner();
	runner.start();
	setContentPane(cont);
	}
	
	public void generateStars()
	{
		for(int i=1;i<arena.length;i++)
		{
			for(int j=0;j<arena[0].length;j++)
			{
				if(arena[j][i].equals(" ") && !arena[j][i].equals("|") && !arena[j][i].equals("#"))
				{
				int placeOrNot =(int)(Math.random()*10);
					if(placeOrNot == 0)
					{
					JLabel star = new JLabel(new ImageIcon("star.png"));
					cont.add(star);
					star.setBounds(j*50,i*50,50,50);
					cont.setComponentZOrder(star,0);
					cont.setComponentZOrder(character,0);
					stars.add(star);
					}
				}
			}
		}
	}
	/*
	public void generateEnemies()
	{
		for(int i=0;i<2;i++){
		JLabel enemy =new JLabel(new ImageIcon("badmario.png"));
		cont.add(enemy);
		int xlocation= (int)(Math.random()*8);
		enemy.setBounds(xlocation*50,0,50,50);
		enemy.setComponentZOrder(enemy,0);
		enemies.add(enemy);
		}
	}*/
	
	public class Runner extends Thread
	{
		public void run()
		{
			while(true)
			{
				try
				{
				// for removal of star when mario touches it
				for(int i=0;i<stars.size();i++)
				{
					JLabel star=(JLabel)stars.get(i);
					if(star.getBounds().intersects(character.getBounds()))
					{
					cont.remove(star);
					stars.remove(star);
					}
				}
				 //let mario fall
				 
				 if(!jumping)
				 {
					if(arena[(character.getY()/50)+1][character.getX()/50].equals(" "))
						character.setBounds(character.getX(),character.getY()+50,50,50);
					
				 }
				 else
				 {
				 jumping=false;
				 if(arena[(character.getY()/50)-1][character.getX()/50].equals(" "))
					character.setBounds(character.getX(),character.getY()-50,50,50);
				 }
				 Thread.sleep(250);
				}
				catch(Exception e){}
			}
		}
	}
	
	public void keyPressed(KeyEvent e){}
	
	public void keyReleased(KeyEvent e){}
	
	public void keyTyped(KeyEvent e){
	// move left
	if(e.getKeyChar() == 'a')
	{
		if(climbing)
		{
		climbing=false;
		character.setIcon(new ImageIcon("mario.png"));
		}
		if(character.getX()>=50 && arena[character.getY()/50][(character.getX()/50)-1].equals(" "))
			character.setBounds(character.getX()-50,character.getY(),50,50);
	}
	
	// move right
	if(e.getKeyChar() == 'd')
	{
		
		if(character.getX() <=400 && arena[character.getY()/50][(character.getX()/50)+1].equals(" "))
			character.setBounds(character.getX()+50,character.getY(),50,50);
		
		try{
		if(arena[character.getY()/50][(character.getX()/50)+1].equals("|"))
		{
		character.setBounds(character.getX()+50,character.getY(),50,50);
		climbing = true;
		character.setIcon(new ImageIcon("onladder.png"));
		}}
		catch(Exception e123){
		SecondScreen ob=new SecondScreen();
		}
		
	}
	// move to top
	if(e.getKeyChar() == 'w')
	{
		if(!climbing)
		{
			if(arena[(character.getY()/50)-1][character.getX()/50].equals(" "))
			{
				if(!jumping && !arena[(character.getY()/50)+1][character.getX()/50].equals(" "))
				{
				jumping=true;
				character.setBounds(character.getX(),character.getY() -50,50,50);
				}
			}
		}
		
		else
		{
			if(arena[(character.getY()/50)-1][character.getX()/50].equals("|"))
			{
				character.setBounds(character.getX(),character.getY()-50,50,50);
			}
			
			if(arena[(character.getY()/50)-1][character.getX()/50].equals(" "))
			{
				if(!jumping && !arena[(character.getY()/50)+1][character.getX()/50].equals(" "))
				{
				jumping=true;
				character.setIcon(new ImageIcon("mario.png"));
				character.setBounds(character.getX(),character.getY() -50,50,50);
				}
			}
		}
	}
	
	}
	
	public static void main(String[] args)
	{
	new Mario();
	}
	
}


class SecondScreen extends JFrame
{
Container cont;
String arena1[][]=
{{" "," "," "," "," "," "," "," "," "," "},
 {" "," "," "," "," "," "," "," ","#","#"},
 {" "," "," "," "," "," "," ","|"," "," "},
 {" ","#","#","#"," "," "," ","#"," "," "},
 {" "," "," "," "," "," "," "," "," "," "},
 {" "," "," ","#","#","#","#"," "," "," "},
 {" "," ","|"," "," "," "," "," "," "," "},
 {" "," ","|"," "," "," ","#","#"," "," "},
 {" "," "," "," "," "," "," "," "," "," "},
 {"#","#","#","#","#","#","#","#","#","#"},
};
JLabel character=new JLabel(new ImageIcon("mario.png"));
	public SecondScreen()
	{
	setResizable(false);
	setVisible(true);
	setSize(500,500);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cont=getContentPane();
	cont.setLayout(null);
	cont.add(character);
	character.setBounds(0,400,50,50);
	new Mario().generateStars();
	for(int i=0;i<arena1.length;i++)
		{
			for(int j=0;j<arena1[0].length;j++)
			{
			JLabel lbl=null;
				if(arena1[j][i].equals(" "))
				lbl=new JLabel(new ImageIcon("air.png"));
				if(arena1[j][i].equals("|"))
				lbl=new JLabel(new ImageIcon("ladder.png"));
				if(arena1[j][i].equals("#"))
				lbl=new JLabel(new ImageIcon("ground.png"));
			cont.add(lbl);
			lbl.setBounds(i*50,j*50,50,50);
			}
		}
	repaint();
	cont.validate();
	setContentPane(cont);
	}
}