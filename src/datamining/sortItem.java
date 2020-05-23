package datamining;

import java.util.Comparator;

public class sortItem implements Comparator<Object>{
	//根据type的先后顺序比大小，type小返回1
	@Override
	public int compare(Object o1, Object o2) {
		item i1 = (item) o1;
		item i2 = (item) o2;
		if(i1.type > i2.type)
			return 1;
		else
			return -1;
	}
	
}
