//file: NaiveBayes.java
import java.util.*;
import java.io.*;
public class NaiveBayes{
	public instanceTable dataTest;
	public probabilityNB ProbabilityNB;

	public NaiveBayes(){
		dataTest = new instanceTable();
		ProbabilityNB = new probabilityNB();
	}

	public NaiveBayes(instanceTable _instanceTable){
		dataTest = new instanceTable();
		ProbabilityNB = new probabilityNB();
		for (int row = 0; row < _instanceTable.size(); row++){
			instance tempInstance = new instance();
			for (int att = 0; att < _instanceTable.getRow(row).size(); att++){
				tempInstance.add(_instanceTable.getElement(row, att));
			}
			dataTest.add(tempInstance);
		}

		//count class
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			float count = 0;
			//iterasi seluruh DataStore
			for(int row = 0; row < datastore.DataStore.size(); row++){
				if(datastore.DataStore.getElement(row,datastore.AttributeDomainTable.size())
					.equals(datastore.ClassDomain.getElement(dcls))){
					count = count + 1;
				}
			}
			ProbabilityNB.addClass(count);
		}
		ProbabilityNB.printClass();


		//count general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			ArrayList<ArrayList<Float>> tempAttProb = new ArrayList<ArrayList<Float>>();
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				ArrayList<Float> tempDattProb = new ArrayList<Float>();
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					float count = 0;
					//iterasi seluruh DataStore
					for(int row = 0; row < datastore.DataStore.size(); row++){
						
							if(datastore.DataStore.getElement(row,att)
								.equals(datastore.AttributeDomainTable.getElement(att,datt))
								&& datastore.DataStore.getElement(row,datastore.AttributeDomainTable.size())
								.equals(datastore.ClassDomain.getElement(dcls))){
								count = count + 1;
							}
					}
					tempDattProb.add(count);
				}
				tempAttProb.add(tempDattProb);
			}
			ProbabilityNB.addGeneral(tempAttProb);
		}
		ProbabilityNB.printGeneral();
	}

	public void countGeneral(){
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			ArrayList<ArrayList<Float>> tempAttProb = new ArrayList<ArrayList<Float>>();
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				ArrayList<Float> tempDattProb = new ArrayList<Float>();
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					float count = 0;
					//iterasi seluruh DataStore
					for(int row = 0; row < datastore.DataStore.size(); row++){
						for(int col = 0; col < datastore.DataStore.getRow(row).size(); col++){
							if(datastore.DataStore.getElement(row,col)
								.equals(datastore.AttributeDomainTable.getElement(att,datt))
								&& datastore.DataStore.getElement(row,datastore.AttributeDomainTable.size())
								.equals(datastore.ClassDomain.getElement(dcls))){
								count = count + 1;
							}
						}
					}
					tempDattProb.add(count);
				}
				tempAttProb.add(tempDattProb);
			}
			ProbabilityNB.addGeneral(tempAttProb);
		}
		ProbabilityNB.printGeneral();
	}

	public static void mulai(){

	}

}