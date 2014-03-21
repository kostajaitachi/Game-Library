import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.lang.Math.*;

public class FifteenPuzzle extends JFrame implements ActionListener
{
    JPanel row[], centerPanel;
    JButton[][] button;
    JMenuBar mbar;
    JMenu game, help;
    JMenuItem newGame, exit, howToPlay;

    FlowLayout f;
    Font font;
    Dimension buttonDimension;
    boolean win;
    int moves;
    long startTime, endTime, elapsedTime;
    short minutes, seconds;
    String[] label = new String[16];
    String buttonLabel[][] = {
                                    {"1", "2", "3", "4"},
                                    {"5", "6", "7", "8"},
                                    {"9", "10", "11", "12"},
                                    {"13", "14", "15", ""}
                             };
    String message = "The aim of the puzzle is to arrange the tiles in order\nfrom 1 to 15 with the help of the blank tile.";

    public FifteenPuzzle()
    {
        setSize(350, 400);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("15 Puzzle");
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon2.jpg"));

        // INITIALIZATION OF COMPONENTS

        buttonDimension = new Dimension(80, 80);
        f = new FlowLayout(FlowLayout.CENTER, 6, 2);
        row = new JPanel[4];
        centerPanel = new JPanel();
        button = new JButton[4][4];
        mbar = new JMenuBar();
        game = new JMenu("Game");
        help = new JMenu("Help");
        newGame = new JMenuItem("New Game");
        howToPlay = new JMenuItem("How To Play");
        exit = new JMenuItem("Exit");
        font = new Font("Consolas", Font.BOLD, 36);

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

        for(int i = 0; i < 4; i++)
        {
            row[i] = new JPanel();
            row[i].setLayout(f);
        }

        centerPanel.setLayout(new GridLayout(4, 1));

        // SETTING BUTTON PROPERTIES

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                button[i][j] = new JButton(buttonLabel[i][j]);
                button[i][j].setPreferredSize(buttonDimension);
                button[i][j].setFont(font);
                button[i][j].setBackground(Color.BLACK);
                button[i][j].setForeground(Color.WHITE);
            }
        }

        // ADDITION OF MENU

        game.add(newGame);
        game.addSeparator();
        game.add(exit);
        help.add(howToPlay);
        mbar.add(game);
        mbar.add(help);
        add(mbar, BorderLayout.NORTH);

        // ADDITION OF BUTTONS

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                row[i].add(button[i][j]);
            }
            centerPanel.add(row[i]);
        }
        add(centerPanel, BorderLayout.CENTER);

        button[3][3].requestFocus(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        JButton temp = (JButton)ae.getSource();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(temp == button[i][j])
                {
                    try
                    {
                        if(button[i-1][j].getText().equals(""))
                        {
                            button[i-1][j].setText(button[i][j].getText());
                            button[i][j].setText("");
                            moves++;
                        }
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        if(button[i+1][j].getText().equals(""))
                        {
                            button[i+1][j].setText(button[i][j].getText());
                            button[i][j].setText("");
                            moves++;
                        }
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        if(button[i][j-1].getText().equals(""))
                        {
                            button[i][j-1].setText(button[i][j].getText());
                            button[i][j].setText("");
                            moves++;
                        }
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        if(button[i][j+1].getText().equals(""))
                        {
                            button[i][j+1].setText(button[i][j].getText());
                            button[i][j].setText("");
                            moves++;
                        }
                    }
                    catch(Exception e)
                    { }
                }
            }
        }

        // CHECK IF THE PLAYER WON

        win = true;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(!button[i][j].getText().equals(buttonLabel[i][j]))
                {
                    win = false;
                    break;
                }
            }
        }

        if(win == true)
        {
            endTime = System.nanoTime() / 1000000000;
            elapsedTime = endTime - startTime;
            minutes = (short)(elapsedTime / 60);
            seconds = (short)(elapsedTime % 60);
            JOptionPane.showMessageDialog(null, "CONGRATULATIONS!...You Won!\nNumber of Moves: " + moves + "\nCompletion Time: " + minutes + ":" + seconds);
        }
    }

    public void startNewGame()
    {
        startTime = System.nanoTime() / 1000000000;
        int r, k = 0;
	moves = 0;
        String temp;
        String[] label = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        // SHUFFLING STRING ARRAY

        for(int i = 15; i > 0; i--)
        {
            r = (int)(Math.random() * i);
            temp = label[i];
            label[i] = label[r];
            label[r] = temp;
        }

        // ASSIGNING LABELS TO BUTTONS

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                button[i][j].setText(label[k]);
                button[i][j].addActionListener(this);
                k++;
                if(button[i][j].getText().equals(""))
                    button[i][j].requestFocus(true);
            }
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new FifteenPuzzle();
            }
        });
    }
}
