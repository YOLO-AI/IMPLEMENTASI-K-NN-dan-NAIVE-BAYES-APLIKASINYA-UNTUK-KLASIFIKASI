// file tenFoldCrossValidationNB.java
import java.util.*;
import java.io.*;
public class tenFoldCrossValidationNB{
	public static naiveBayes[] TenNaiveBayes;
        public static kNN[] TenkNN;
	public static instanceTable[] TenDataTraining;
	public static instanceTable[] TenDataTest;
        
	public tenFoldCrossValidationNB(){
		TenNaiveBayes = new naiveBayes[10];
                TenkNN = new kNN[10];
		TenDataTraining = new instanceTable[10];
		TenDataTest = new instanceTable[10];
		for(int i = 0; i < 10; i++){
			TenNaiveBayes[i] = new naiveBayes();
			TenDataTraining[i] = new instanceTable();
			TenDataTest[i] = new instanceTable();
		}
	}
	public static void shuffle(instanceTable tempInstanceTable){
		Random randomGenerator = new Random();
		for(int index = tempInstanceTable.size()-1; index >= 0; index--){
			int shuffleIndex = randomGenerator.nextInt(index+1);
			instance tempInstance = new instance();
			tempInstance = tempInstanceTable.getRow(index);
			tempInstanceTable.set(index, tempInstanceTable.getRow(shuffleIndex));
			tempInstanceTable.set(shuffleIndex, tempInstance);
		}
	}
	public static void divideTen(instanceTable tempInstanceTable){
		int partition = tempInstanceTable.size() / 10; //lebih kecil kayanya
		for(int i = 0; i < 10; i++){
			TenDataTest[i] = new instanceTable();
			TenDataTraining[i] = new instanceTable();
		}
		for(int i = 0; i < 9; i++){
			for (int j = 0; j < partition; j++){				
				TenDataTest[i].add(tempInstanceTable.getRow((j + i*partition)));
			}
			for (int j = 0; j < tempInstanceTable.size() - partition; j++){
				TenDataTraining[i].add(tempInstanceTable.getRow((j + i*partition)%tempInstanceTable.size()));
			}		}
		for (int j = 0; j < partition; j++){
			TenDataTraining[9].add(tempInstanceTable.getRow(j + 9*partition));
		}
		for (int j = 0; j < tempInstanceTable.size() - partition; j++){				
			TenDataTest[9].add(tempInstanceTable.getRow((j + 9*partition)%tempInstanceTable.size()));
		}
	}

	public static void doNaiveBayes(){
		for(int i = 0; i < 10; i++){
			TenNaiveBayes[i] = new naiveBayes();
			TenNaiveBayes[i].makeModel(TenDataTraining[i]);
                        TenNaiveBayes[i].classify(TenDataTest[i]);
			TenNaiveBayes[i].calculateAccuracy(TenDataTest[i]);
                        TenNaiveBayes[i].confusionMatrix(TenDataTest[i]);
		}
	}
        
        public static void dokNN(int k){
                for(int i = 0; i < 10; i++){
			TenkNN[i] = new kNN(TenDataTest[i],TenDataTraining[i],k);
                        TenkNN[i].printAccuracy();
                        TenkNN[i].printConfusionMatrix();
		}
        }

	public static void printNB(){
		for(int i = 0; i < 10; i++){
			System.out.println("*** Ten Fold Cross Validation Naive Bayes ke-" + (i+1) + " ***");
			TenNaiveBayes[i].printThis();
			System.out.println();
		}
		System.out.println();
	}
        
        public static void printkNN(){
		for(int i = 0; i < 10; i++){
			System.out.println("*** Ten Fold Cross Validation k-Nearest Neighbor ke-" + (i+1) + " ***");
			TenkNN[i].printklasifikasi();
                        TenkNN[i].printAccuracy();
			System.out.println();
		}
		System.out.println();
	}
        
        

	public static void mulaiNB(){
		instanceTable tempInstanceTable = new instanceTable();
		for (int row = 0; row < datastore.DataStore.size(); row++){
			instance tempInstance = new instance();
			for (int att = 0; att < datastore.DataStore.getRow(row).size(); att++){
				tempInstance.add(datastore.DataStore.getElement(row, att));
			}
			tempInstanceTable.add(tempInstance);
		}
		//shuffle
		shuffle(tempInstanceTable);

		//bagi sepuluh
		divideTen(tempInstanceTable);
		
		//doNB
		doNaiveBayes();

		//cetak ke layar semua hasil NB
		printNB();
	}
        
        public static void mulaikNN(int k){
		instanceTable tempInstanceTable = new instanceTable();
		for (int row = 0; row < datastore.DataStore.size(); row++){
			instance tempInstance = new instance();
			for (int att = 0; att < datastore.DataStore.getRow(row).size(); att++){
				tempInstance.add(datastore.DataStore.getElement(row, att));
			}
			tempInstanceTable.add(tempInstance);
		}
		//shuffle
		shuffle(tempInstanceTable);

		//bagi sepuluh
		divideTen(tempInstanceTable);
		
		//dokNN
		dokNN(k);

	}
}