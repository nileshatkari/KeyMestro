/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keymestro;

import java.awt.Dimension;
import java.awt.Toolkit;



/**
* The  WindowSettings class gives basic window settings to application
* ad hoc  that provide window properties to application.
*
* @author  Nilesh Atkari
* @version 1.0
* @since   2015-22-10 
*/


public class WindowSettings {
	Dimension screenSize;
	
	public Dimension getWindowSize(){
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	
	}
	

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //Test CODE
		WindowSettings setings=new WindowSettings();
		
		System.out.println("Tje OP is :"+setings.getWindowSize());
	}*/

}

