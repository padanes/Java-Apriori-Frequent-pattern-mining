package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class aprioriMining {
	private ArrayList<itemSet<item>> allItem;
	private ArrayList<itemSet<item>> kcandidate;
	private ArrayList<itemSet<item>> k_1candidate;
	
	public int minSup;
	//初始化算法参数，读入数据，设置最小支持度
	public aprioriMining(int minSup){
		allItem = new ArrayList<itemSet<item>>();
		kcandidate = new ArrayList<itemSet<item>>();
		k_1candidate = new ArrayList<itemSet<item>>();
		this.minSup = minSup;
		
		dataProcessor dp = new dataProcessor("item.csv");
		ArrayList<String> all = dp.Read();
		for(int i=0;i<all.size();i++)
			allItem.add(new itemSet<item>(all.get(i)));  
			
	}
	//寻找频繁的一项集
	public void find_frequent_oneItemsets() {
		for(int i=0;i<8;i++) {
			itemSet<item> temp = new itemSet<item>();
			temp.add(new item(i));
			if(temp.examineFreNum(allItem) >= this.minSup)
				k_1candidate.add(temp);
		}
	}
	//apriori算法主体
	public void aprioriMethod() {
		this.find_frequent_oneItemsets();
		while(!k_1candidate.isEmpty()) {
			kcandidate.clear();
			this.apriori_gen();
			int m =1;
			m++;
			for(int i=0;i< kcandidate.size();i++) {
				if(kcandidate.get(i).examineFreNum(allItem)<this.minSup) {
					kcandidate.remove(i);
					i--;
				}
			}
			for(int i=0;i< kcandidate.size();i++)
				System.out.println(kcandidate.get(i).toString());
			ArrayList<itemSet<item>> temp = k_1candidate;
			k_1candidate = kcandidate;
			kcandidate = temp;
			dataProcessor dp = new dataProcessor("frequent_pattern_by_apriori.csv");
			for(itemSet<item> i:kcandidate)
				dp.WriteLine(i.toString());
		}
		
	}
	//生成k+1项的候选集
	public void apriori_gen() {
		for(int i=0;i<k_1candidate.size();i++) {
			for(int j=i+1;j<k_1candidate.size();j++) {
				itemSet<item> join = k_1candidate.get(i).joinSet(k_1candidate.get(j));

				if(!this.has_infrequent_subset(join)) {
					kcandidate.add(join);
				}
				
			}
		}
	}
	//寻找不频繁的子集
	public boolean has_infrequent_subset(itemSet<item> target) {
		if(target == null)
			return true;
		for(int i=0;i<target.size();i++) {
			item temp = target.get(i);
			target.remove(i);
			target.sort(new sortItem());
			boolean flag = true;
			for(itemSet<item> j:k_1candidate) {
				if(j.equals(target)) {
					flag = false;
					break;
				}
			}
			if(!flag)
				return flag;
			target.add(temp);
			target.sort(new sortItem());
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aprioriMining a = new aprioriMining(80);
		a.aprioriMethod();
	}
}
