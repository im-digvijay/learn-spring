package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {

	private String fileName = "src/fortune-data.txt";
	private List<String> fortunes;

	// create a random number generator
	private Random myRandom = new Random();

	public FileFortuneService() {
		System.out.println(">> FileFortuneService: inside default constructor");
	}
	
	@PostConstruct
	private void loadTheFortunesFile() {
		
		System.out.println(">> FileFortuneService: inside method loadTheFortunesFile");

		File theFile = new File(fileName);
		
		System.out.println("Reading fortunes from file: " + theFile);
		System.out.println("File exists: " + theFile.exists());
		
		// initialize array list
		fortunes = new ArrayList<String>();
		
		// read fortunes from file
		try (BufferedReader br = new BufferedReader(
				new FileReader(theFile))) {

			String tempLine;

			while ((tempLine = br.readLine()) != null) {
				fortunes.add(tempLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myRandom.nextInt(fortunes.size());

		return fortunes.get(index);
	}

}
