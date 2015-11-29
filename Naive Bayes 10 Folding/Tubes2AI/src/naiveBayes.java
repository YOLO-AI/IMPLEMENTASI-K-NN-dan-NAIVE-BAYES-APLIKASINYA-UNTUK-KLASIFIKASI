//file: naiveBayes.java
import java.util.*;

public class naiveBayes{
	public ArrayList<Float> classProbabilityModel;
	public ArrayList<ArrayList<ArrayList<Float>>> generalProbabilityModel; // (row,att,class)
	public float accuracy; //akurasi model Naive Bayes
        public String[] solution;
        
	public naiveBayes(){
		classProbabilityModel = new ArrayList<Float>();
		generalProbabilityModel = new ArrayList<ArrayList<ArrayList<Float>>>();
	}

	public void makeModel(instanceTable DataTraining){
		//count class
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			float count = 0;
			//iterasi seluruh DataStore
			for(int row = 0; row < DataTraining.size(); row++){
				if(DataTraining.getElement(row,datastore.AttributeDomainTable.size())
					.equals(datastore.ClassDomain.getElement(dcls))){
					count = count + 1;
				}
			}
			classProbabilityModel.add(count);
		}

		//bagi jadi probabilitas, class
		float total_class = 0;
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			total_class = total_class + classProbabilityModel.get(dcls);
		}
		for(int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			if(classProbabilityModel.get(dcls) != 0 && total_class != 0){
				classProbabilityModel.set(dcls, classProbabilityModel.get(dcls) / total_class);
			}
		}

		//count general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			ArrayList<ArrayList<Float>> tempAttProb = new ArrayList<ArrayList<Float>>();
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				ArrayList<Float> tempDattProb = new ArrayList<Float>();
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					float count = 0;
					//iterasi seluruh DataStore
					for(int row = 0; row < DataTraining.size(); row++){
                                                if(DataTraining.getElement(row,att)
                                                        .equals(datastore.AttributeDomainTable.getElement(att,datt))
                                                        && DataTraining.getElement(row,datastore.AttributeDomainTable.size())
                                                        .equals(datastore.ClassDomain.getElement(dcls))){
                                                        count = count + 1;
                                                }
					}
					tempDattProb.add(count);
				}
				tempAttProb.add(tempDattProb);
			}
			generalProbabilityModel.add(tempAttProb);
		}
		//bagi jadi probabilitas, general
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
				float total_prob = 0;
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					total_prob = total_prob + generalProbabilityModel.get(att).get(datt).get(dcls);
				}
				for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
					if(generalProbabilityModel.get(att).get(datt).get(dcls) != 0 && total_prob != 0)
					generalProbabilityModel.get(att).get(datt).set(dcls, generalProbabilityModel.get(att).get(datt).get(dcls) / total_prob);
				}
			}
		}
	}

        public void classify(instanceTable DataTest){
            //Array of string berisi nama kelas yang dihasilkan setelah dilakukan Naive Bayes
            solution = new String[DataTest.size()];
            float[] classProb = new float[datastore.ClassDomain.size()];
            
            //Inisialisasi dengan probabilitas per domain kelas
            //Klasifikasi kelas setiap instance pada DataTest
            for(int i=0; i<DataTest.size(); i++){
                    for(int k=0; k<datastore.ClassDomain.size(); k++){
                            classProb[k] = classProbabilityModel.get(k);
                    }
                    //Klasifikasi kelas terhadap instance ke-i di DataTest
                    for(int j=0; j<(DataTest.getRow(i).size()-1); j++){
                            String attribute = DataTest.getRow(i).getElement(j);
                            //Cari indeks atribut
                            int dattIndex=0;
                            String domainAtt = datastore.AttributeDomainTable.getElement(j, dattIndex);
                            while(!(attribute.equals(domainAtt))){
                                    dattIndex++;
                                    domainAtt = datastore.AttributeDomainTable.getElement(j, dattIndex);
                            }
                            
                            //Kalikan dari model probabilitas
                            for(int k=0; k<datastore.ClassDomain.size(); k++){
                                    classProb[k] *= generalProbabilityModel.get(j).get(dattIndex).get(k);
                            }
                    }
                    
                    int maxIndex = 0;
                    float max = classProb[0];
                    for (int k=1; k<classProb.length; k++){
                            //belum tau harus '>=' atau '>'
                            if(classProb[k] > max){
                                    max = classProb[k];
                                    maxIndex = k;
                            }
                    }
                    solution[i] = datastore.ClassDomain.getElement(maxIndex);
            }
        }
        
	public void calculateAccuracy(instanceTable DataTest){
                int count = 0;
                for(int i=0; i<DataTest.size(); i++){
                    String realClass = datastore.DataStore.getElement(i, datastore.DataStore.getRow(i).size()-1);
                    if(realClass.equals(solution[i])){
                        count++;
                    }
                }
                accuracy = (float) count / DataTest.size();
	}

	public void printClass(){
		System.out.println("Model Probabilitas per Kelas:");
		for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			System.out.print(classProbabilityModel.get(dcls) + " ");
		}
		System.out.println();
	}

	public void printGeneral(){
		System.out.println("Model Probabilitas Keseluruhan:");
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					System.out.print(generalProbabilityModel.get(att).get(datt).get(dcls) + " ");
				}
				System.out.print(" | ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public void printAccuracy(){
		System.out.println("Akurasi Model Probabilitas:");
		System.out.println(accuracy*100 + "%");
	}

	public void printThis(){
		printClass();
		System.out.println();
		printGeneral();
		printAccuracy();
                System.out.println();
	}

	public void mulai(instanceTable DataTraining, instanceTable DataTest){
		makeModel(DataTraining);
                classify(DataTest);
                calculateAccuracy(DataTest);
                printThis();
	}
}