package com.qait.gale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAdder {
	
	static void addToDb(String type,String prodLoc, String prodName, String loadTestNo, String loadTestDuration, Statement s) throws IOException, SQLException{
		
		String tempFileFullPath =prodLoc+"\\"+prodName+".csv";
		int x;
	    String line;
	    String[] dataInLine;
        FileReader fileReader = new FileReader(tempFileFullPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        while ((line = bufferedReader.readLine()) != null) {
        	
        	if(line.startsWith("sampler_label"))
        		continue;
        	else if(line.contains("Overall Response Times"))
        		continue;
        	
        	dataInLine=line.split(",");
        	
        	if(type.equals("AggregateReport"))
        	   x = s.executeUpdate("insert into aggregate_report values("
        			   +"'"+dataInLine[0]+"','"+dataInLine[1]+"','"+dataInLine[2]+"','"+dataInLine[3]
        			   +"','"+dataInLine[4]+"','"+dataInLine[5]+"','"+dataInLine[6]+"','"+dataInLine[7]
        			   +"','"+dataInLine[8]+"','"+dataInLine[9]+"','"+prodName+"','"+loadTestNo+"','"+loadTestDuration
        	   		   + "')");
        	
        	if(type.equals("ResponseTime"))
        	   x = s.executeUpdate("insert into response_time_graph values("
        			   +"'"+dataInLine[0]+"','"+dataInLine[1]+"','"+prodName+"','"+loadTestNo
        	   		   + "')");
//        	System.out.println(dataInLine[0]);
        }	
        bufferedReader.close();
        fileReader.close();
	}
}
