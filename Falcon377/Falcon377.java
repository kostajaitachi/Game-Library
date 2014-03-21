import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.net.*;
import java.applet.*;
import p1.*;
import p2.*;

public class Falcon377 extends JFrame implements ActionListener
{

	// FOR SOUND BOOLEAN
	boolean on = false;
    // BUTTONS AND MENUS

    JButton game[][], application[][],inst[][],hacks[][];
    JMenuBar mbar;
    JMenu mode, help,settings;
    JRadioButtonMenuItem games, applications,instruments,tools;
    JMenuItem exit, aboutFalcon377, music;

    // PANELS AND OTHER COMPONENTS

    JPanel mainPanel, namePanel, cardPanel, gamePanel, applicationPanel,instrumentPanel,hackersToolsPanel;
    TitledBorder gamePanelBorder, applicationPanelBorder,instrumentPanelBorder,hackersToolBorder;
    CardLayout card;
    Font font;
	ImageIcon i = new ImageIcon("si.jpg");
	JLabel l = new JLabel(i);
	
	//INITIALSING PARAMETERS FOR SOUND
	URL url = null;
	AudioClip au = null;
	
    public Falcon377()
    {
        // FRAME PROPERTIES

        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Falcon377 Fun Store");
        setLayout(new BorderLayout());
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		
		

        // INSTANTIATING OTHER COMPONENTS

        card = new CardLayout(10, 10);
        game = new JButton[4][3];
        application = new JButton[2][2];
		inst =new JButton[2][2];
		hacks = new JButton[2][2];
        font = new Font("Consolas", Font.PLAIN, 24);

        // INSTANTIATING AND ADDING MENU

        mbar = new  JMenuBar();
        games = new JRadioButtonMenuItem("Games", true);
        applications = new JRadioButtonMenuItem("Applications");
		instruments = new JRadioButtonMenuItem("Musical Instruments");
		tools = new JRadioButtonMenuItem("Hacking Tools");
        ButtonGroup bg = new ButtonGroup();
        bg.add(games);
        bg.add(applications);
		bg.add(instruments);
		bg.add(tools);
        mode = new JMenu("Mode");
        help = new JMenu("Help");
		settings = new JMenu("Settings");
        exit = new JMenuItem("Exit");
        aboutFalcon377 = new JMenuItem("About Falcon 377 Fun Store");
		music  = new JMenuItem("Music On/Off");
        mode.add(games);
        mode.add(applications);
		mode.add(instruments);
		mode.add(tools);
        mode.addSeparator();
        mode.add(exit);
        help.add(aboutFalcon377);
		settings.add(music);
        mbar.add(mode);
        mbar.add(help);
		mbar.add(settings);
        add(mbar, BorderLayout.NORTH);

        // INSTANTIATING GAME PANEL

        gamePanel = new JPanel();
        gamePanelBorder = BorderFactory.createTitledBorder("Games");
		gamePanelBorder.setTitleColor(Color.WHITE);
        gamePanel.setBorder(gamePanelBorder);
        gamePanel.setLayout(new GridLayout(4, 3, 10, 10));

        // INSTANTIATING APPLICATION PANEL

        applicationPanel = new JPanel();
        applicationPanelBorder = BorderFactory.createTitledBorder("Applications");
		applicationPanelBorder.setTitleColor(Color.WHITE);
        applicationPanel.setBorder(applicationPanelBorder);
        applicationPanel.setLayout(new GridLayout(2, 2, 10, 10));
		
		// INSTANTIATING INSTRUMENT PANEL
		instrumentPanel = new JPanel();
		instrumentPanelBorder = BorderFactory.createTitledBorder("Musical Touch");
		instrumentPanelBorder.setTitleColor(Color.WHITE);
		instrumentPanel.setBorder(instrumentPanelBorder);
		instrumentPanel.setLayout(new GridLayout(2,2,10,10));
		
		//INITIALISING TOOLS PANEL
		hackersToolsPanel = new JPanel();
		hackersToolBorder = BorderFactory.createTitledBorder("Hacking Tools");
		hackersToolBorder.setTitleColor(Color.WHITE);
		hackersToolsPanel.setBorder(hackersToolBorder);
		hackersToolsPanel.setLayout(new GridLayout(2,2,10,10));

        // INSTANTIATING NAME PANEL

        namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(l);

        // INSTANTIATING CARD PANEL

        cardPanel = new JPanel();
        cardPanel.setLayout(card);

        // INSTANTIATING MAIN PANEL

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // ADDING LISTENERS TO MENU ITEMS

        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });

        games.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                card.first(cardPanel);
            }
        });

        applications.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                card.next(cardPanel);
            }
        });
		
		instruments.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			card.next(cardPanel);
			}
		});
		
		tools.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			card.last(cardPanel);
			}
		});

        aboutFalcon377.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
			//JOptionPane.showMessageDialog(null,"Game Description");
			JFrame f = new JFrame("About Falcon 377");
			f.setSize(300,400);
			f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			f.setVisible(true);
			f.setLocation(400,200);
			f.setLayout(null);
			f.setResizable(false);
			JTextArea ta = new JTextArea(10,20);
			f.add(ta);
			ta.setBounds(10,10,260,340);
			ta.setFont(new Font("Consolas",Font.PLAIN,20));
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
			ta.setText("Hey all this application is build with the purpose of giving you a complete package of mini games, applications, musical instruments and some basic hacking tools");
			ta.setEditable(false);
			ta.setForeground(Color.BLUE);
            }
        });
		
		music.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent ae)
			{ 
			on = !on;
				if(on)
				{
					//ADDING SOUND CONTINIOUSLY IN THE BACKGROUND ON BUTTON CLICK
					try
					{
					url = this.getClass().getResource("sizeOfTheMoon.wav");
					au = JApplet.newAudioClip(url);
					au.loop();
					}
					catch(Exception e){}
				}
				else
				{
					au.stop();
				}
			}
		});

        // INSTANTIATING GAME PANEL BUTTONS

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                game[i][j] = new JButton("");
                gamePanel.add(game[i][j]);
                game[i][j].addActionListener(this);
                game[i][j].setFont(font);
            }
        }

        // NAMING GAME BUTTONS

        game[0][0].setText("Test Your Memory");
        game[0][1].setText("Minesweeper");
        game[0][2].setText("Lights Out");
        game[1][0].setText("Mario");
        game[1][1].setText("Earth Invasion");
        game[1][2].setText("Fifteen Puzzle");
        game[2][0].setText("Racing");
        game[2][1].setText("Watch The Cars");
        game[2][2].setText("Contra");
        game[3][0].setText("Arkanoid");
        game[3][1].setText("Checkers");
        game[3][2].setText("Catch Me If You Can");
		
		// ADDING ACTIONLISTENERS TO GAME BUTTONS
		game[0][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Memory();
			}
		});
		
		game[0][1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Minesweeper();
			}
		});
		
		game[0][2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new LightsOut();
			}
		});
		
		game[1][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Mario();
			}
		});
		
		game[1][1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Earth();
			}
		});
		
		game[1][2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new FifteenPuzzle();
			}
		});
		
		game[2][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Racing();
			}
		});
		
		game[2][1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Car();
			}
		});
		
		game[2][2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			
			}
		});
		
		game[3][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new BouncingBallAnimation();
			}
		});
		
		game[3][1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Checkers();
			}
		});
		
		game[3][2].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new brain();
			}
		});
        // INSTANTIATING APPLICATION PANEL BUTTONS

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                application[i][j] = new JButton("Text");
                applicationPanel.add(application[i][j]);
                application[i][j].addActionListener(this);
                application[i][j].setFont(font);
            }
        }

        // NAMING APPLICATION BUTTONS

        application[0][0].setText("Sketch Pad");
        application[0][1].setText("Scientific Calculator");
        application[1][0].setText("Note Pad");
        application[1][1].setText("Coming Soon");
		
		// ADDING LISTENERS TO APPLICATION BUTTONS
		application[0][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			p2.sketch s = new p2.sketch();
			s.content2();
			}
		});
		
		application[0][1].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Calc();
			}
		});
		
		application[1][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			p1.pad p =new p1.pad();
			p.content1();
			}
		});
		//INSTANTIATING INSTRUMENT BUTTONS
		 for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                inst[i][j] = new JButton("Coming soon");
                instrumentPanel.add(inst[i][j]);
                inst[i][j].addActionListener(this);
                inst[i][j].setFont(font);
            }
        }
		
		// NAMING INSTRUMENT BUTTONS
		inst[0][0].setText("Piano");
		
		// ADDING LISTENERS TO INSTRUMENT BUTTONS
		inst[0][0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
			new Sound();
			}
		});
		
		
		//INSTANTIATING TOOLS BUTTONS
		 for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                hacks[i][j] = new JButton("Coming Soon");
                hackersToolsPanel.add(hacks[i][j]);
                hacks[i][j].addActionListener(this);
                hacks[i][j].setFont(font);
            }
        }
		
		
        // ADDITION OF PANELS
		gamePanel.setBackground(Color.BLACK);
		applicationPanel.setBackground(Color.BLACK);
		instrumentPanel.setBackground(Color.BLACK);
		hackersToolsPanel.setBackground(Color.BLACK);
        cardPanel.add(gamePanel);
        cardPanel.add(applicationPanel);
		cardPanel.add(instrumentPanel);
		cardPanel.add(hackersToolsPanel);
        mainPanel.add(namePanel);
        mainPanel.add(cardPanel);
		//mainPanel.setBackground(Color.BLACK);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent ae)
    {

    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Falcon377();
            }
        });
    }
}
