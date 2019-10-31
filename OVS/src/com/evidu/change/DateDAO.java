package com.evidu.change;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





public class DateDAO {
	public ArrayList<Model> getElectionInfo() {
		Statement statement = null;
		Model dateVO = null;
		Connection connection = null;
	
		
		ArrayList<Model> arrList01 = new ArrayList<Model>();
		try {
			DatabaseController d = DatabaseController.getInstance();
			connection = (Connection) d.getConnection();
						
			
			String param="INACTIVE";
			String query1="SELECT * FROM commonadminmodule.election Where status='"+param+"'"; 
			ResultSet rs = null;
			statement = (Statement) connection.createStatement();
			rs = statement.executeQuery(query1);
			System.out.println("SQL " + query1);
			while (rs.next()) {
				dateVO = new Model();

				
				
				
				
				dateVO.setStartDate(rs.getString("startdate"));
				dateVO.setEndDate(rs.getString("enddate"));
				
				
				
				
				arrList01.add(dateVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arrList01;
	}
	
	
	boolean count=false;
	public boolean updateDate (String startDate) throws SQLException{
		System.out.println("Check point activate sataus");
		DatabaseController dbController = DatabaseController.getInstance();
		Connection conn = null;
		conn = DatabaseController.getConnection();
		ResultSet rs = null;
		PreparedStatement statement = null;
		String query = "UPDATE `election` SET `status` = 'ACTIVE' Where startdate='"+startDate+"'";
		System.out.println("Query "+query);
		try {
			Statement stmt = conn.createStatement();
		      stmt.executeUpdate(query);
			count=true;
		}catch(SQLException e){
			
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return count;
		
	}
	boolean count1=false;
	public boolean updateDate1(String endDate) throws SQLException{
		//System.out.println("Check point diactivate sataus");
		DatabaseController dbController = DatabaseController.getInstance();
		Connection conn = null;
		conn = DatabaseController.getConnection();
		ResultSet rs = null;
		PreparedStatement statement = null;
		String query = "UPDATE `election` SET `status` = 'INACTIVE' Where enddate='"+endDate+"' AND status='ACTIVE'";
		//System.out.println("query"+query);
		try {
			Statement stmt = conn.createStatement();
		      stmt.executeUpdate(query);
			count1=true;
		}catch(SQLException e){
			
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return count1;
		
	}
	
	
}
