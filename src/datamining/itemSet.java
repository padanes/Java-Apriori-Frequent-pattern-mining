package datamining;

import java.util.*;

public class itemSet<E> extends ArrayList<E>{
	public int frequentNumber;
	

	public itemSet(ArrayList<item> input) {
		for(item i:input)
			this.add((E) i);
		frequentNumber = 0;
		// TODO Auto-generated constructor stub
	}

	public itemSet(String str) {
		String[] substract = str.split(",");
		for(int i=0;i<substract.length;i++)
			this.add((E) new item(substract[i]));
	}
	public itemSet() {
		// TODO Auto-generated constructor stub
	}

	//两个集合生成k+1项集
	public itemSet<E> joinSet(itemSet<E> target){
		List<E> subThis = this.subList(0, this.size()-1);
		List<E> subTarget = target.subList(0, target.size()-1);
		if(subThis.equals(subTarget)) {
			if(!this.get(this.size()-1).equals(target.get(target.size()-1))) {
				itemSet<E> newSet = new itemSet<E>();
				newSet.addAll(this);
				newSet.add(target.get(target.size()-1));
				newSet.sort(new sortItem());
				return newSet;
			}
		}
		return null;
	}
	
	//检验自身在整个交易记录中出现的次数
	public int examineFreNum(ArrayList<itemSet<item>> IS) {
		for(int i=0;i<IS.size();i++) {
			itemSet<item> a = IS.get(i);
			if(IS.get(i).containsAll(this))
				this.frequentNumber++;
		}
		return this.frequentNumber;
	}
	

	public String toString() {

		String oneLine = "";
		for(int i=0;i<this.size();i++) {
			oneLine += this.get(i).toString();
			if(i!=this.size()-1)
				oneLine += ",";
		}
		return oneLine;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		itemSet<item> a = new itemSet<item>("b,c,e,f,h");
		itemSet<item> b = new itemSet<item>("a,c");
		a.joinSet(b);
		
		System.out.println();
	}

}
