package datamining;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class dataProcessor {
	private File csvFile;
	private BufferedWriter csvWriter;
	private BufferedReader csvReader;
	
	public dataProcessor(String filename) {
		csvFile = new File(filename);
		if(!this.csvFile.exists()) {
			try {
				csvFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void WriteLine(String str) {
		try {
			csvWriter = new BufferedWriter(new FileWriter(csvFile,true));
            csvWriter.write(str);
            csvWriter.newLine();
            csvWriter.flush();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> Read()
	{
		String temp;
		ArrayList<String> result = new ArrayList<String>();
		try {
				csvReader = new BufferedReader(new FileReader(csvFile));
				while ((temp = csvReader.readLine()) != null){
					String[] str = temp.split(",");
					result.add(temp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return result;
	}
	//生成随机数据
	public void generateData(int num) {
		for(int i=0;i<num;i++) {
			this.WriteLine(this.chooseItem());
		}
		
	}
	//随机选择transaction中的item
	public String chooseItem() {
		int items[] = {0,1,2,3,4,5,6,7};
		String out[]= {"a","b","c","d","e","f","g","h"};
		String oneLine = "";
		Random random = new Random();
		ArrayList<Integer> item = new ArrayList<Integer>();
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i=0; i< items.length;i++)
			item.add(items[i]);
		int randomNum = random.nextInt(7)+2;
		for(int j=0;j<randomNum;j++) {
			int choose = random.nextInt(item.size());
			output.add(item.get(choose));
			item.remove(choose);	
		}
		output.sort(null);
		for(int i=0;i<output.size();i++) {
			oneLine += out[output.get(i)];
			if(i!=output.size()-1)
			oneLine += ",";
		}
		return oneLine;
	}

	public static void main(String[] args) {
		dataProcessor dg = new dataProcessor("item.csv");
		dg.generateData(300);
	}
}
