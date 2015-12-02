//file: naiveBayes.java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;

public class naiveBayes{
	public ArrayList<Float> classProbabilityModel;
	public ArrayList<ArrayList<ArrayList<Float>>> generalProbabilityModel; // (row,att,class)
	public float accuracy; //akurasi model Naive Bayes
        public String[] solution;
        public int[][] Matrix = new int[datastore.ClassDomain.size()][datastore.ClassDomain.size()];
        
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
                            while(!(dattIndex < datastore.AttributeDomainTable.getRow(j).size())){
                                    if(attribute.equals(domainAtt)){
                                        break;
                                    }
                                    else{
                                       domainAtt = datastore.AttributeDomainTable.getElement(j, dattIndex);
                                       dattIndex++;    
                                    }
                            }
                            if(dattIndex>= datastore.AttributeDomainTable.getRow(j).size()){
                                dattIndex = datastore.AttributeDomainTable.getRow(j).size() - 1;
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
        
        public void InisialisasiMatrix(){
             for (int a = 0; a < Matrix.length; a++){
                 for (int b = 0; b < Matrix.length; b++){
                     Matrix[a][b] = 0;
                 }
             }
        }
        
         public void confusionMatrix(instanceTable DataTest){
            int j;       
            for ( j = 0; j < DataTest.size(); j++) {
                int lock = 0;
                int k = 0, m = 0;
                String realClass = datastore.DataStore.getElement(j, datastore.DataStore.getRow(j).size()-1);
                //if (dataTest.getElement(j, dataTest.getRow(j).size() - 1).equals(preAns[j])) {
                while (k < datastore.ClassDomain.size() && !realClass.equals(datastore.ClassDomain.getElement(k))){
                    k++;
                }

                while (m < datastore.ClassDomain.size() && !solution[j].equals(datastore.ClassDomain.getElement(m))){
                    m++;
                }

                Matrix[k][m] = Matrix[k][m] + 1;
            }        
        }
	public void printClass(){
		System.out.println("Model Probabilitas per Kelas:");
		for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                        System.out.printf("%.3f", classProbabilityModel.get(dcls));
			System.out.print(" ");
		}
		System.out.println();
	}

	public void printGeneral(){
		System.out.println("Model Probabilitas Keseluruhan:");
                System.out.printf("%6s", "");
                System.out.print(" ");
                for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(0).size(); datt++){
                    for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                            System.out.printf("%5s", datastore.ClassDomain.getElement(dcls));
                            System.out.print(" ");
                    }
                    System.out.print("| ");
                }
                System.out.println();
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
                        System.out.printf("%6s", datastore.AttributeDomainTable.attributeName.get(att));
                        System.out.print(" ");
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                                        System.out.printf("%.3f", generalProbabilityModel.get(att).get(datt).get(dcls));
					System.out.print(" ");
				}
				System.out.print("| ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public void printAccuracy(){
                System.out.println("Akurasi Model Probabilitas:");
                System.out.println(accuracy*100 + "%");
	}
        
        public void printConfusionMatrix(){
            System.out.printf("%9s", "");
            for (int a = 0; a < datastore.ClassDomain.size(); a++){
                System.out.printf("%8s", datastore.ClassDomain.getElement(a));
            }
            System.out.println();
            for (int a = 0; a < Matrix.length; a++){
                System.out.printf("%9s", datastore.ClassDomain.getElement(a));
                for (int b = 0; b < Matrix.length; b++){
                    System.out.printf("%8d", Matrix[a][b]);
                }
                System.out.println();
            }
            System.out.println();
        }
        
	public void printThis(){
		printClass();
		System.out.println();
		printGeneral();
		printAccuracy();
                System.out.println();
                printConfusionMatrix();
	}

	public void mulai(instanceTable DataTraining, instanceTable DataTest){
		makeModel(DataTraining);
                //classify(DataTest);
                InisialisasiMatrix();
                confusionMatrix(DataTest);
                calculateAccuracy(DataTest);
                printThis();
	}
        
        public void toText(String fileName) throws FileNotFoundException{
            PrintWriter out = new PrintWriter(fileName);
            
            //print class
            out.println("Model Probabilitas per Kelas:");
		for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                        out.printf("%.3f", classProbabilityModel.get(dcls));
			out.print(" ");
		}
            out.println();
            
            //print general
            out.println("Model Probabilitas Keseluruhan:");
            out.printf("%6s", "");
            out.print(" ");
            for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(0).size(); datt++){
                for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                        out.printf("%5s", datastore.ClassDomain.getElement(dcls));
                        out.print(" ");
                }
                out.print("| ");
            }
            out.println();
            for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
                    out.printf("%6s", datastore.AttributeDomainTable.attributeName.get(att));
                    out.print(" ");
                    for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
                            for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
                                    out.printf("%.3f", generalProbabilityModel.get(att).get(datt).get(dcls));
                                    out.print(" ");
                            }
                            out.print("| ");
                    }
                    out.println();
                    out.println();
            }    
            
            //print accuracy
            out.println("Akurasi Model Probabilitas:");
            out.println(accuracy*100 + "%");
            
            //print confusion matrix
            out.printf("%9s", "");
            for (int a = 0; a < datastore.ClassDomain.size(); a++){
                out.printf("%8s", datastore.ClassDomain.getElement(a));
            }
            out.println();
            for (int a = 0; a < Matrix.length; a++){
                out.printf("%9s", datastore.ClassDomain.getElement(a));
                for (int b = 0; b < Matrix.length; b++){
                    out.printf("%8d", Matrix[a][b]);
                }
                out.println();
            }
            out.println();
            
            out.close();
        }
        
        public void filterData(String fileName) throws Exception{
            DataSource source = new DataSource(fileName);
            Instances dataset = source.getDataSet();
            dataset.setClassIndex(dataset.numAttributes()-1);
            Discretize discretize = new Discretize();
            discretize.setInputFormat(dataset);
            Instances newData=Filter.useFilter(dataset,discretize);
            ArffSaver arff_saver=new ArffSaver();
            arff_saver.setInstances(newData);
            arff_saver.setFile(new File("discretized.arff"));
            arff_saver.writeBatch();
            System.out.println("Attributes discretized successfully");
        }
}