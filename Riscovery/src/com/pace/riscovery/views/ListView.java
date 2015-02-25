package com.pace.riscovery.views;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ListView extends JList<String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 103815932422696827L;
	
	static DefaultListModel<String> model = new DefaultListModel<String>();
	
	public ListView(){	
		super(model);
		model.addElement("MOTOR");
		model.addElement("HOME & PERSONAL ASSETS");		
		model.addElement("ASSETS ALL RISKS");
		model.addElement("WORKMEN'S COMPENSATION");
	    model.addElement("BUSINESS COMBINED");
		model.addElement("HOME OWNERS LIABILITY");
		model.addElement("FIDELITY GUARANTEE");
		model.addElement("PROFESSIONAL INDEMNITY");
		model.addElement("GOODS IN TRANSIT");
		model.addElement("EMPLOYER'S LIABIITY");
		model.addElement("PUBLIC LIABILITY");
		model.addElement("FOREIGN TRAVEL");
		model.addElement("PLANT AND MACHINERY");
		model.addElement("PROFESSIONAL INDEMNITY");
		model.addElement("FIRE & ALLIED PERILS");
		model.addElement("HOTEL ALL RISK");
		model.addElement("FIRE");
		
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Only one item can be selected at a time
		setVisibleRowCount(-1);
		setFont(new Font("Calibri", Font.BOLD, 13));
	}

}
