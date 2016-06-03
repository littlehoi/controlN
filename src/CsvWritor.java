

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvWritor {
  static String folder = "C:/Users/npitnhn/Desktop/";
  static List<String[]> listRowOut = new ArrayList<String[]>();
  private static void writeCSVFile(String fileName, List<String[]> allEntries) {
    CSVWriter writer;
    try {
        writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(allEntries);
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

	public static void generateFile(List<String[]> listRowIn,String fileName) {
		int hit = -1;
		int indexStart, indexEnd = 0;
		String no = null;
		String[] row = null;
		String[] rowOut = null;
		listRowOut=listRowIn;

		System.out.println("listRowOut=" + listRowOut.size());
		if ((fileName==null)||(fileName.isEmpty()))
			fileName = "RowOut.csv";
		writeCSVFile(folder + fileName, listRowOut);
	}

}
