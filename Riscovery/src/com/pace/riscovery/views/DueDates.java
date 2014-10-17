package com.pace.riscovery.views;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author Andy
 * This class displays List of due clients in a JFrame
 * Will continue development later. Might make it a JDialog rather.
 */
public class DueDates extends JDialog{
		/**
	 * 
	 */
	private static final long serialVersionUID = 8814663434866634764L;
	static JFrame frame = new JFrame("List of Due Policies");
	private JList<?> list = new JList<Object>();
//	JButton button = new JButton("Ok");			
	public static DefaultListModel<String> model = new DefaultListModel<String>();
	
	
	public DueDates(){
		super(frame,"Due Policies", true);
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(60, 100));
		add(listScroller);	
		setLocationRelativeTo(null);
		pack();
		setVisible(true);	
	}
	
	
	public void go(){
//		JFrame frame = new JFrame("List of Due Policies");
		
//		JList<String> list;
//			JButton button = new JButton("Ok");								
//		List<Motor> motorList = MotorHelper.getInstance().getMotors();
		
//		System.out.println("Successful");
//			JOptionPane.showMessageDialog(null,
//					"PLEASE NOTE: Policy belonging to " +
//					((Motor) motorList.get(0)).getInsured() + " has expired");
		
//		motorList  = (List<Motor>) (MotorHelper.getInstance().getMotors());
				
				
	}
	
	
	
}
