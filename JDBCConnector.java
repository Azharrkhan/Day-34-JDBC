package com.bl.sqlandado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver object

			String DBName = "payroll_service"; // Make connection
			String URL = "jdbc:mysql://localhost:3306/" + DBName;
			String dbUserName = "root";
			String dbPass = "1074";
			Connection con = DriverManager.getConnection(URL, dbUserName, dbPass);
			System.out.println("Connected with " + DBName);

			Statement stmt = con.createStatement(); // create statement
			System.out.println("Statement obj create....");

			String selectQuery = "select * from payroll_service";
			ResultSet rs = stmt.executeQuery(selectQuery);
			System.out.println(rs);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(4) + " | "
						+ rs.getString(3) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | " + rs.getString(7)
						+ " | " + rs.getString(8) + " | " + rs.getString(9) + " | " + rs.getString(10) + " | "
						+ rs.getString(11) + " | " + rs.getString(12) + " | " + rs.getString(13));
			}

			String updateQuery = "update payroll_service set department = 'IT' where name = 'Azhar'";
			String updateQuery1 = "update basic_pay = 300000 from payroll_service where id=13";

			int noOFRowsAffected = stmt.executeUpdate(updateQuery);
			System.out.println("Execute statement....");
			if (noOFRowsAffected > 0) {
				System.out.println("Record updated....");
			}

			String updateQuery2 = "update payroll_service set basic_pay = ? where id =?";
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setLong(1, 3000000);
			pstmt.setInt(2, 13);

			int noOfRowsAffected = pstmt.executeUpdate();
			System.out.println("Execute Statement...");
			if (noOfRowsAffected > 0) {
				System.out.println("Record updated...");
			}

			PreparedStatement pstmt1 = con.prepareStatement("select * from payroll_service");
			ResultSet rs1 = pstmt1.executeQuery();
			System.out.println(rs1);
			while (rs.next()) {
				System.out.println(rs1.getInt(1) + " | " + rs1.getString(2) + " | " + rs1.getInt(4) + " | "
						+ rs1.getString(3) + " | " + rs1.getString(5) + " | " + rs1.getString(6) + " | " + rs1.getString(7)
						+ " | " + rs1.getString(8) + " | " + rs1.getString(9) + " | " + rs1.getString(10) + " | "
						+ rs1.getString(11) + " | " + rs1.getString(12) + " | " + rs1.getString(13));
			}

			PreparedStatement pstmt2 = con.prepareStatement("select salary from payroll_service where name = 'Azhar'");
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println(rs);
			while (rs2.next()) {
				System.out.println(rs2.getLong(7));
			}

			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
