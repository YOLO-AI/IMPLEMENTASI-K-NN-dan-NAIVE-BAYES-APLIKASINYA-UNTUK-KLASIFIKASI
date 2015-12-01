
import java.util.Scanner;

// file: Main.java
public class Main{
	public static void main(String[] args) throws Exception{
                //datastore Datastore = new datastore();	
                //Datastore.inputDatastore();
                datastore.inputDatastore("glass.arff");
                while (true){
                    System.out.println("Menu : ");
                    System.out.println("1. Naive-Bayes Full Training");
                    System.out.println("2. Naive-Bayes 10-Fold Cross Validation");
                    System.out.println("3. k-NN Full Training");
                    System.out.println("4. k-NN 10-Fold Cross Validation");
                    System.out.println("5. Exit");
                    System.out.print("Masukkan pilihan Anda : ");
                    Scanner scan = new Scanner(System.in);
                    int inputFile = scan.nextInt();
                    if (inputFile == 1){
                        naiveBayes NaiveBayes = new naiveBayes();
                        NaiveBayes.makeModel(datastore.DataStore);
                        NaiveBayes.classify(datastore.DataStore);
                        NaiveBayes.calculateAccuracy(datastore.DataStore);
                        System.out.println("*** Full Training Naive Bayes ***");
			NaiveBayes.printThis();
                    } else if (inputFile == 2){
                        naiveBayes NaiveBayes = new naiveBayes();
                        tenFoldCrossValidationNB TenFoldCrossValidationNB = new tenFoldCrossValidationNB();
                        tenFoldCrossValidationNB.mulaiNB();
                    } else if (inputFile == 3){
                        System.out.print("Masukkan nilai k : ");
                        scan = new Scanner(System.in);
                        int k = scan.nextInt();
                        kNN KNN = new kNN(datastore.DataStore, datastore.DataStore,k);
                        KNN.printAccuracy();
                    } else if (inputFile == 4){
                        System.out.print("Masukkan nilai k : ");
                        scan = new Scanner(System.in);
                        int k = scan.nextInt();
                        kNN KNN = new kNN(datastore.DataStore, datastore.DataStore,k);
                        tenFoldCrossValidationNB TenFoldCrossValidationNB = new tenFoldCrossValidationNB();
                        tenFoldCrossValidationNB.mulaikNN(5);
                    } else if (inputFile == 5){
                        break;
                    }
                }
                
	}
}