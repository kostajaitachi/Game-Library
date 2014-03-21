package p1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.datatransfer.*;
import java.applet.*;
class VConnectPad extends Frame
{
	Clipboard cb=getToolkit().getSystemClipboard();
	TextArea ta;
	String fName;

	VConnectPad()
	{
		gaListener gl=new gaListener();
		addWindowListener(gl);
		ta=new TextArea();
		add(ta);

		MenuBar mb=new MenuBar();
		mb.setFont(new Font("Snap ITC",Font.BOLD,20));
		
		Menu fileMenu=new Menu("File");
		MenuItem nOption=new MenuItem("New");
		MenuItem oOption=new MenuItem("Open");
		MenuItem sOption=new MenuItem("Save");
		MenuItem cOption=new MenuItem("Close");

		nOption.addActionListener(new Ne_option());
		fileMenu.add(nOption);
		fileMenu.addSeparator();
		oOption.addActionListener(new Ope_option());
		fileMenu.add(oOption);
		fileMenu.addSeparator();
		sOption.addActionListener(new Sav_option());
		fileMenu.add(sOption);
		fileMenu.addSeparator();
		cOption.addActionListener(new Clos_option());
		fileMenu.add(cOption);
		mb.add(fileMenu);
		
		Menu editMenu=new Menu("Edit");
		MenuItem cutOption=new MenuItem("Cut");
		MenuItem copyOption=new MenuItem("Copy");
		MenuItem pasteOption=new MenuItem("Paste");
		
		cutOption.addActionListener(new Cu_option());
		editMenu.add(cutOption);
		editMenu.addSeparator();
		copyOption.addActionListener(new Cop_option());
		editMenu.add(copyOption);
		editMenu.addSeparator();
		pasteOption.addActionListener(new Past_option());
		editMenu.add(pasteOption);
		mb.add(editMenu);
		setMenuBar(mb);
		
		Menu formatMenu=new Menu("Format");
		MenuItem fontOption=new MenuItem("Font");
		MenuItem colorOption=new MenuItem("Color");
		
		fontOption.addActionListener(new Font_option());
		formatMenu.add(fontOption);
		formatMenu.addSeparator();
		colorOption.addActionListener(new Color_option());
		formatMenu.add(colorOption);
		mb.add(formatMenu);
		setMenuBar(mb);
		
		Menu aboutMenu=new Menu("About");
		MenuItem abtOption=new MenuItem("About Us");
		
		abtOption.addActionListener(new abt_option());
		aboutMenu.add(abtOption);
		mb.add(aboutMenu);
		setMenuBar(mb);
		
		setTitle("VConnect Pad- TEXT EDITOR");
	}
	
	class gaListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent closeVConnectPad)
		{	
			setVisible(false);
		}
	}
	class Ne_option implements ActionListener
	{
		public void actionPerformed(ActionEvent ne)
		{
			ta.setText(" ");
		}
	}
	class Ope_option implements ActionListener
	{
		public void actionPerformed(ActionEvent ope)
		{
			FileDialog fd=new FileDialog(VConnectPad.this, "Select a text file", FileDialog.LOAD);
			fd.show();
			if(fd.getFile()!=null)
			{	
				fName=fd.getDirectory() + fd.getFile();
				setTitle(fName);
				ReadFile();
			}
			ta.requestFocus();
		}
	}
	class Clos_option implements ActionListener
	{
		public void actionPerformed(ActionEvent clos)
		{
			System.exit(0);
		}
	}
	class Sav_option implements ActionListener
	{
		public void actionPerformed(ActionEvent sav)
		{
			FileDialog fd = new FileDialog(VConnectPad.this,"Save the text file with .txt extension",FileDialog.SAVE);
			fd.show();
			if(fd.getFile()!=null)
			{
				fName=fd.getDirectory() + fd.getFile();
				setTitle(fName);
				try
				{
					DataOutputStream dos=new DataOutputStream(new FileOutputStream(fName));
					String oLine=ta.getText();
					BufferedReader br=new BufferedReader(new StringReader(oLine));
					while((oLine=br.readLine())!=null)
					{
						dos.writeBytes(oLine + "\r\n");
						dos.close();
					}
				}
				catch(Exception ex)
				{
					System.out.print("Required File Not Found!!!");
				}
				ta.requestFocus();
			}
		}
	}
	void ReadFile()
	{
		BufferedReader bR;
		StringBuffer sb = new StringBuffer();
		try
		{	
			bR=new BufferedReader(new FileReader(fName));
			String oLine;
			while((oLine=bR.readLine())!=null)
			sb.append(oLine + "\n");
			ta.setText(sb.toString());
			bR.close();
		}
		catch(FileNotFoundException fe)
		{
			System.out.print("Required file not found");
		}
		catch(IOException ioe)
		{
		}
	}
	class Cu_option implements ActionListener
	{
		public void actionPerformed(ActionEvent cut)
		{
			String st=ta.getSelectedText();
			StringSelection sSelection=new StringSelection(st);
			cb.setContents(sSelection,sSelection);
			ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
		}
	}
	class Cop_option implements ActionListener
	{
		public void actionPerformed(ActionEvent copy)
		{
			String st=ta.getSelectedText();
			StringSelection cString=new StringSelection(st);
			cb.setContents(cString,cString);
		}
	}
	class Past_option implements ActionListener
	{
		public void actionPerformed(ActionEvent paste)
		{
			Transferable cTransfer=cb.getContents(VConnectPad.this);
			try
			{
				String st=(String)cTransfer.getTransferData(DataFlavor.stringFlavor);
				ta.replaceRange(st,ta.getSelectionStart(),ta.getSelectionEnd());
			}
			catch(Exception exc)
			{
				System.out.println("Not a String flavor!!!");
			}
		}
	}
	class Font_option implements ActionListener
	{
		public void actionPerformed(ActionEvent font)
		{
			//showFontDialog sfd = new showFontDialog(this, "Font", True);
				//	sfd.setVisible(true);	
		}
	}
	class Color_option implements ActionListener
	{
		public void actionPerformed(ActionEvent color)
		{
					
		}
	}
	
	class abt_option implements ActionListener
	{
		/*public void paint(Graphics g)
		{
			Font f=new Font("Arial",Font.BOLD,20);
		} */
		public void actionPerformed(ActionEvent abt)
		{
						/*Font f=new Font("Arial",Font.BOLD,20);
						g.setFont(f);
						g.setColor(Color.red);*/
						ta.setBackground(Color.red);
						ta.setFont(new Font("Snap ITC",Font.BOLD,25));
						ta.setText("This Software is a Product/part \n of VConnect Multimedia which was developed\n by Prakhar Gupta. He is interested in programming and developing applications on\n java and android");
						//ta.setLineWrap(true);
		}
	}
	
}
public class pad
{
public void content1()
	{
	Frame nFrame=new VConnectPad();
				nFrame.setSize(400,400);
				nFrame.setVisible(true);
				//JOptionPane.showMessageDialog(null,"Developer- Prakhar Gupta");
	}


	public static void main(String args[])
	{
	pad ob1=new pad();
	ob1.content1();
	//JOptionPane.showMessageDialog(null,"Developer- Prakhar Gupta");
	}
}

