package model;
/**
 * This code is a model for the list of policy types.
 * This is a prototype. I will add code for retrieving names from the database later.
 * 
 * Authored by ZION.
 */
import javax.swing.DefaultListModel;

public class ListModel extends DefaultListModel<String> {
	
	public ListModel()
	{
		addElement("MOTOR");
		addElement("HOME & PERSONAL ASSETS");		
		addElement("ASSETS ALL RISKS");
		addElement("WORKMEN'S COMPENSATION");
	    addElement("BUSINESS COMBINED");
		addElement("HOME OWNERS LIABILITY");
		addElement("FIDELITY GUARANTEE");
		addElement("PROFESSIONAL INDEMNITY");
		addElement("GOODS IN TRANSIT");
		addElement("EMPLOYER'S LIABIITY");
		addElement("PUBLIC LIABILITY");
		addElement("FOREIGN TRAVEL");
		addElement("PLANT AND MACHINERY");
		addElement("PROFESSIONAL INDEMNITY");
		addElement("FIRE & ALLIED PERILS");
		addElement("HOTEL ALL RISK");
		addElement("FIRE");
	}
}