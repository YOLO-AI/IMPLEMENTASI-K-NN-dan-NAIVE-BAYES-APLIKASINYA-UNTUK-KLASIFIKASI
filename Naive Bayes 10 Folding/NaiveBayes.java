//file: NaiveBayes.java
import java.util.*;
import java.io.*;
public class NaiveBayes{

	public NaiveBayes(){
	}

	public probabilityNB makeModel(instanceTable _instanceTable){
		probabilityNB ProbabilityNB = new probabilityNB();

		//count class
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			float count = 0;
			//iterasi seluruh DataStore
			for(int row = 0; row < _instanceTable.size(); row++){
				if(_instanceTable.getElement(row,datastore.AttributeDomainTable.size())
					.equals(datastore.ClassDomain.getElement(dcls))){
					count = count + 1;
				}
			}
			ProbabilityNB.addClass(count);
		}

		//bagi jadi probabilitas, class
		float total_class = 0;
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			total_class = total_class + ProbabilityNB.getClassProb().get(dcls);
		}
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			if(ProbabilityNB.getClassProb().get(dcls) != 0 && total_class != 0){
				ProbabilityNB.getClassProb().set(dcls, ProbabilityNB.getClassProb().get(dcls) / total_class);
			}
		}
		System.out.println("Model Kelas Naive Bayes:");
		ProbabilityNB.printClass();
		System.out.println();

		//count general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			ArrayList<ArrayList<Float>> tempAttProb = new ArrayList<ArrayList<Float>>();
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				ArrayList<Float> tempDattProb = new ArrayList<Float>();
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					float count = 0;
					//iterasi seluruh DataStore
					for(int row = 0; row < _instanceTable.size(); row++){
						
							if(_instanceTable.getElement(row,att)
								.equals(datastore.AttributeDomainTable.getElement(att,datt))
								&& _instanceTable.getElement(row,datastore.AttributeDomainTable.size())
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
		//bagi jadi probabilitas, general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
				float total_prob = 0;
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					total_prob = total_prob + ProbabilityNB.getGeneralProb().get(att).get(datt).get(dcls);
				}
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					if(ProbabilityNB.getGeneralProb().get(att).get(datt).get(dcls) != 0 && total_prob != 0)
					ProbabilityNB.getGeneralProb().get(att).get(datt).set(dcls, ProbabilityNB.getGeneralProb().get(att).get(datt).get(dcls) / total_prob);
				}
			}
		}
		System.out.println("Model Naive Bayes:");
		ProbabilityNB.printGeneral();

		return ProbabilityNB;

	}


}