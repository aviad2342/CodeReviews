package model;

import controller.ConnectionToDB;
import view.LoginFrame;
import view.ViewLogic;

public class MainClass {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
                ConnectionToDB.getConect();
		LoginFrame loginFrame = new LoginFrame(ViewLogic.getInstance());

	}

}
