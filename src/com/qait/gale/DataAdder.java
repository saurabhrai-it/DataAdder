package com.qait.gale;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class DataAdder {

	public static void main(String[] args) throws SQLException, Exception {
		
		String loadTestNumber = args[0];
		String loadTestDuration = args[1];
		String currDir = System.getProperty("user.dir").toString();

		String AggregateReportPath = currDir+"\\"+loadTestNumber+"\\AggregateReport";
		String ResponseTimePath = currDir+"\\"+loadTestNumber+"\\ResponseTime";
		String ResponseCodePath = currDir+"\\"+loadTestNumber+"\\ResponseCode";
		String regex="(.+?).csv";
		Pattern prodName = Pattern.compile(regex);
		Matcher prodNameFound = null;
		String tempFile = "";
		Statement s = Admin.connect().createStatement();
		
		File AggregateReportFolder = new File(AggregateReportPath);
		File[] AggregateReportFileList = AggregateReportFolder.listFiles();
		
		for(int i =0; i < AggregateReportFileList.length;i++)
		{
			tempFile=AggregateReportFileList[i].getName();
			prodNameFound = prodName.matcher(tempFile);
            if(prodNameFound.find()) {
            	try {
            		DatabaseAdder.addToDb("AggregateReport",AggregateReportPath,prodNameFound.group(1),loadTestNumber,loadTestDuration,s);
//            		System.out.println(prodNameFound.group(1));	
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					System.exit(0);
				}
            }
		}
		JOptionPane.showMessageDialog(null, "Hurray!!! Results were successfully added!");
	}

}
