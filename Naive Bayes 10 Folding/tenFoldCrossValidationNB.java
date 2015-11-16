// file tenFoldCrossValidationNB.java
import java.util.*;
import java.io.*;
public class tenFoldCrossValidationNB{
	public static probabilityNB[] TenProbabilityNB = new probabilityNB[10];
	public static instanceTable[] TenInstanceTable = new instanceTable[10];
	public tenFoldCrossValidationNB(){
		TenProbabilityNB = new probabilityNB[10];
	}
	public static void mulai(){
		instanceTable tempInstanceTable = new instanceTable();
		for (int row = 0; row < datastore.DataStore.size(); row++){
			instance tempInstance = new instance();
			for (int att = 0; att < datastore.DataStore.getRow(row).size(); att++){
				tempInstance.add(datastore.DataStore.getElement(row, att));
			}
			tempInstanceTable.add(tempInstance);
		}

		//bagi sepuluh
		int partition = tempInstanceTable.size() / 10; //lebih kecil kayanya
		Random randomGenerator = new Random();
		for(int i = 0; i < 9; i++){
			for (int j = 0; j < partition; j++){
				TenInstanceTable[i] = new instanceTable();
				int index = randomGenerator.nextInt(tempInstanceTable.size());
				System.out.println("index" + index);
				System.out.println("size" + tempInstanceTable.size());
				instance foo = new instance();
				TenInstanceTable[i].add(tempInstanceTable.getRow(index));
				tempInstanceTable.instanceTableList.remove(index);
			}
		}
		TenInstanceTable[9] = tempInstanceTable;

		//Naive Bayes 10 kali:) kerjain abis latihan yha:) semangat:) bismillah:) rapiin kodingan juga yah :3
	}
}