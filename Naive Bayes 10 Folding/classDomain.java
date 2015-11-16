// file: classDomain.java
import java.util.*;
import java.io.*;
public class classDomain{
	// kelas ini merepresentasikan domain dari kelas-kelas klasifikasi

	public ArrayList<String> classDomainList;

	public classDomain(){
		classDomainList = new ArrayList<String>();
	}

	public classDomain(String[] values){
		classDomainList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		classDomainList.add(values[i]);
       	}
	}

	public void setClassDomainList(String[] values){
		classDomainList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		classDomainList.add(values[i]);
       	}
	}

	public void addElement(String value){
		classDomainList.add(value);
	}

	public int size(){
		return classDomainList.size();
	}

	public void add(String value){
		classDomainList.add(value);
	}

	public String getElement(int column){
		return classDomainList.get(column);
	}

	public void printThis(){
		for (int i = 0; i < size(); i++){
			System.out.print(getElement(i) + ",");
		}
	}
}
