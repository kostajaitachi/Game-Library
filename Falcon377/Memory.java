import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class Memory extends JFrame implements ActionListener
{
JLabel m = new JLabel(" Sharp  your Memory");
ImageIcon blank = new ImageIcon("blank.png");
JButton buttons[][] = new JButton[4][4];
ImageIcon[][] image = new ImageIcon[4][4];
ImageIcon[] i1=new ImageIcon[2];
int[] x = new int[2];
int[] y =new int[2];
int[][] pra = new int[4][4];
int p[] =new int[2];
Container cont;
int count=0;
int calc=0;
String name;
int time=90;
	public Memory()
	{
	super("Memory");
	setSize(415,500);
	setVisible(true);
	setLocation(200,200);
	setResizable(false);
	//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	cont=getContentPane();
	//cont.setLayout(new GridLayout(4,4));
	cont.setLayout(null);
	cont.add(m);
	Font f = new Font("Arial",Font.BOLD,30);
	m.setFont(f);
	m.setBounds(50,400,300,40);
	JMenuBar menubar = new JMenuBar();
	JMenu menu =new JMenu("Options");
	JMenuItem item1=new JMenuItem("Play Again");
	menu.add(item1);
	menu.addSeparator();
	JMenuItem item2 = new JMenuItem("Timer Game");
	menu.add(item2);
	menubar.add(menu);
	setJMenuBar(menubar);
	
	item1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
		dispose();
		new Memory();
		}
	});
	
	item2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
		
		}
	});
		for(int i=0;i<buttons[0].length;i++)
		{
			for(int j=0;j<buttons.length;j++)
			{
			buttons[i][j] = new JButton(blank);
			cont.add(buttons[i][j]);
			buttons[i][j].setBounds(i*100,j*100,100,100);
			buttons[i][j].addActionListener(this);
			}
		}
	mixup();
	}
	
	public void mixup()
	{
		try
		{
		Thread.sleep(2000);
		int usedCount[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			for(int i=0;i<4;i++)
			{
				for(int j=0;j<4;j++)
				{
				int rand = (int)(Math.random()*16) +1;
					while(usedCount[rand-1] > 0)
					{
					rand = (int)(Math.random()*16) + 1;
					}
					
				
						if(rand==1 || rand==16)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img1.png"));
						image[i][j] = new ImageIcon("img1.png");
						pra[i][j] = 1;
						}
						else if(rand==2 || rand==15)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img2.png"));
						image[i][j] = new ImageIcon("img2.png");
						pra[i][j] = 2;
						}
						else if(rand==3 || rand==14)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img3.png"));
						image[i][j] = new ImageIcon("img3.png");
						pra[i][j] = 3;

						}
						else if(rand==4 || rand==13)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img4.png"));
						image[i][j] = new ImageIcon("img4.png");
						pra[i][j] = 4;
						}
						else if(rand==5 || rand==12)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img5.png"));
						image[i][j] = new ImageIcon("img5.png");
						pra[i][j] = 5;
						}
						
						else if(rand==6 || rand==11)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img6.png"));
						image[i][j] = new ImageIcon("img6.png");
						pra[i][j] = 6;
						}
						else if(rand==7 || rand==10)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img7.png"));
						image[i][j] = new ImageIcon("img7.png");
						pra[i][j] = 7;
						}
						
						else if(rand==8 || rand==9)
						{
						usedCount[rand-1]++;
						buttons[i][j].setIcon(new ImageIcon("img8.png"));
						image[i][j] = new ImageIcon("img8.png");
						pra[i][j] = 8;
						}
					
				}
			}
			
			Thread.sleep(2000);
			for(int i=0;i<buttons[0].length;i++)
			{
				for(int j=0;j<buttons.length;j++)
				{
				buttons[i][j].setIcon(blank);
				cont.validate();
				}
			}
			
		
		}
		catch(Exception e){}
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
	count++;
	JButton[] temp = new JButton[2];
	temp[count-1] = (JButton)ae.getSource();
	i1[count-1] = new ImageIcon();
		for(int i=0;i<buttons.length;i++)
		{
			for(int j=0;j<buttons[0].length;j++)
			{
				if(temp[count-1]== buttons[i][j])
				{
					x[count-1]=i;
					y[count-1]=j;
					i1[count-1] = image[i][j];
					p[count-1]=pra[i][j];
				}
			}
			
		}
	temp[count-1].setIcon(i1[count-1]);
	if(count%2==0)
	{
		try
		{
		
			if(p[0]!=p[1])
			{
			System.out.println(i1[0]);
			System.out.println(i1[1]);
			buttons[x[0]][y[0]].setIcon(blank);
			buttons[x[1]][y[1]].setIcon(blank);
			
			}
			else
			{
			buttons[x[0]][y[0]].setEnabled(false);
			buttons[x[1]][y[1]].setEnabled(false);
			}
		
		}
		catch(Exception e){}
		count=0;
		calc++;
	}
	int flag=0;
	for(int i=0;i<buttons.length;i++)
	{
		for(int j=0;j<buttons[0].length;j++)
		{
			ImageIcon i2 =new ImageIcon();
			i2= (ImageIcon)(buttons[i][j].getIcon());
			if(i2 == blank)
			{
				flag=1;
				break;
			}
		}
	}
	
	if(flag==0)
	{
	JOptionPane.showMessageDialog(null,"You finished in " + calc + " attempts");
	name = JOptionPane.showInputDialog("Enter You name");
		try
		{
		File file  = new File("HighScores.txt");
		FileOutputStream outstream = new FileOutputStream(file);
		PrintWriter out = new PrintWriter(outstream);
		out.println(name + " : " + calc +" trials");
		out.flush();
		out.close();
		outstream.close();
		}
		catch(Exception e){}
			
	}
	

	}
	
	public static void main(String args[])
	{
	new Memory();
	}
}

