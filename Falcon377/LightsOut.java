import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.lang.Math;

public class LightsOut extends JFrame implements ActionListener
{
    JToggleButton[][] button;
    JPanel row[], centerPanel;
    JMenuBar mbar;
    JMenu game, mode, help;
    JMenuItem newGame, exit, howToPlay;
    JRadioButtonMenuItem normal, challenge;

    FlowLayout f;
    Dimension buttonDimension;
    boolean win;
    String message = "The aim of the puzzle is to turn all the 25 light bulbs ON.\nBut switching a light bulb ON/OFF also toggles the state\nof the 4 adjacent light bulbs.";

    public LightsOut()
    {
        setSize(290, 330);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Lights Out");
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));

        // INITIALIZATION OF COMPONENTS

        buttonDimension = new Dimension(50, 50);
        f = new FlowLayout(FlowLayout.CENTER, 5, 1);
        row = new JPanel[5];
        centerPanel = new JPanel();
        button = new JToggleButton[5][5];
        mbar = new JMenuBar();
        game = new JMenu("Game");
        mode = new JMenu("Mode");
        help = new JMenu("Help");
        newGame = new JMenuItem("New Game");
        howToPlay = new JMenuItem("How To Play");
        exit = new JMenuItem("Exit");
        normal = new JRadioButtonMenuItem("Normal", true);
        challenge = new JRadioButtonMenuItem("Challenge");
        ButtonGroup bg = new ButtonGroup();
        bg.add(normal);
        bg.add(challenge);

        // ADDING LISTENERS TO MENU AND MENU ITEMS

        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                startNewGame();
            }
        });

        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        howToPlay.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, message);
            }
        });

        // SETTING LAYOUTS OF PANELS

        for(int i = 0; i < 5; i++)
        {
            row[i] = new JPanel();
            row[i].setLayout(f);
        }

        centerPanel.setLayout(new GridLayout(5, 1));

        // SETTING BUTTON DIMENSIONS

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                button[i][j] = new JToggleButton();
                button[i][j].setPreferredSize(buttonDimension);
                button[i][j].setBackground(Color.WHITE);
                button[i][j].addActionListener(this);
            }
        }

        // ADDITION OF MENU

        game.add(newGame);
        mode.add(normal);
        mode.add(challenge);
        game.add(mode);
        game.addSeparator();
        game.add(exit);
        help.add(howToPlay);
        mbar.add(game);
        mbar.add(help);
        add(mbar, BorderLayout.NORTH);

        // ADDITION OF BUTTONS

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                row[i].add(button[i][j]);
            }
            centerPanel.add(row[i]);
        }
        add(centerPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent ae)
    {
        JToggleButton temp = (JToggleButton)ae.getSource();

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(temp == button[i][j])
                {
                    try
                    {
                        toggle(button[i-1][j]);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        toggle(button[i+1][j]);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        toggle(button[i][j-1]);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        toggle(button[i][j+1]);
                    }
                    catch(Exception e)
                    { }
                }
            }
        }

        // TO CHECK IF ALL THE BUTTONS ARE SELECTED

        win = true;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(!button[i][j].isSelected())
                {
                    win = false;
                    break;
                }
            }
        }

        if(win == true)
            JOptionPane.showMessageDialog(null, "CONGRATULATIONS!...You Won!");
    }

    public void toggle(JToggleButton but)
    {
        if(but.isSelected())
        {
            but.setSelected(false);
            but.setBackground(Color.WHITE);
        }
        else
        {
            but.setSelected(true);
        }
    }

    public void startNewGame()
    {
        if(normal.isSelected())
        {
            for(int i = 0; i < 5; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    button[i][j].setSelected(false);
                    button[i][j].setBackground(Color.WHITE);
                }
            }
        }
        else if(challenge.isSelected())
        {
            for(int i = 0; i < 5; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    double n = Math.random();
                    if(n < 0.5)
                    {
                        button[i][j].setSelected(false);
                        button[i][j].setBackground(Color.WHITE);
                    }
                    else
                    {
                        button[i][j].setSelected(true);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new LightsOut();
            }
        });
    }
}
