package net.codejava.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdventSolverApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdventSolverApp frame = new AdventSolverApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdventSolverApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//day one
		//------------------------------------------------
		JPanel panelDayOne = new JPanel();
		tabbedPane.addTab("DayOne",null, panelDayOne,null);
		panelDayOne.setLayout(null);
		
		String dayOneFilename = "dayOneInput.txt";
		
		JLabel dayOneLabelOne = new JLabel(String.format("file: %s", dayOneFilename));
		dayOneLabelOne.setBounds(10, 11, 124, 14);
		panelDayOne.add(dayOneLabelOne);
		
		JButton dayOneOneButton = new JButton("execute Part One");
		dayOneOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dayOneAnswer = ""+dayOneMain(dayOneFilename);
				JOptionPane.showMessageDialog(AdventSolverApp.this, dayOneAnswer);
			}
		});
		dayOneOneButton.setBounds(10, 36, 147, 23);
		panelDayOne.add(dayOneOneButton);
		
		JButton dayOneTwoButton = new JButton("execute Part Two");
		dayOneTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dayOneAnswerTwo = ""+dayOneMainTwo(dayOneFilename);
				JOptionPane.showMessageDialog(AdventSolverApp.this, dayOneAnswerTwo);
			}
		});
		dayOneTwoButton.setBounds(10, 70, 147, 23);
		panelDayOne.add(dayOneTwoButton);
				
		
		
		//day two
		//------------------------------------------------
		JPanel panelDayTwo = new JPanel();
		tabbedPane.addTab("DayTwo", null, panelDayTwo, null);
		panelDayTwo.setLayout(null);
		
		String dayTwoFilename = "dayTwoInput.txt";
		
		JLabel dayTwoLabelOne = new JLabel(String.format("file: %s", dayTwoFilename));
		dayTwoLabelOne.setBounds(10,11,124,14);
		panelDayTwo.add(dayTwoLabelOne);
		
		JButton dayTwoOneButton = new JButton("execute Part One");
		dayTwoOneButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				String dayTwoAnswer = ""+dayTwoMainOne(dayTwoFilename);
				JOptionPane.showMessageDialog(AdventSolverApp.this, dayTwoAnswer);
			}
		});
		dayTwoOneButton.setBounds(10,36,147,23);
		panelDayTwo.add(dayTwoOneButton);
		
		JButton dayTwoTwoButton = new JButton("execute Part Two");
		dayTwoTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dayTwoAnswerTwo = ""+dayTwoMainTwo(dayTwoFilename);
				JOptionPane.showMessageDialog(AdventSolverApp.this, dayTwoAnswerTwo);
			}
		});
		dayTwoTwoButton.setBounds(10,70,147,23);
		panelDayTwo.add(dayTwoTwoButton);
		
		//day five
				//------------------------------------------------
				JPanel panelDayFive = new JPanel();
				tabbedPane.addTab("DayFive", null, panelDayFive, null);
				panelDayFive.setLayout(null);
				
				String dayFiveFilename = "dayFiveInput.txt";
				
				JLabel dayFiveLabelOne = new JLabel(String.format("file: %s", dayFiveFilename));
				dayFiveLabelOne.setBounds(10,11,124,14);
				panelDayFive.add(dayFiveLabelOne);
				
				JButton dayFiveOneButton = new JButton("execute Part One");
				dayFiveOneButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0)
					{
						String dayFiveAnswer = ""+dayFiveMainOne(dayFiveFilename);
						JOptionPane.showMessageDialog(AdventSolverApp.this, "Answer:\n"+dayFiveAnswer);
					}
				});
				dayFiveOneButton.setBounds(10,36,147,23);
				panelDayFive.add(dayFiveOneButton);
				
				JButton dayFiveTwoButton = new JButton("execute Part Two");
				dayFiveTwoButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String dayFiveAnswerTwo = ""+dayFiveMainTwo(dayFiveFilename);
						JOptionPane.showMessageDialog(AdventSolverApp.this, "Answer:\n"+dayFiveAnswerTwo);
					}
				});
				dayFiveTwoButton.setBounds(10,70,147,23);
				panelDayFive.add(dayFiveTwoButton);
	}
	
	
	/* Day Five Helper Method
	 * divides one int by another, always rounding Up
	 * */
	private int roundUpDivide(int input,int divisor)
	{
		return ((input+1)/divisor);
	}
	
	private int dayFiveMainOne(String filename)
	{
		//create a scanner
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}
		
		int ret = 0, spamCounter = 0; 
		int seatID;
		int minimumRow = 0, maximumRow = 127;
		int minimumSeat = 0, maximumSeat = 7;
		char peeker;
		String line;
		
		while (scan.hasNext())
		{
			minimumRow = 0;
			maximumRow = 127;
			line = scan.nextLine();//"FBFBBFFRLR";//scan.nextLine();
			for(int i=0;i<7;i++)
			{
				peeker = line.charAt(i);
				if(peeker == 'B')
				{
					minimumRow = minimumRow + roundUpDivide((maximumRow-minimumRow),2);
					//JOptionPane.showMessageDialog(this, "B MIN: "+minimumRow+" max: "+maximumRow);
				}
				else if(peeker == 'F')
				{
					maximumRow = maximumRow - roundUpDivide((maximumRow-minimumRow),2);//((maximum - minimum)/2);
					//JOptionPane.showMessageDialog(this, "F min: "+minimumRow+" MAX: "+maximumRow);
				}
				else
					JOptionPane.showMessageDialog(this, "something went wrong: "+line+" ("+i);
			}
			//JOptionPane.showMessageDialog(this, "Row: "+minimumRow+" / "+maximumRow);
			
			minimumSeat=0;
			maximumSeat=7;
			for(int j = 7;j < 10; j++) {
				peeker = line.charAt(j);
				if(peeker == 'R')
				{
					minimumSeat = minimumSeat + roundUpDivide((maximumSeat-minimumSeat),2);
					//JOptionPane.showMessageDialog(this, "R MIN: "+minimumSeat+" max: "+maximumSeat);
				}
				else if(peeker == 'L')
				{
					maximumSeat = maximumSeat - roundUpDivide((maximumSeat-minimumSeat),2);
					//JOptionPane.showMessageDialog(this, "L min: "+minimumSeat+" MAX: "+maximumSeat);
				}
				else
					JOptionPane.showMessageDialog(this, "something went wrong (LR): "+line+" ("+j);
			}
			
			seatID = ((maximumRow * 8)+(maximumSeat));
			
			if(seatID > ret)
				ret = seatID;
			
			if(spamCounter < 4 || (spamCounter % 100 == 0))
				JOptionPane.showMessageDialog(this, line +"\nRow: "+(maximumRow)+" Seat: "+(maximumSeat)
						+"\nID: "+seatID+"\nret: "+ret);
			
			if(maximumRow != minimumRow || maximumSeat != minimumSeat)
				JOptionPane.showMessageDialog(this, "Something went wrong\nRow: "+
						maximumRow+" Seat:"+maximumSeat);
			
			spamCounter ++;
		}
		
	//	if(maximumRow == minimumRow && maximumSeat == minimumSeat)
	//		JOptionPane.showMessageDialog(this, "Row: "+(maximumRow)+" Seat:"+(maximumSeat));
		if(maximumRow != minimumRow || maximumSeat != minimumSeat)
			JOptionPane.showMessageDialog(this, "Something went wrong\nRow: "+
		maximumRow+" Seat:"+maximumSeat);
		
		scan.close();
		return ret;
	}
	
	private int dayFiveMainTwo(String filename)
	{
		//create a scanner
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}

		int ret = 0, spamCounter = 0; 
		int seatID;
		int minimumRow = 0, maximumRow = 127;
		int minimumSeat = 0, maximumSeat = 7;
		char peeker;
		String line;
		char[] allIDs = new char[1025]; // maximum seat ID is 1023
		Arrays.fill(allIDs, 'n'); 
		
		while (scan.hasNext())
		{
			minimumRow = 0;
			maximumRow = 127;
			line = scan.nextLine();//"FBFBBFFRLR";//scan.nextLine();
			for(int i=0;i<7;i++)
			{
				peeker = line.charAt(i);
				if(peeker == 'B')
				{
					minimumRow = minimumRow + roundUpDivide((maximumRow-minimumRow),2);
					//JOptionPane.showMessageDialog(this, "B MIN: "+minimumRow+" max: "+maximumRow);
				}
				else if(peeker == 'F')
				{
					maximumRow = maximumRow - roundUpDivide((maximumRow-minimumRow),2);//((maximum - minimum)/2);
					//JOptionPane.showMessageDialog(this, "F min: "+minimumRow+" MAX: "+maximumRow);
				}
				else
					JOptionPane.showMessageDialog(this, "something went wrong (FB): "+line+" ("+peeker);
			}

			minimumSeat=0;
			maximumSeat=7;
			for(int j = 7;j < 10; j++) {
				peeker = line.charAt(j);
				if(peeker == 'R')
				{
					minimumSeat = minimumSeat + roundUpDivide((maximumSeat-minimumSeat),2);
				}
				else if(peeker == 'L')
				{
					maximumSeat = maximumSeat - roundUpDivide((maximumSeat-minimumSeat),2);
				}
				else
					JOptionPane.showMessageDialog(this, "something went wrong (LR): "+line+" ("+peeker);
			}

			seatID = ((maximumRow * 8)+(maximumSeat));

			allIDs[seatID] = 'y';
			//JOptionPane.showMessageDialog(this, seatID+")\n"+allIDs[seatID-1]+""+allIDs[seatID]+""+allIDs[seatID+1]);
		}

		//String wholePlaneString = "";
		for(int k = 1; k < allIDs.length ; k++) {
			if(allIDs[k-1] == 'y' && allIDs[k] == 'n' && allIDs[k+1] == 'y')
			{
				ret = k;
			}
			//wholePlaneString += allIDs[k];
		}
		
		//JOptionPane.showMessageDialog(this, wholePlaneString);

		scan.close();
		return ret;
	}
	
	private int dayTwoMainTwo(String filename)
	{
		//create a scanner
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}
		
		String line,password;			//the full line from the input file, and the password
		char keyChar;					//key char value
		int numOne,numTwo;	
		boolean oneMatches, twoMatches;
		String[] strArr;				//an array of strings
		int spamCounter = 0;			//prevent spam
		int ret = 0; 					//return variable
		
		
		while(scan.hasNext())
		{
			//get the next line
			line = scan.nextLine();
			
			//split the line into a string array using either '-' or ':' or ' '
			strArr = line.split("[-: ]");
			
			if(Array.getLength(strArr) > 1)
			{
				//parse string array into usable variables
				numOne = Integer.parseInt(strArr[0]);
				numTwo = Integer.parseInt(strArr[1]);
				keyChar	 = strArr[2].charAt(0);
				password = strArr[4];
				
				oneMatches = (password.charAt(numOne-1) == keyChar);
				twoMatches = (password.charAt(numTwo-1) == keyChar);
						
				//if oneMatches XOR twoMatches, increase ret by one
				if(oneMatches ^ twoMatches) {
					ret++;
					/*
					 * the following lines are for testing purposes and 
					 * transparency of the internal process.
					 * It displays the first few valid passwords, and some variables
					 * */
					spamCounter ++;
					if(spamCounter <= 3)
						JOptionPane.showMessageDialog(this, "Is valid: \n(left:"+numOne+" "+oneMatches+")(right:"+numTwo+" "+twoMatches+
								")\n(char:"+keyChar+")\n(pass:"+password+")");
					
				}
			}
		}
		
		scan.close();
		//return the return value
		return ret;
	}
	
	private int dayTwoMainOne(String filename)
	{
		//create a scanner
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}
		
		String line,password;			//the full line from the input file, and the password
		char keyChar;					//key char value
		int minCount,maxCount, count;	//minimum char occurrences, maximum, and count of occurences
		String[] strArr;				//an array of strings
		int spamCounter = 0;			//prevent spam
		int ret = 0; 					//return variable
		
		
		while(scan.hasNext())
		{
			//get the next line
			line = scan.nextLine();
			
			//split the line into a string array using either '-' or ':' or ' '
			strArr = line.split("[-: ]");
			
			if(Array.getLength(strArr) > 1)
			{
				//parse string array into usable variables
				minCount = Integer.parseInt(strArr[0]);
				maxCount = Integer.parseInt(strArr[1]);
				keyChar	 = strArr[2].charAt(0);
				password = strArr[4];
				
				//count the number of occurrences of the key character in the password
				count = 0;
				for(int i=0 ;i<password.length();i++)
				{
					if( password.charAt(i) == keyChar)
						count++;
				}
				
				//if the count is appropriate, increase return value by one
				if(count <= maxCount && count >= minCount)
				{
					//create a messagebox (but only a few to avoid spam)
					spamCounter++;
					if(spamCounter <= 3)
						JOptionPane.showMessageDialog(this, "Is valid:\n(min:"+minCount+")(max:"+maxCount+
								")\n(char:"+keyChar+")\n(pass:"+password+")");
					
					//increase return value by one
					ret++;
				}
			}
		}
		scan.close();
		
		//return the return value
		return ret;
	}

	private int dayOneMain(String filename)
	{
		int ret = 0;
		Scanner scanCtr = null;
		Scanner scanReal = null;
		try
		{
			scanCtr = new Scanner(new File(filename));
			scanReal = new Scanner(new File(filename));
		}
		catch( FileNotFoundException fnfe)
		{
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}
		
		int ctr = 0;
		while(scanCtr.hasNext()) 
		{
			scanCtr.nextInt();
			ctr++;
		}
		
		int[] arr = new int[ctr];
		
		for(int i=0;i<ctr;i++)
		{
			arr[i] = scanReal.nextInt();
		}
		
		//JOptionPane.showMessageDialog(this, "{"+ctr+"}");
		
		for(int j=0;j<ctr;j++)
		{
			for(int k=0;k<ctr;k++)
			{
				if(arr[j] + arr[k] == 2020)
				{
					ret = arr[j] * arr[k];
					JOptionPane.showMessageDialog(this, "("+arr[j]+"),("+arr[k]+")");
				}
			}
		}
		scanCtr.close();
		scanReal.close();
		
		return ret;
	}
	
	private int dayOneMainTwo(String filename)
	{
		int ret = 0;
		Scanner scanCtr = null;
		Scanner scanReal = null;
		try
		{
			scanCtr = new Scanner(new File(filename));
			scanReal = new Scanner(new File(filename));
		}
		catch( FileNotFoundException fnfe)
		{
			JOptionPane.showMessageDialog(this, "file "+filename+" not found");
		}
		
		int ctr = 0;
		while(scanCtr.hasNext()) 
		{
			scanCtr.nextInt();
			ctr++;
		}
		
		int[] arr = new int[ctr];
		
		for(int i=0;i<ctr;i++)
		{
			arr[i] = scanReal.nextInt();
		}
		
		for(int j=0;j<ctr;j++)
		{
			for(int k=0;k<ctr;k++)
			{
				for(int l=0;l<ctr;l++)
				{
					if(arr[j] + arr[k] + arr[l] == 2020)
					{
						ret = arr[j] * arr[k] * arr[l];
						JOptionPane.showMessageDialog(this, "("+arr[j]+"),("+arr[k]+"),("+arr[l]+")");
					}
				}
			}
		}
		scanCtr.close();
		scanReal.close();
		
		return ret;
	}
}
