package newDataParser;

public class IDTIndex {
	public IowaDataType iowa;
	public double index;
	
	public IDTIndex(IowaDataType iowa, double index) {
		this.index = index;
		this.iowa = iowa;
	}
	
	public void setIndex(double i) {
		index = i;
	}
	
	public double getIndex() {
		return index;
	}
}

