//file: NaiveBayes.java
import java.util.*;
import java.io.*;
public class NaiveBayes{
	public ArrayList<instanceTable> dataTest;

	public NaiveBayes(){
		dataTest = new ArrayList<instanceTable>();
	}

	public NaiveBayes(instanceTable _instanceTable){
		dataTest = new ArrayList<instanceTable>();
		for(int cls = 0; cls < datastore.ClassDomain.size(); cls++){
			instanceTable tempInstanceTable = new instanceTable();
			for (int row = 0; row < dataTest.size(); row++){
				instance tempInstance = new instance();
				for(int att = 0; att < datastore.AttributeDomainTable.size(); att++){
					tempInstance.add(_instanceTable.getElement(row,att));
				}
				if(tempInstance.getElement(tempInstance.size()-1).equals(datastore.ClassDomain.getElement(cls))){
					tempInstanceTable.add(tempInstance);
				}
			}
			dataTest.add(tempInstanceTable);
		}
	}

}