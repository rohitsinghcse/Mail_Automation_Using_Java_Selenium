package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		int senderCount = 1;
		File myObj = new File("sender.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] userpass = data.split(",");
			System.out.print(" username " + userpass[0]);
			System.out.println(" password " + userpass[1]);
			try {
				MailUtil.sendmail(userpass[0], userpass[1], senderCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			senderCount++;
		}
		myReader.close();

	}

}
