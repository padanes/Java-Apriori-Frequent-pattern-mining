package datamining;

import java.util.Comparator;

public class sortItem implements Comparator<Object>{
	//����type���Ⱥ�˳��ȴ�С��typeС����1
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
