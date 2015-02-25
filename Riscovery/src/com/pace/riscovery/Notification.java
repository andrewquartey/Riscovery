package com.pace.riscovery;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;

public class Notification {
	
	static private Notification notify = new Notification();
	
	final TrayIcon trayIcon = new TrayIcon(createImage("images/riscovery.png", "tray icon"));	
	final SystemTray tray = SystemTray.getSystemTray();
	
	public static Notification getInstance(){
		return notify;
	}
	
	public Notification(){		
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }                
		trayIcon.setImageAutoSize(true); //this resizes tray icon
		
		final PopupMenu popup = new PopupMenu();
		
		 // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");    
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popup.add(aboutItem);
//        popup.addSeparator();        
//        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popup.add(exitItem);
         
        trayIcon.setPopupMenu(popup);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public void display(){
		trayIcon.displayMessage("Riscovery", "You have some due policies",
				TrayIcon.MessageType.INFO);
	}
	
	public void twoMonthsDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in two months",
				TrayIcon.MessageType.INFO);
	}
	
	public void oneMonthDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in one month",
				TrayIcon.MessageType.INFO);
	}
	
	public void twoWeeksDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in two weeks",
				TrayIcon.MessageType.INFO);
	}
	
	public void oneWeekDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in one week",
				TrayIcon.MessageType.INFO);
	}
	
	public void threeDaysDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in three days",
				TrayIcon.MessageType.INFO);
	}
	
	public void twoDaysDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in two days",
				TrayIcon.MessageType.INFO);
	}
	
	public void oneDayDisplay(){
		trayIcon.displayMessage("Riscovery", "You have policies due in one day",
				TrayIcon.MessageType.INFO);
	}
	
	protected static Image createImage(String path, String description) {
//        URL imageURL = Notification.class.getResource(path);
//        
//        if (imageURL == null) {
//            System.err.println("Resource not found: " + path);
//            return null;
//        } else {
            return (new ImageIcon(path, description)).getImage();
//        }		
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		show();		
//	}
}
