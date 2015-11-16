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

		//bagi jadi probabilitas, class
		float total_class = 0;
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			total_class = total_class + ProbabilityNB.getClassProb().get(dcls);
		}
		System.out.println(total_class);
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			if(ProbabilityNB.getClassProb().get(dcls) != 0 && total_class != 0){
				ProbabilityNB.getClassProb().set(dcls, ProbabilityNB.getClassProb().get(dcls) / total_class);
			}
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

		//bagi jadi probabilitas, general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
				float total_prob = 0;
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					total_prob = total_prob + ProbabilityNB.getGeneralProb().get(att).get(datt).get(dcls);
				}
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					ProbabilityNB.getGeneralProb().get(att).get(datt).set(dcls, ProbabilityNB.getGeneralProb().get(att).get(datt).get(dcls) / total_prob);
				}
			}
		}
		System.out.println();
		ProbabilityNB.printGeneral();

	}
	public static void mulai(){

	}

}