/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keymestro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
* The KeyMestroSettings Class gives Setting GUI for application
* Basic Settings
*
* @author  Nilesh Atkari
* @version 1.0
* @since   2015-21-10 
*/
public class KeyMestroSettings extends JFrame implements ActionListener{

	/*Declaration of Variables*/
	//JFrame jsettings;
	JPanel jsettingspanel;
	JTextField  jviewpath;
	JTextField jlogpath;
	JButton jview;
	JButton jlog;
	JButton jsavesettings;
	JButton jcancelsettings;
	JFileChooser jfc;
	
	
	String viewpath;
	String logpath;
        File settingsfile;
	FileWriter fw;
	BufferedWriter bw;
    BufferedReader br;
    FileReader fr;
    
	public KeyMestroSettings() {
		// TODO Auto-generated constructor stub

		// Initialization of the Variables 
	//	jsettings=new JFrame("Settings");
		jsettingspanel=new JPanel();
		jviewpath=new JTextField("Path for store  Recording...");
		jlogpath=new JTextField("Path to store detailed Log...");
		jview=new JButton();
		jlog=new JButton();
		jsavesettings=new JButton("Save");
		jcancelsettings=new JButton("Cancel");
		
		
		
		
		//Display Settings Window
		displaySettingsWindow();
                jview.addActionListener(this);
		jlog.addActionListener(this);
		jsavesettings.addActionListener(this);
		jcancelsettings.addActionListener(this);
		
	}
	
	public void displaySettingsWindow(){
		
		
		stylingSettingsWindow();// Arrange the components on window
		setSize(420, 180);
		setLocation(((new WindowSettings().getWindowSize().width)/2)-(getWidth()/2),
				(new WindowSettings().getWindowSize().height)/2-(getHeight()/2));
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	public void loadSettings(){
                
              //  System.out.println("The OP is :"+KeyMestroSettings.class.getResource("/KeyMestroFiles"));
		settingsfile=new File(KeyMestroSettings.class.getResource("/KeyMestroFiles").getPath()+"/settings.txt");
		
		if(settingsfile.exists()){
			try{
				fr=new FileReader(settingsfile);
				br=new BufferedReader(fr);
				jviewpath.setText(br.readLine());
				jlogpath.setText(br.readLine());
				
			}catch(Exception loadSettingsException){
				JOptionPane.showMessageDialog(null,"File Error while Load Settings :"+loadSettingsException);
			}
		}
		
	}
	
	public void stylingSettingsWindow(){
		int x,y,width,height;
		x=20;
		y=20;
		width=320;
		height=30;
		jsettingspanel.setLayout(null);
		
		//Setting components location
		jviewpath.setBounds(x, y, width, height);
		jview.setBounds(x+width, y, width-295, height);
		jlogpath.setBounds(x, y+40, width, height);
		jlog.setBounds(x+width, y+40, width-295, height);
		jsavesettings.setBounds(x,y+80,width-250,height);
		jcancelsettings.setBounds(x+120, y+80, width-240, height);
		
		//Setting icons for buttons
		jview.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/choose.png")));
		jlog.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/choose.png")));
		
		//Setting tooltiptext 
		jview.setToolTipText("Browse.. to local path");
		jlog.setToolTipText("Browse.. to local path");
		
		//make Text field uneditable and set the default values settings exist in files
		//jviewpath.setEditable(false);
		//jlogpath.setEditable(false);
		loadSettings();
		
		
		//adding component to panel and window
		jsettingspanel.add(jviewpath);
		jsettingspanel.add(jview);
		jsettingspanel.add(jlogpath);
		jsettingspanel.add(jlog);
		jsettingspanel.add(jsavesettings);
		jsettingspanel.add(jcancelsettings);
		add(jsettingspanel);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==jview){
		    jfc=new JFileChooser();
		    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    int retvalue=jfc.showOpenDialog(null);
		   		    
		    if(retvalue==JFileChooser.APPROVE_OPTION){
		    	
		    	viewpath=jfc.getSelectedFile().getAbsolutePath();
		    	jviewpath.setText(viewpath);
		    }
		    
		}else if(e.getSource()==jlog){
			jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int retvalue=jfc.showOpenDialog(null);
						
			if (retvalue== JFileChooser.APPROVE_OPTION){
				
				logpath=jfc.getSelectedFile().getAbsolutePath();
				jlogpath.setText(logpath);
			}
		}else if (e.getSource()==jsavesettings) {
			
			try{
				
				//settingsfile=new File((KeyMestroSettings.class.getResource("/KeyMestroFiles/").getFile())+"settings.txt");
				settingsfile=new File("settings.txt");
				
				if(!settingsfile.exists()){
					//System.out.println("I am In");
					settingsfile.createNewFile();
				}
				fw=new FileWriter(settingsfile);
				bw=new BufferedWriter(fw);
				bw.write(jviewpath.getText());
				bw.newLine();
				bw.write(jlogpath.getText());
				JOptionPane.showMessageDialog(null, "The settings saved sucessfully...");
				bw.close();
				
				
			}catch (Exception settingsFileException) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Settings File Error :"+settingsFileException);
			}
			
		}else if (e.getSource()==jcancelsettings) {
			   //JOptionPane.showMessageDialog(null, "TEST.");
			   this.dispose();		   
		}
	}
	
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
           new KeyMestroSettings();
	}*/

	

}

