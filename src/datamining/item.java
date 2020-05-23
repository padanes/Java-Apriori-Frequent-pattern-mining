package datamining;

import java.util.Arrays;

public class item extends Object{
	public int type;
	//…Ë÷√item¿‡–Õ
	public item(int type) {
		this.type = type;
	}
	
	public item(String Name) {
		String []name = {"a","b","c","d","e","f","g","h"};
		for(int i=0;i<name.length;i++) {
			if(name[i].equals(Name)) {
				this.type = i;
				break;
			}
		}
	}
	
	public String toString() {
		String []name = {"a","b","c","d","e","f","g","h"};
		return name[this.type];
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		item other = (item) obj;
		if (type != other.type)
			return false;
		return true;
	}
}
