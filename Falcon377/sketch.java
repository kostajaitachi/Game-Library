package p2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.datatransfer.*;
import java.applet.*;
	
	class sketch_pad extends Frame implements ActionListener,MouseListener,MouseMotionListener,ItemListener
	{
		String selected_shape = new String("Square");
		String selected_color=new String("Blue");
		boolean eraser=false;
		int up_L_X,up_L_Y,W,H,se1_x1,se1_y1,se1_x2,se1_y2;
		String[] extras_list= {"Clear canvas ","Eraser"};
		String[] color_list ={"Black","Green","Cyan","Blue","Yellow","Magenta","Red","White"};
		String[] shape_list={"Line","Rectangle","Square","Circle"};
		public void windowClosing(WindowEvent eve)
			{
			setVisible(false);
			}
		public void windowActivated(WindowEvent eve)
			{
			}
		public void windowOpened(WindowEvent eve)
			{
			}
		public void windowIconified(WindowEvent eve)
			{
			}
		public void windowClosed(WindowEvent eve)
			{
			}
		public void windowDeactivated(WindowEvent eve)
			{
			}
		public void windowDeIconfied(WindowEvent eve)
			{
			}
		public void mouseMoved(MouseEvent mouse_mov_eve)
			{
			}
		public void mouseClicked(MouseEvent mouse_clicked_eve)
			{
			}
		public void mouseExited(MouseEvent mouse_exited_eve)
			{
			}
		public void mouseEntered(MouseEvent mouse_entered_eve)
			{
			}
		public void itemStateChanged(ItemEvent item_state_chng_eve)
			{
			}
		public sketch_pad(String str)
		{
			super(str);
			addMouseMotionListener(this);
			//addWindowListener(this);
			addMouseListener(this);
			setLayout(null);
			set_menu_items();
			setBackground(Color.white);
		}
		public void actionPerformed(ActionEvent action_performed_eve)
		{
		Graphics ga =getGraphics();
		Object s=action_performed_eve.getActionCommand();
		
		for(int j=0;j !=color_list.length;j++)
			if(s.equals(color_list[j]))
			{
			selected_color = color_list[j];
			return;
			}
		for(int i=0;i !=shape_list.length;i++)
			if(s.equals(shape_list[i]))
			{
			selected_shape=shape_list[i];
			return;
			}
		if(s.equals("eraser"))
			{
			eraser=true;
			return;
			}
		if(s.equals("clear canvas"))
			{
			ga.clearRect(0,0,700,700);
			return;
			}
		
		
		}
		void choose_color(Graphics ga)
		{
		for(int i=0;i!=color_list.length;i++)
			{
			if(selected_color.equals(color_list[i]))
				{
				switch(i)
					{
					
					case 0: ga.setColor(Color.black);break;
					case 1: ga.setColor(Color.green);break;
					case 2: ga.setColor(Color.cyan);break;
					case 3: ga.setColor(Color.blue);break;
					case 4: ga.setColor(Color.yellow);break;
					case 5: ga.setColor(Color.magenta);break;
					case 6: ga.setColor(Color.red);break;
					}
				}
			}
		}
		public void mouseReleased(MouseEvent mouse_reles_eve)
		{
		Graphics ga=getGraphics();
			if(eraser)
			{
			eraser =false;
			return;
			}
		choose_color(ga);
		se1_x2=mouse_reles_eve.getX();
		se1_y2=mouse_reles_eve.getY();
		if(selected_shape.equals("Line"))
			{
			ga.drawLine(se1_x1,se1_y1,se1_x2,se1_y2);
			}
		else if (selected_shape.equals("Circle"))
			{
			draw_selected_shape(ga,"Circle");
			}
		else if(selected_shape.equals("Square"))
			{
			draw_selected_shape(ga,"Square");
			}
		else if(selected_shape.equals("Rectangle"))
			{
			draw_selected_shape(ga,"Rectangle");
			}
		ga.setColor(Color.yellow);
		ga.drawString(".",se1_x1,se1_y1);
		ga.setColor(Color.black);
		}
		void draw_selected_shape(Graphics ga,String sel_shape)
		{
			up_L_X=Math.min(se1_x1,se1_x2);
			up_L_Y=Math.min(se1_y1,se1_y2);
			W=Math.abs(se1_x1-se1_x2);
			H=Math.abs(se1_y1-se1_y2);
			if(selected_shape.equals("Square"))
			ga.fillRect(up_L_X,up_L_Y,W,W);
			else if(selected_shape.equals("Rectangle"))
			ga.fillRect(up_L_X,up_L_Y,W,H);
			else if(selected_shape.equals("Circle"))
			ga.fillOval(up_L_X,up_L_Y,W,W);
			
		}
		public void mouseDragged(MouseEvent mouse_drag_eve)
		{
			Graphics ga=getGraphics();
			se1_x2=mouse_drag_eve.getX();
			se1_y2=mouse_drag_eve.getY();
			if(eraser){
			ga.setColor(Color.white);
			ga.fillRect(se1_x2,se1_y2,10,10);
			}
		}
		public void mousePressed(MouseEvent mouse_press_eve)
		{
			if(eraser)
			return;
			up_L_X=0;up_L_Y=0;W=0;H=0;
			se1_x1=mouse_press_eve.getX();
			se1_y1=mouse_press_eve.getY();
			Graphics ga=getGraphics();
			ga.drawString(".",se1_x1,se1_y1);
		}
		void set_menu_items()
		{
		gaListener gl=new gaListener();
		addWindowListener(gl);
			MenuBar mbar =new MenuBar();
			
			Menu menu_sh=new Menu("Shapes");
			for(int i=0;i!=shape_list.length;i++)
			menu_sh.add(shape_list[i]);
				mbar.add(menu_sh);
				menu_sh.addActionListener(this);
				
			Menu menu_co =new Menu("Colors");
			for(int i=0;i!=color_list.length;i++)
			menu_co.add(color_list[i]);
				mbar.add(menu_co);
				menu_co.addActionListener(this);
				
			Menu menu_ex =new Menu("Extras");
			for(int i=0;i!=extras_list.length;i++)
				menu_ex.add(extras_list[i]);
				mbar.add(menu_ex);
				menu_ex.addActionListener(this);
			
				
			setMenuBar(mbar);
		}
		class gaListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent closeVConnectPad)
		{	
			setVisible(false);
		}
	}
		
}
public class sketch
{
	public void content2()
	{
	sketch_pad draw_win =new sketch_pad("VConnect presents you VSketchpad... Enjoy!!!");
				draw_win.setSize(700,700);
				draw_win.setVisible(true);
				//JOptionPane.showMessageDialog(null,"Developer- Prakhar Gupta");
	}


	public static void main(String args[])
	{
	sketch ob2=new sketch();
	ob2.content2();
	//JOptionPane.showMessageDialog(null,"Developer- Prakhar Gupta");
	}
}	
