package testOutput;

import java.util.ArrayList;

import datamining.*;


public class FPoutputTest {
	private ArrayList<itemSet<item>> allItem;
	
	private ArrayList<itemSet<item>> outputResult;
	public int minSup;
	
	public FPoutputTest(int minSup) {
		allItem = new ArrayList<itemSet<item>>();
		outputResult = new ArrayList<itemSet<item>>();
		this.minSup = minSup;
		
		
		dataProcessor dp = new dataProcessor("item.csv");
		ArrayList<String> all = dp.Read();
		for(int i=0;i<all.size();i++)
			allItem.add(new itemSet<item>(all.get(i)));  
		
		dp = new dataProcessor("frequent_pattern_by_apriori.csv");
		all = dp.Read();
		for(int i=0;i<all.size();i++)
			outputResult.add(new itemSet<item>(all.get(i)));
	}
	
	public void startTest() {
		for(itemSet<item> o:outputResult) {
			o.examineFreNum(allItem);
			if(o.frequentNumber >= minSup)
				System.out.println("Frequent Pattern [ "+ o.toString() + " ] is passed! "
						+ "Support number is "+ String.valueOf(o.frequentNumber));
			else
				System.out.println("[ "+ o.toString() + " ] is not the Frequent Pattern! "
						+ "Support number is "+ String.valueOf(o.frequentNumber) + " < minimum support");
		}
	}
	
	public static void main(String[] args) {
		FPoutputTest p = new FPoutputTest(80);
		p.startTest();
	}
	
}
