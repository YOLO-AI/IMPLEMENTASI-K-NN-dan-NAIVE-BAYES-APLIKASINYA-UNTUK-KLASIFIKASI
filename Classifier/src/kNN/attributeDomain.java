// file: attributeDomain.java
import java.util.*;
import java.io.*;
public class attributeDomain{
	// kelas ini merepresentasikan domain dari atribut-atribut

	public ArrayList<String> attributeDomainList;

	public attributeDomain(){
		attributeDomainList = new ArrayList<String>();
	}

	public attributeDomain(String[] values){
		attributeDomainList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		attributeDomainList.add(values[i]);
       	}
	}

	public void setAttributeDomainList(String[] values){
		attributeDomainList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		attributeDomainList.add(values[i]);
       	}
	}

	public int size(){
		return attributeDomainList.size();
	}

	public void add(String value){
		attributeDomainList.add(value);
	}

	public String getElement(int column){
		return attributeDomainList.get(column);
	}

	public void printThis(){
		for (int i = 0; i < size(); i++){
			System.out.print(getElement(i) + " ");
		}
	}
}
