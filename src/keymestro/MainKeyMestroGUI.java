/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keymestro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
* The MainKeyMestroGUI Class gives Main GUI to MainKeyMestro.java
* displays Window.
*
* @author  Nilesh Atkari
* @version 1.0
* @since   2015-21-10 
*/


public class MainKeyMestroGUI extends JFrame implements ActionListener,NativeKeyListener {
     
/*Declaration Of  Variables */
//	JFrame mainwindow;
	JPanel mainpanel;
	JButton jstart;
	JButton jstop;
	JButton jsetting;
	JButton jview;
	JButton jaboutme;
	WindowSettings defaultwindow;// Class WindowSettings
	public static final long serialVersionUID = 1L;	//These is for removing Class warning nowhere used inprogram
	
	
	File settings;
	File viewfile;
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	Calendar calobj;
    //StringBuffer sb;	
	String sb;
	int hiddingflag=1;// for hiding window of keygen
	boolean bwcloseflag=false;
	int toggleflag=0;
	String viewfilename="";
	
	public MainKeyMestroGUI(){
		
		
	//	mainwindow=new JFrame();
		mainpanel=new JPanel();
		jstart=new JButton();
		jstop=new JButton();
		jsetting=new JButton();
		jview=new JButton();
		jaboutme=new JButton();
		
		
		defaultwindow=new WindowSettings();
		
		//setting the GridLayout to mainframe
		mainpanel.setLayout(new GridLayout(1,5,3,3));
	    
		// Settings For Buttons
		jstart.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/start.png")));
		jstop.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/stop.png")));
		jsetting.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/settings.png")));
		jview.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/view.png")));
		jaboutme.setIcon(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/aboutme.png")));
		
		jstart.setToolTipText("Start");
		jstop.setToolTipText("Stop");
		jsetting.setToolTipText("Settings");
		jview.setToolTipText("View The record Log...");
		jaboutme.setToolTipText("About ME!");
		
		//Add ActionListener to Buttons
		 jstart.addActionListener(this);
		 jstop.addActionListener(this);
		 jsetting.addActionListener(this);
		 jview.addActionListener(this);
		 jaboutme.addActionListener(this);
				 
		//Adding Components on Window
		mainpanel.add(jstart);
		mainpanel.add(jstop);
		mainpanel.add(jsetting);
		mainpanel.add(jview);
		mainpanel.add(jaboutme);
		add(mainpanel);
		mainpanel.setOpaque(false);
	    
		/*additional variables for Visual Effect
		ImageIcon mainicon=new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/mylogo.png"));
		*/
		
		//mainwindow settings
		setTitle("KyMestro 1.0");
		setSize(280,80);
		//setBackground(new Color(0, 0, 0, 0));
		setLocation((defaultwindow.getWindowSize().width)-(getWidth()+40)
				, (defaultwindow.getWindowSize().height)-(getHeight()+50));
		setIconImage(new ImageIcon(MainKeyMestroGUI.class.getResource("/Icons/frameicon.png")).getImage());
		setVisible(true);
		setResizable(false);		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(sb != null){
					//System.out.println("The OP in CLose:"+sb);
					try {
						 if(!bwcloseflag){
						     bw.write(Calendar.getInstance().getTime().toString()+" : "+sb);
						     bw.close();
						 }
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Issue while closing..."+e1);
					}
				}
				System.exit(0);
			}
		}); 
		
		
		
		//Initial Settings 
	   	jstop.setEnabled(false);
		jview.setEnabled(false);
		 
	}
	
	//Action Listener for handling all The Actions on the Button
	public void actionPerformed(ActionEvent e) {
		  

		  if (e.getSource() == jstart) {
			  
		     //Enable Stop Disable Start
			  jstart.setEnabled(false);
			  jstop.setEnabled(true);
			  jview.setEnabled(false);
			  
			  bwcloseflag=false;
             
			 try {
				    GlobalScreen.registerNativeHook();
				    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	                logger.setLevel(Level.OFF);
        
	               
	                sb="";//new StringBuffer();
	                settings=new File("settings.txt");
	                
	                fr=new FileReader(settings);
	                br=new BufferedReader(fr);
	                               
	                calobj = Calendar.getInstance();
	               
	                //System.out.println(br.readLine()+"/"+(calobj.getTime().toString()));
	                viewfilename=br.readLine().trim()+"/"+(calobj.getTime().toString().replace(":", "_"))+".txt";
	                viewfile=new File(viewfilename);
	               // System.out.println(viewfilename);
                    if(!viewfile.exists()){
                    	System.out.println("Creating...");
                    	viewfile.createNewFile();
                    }
                    fw=new FileWriter(viewfile);
                    bw=new BufferedWriter(fw);
                    
                    br.close();
                    
                    
                    
                }catch (NativeHookException ex) {
                	JOptionPane.showMessageDialog(this,"Error registering the native hook :"+ex);
                	System.exit(1);
                }catch(Exception fe){
                	JOptionPane.showMessageDialog(this,"File Error  :"+fe);
                	
                }
               
         //initialze native hook.
         GlobalScreen.addNativeKeyListener(this);
      

             
		  }else if(e.getSource()== jstop){
			  //Enable the start button
			  jstart.setEnabled(true);
			  jstop.setEnabled(false);
			  jview.setEnabled(true);
			  
			  bwcloseflag=true;
               
			    try {
					GlobalScreen.unregisterNativeHook();
					
				} catch (NativeHookException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        System.runFinalization();
		        try {
		        	bw.write(Calendar.getInstance().getTime().toString()+" : "+sb);
		        	bw.newLine();
	                sb="";
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  
		  }else if (e.getSource()==jsetting) {
			  new KeyMestroSettings();//open settings for keymestro settings
		
		  }else if(e.getSource()==jview){
			  
			  //View the resent log into view file
			  
		      try{
			
		    	  
		    	  File f=new File(viewfilename);
		    	  FileReader fr=new FileReader(f);
		    	  String line="";
		    	  String sb="";
		    	  BufferedReader br=new BufferedReader(fr);
			        
		    	  while((line=br.readLine())!=null){
		    		  		sb=sb+line+"\n\r";	
		    	  }
		    	 
		    	 JOptionPane.showMessageDialog(this, sb,"KeyMestro 1.0 Resent Log" ,JOptionPane.INFORMATION_MESSAGE);
			  
			  	br.close();	
			  }catch(Exception ex){
				  JOptionPane.showMessageDialog(this,"Error in viewing the file :"+ex);
			  }
			  
		  }else if(e.getSource()==jaboutme){
			  String about="";
			  about="KeyMestro 1.0 Beta VE\n";
			  about=about+"Developed by : Mr. Nilesh Atkari\n";
			  about=about+"E-Mail :nileshatkari777@gmail.com\n";
			  about=about+"Contact : +91 9175535568\n";
			  about=about+"Special Tanks To: Mr.Sumit Lubal and Vinod Patil";
			  
			  
			  JOptionPane.showMessageDialog(this,about,"About KeyMestro 1.0 Beta VS",JOptionPane.INFORMATION_MESSAGE);
			  
		  }
	}

	
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		//Test Code
		
		 URL one=MainKeyMestroGUI.class.getResource("/Icons");
         //new MainKeyMestroGUI();
		// System.out.print("OUTPUT :"+one);
	       // URI two=one.toURI();
		SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new MainKeyMestroGUI();
	                
	            }
	        });
		
		 
		
	}*/

	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	//	System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(arg0.getKeyCode()));
		if(arg0.getKeyCode()==NativeKeyEvent.VC_CONTROL_L || arg0.getKeyCode()==NativeKeyEvent.VC_CONTROL_R){
			toggleflag=1;
		}
		
		if(toggleflag==1 && NativeKeyEvent.getKeyText(arg0.getKeyCode())=="T"){
			if(hiddingflag%2==1){
				setVisible(false);
				hiddingflag+=1;
			}else{
				setVisible(true);
				hiddingflag+=1;
			}
			toggleflag=0;
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Key Released: " + NativeKeyEvent.getKeyText(arg0.getKeyCode()));
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Key Typed: " + arg0.getKeyChar());
		sb=sb+String.valueOf(arg0.getKeyChar());
		
		//System.out.println((int)'');
		if(arg0.getKeyChar()==(char)8){
			//System.out.println((int)'');
			sb=sb+" Backspace";
			
			
		}
		if(arg0.getKeyChar()==(char)13){
			try{
				  
				//  System.out.println("THE OP :"+sb);
                  bw.write(Calendar.getInstance().getTime().toString()+" : "+sb);
                  bw.newLine();
                  sb="";
				 
				  
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Error Viewfile writing :"+e);
			}
			
		}
		
	}




}

