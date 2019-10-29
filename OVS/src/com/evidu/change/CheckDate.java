package com.evidu.change;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CheckDate extends TimerTask {
	public static void main(String args[]) {
		// Instantiate a objects
		Timer timer = new Timer();
		timer.schedule(new CheckDate(), 0, 1000);
	
}

	@Override
	public void run() {
		String startDate="";
		String endDate="";
		Model model=new Model();
		DateDAO dateDAO=new DateDAO(); 
		DateDAO dateDAO1=new DateDAO(); 
		ArrayList<Model> dateArray = dateDAO.getElectionInfo();
		for(int i=0;i<dateArray.size();i++){
			
			if(i!=0){
				startDate =startDate+","+ dateArray.get(i).getStartDate();
			endDate =endDate+","+ dateArray.get(i).getEndDate();
			}
			else{
				startDate =startDate+ dateArray.get(i).getStartDate();
			endDate =endDate+ dateArray.get(i).getEndDate();
			}}
		//System.out.println("All  startDates: "+ startDate);
		//System.out.println("All  endDates: "+ endDate);
		
	String startDateIndex[]=startDate.split(",");
	
	
	String endDateIndex[]=endDate.split(",");
	
	
	  LocalDateTime myObj = LocalDateTime.now();
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	   String formattedDate = myObj.format(myFormatObj); 
	 // System.out.println(formattedDate);
	
	
	
	  LocalDateTime now = LocalDateTime.parse(formattedDate, myFormatObj);
	//  System.out.println(now);
	
	for(int i=0;i<dateArray.size();i++){
		
		LocalDateTime startdate= LocalDateTime.parse(startDateIndex[i], myFormatObj);
		LocalDateTime enddate= LocalDateTime.parse(endDateIndex[i], myFormatObj);
		
		System.out.println("now "+now);
		
		if((now.isEqual(startdate)||now.isAfter(startdate))&&(now.isBefore(enddate))){
			System.out.println("Time to Update status");
			
			try {
				boolean test=dateDAO1.updateDate(startDateIndex[i]);
				System.out.println("startDateIndex="+startDateIndex[i]);
				if(test==true){
					System.out.println("Status updated");
				}
				else{
					System.out.println("not updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(now.isEqual(enddate)||now.isAfter(enddate)){
			System.out.println("Time to Diactivate status");
			try {
				boolean test1=dateDAO1.updateDate1(endDateIndex[i]);
				if(test1==true){
					System.out.println("Status updated");
				}
				else{
					System.out.println("not updated");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("nothing to happen");
		}
		
	}
	
	
	}
		
		
	}

