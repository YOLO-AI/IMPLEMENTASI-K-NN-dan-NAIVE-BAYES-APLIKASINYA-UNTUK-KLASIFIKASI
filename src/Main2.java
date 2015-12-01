import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 *
 * @author yoga
 */
public class Main2 {

    public static void main(String[] args) throws Exception {
                //datastore Datastore = new datastore();	
        //Datastore.inputDatastore();
        datastore.inputDatastore("car_data.arff");
        Scanner scan = new Scanner(System.in);
        String[] inputatt= new String[6];
        inputatt[0] = scan.nextLine();
        inputatt[1] = scan.nextLine();
        inputatt[2] = scan.nextLine();
        inputatt[3] = scan.nextLine();
        inputatt[4] = scan.nextLine();
        inputatt[5] = scan.nextLine();
        instance data= new instance(inputatt);
        instanceTable datatest= new instanceTable();
        datatest.add(data);
       
        while (true) {
            System.out.println("Menu : ");
            System.out.println("1. Naive-Bayes Full Training");
            System.out.println("2. k-NN Full Training");
            System.out.println("3. Exit");
            System.out.print("Masukkan pilihan Anda : ");
            int inputFile = scan.nextInt();
            if (inputFile == 1) {
                naiveBayes NaiveBayes = new naiveBayes();
                NaiveBayes.makeModel(datastore.DataStore);
                NaiveBayes.classify(datatest);
                System.out.println(NaiveBayes.solution[0]);
                
            } else if (inputFile == 2) {
                System.out.print("Masukkan nilai k : ");
                scan = new Scanner(System.in);
                int k = scan.nextInt();
                kNN KNN = new kNN(datatest, datastore.DataStore, k);
                KNN.printClass();
            } else if (inputFile == 3) {
                break;
            }
        }

    }
}
