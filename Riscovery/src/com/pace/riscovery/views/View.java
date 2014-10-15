package com.pace.riscovery.views;

import java.io.File;

import com.pace.riscovery.Logic.Logic;

public interface View {
	
	void setLogic(Logic controller);
	File showOpenFileChooser(File currentFile);
	void showWarning(String title, String message);
}
