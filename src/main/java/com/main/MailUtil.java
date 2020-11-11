package com.main;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MailUtil {

	public static void sendmail(final String myAccountEmail, final String password, int senderCount) throws Exception
			 {

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.gmail.com/");
		WebElement emailInputBox = driver.findElement(By.xpath("//*[@id='identifierId']"));
		emailInputBox.sendKeys(myAccountEmail);

		// Click next button
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/div[2]")).click();

		Thread.sleep(6000);

		WebElement pwdInputBox = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
		pwdInputBox.sendKeys(password);

		// Click next button
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/div[2]")).click();

		int recepientCount = 1;
		System.out.println("Prearing to sent ..");

		File myObj = new File("receiver.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String receiver = myReader.nextLine();
			System.out.println(" receiver " + receiver);
			Thread.sleep(6000);
			driver.findElement(By.xpath("//*[text()=\"Compose\"]")).click();

			Thread.sleep(6000);

			WebElement toField = driver.findElement(By.xpath("//*[@name=\"to\"]"));
			toField.sendKeys(receiver);

			WebElement subject = driver.findElement(By.xpath("//*[@name=\"subjectbox\"]"));
			subject.sendKeys(senderCount + " ROHIT SINGH " + recepientCount);

			String content = new String(Files.readAllBytes(Paths.get("mailBody.txt")), StandardCharsets.UTF_8);

			WebElement mailbodyField = driver
					.findElement(By.xpath("//*[@aria-label=\"Message Body\"][@role=\"textbox\"]"));
			mailbodyField.sendKeys(content);

			String filename = "attachment.txt";

			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(senderCount + " ROHIT SINGH " + recepientCount);
			myWriter.close();

			WebElement attachment = driver.findElement(By.xpath("//*[@name=\"Filedata\"]"));
			String currentDirectory = System.getProperty("user.dir");
			System.out.println(currentDirectory + "--------------------");
			String filePath = currentDirectory + "\\attachment.txt";
			attachment.sendKeys(filePath);

			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[text()=\"Send\"]")).click();

			System.out.println("Message sent");

			recepientCount++;

		}
		myReader.close();

	}

}
