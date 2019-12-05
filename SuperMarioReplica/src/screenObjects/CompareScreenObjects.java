package screenObjects;

import java.util.Comparator;

public class CompareScreenObjects implements Comparator<ScreenObject>{
	
	@Override
	public int compare(ScreenObject so1, ScreenObject so2) {
		
		if(so1.getXValue() == so2.getXValue()) {
			return 0;
		}
		else {
			return (so1.getXValue() - so2.getXValue());
		}
	}


}
