// file: probabilityNB.java
import java.util.*;
import java.io.*;
public class probabilityNB{
	public ArrayList<Float> classProbabilityNBList;
	public ArrayList<ArrayList<ArrayList<Float>>> generalProbabilityNBList; // (row,att,class)

	public probabilityNB(){
		classProbabilityNBList = new ArrayList<Float>();
		generalProbabilityNBList = new ArrayList<ArrayList<ArrayList<Float>>>();
	}

	public void addGeneral(ArrayList<ArrayList<Float>> value){
		generalProbabilityNBList.add(value);
	}

	public void addClass(Float value){
		classProbabilityNBList.add(value);
	}

	public void printGeneral(){
		for (int att = 0; att < datastore.AttributeDomainTable.size(); att++){
			for (int datt = 0; datt < datastore.AttributeDomainTable.getRow(att).size(); datt++){
				for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
					System.out.print(generalProbabilityNBList.get(att).get(datt).get(dcls) + " ");
				}
				System.out.print(" | ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public void printClass(){
		for (int dcls = 0; dcls < datastore.ClassDomain.size(); dcls++){
			System.out.print(classProbabilityNBList.get(dcls) + " ");
		}
		System.out.println();
	}
}