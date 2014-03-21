import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Checkers extends JFrame //implements ActionListener
{
JButton[][] pawns= new JButton[8][8];
ImageIcon black_pawn=new ImageIcon("black_pawn.png");
ImageIcon white_pawn=new ImageIcon("white_pawn.png");
	public Checkers()
	{
	super("Checkers ");
	setSize(700, 700);
	setLocation(250,10);
	setVisible(true);
	setLayout(new GridLayout(8,8));
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	
	JMenuBar menubar=new JMenuBar();
	JMenu menu =new JMenu("Options");
	JMenuItem item1=new JMenuItem("New Game");
	menu.add(item1);
	menu.addSeparator();
	JMenuItem item2=new JMenuItem("Undo");
	menu.add(item2);
	menubar.add(menu);
	setJMenuBar(menubar);
	
	setPawns();
	startBlack();
	}
	
	public void setPawns()
	{
	for(int i=0;i<pawns.length;i++)
		{
			for(int j=0;j<pawns[0].length;j++)
			{
			pawns[i][j] = new JButton();
			add(pawns[i][j]);
			if((i+j)%2!=0)
			{
				pawns[i][j].setBackground(Color.BLACK);
					if(i==0||i==1)
						pawns[i][j].setIcon(black_pawn);
					else if(i==6 || i==7)
						pawns[i][j].setIcon(white_pawn);
			}
			
			
			}
		}	
	}
	
	// creting a deadlock from which we will come out only when either of the player looses
	// if comes out of startBlack then black looses the match
	// and if comes out of startWhite then white looses the match
	// one more way when all of one's players are finished
	
	public void startBlack()
	{
	startWhite();
	}
	
	public void startWhite()
	{
	startBlack();
	}
	
	public static void main(String args[])
	{
		new Checkers();
	}
	
	
}