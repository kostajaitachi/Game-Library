import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.Math;

public class Calc extends JFrame implements ActionListener
{
    JMenuBar mbar;
    JMenu mode;
    JRadioButtonMenuItem standard, scientific;
    JMenuItem exit;

    JPanel centerPanel, row[];
    JButton button[];
    JTextArea textArea;
    Border border = BorderFactory.createLineBorder(Color.BLACK);

    Font font = new Font("Consolas", Font.PLAIN, 18);
    FlowLayout f1, f2;
    Dimension buttonDimension = new Dimension(50, 30);
    double temporary[] = {0, 0};
    char sign;

    public Calc()
    {
        setSize(300, 240);
        setTitle("Calculator");
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(Color.LIGHT_GRAY);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));

        //INITIALIZATOIN OF COMPONENTS

        row = new JPanel[5];
        button = new JButton[20];
        textArea = new JTextArea(1, 24);
        f1 = new FlowLayout(FlowLayout.CENTER);
        f2 = new FlowLayout(FlowLayout.CENTER, 5, 5);

        String[] buttonLabel = {
                                "7", "8", "9", "", "C",
                                "4", "5", "6", "", "",
                                "1", "2", "3", "", "/",
                                "0", ".", "=", "+", "-"
                               };

        centerPanel = new JPanel();
        for(int i = 0; i < 5; i++)
        {
            row[i] = new JPanel();
        }

        //INITIALIZING AND ASSIGNING PROPERTIES TO THE BUTTONS

        for(int i = 0; i < 20; i++)
        {
            button[i] = new JButton(buttonLabel[i]);
            button[i].setFont(font);
            button[i].setPreferredSize(buttonDimension);
            button[i].addActionListener(this);
        }
        char root = 0x221a;
        char mul = 0xd7;
        char plusMinus = 0xb1;
        char del = 0x2190;
        button[9].setFont(new Font("Arial", Font.BOLD, 14));
        button[9].setText(root + "");
        button[13].setText(mul + "");
        button[8].setText(plusMinus + "");
        button[3].setText(del + "");

        button[0].setBackground(Color.WHITE);
        button[1].setBackground(Color.WHITE);
        button[2].setBackground(Color.WHITE);
        button[3].setBackground(new Color(200, 200, 200));
        button[4].setBackground(Color.PINK);
        button[5].setBackground(Color.WHITE);
        button[6].setBackground(Color.WHITE);
        button[7].setBackground(Color.WHITE);
        button[8].setBackground(new Color(200, 200, 200));
        button[9].setBackground(new Color(200, 200, 200));
        button[10].setBackground(Color.WHITE);
        button[11].setBackground(Color.WHITE);
        button[12].setBackground(Color.WHITE);
        button[13].setBackground(new Color(200, 200, 200));
        button[14].setBackground(new Color(200, 200, 200));
        button[15].setBackground(Color.WHITE);
        button[16].setBackground(Color.WHITE);
        button[17].setBackground(Color.WHITE);
        button[18].setBackground(new Color(200, 200, 200));
        button[19].setBackground(new Color(200, 200, 200));

        //SETTING LAYOUTS FOR THE PANEL ROWS

        centerPanel.setLayout(new GridLayout(5, 1));
        row[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
        {
            row[i].setLayout(f2);
        }

        //SETTING TEXTAREA PROPERTIES

        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.BOLD, 20));
        textArea.setBorder(border);

        //ADDING COMPONENTS TO THE RESPECTIVE PANELS

        row[0].add(textArea);
        centerPanel.add(row[0]);

        for(int i = 0; i < 5; i++)
        {
            row[1].add(button[i]);
        }
        centerPanel.add(row[1]);

        for(int i = 5; i < 10; i++)
        {
            row[2].add(button[i]);
        }
        centerPanel.add(row[2]);

        for(int i = 10; i < 15; i++)
        {
            row[3].add(button[i]);
        }
        centerPanel.add(row[3]);

        for(int i = 15; i < 20; i++)
        {
            row[4].add(button[i]);
        }
        centerPanel.add(row[4]);

        //INSTANTIATING MENUBAR AND RELATED COMPONENTS

        mbar = new JMenuBar();
        mode = new JMenu("Mode");
        standard = new JRadioButtonMenuItem("Standard", true);
        scientific = new JRadioButtonMenuItem("Scientific");
        exit = new JMenuItem("Exit");
        ButtonGroup menubg = new ButtonGroup();
        menubg.add(standard);
        menubg.add(scientific);

        //ADDING ACTIONLISTENERS TO MENUITEMS

        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });

        scientific.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new ScientificCalculator();
                dispose();
            }
        });

        //ADDING ITEMS TO MENUBAR

        mode.add(standard);
        mode.add(scientific);
        mode.addSeparator();
        mode.add(exit);
        mbar.add(mode);
        add(mbar, BorderLayout.NORTH);

        //ADDING PANELS TO FRAME

        for(int i = 0; i < 5; i++)
        {
            centerPanel.add(row[i]);
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == button[0])
            textArea.append("7");

        else if(ae.getSource() == button[1])
            textArea.append("8");

        else if(ae.getSource() == button[2])
            textArea.append("9");

        else if(ae.getSource() == button[3])
        {
            if(textArea.getText().equals(""))
            { }
            else
            {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
            }
        }

        else if(ae.getSource() == button[4])
            clear();

        else if(ae.getSource() == button[5])
            textArea.append("4");

        else if(ae.getSource() == button[6])
            textArea.append("5");

        else if(ae.getSource() == button[7])
            textArea.append("6");

        else if(ae.getSource() == button[8])
            getPositiveNegative();

        else if(ae.getSource() == button[9])
            getSquareRoot();

        else if(ae.getSource() == button[10])
            textArea.append("1");

        else if(ae.getSource() == button[11])
            textArea.append("2");

        else if(ae.getSource() == button[12])
            textArea.append("3");

        else if(ae.getSource() == button[13])
        {
            //MULTIPLICATION
            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '*';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[14])
        {
            //DIVISION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '/';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[15])
            textArea.append("0");

        else if(ae.getSource() == button[16])
            textArea.append(".");

        else if(ae.getSource() == button[17])
            getResult();

        else if(ae.getSource() == button[18])
        {
            //ADDITION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '+';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[19])
        {
            //SUBTRACTION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '-';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }
    }

    public void clear()
    {
        try
        {
            textArea.setText("");

            for(int i = 0; i < 2; i++)
            {
                temporary[i] = 0;
            }
        }
        catch(NullPointerException npe)
        {

        }
    }

    public void getSquareRoot()
    {
        try
        {
            if(textArea.getText().contains("-"))
            {
                JOptionPane.showMessageDialog(null, "Square Root of Negative Number Not Allowed!");
                textArea.setText("");
            }
            else
            {
                try
                {
                    double value = Math.sqrt(Double.parseDouble(textArea.getText()));
                    textArea.setText(Double.toString(value));
                }
                catch(NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Number!");
                }
            }
        }
        catch(NumberFormatException nfe)
        {

        }
    }

    public void getPositiveNegative()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(value != 0)
            {
                value = value * (-1);
            }
            textArea.setText(Double.toString(value));
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getResult()
    {
        try
        {
            temporary[1] = Double.parseDouble(textArea.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Enter Second Argument!");
        }

        try
        {
            switch(sign)
            {
                case '+':
                    textArea.setText(Double.toString(temporary[0] + temporary[1]));
                    break;
                case '-':
                    textArea.setText(Double.toString(temporary[0] - temporary[1]));
                    break;
                case '*':
                    textArea.setText(Double.toString(temporary[0] * temporary[1]));
                    break;
                case '/':
                    if(temporary[1] == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Division By Zero Exception!");
                        textArea.setText("");
                    }
                    else
                    {
                        textArea.setText(Double.toString(temporary[0] / temporary[1]));
                    }
                    break;
            }
        }
        catch(NumberFormatException nfe)
        {

        }
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Calc();
            }
        });
    }
}

class ScientificCalculator extends JFrame implements ActionListener
{
    JMenuBar mbar;
    JMenu mode;
    JRadioButtonMenuItem standard, scientific;
    JMenuItem exit;

    JPanel centerPanel, row[], anglePanel;
    JButton button[];
    JTextArea textArea;
    JRadioButton degrees, radians;
    Border border = BorderFactory.createLineBorder(Color.BLACK);

    Font font = new Font("Arial Narrow", Font.BOLD, 11);
    FlowLayout f1, f2;
    Dimension buttonDimension = new Dimension(56, 30);
    Dimension anglePanelDimension = new Dimension(178, 30);
    double temporary[] = {0, 0};
    double answer = 0;
    char sign;

    public ScientificCalculator()
    {
        setSize(500, 310);
        setTitle("Scientific Calculator");
        setVisible(true);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon4.png"));

        //INITIALIZATOIN OF COMPONENTS

        row = new JPanel[7];
        button = new JButton[45];
        textArea = new JTextArea(1, 40);
        f1 = new FlowLayout(FlowLayout.CENTER);
        f2 = new FlowLayout(FlowLayout.CENTER, 5, 5);
        degrees = new JRadioButton("Degrees", true);
        radians = new JRadioButton("Radians");
        ButtonGroup bg = new ButtonGroup();
        bg.add(degrees);
        bg.add(radians);

        //INSTANTIATING MENUBAR AND RELATED COMPONENTS

        mbar = new JMenuBar();
        mode = new JMenu("Mode");
        standard = new JRadioButtonMenuItem("Standard");
        scientific = new JRadioButtonMenuItem("Scientific", true);
        ButtonGroup menubg = new ButtonGroup();
        menubg.add(standard);
        menubg.add(scientific);
        exit = new JMenuItem("Exit");

        String[] buttonLabel = {
            "C", "", "", "", "", "ln", "log", "",
            "7", "8", "9", "/", "n!", "sin", "sinh", "",
            "4", "5", "6", "", "", "cos", "cosh", ",",
            "1", "2", "3", "-", "1/x", "tan", "tanh", "Rec",
            "0", ".", "=", "+", "asin", "acos", "atan", "Pol", "Ans",
            "", "e", "gcd", "rand"
        };

        //ADDING ACTIONLISTENERS TO MENUITEMS

        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });

        standard.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                new Calc();
                dispose();
            }
        });

        //ADDING ITEMS TO MENUBAR

        mode.add(standard);
        mode.add(scientific);
        mode.addSeparator();
        mode.add(exit);
        mbar.add(mode);
        add(mbar, BorderLayout.NORTH);

        //SETTING TEXTAREA PROPERTIES

        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.BOLD, 22));
        textArea.setBorder(border);

        //INSTANTIATION OF VARIOUS PANELS

        anglePanel = new JPanel();
        centerPanel = new JPanel();
        anglePanel.setPreferredSize(anglePanelDimension);
        for(int i = 0; i < 7; i++)
        {
            row[i] = new JPanel();
        }

        //SETTING LAYOUTS OF VARIOUS PANELS AND PANEL ROWS

        centerPanel.setLayout(new GridLayout(7, 1));
        anglePanel.setLayout(new FlowLayout());
        row[0].setLayout(f1);
        for(int i = 1; i < 7; i++)
        {
            row[i].setLayout(f2);
        }

        //ADDING COMPONENTS TO ANGLEPANEL

        anglePanel.add(degrees);
        anglePanel.add(radians);

        //INITIALIZING AND ASSIGNING PROPERTIES TO THE BUTTONS

        for(int i = 0; i < 45; i++)
        {
            button[i] = new JButton(buttonLabel[i]);
            button[i].setFont(font);
            button[i].setPreferredSize(buttonDimension);
            button[i].addActionListener(this);
        }

        char leftArrow = 0x2190;
        char plusMinus = 0xb1;
        char pi = 0x3c0;
        char mul = 0xd7;
        char root = 0x221a;

        button[1].setText(leftArrow + "");
        button[2].setText(plusMinus + "");
        button[3].setText(root + "");
        button[4].setText("<html>x<sup>2</sup></html>");
        button[7].setText("<html>e<sup>x</sup></html>");
        button[15].setText("<html>10<sup>x</sup></html>");
        button[19].setText("x");
        button[20].setText("<html>x<sup>y</sup></html>");
        button[41].setText(pi + "");

        button[23].setFont(new Font("Consolas", Font.BOLD, 11));
        for(int i = 0; i < 4; i++)
        {
            if(i == 1)
            {
                button[i].setFont(new Font("Consolas", Font.BOLD, 20));
            }
            else if(i == 2)
            {
                button[i].setFont(new Font("Consolas", Font.PLAIN, 14));
            }
            else if(i == 3)
            {
                button[i].setFont(new Font("Arial", Font.BOLD, 12));
            }
            else
            {
                button[i].setFont(new Font("Consolas", Font.BOLD, 11));
            }
        }

        for(int i = 8; i < 12; i++)
        {
            button[i].setFont(new Font("Consolas", Font.BOLD, 11));
        }

        for(int i = 16; i < 20; i++)
        {
            button[i].setFont(new Font("Consolas", Font.BOLD, 11));
        }

        for(int i = 24; i < 28; i++)
        {
            button[i].setFont(new Font("Consolas", Font.BOLD, 11));
        }

        for(int i = 32; i < 36; i++)
        {
            button[i].setFont(new Font("Consolas", Font.BOLD, 11));
        }

        for(int i = 40; i < 44; i++)
        {
            button[i].setFont(new Font("Consolas", Font.BOLD, 11));
        }

        button[0].setBackground(Color.PINK);

        for(int i = 8; i < 11; i++)
        {
            button[i].setBackground(Color.WHITE);
        }

        for(int i = 16; i < 19; i++)
        {
            button[i].setBackground(Color.WHITE);
        }

        for(int i = 24; i < 27; i++)
        {
            button[i].setBackground(Color.WHITE);
        }

        for(int i = 32; i < 35; i++)
        {
            button[i].setBackground(Color.WHITE);
        }

        //ADDING COMPONENTS TO PANEL ROWS

        row[0].add(textArea);
        centerPanel.add(row[0]);

        row[1].add(anglePanel);
        for(int i = 40; i < 45; i++)
        {
            row[1].add(button[i]);
        }
        centerPanel.add(row[1]);

        for(int i = 0; i < 8; i++)
        {
            row[2].add(button[i]);
        }
        centerPanel.add(row[2]);

        for(int i = 8; i < 16; i++)
        {
            row[3].add(button[i]);
        }
        centerPanel.add(row[3]);

        for(int i = 16; i < 24; i++)
        {
            row[4].add(button[i]);
        }
        centerPanel.add(row[4]);

        for(int i = 24; i < 32; i++)
        {
            row[5].add(button[i]);
        }
        centerPanel.add(row[5]);

        for(int i = 32; i < 40; i++)
        {
            row[6].add(button[i]);
        }
        centerPanel.add(row[6]);

        //ADDING PANELS TO FRAME

        add(centerPanel, BorderLayout.CENTER);
    }

    public void getSquareRoot()
    {
        try
        {
            if(textArea.getText().contains("-"))
            {
                JOptionPane.showMessageDialog(null, "Square Root of Negative Number Not Allowed!");
                textArea.setText("");
                answer = 0;
            }
            else
            {
                try
                {
                    double value = Math.sqrt(Double.parseDouble(textArea.getText()));
                    textArea.setText(Double.toString(value));
                    answer = value;
                }
                catch(NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Number!");
                }
            }
        }
        catch(NumberFormatException nfe)
        {

        }
    }

    public void getPositiveNegative()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(value != 0)
            {
                value = value * (-1);
            }
            textArea.setText(Double.toString(value));
            answer = value;
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void clear()
    {
        try
        {
            textArea.setText("");

            for(int i = 0; i < 2; i++)
            {
                temporary[i] = 0;
            }
        }
        catch(NullPointerException npe)
        {

        }
    }

    public void getResult()
    {
        if(textArea.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter Second Argument!");
        }
        else
        {
            try
            {
                temporary[1] = Double.parseDouble(textArea.getText());
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            try
            {
                switch(sign)
                {
                    case '+':
                        textArea.setText(Double.toString(temporary[0] + temporary[1]));
                        break;
                    case '-':
                        textArea.setText(Double.toString(temporary[0] - temporary[1]));
                        break;
                    case '*':
                        textArea.setText(Double.toString(temporary[0] * temporary[1]));
                        break;
                    case '/':
                        if(temporary[1] == 0)
                        {
                            JOptionPane.showMessageDialog(null, "Division By Zero Exception!");
                            textArea.setText("");
                        }
                        else
                        {
                            textArea.setText(Double.toString(temporary[0] / temporary[1]));
                        }
                        break;
                    case 'p':
                        textArea.setText(Double.toString(Math.pow(temporary[0], temporary[1])));
                        break;
                }

                answer = Double.parseDouble(textArea.getText());
            }
            catch(NumberFormatException nfe)
            {

            }
        }
    }

    public void getSine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.sin(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText(Math.sin(value / 180 * Math.PI) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getCosine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.cos(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText(Math.cos(value / 180 * Math.PI) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getTangent()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.tan(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText(Math.tan(value / 180 * Math.PI) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getHyperbolicSine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText(Math.sinh(value) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getHyperbolicCosine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText(Math.cosh(value) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getHyperbolicTangent()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText(Math.tanh(value) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getArcOfSine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.asin(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText((Math.asin(value) / Math.PI * 180) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getArcOfCosine()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.acos(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText((Math.acos(value) / Math.PI * 180) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getArcOfTangent()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(radians.isSelected())
            {
                textArea.setText(Math.atan(value) + "");
            }
            else if(degrees.isSelected())
            {
                textArea.setText((Math.atan(value) / Math.PI * 180) + "");
            }
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getReciprocal()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());

            if(value == 0)
            {
                JOptionPane.showMessageDialog(null, "Reciprocal of Zero Not Allowed!");
            }
            else
            {
                textArea.setText((1 / value) + "");
                answer = Double.parseDouble(textArea.getText());
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getFactorial()
    {
        try
        {
            long value = Long.parseLong(textArea.getText());

            if(value < 0)
            {
                JOptionPane.showMessageDialog(null, "Factorial of Negative Number Not Defined!");
            }
            else if(value > 20)
            {
                JOptionPane.showMessageDialog(null, "Output Too Large!");
                textArea.setText("");
            }
            else
            {
                textArea.setText(factorial(value) + "");
                answer = Double.parseDouble(textArea.getText());
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public long factorial(long n)
    {
        long fact = 1;
        while(n > 0)
        {
            fact *= n;
            n--;
        }
        return fact;
    }

    public void getSquare()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText((value * value) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getNaturalExponent()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText((Math.pow(Math.E, value)) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getCommonExponent()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            textArea.setText((Math.pow(10, value)) + "");
            answer = Double.parseDouble(textArea.getText());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getNaturalLogarithm()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(value <= 0)
            {
                JOptionPane.showMessageDialog(null, "Logarithm of Non-Positive Numbers Not Allowed!");
            }
            else
            {
                textArea.setText((Math.log(value)) + "");
                answer = Double.parseDouble(textArea.getText());
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getCommonLogarithm()
    {
        try
        {
            double value = Double.parseDouble(textArea.getText());
            if(value <= 0)
            {
                JOptionPane.showMessageDialog(null, "Logarithm of Non-Positive Numbers Not Allowed!");
            }
            else
            {
                textArea.setText((Math.log10(value)) + "");
                answer = Double.parseDouble(textArea.getText());
            }
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void getRectangularCoordinates()
    {
        try
        {
            String[] temp = textArea.getText().split(",", 2);
            try
            {
                double first = Double.parseDouble(temp[0]);

                if(radians.isSelected())
                {
                    double second = Double.parseDouble(temp[1]);
                    textArea.setText(first * Math.cos(second) + "," + first * Math.sin(second));
                }
                else if(degrees.isSelected())
                {
                    double second = Double.parseDouble(temp[1]) * Math.PI / 180;
                    textArea.setText(first * Math.cos(second) + "," + first * Math.sin(second));
                }
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Arguments!");
                textArea.setText("");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    }

    public void getPolarCoordinates()
    {
        try
        {
            String[] temp = textArea.getText().split(",", 2);
            try
            {
                double first = Double.parseDouble(temp[0]);
                double second = Double.parseDouble(temp[1]);

                if(radians.isSelected())
                {
                    textArea.setText(Math.sqrt(first * first + second * second) + "," + Math.atan(second/first));
                }
                else if(degrees.isSelected())
                {
                    textArea.setText(Math.sqrt(first * first + second * second) + "," + Math.atan(second/first) * 180 / Math.PI);
                }

            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Arguments!");
                textArea.setText("");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    }

    public void getGCD()
    {
        try
        {
            String[] temp = textArea.getText().split(",", 2);

            try
            {
                long first = Long.parseLong(temp[0]);
                long second = Long.parseLong(temp[1]);
                if(first >= 0 && second >= 0)
                {
                    if(first >= second)
                    {
                        textArea.setText(GCD(first, second) + "");
                    }
                    else
                    {
                        textArea.setText(GCD(second, first) + "");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Cannot Calculate GCD of Negative Numbers!");
                }
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Arguments!");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Entry!");
        }
    }

    public long GCD(long a, long b)
    {
        if(b == 0)
            return a;
        else
            return GCD(b, a%b);
    }

    public void getPower()
    {
        try
        {
            temporary[0] = Double.parseDouble(textArea.getText());
            textArea.setText("");
            sign = 'p';
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Invalid Number!");
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == button[0])
            clear();

        else if(ae.getSource() == button[1])
        {
            if(textArea.getText().equals(""))
            { }
            else
            {
                textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
            }
        }

        else if(ae.getSource() == button[2])
            getPositiveNegative();

        else if(ae.getSource() == button[3])
            getSquareRoot();

        else if(ae.getSource() == button[4])
            getSquare();

        else if(ae.getSource() == button[5])
            getNaturalLogarithm();

        else if(ae.getSource() == button[6])
            getCommonLogarithm();

        else if(ae.getSource() == button[7])
            getNaturalExponent();

        else if(ae.getSource() == button[8])
            textArea.append("7");

        else if(ae.getSource() == button[9])
            textArea.append("8");

        else if(ae.getSource() == button[10])
            textArea.append("9");

        else if(ae.getSource() == button[11])
        {
            //DIVISION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '/';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[12])
            getFactorial();

        else if(ae.getSource() == button[13])
            getSine();

        else if(ae.getSource() == button[14])
            getHyperbolicSine();

        else if(ae.getSource() == button[15])
            getCommonExponent();

        else if(ae.getSource() == button[16])
            textArea.append("4");

        else if(ae.getSource() == button[17])
            textArea.append("5");

        else if(ae.getSource() == button[18])
            textArea.append("6");

        else if(ae.getSource() == button[19])
        {
            //MULTIPLICATION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '*';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[20])
        {
            //POWER

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = 'p';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[21])
            getCosine();

        else if(ae.getSource() == button[22])
            getHyperbolicCosine();

        else if(ae.getSource() == button[23])
            textArea.append(",");

        else if(ae.getSource() == button[24])
            textArea.append("1");

        else if(ae.getSource() == button[25])
            textArea.append("2");

        else if(ae.getSource() == button[26])
            textArea.append("3");

        else if(ae.getSource() == button[27])
        {
            //SUBTRACTION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '-';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[28])
            getReciprocal();

        else if(ae.getSource() == button[29])
            getTangent();

        else if(ae.getSource() == button[30])
            getHyperbolicTangent();

        else if(ae.getSource() == button[31])
            getRectangularCoordinates();

        else if(ae.getSource() == button[32])
            textArea.append("0");

        else if(ae.getSource() == button[33])
            textArea.append(".");

        else if(ae.getSource() == button[34])
            getResult();

        else if(ae.getSource() == button[35])
        {
            //ADDITION

            try
            {
                temporary[0] = Double.parseDouble(textArea.getText());
                sign = '+';
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number!");
            }

            textArea.setText("");
        }

        else if(ae.getSource() == button[36])
            getArcOfSine();

        else if(ae.getSource() == button[37])
            getArcOfCosine();

        else if(ae.getSource() == button[38])
            getArcOfTangent();

        else if(ae.getSource() == button[39])
            getPolarCoordinates();

        else if(ae.getSource() == button[40])
            textArea.setText(answer + "");

        else if(ae.getSource() == button[41])
            textArea.setText(Math.PI + "");

        else if(ae.getSource() == button[42])
            textArea.setText(Math.E + "");

        else if(ae.getSource() == button[43])
            getGCD();

        else if(ae.getSource() == button[44])
            textArea.setText(Math.random() + "");
    }
}
