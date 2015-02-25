package com.pace.riscovery;

public class MyRunnable implements Runnable{

	/**This class helps create thread for DateChecker for loop
	 * @param args
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DateChecker checker = new DateChecker();
		checker.go();		
	}
}
