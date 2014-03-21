import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.lang.Math;

public class Minesweeper extends JFrame implements ActionListener, MouseListener
{
    JButton[][] button;
    JPanel centerPanel;
    JMenuBar mbar;
    JMenu game, help;
    JMenuItem newGame, exit, howToPlay;

    ImageIcon flag = new ImageIcon("flag.gif");
    ImageIcon mine = new ImageIcon("mine.jpg");
    ImageIcon blank = new ImageIcon("blank.gif");
    Dimension buttonDimension;
    boolean win, isFlag;
    int[][] minesLocation;
    int[] randomNumberArray;
    ImageIcon[] numberIcon;
    String message = "The field contains 10 mines hidden below the tiles.\nClick the tiles to traverse the entire field without\nhitting the mines. Right click on a tile to set a flag.\nClicking a tile shows a number that displays the\nnumber of mines in the tiles surrounding it.";

    public Minesweeper()
    {
        setSize(355, 410);
        setTitle("Minesweeper");
        setVisible(true);
        //setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));

        // INITIALIZATION OF COMPONENTS

        buttonDimension = new Dimension(30, 30);
        numberIcon = new ImageIcon[9];
        centerPanel = new JPanel();
        button = new JButton[10][10];
        mbar = new JMenuBar();
        game = new JMenu("Game");
        help = new JMenu("Help");
        newGame = new JMenuItem("New Game");
        howToPlay = new JMenuItem("How To Play");
        exit = new JMenuItem("Exit");
        for(int i = 1; i < 9; i++)
        {
            numberIcon[i] = new ImageIcon(i + "" + ".gif");
        }

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

        // SETTING LAYOUTS OF CENTER PANEL

        centerPanel.setLayout(new GridLayout(10, 10));

        // SETTING BUTTON DIMENSIONS

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                button[i][j] = new JButton();
                button[i][j].setPreferredSize(buttonDimension);
                button[i][j].setBackground(Color.WHITE);
                button[i][j].setEnabled(false);
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

        // ADDITION OF BUTTONS AND CENTER PANEL

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                centerPanel.add(button[i][j]);
            }
        }
        add(centerPanel, BorderLayout.CENTER);
    }

    public void startNewGame()
    {
        // GENERATION OF 10 RANDOM NUMBERS IN THE RANGE 0 TO 99 WITHOUT REPETITION AND STORING IN AN ARRAY

        int k = 0, num, checkNumberRepetition;
        minesLocation = new int[10][10];
        randomNumberArray = new int[10];

        while(k < 10)
        {
            num = (int)(Math.random() * 100);
            checkNumberRepetition = 0;

            for(int j = 0; j < k; j++)
            {
                if(num == randomNumberArray[j])
                {
                    checkNumberRepetition = 1;
                    break;
                }
            }

            if(checkNumberRepetition == 0)
            {
                randomNumberArray[k] = num;
                k++;
            }
        }

        // RESETTING THE BUTTONS TO THEIR DEFAULT STATES AND SETTING THE POSITIONS OF MINES

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                button[i][j].setBackground(new Color(153, 204, 255));
                button[i][j].setIcon(null);
                button[i][j].setEnabled(true);
                button[i][j].addActionListener(this);
                button[i][j].addMouseListener(this);
            }
            minesLocation[randomNumberArray[i] / 10][randomNumberArray[i] % 10] = 1;
        }
    }

    public void displayAllMines()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                if(minesLocation[i][j] == 1)
                {
                    button[i][j].setIcon(mine);
                }

                button[i][j].removeMouseListener(this);
                button[i][j].removeActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        JButton temp = (JButton)ae.getSource();
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                if(temp == button[i][j])
                {
                    if(minesLocation[i][j] == 1)
                    {
                        displayAllMines();
                    }
                    else
                    {
                        check(i, j);
                    }
                }
            }
        }
    }

    public void check(int i, int j)
    {
        int count = 0;

        try
        {
            if(minesLocation[i - 1][j - 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i][j - 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i + 1][j - 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i - 1][j] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i + 1][j] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i - 1][j + 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i][j + 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        try
        {
            if(minesLocation[i + 1][j + 1] == 1)
                count++;
        }
        catch(Exception e)
        { }

        // IF THE CLICKED TILE CONTAINS NO MINE SURROUNDING IT, THIS WILL CALL RECURSION ON THE SURROUNDING TILES UNTIL A NON-ZERO NUMBER APPEARS

        if(button[i][j].getIcon() == null || button[i][j].getIcon() == flag)
        {
            switch(count)
            {
                case 0:
                    button[i][j].setIcon(blank);

                    try
                    {
                        check(i - 1, j - 1);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i, j - 1);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i + 1, j - 1);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i - 1, j);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i + 1, j);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i - 1, j + 1);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i, j + 1);
                    }
                    catch(Exception e)
                    { }

                    try
                    {
                        check(i + 1, j + 1);
                    }
                    catch(Exception e)
                    { }

                    break;

                case 1:
                    button[i][j].setIcon(numberIcon[1]);
                    break;

                case 2:
                    button[i][j].setIcon(numberIcon[2]);
                    break;

                case 3:
                    button[i][j].setIcon(numberIcon[3]);
                    break;

                case 4:
                    button[i][j].setIcon(numberIcon[4]);
                    break;

                case 5:
                    button[i][j].setIcon(numberIcon[5]);
                    break;

                case 6:
                    button[i][j].setIcon(numberIcon[6]);
                    break;

                case 7:
                    button[i][j].setIcon(numberIcon[7]);
                    break;

                case 8:
                    button[i][j].setIcon(numberIcon[8]);
                    break;
            }
        }

        button[i][j].removeMouseListener(this);
        button[i][j].removeActionListener(this);
    }

    public void mousePressed(MouseEvent me)
    {
        // TO INSERT A FLAG WHEN RIGHT MOUSE BUTTON IS CLICKED

        if(SwingUtilities.isRightMouseButton(me))
        {
            JButton temp = (JButton)me.getSource();

            for(int i = 0; i < 10; i++)
            {
                for(int j = 0; j < 10; j++)
                {
                    if(temp == button[i][j])
                    {
                        if(button[i][j].getIcon() == null)
                            button[i][j].setIcon(flag);
                        else
                            button[i][j].setIcon(null);
                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent me)
    { }

    public void mouseReleased(MouseEvent me)
    { }

    public void mouseEntered(MouseEvent me)
    { }

    public void mouseExited(MouseEvent me)
    { }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Minesweeper();
            }
        });
    }
}
