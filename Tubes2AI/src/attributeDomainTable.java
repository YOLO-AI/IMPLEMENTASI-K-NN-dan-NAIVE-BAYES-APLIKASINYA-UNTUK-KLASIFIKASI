// file: attributeDomainTable.java
import java.util.*;
import java.io.*;
public class attributeDomainTable{
	// kelas ini merepresentasikan struktur data sebuah table atribut domain

	public ArrayList<attributeDomain> attributeDomainTableList;
        public ArrayList<String> attributeName = new ArrayList<String>();

	public attributeDomainTable(){
		attributeDomainTableList = new ArrayList<attributeDomain>();
	}

	public attributeDomainTable(attributeDomain[] values){
		attributeDomainTableList = new ArrayList<attributeDomain>();
		for (int i = 0; i < values.length; i++){
    		attributeDomainTableList.add(values[i]);
            }
	}

	public void setAttributeDomainListTable(attributeDomain[] values){
		attributeDomainTableList = new ArrayList<attributeDomain>();
		for (int i = 0; i < values.length; i++){
    		attributeDomainTableList.add(values[i]);
       	}
	}

	public attributeDomain getRow(int row){
		return attributeDomainTableList.get(row);
	}

	public int size(){
		return attributeDomainTableList.size();
	}

	public void add(attributeDomain value){
		attributeDomainTableList.add(value);
	}

	public String getElement(int attribute, int column){
		return attributeDomainTableList.get(attribute).getElement(column);
	}

	public void printThis(){
		for (int i = 0; i < size(); i++){
                        System.out.print(attributeName.get(i)+ ": ");
			getRow(i).printThis();
			System.out.println();
		}
	}
        
        public void remove(int index){
            attributeDomainTableList.remove(index);
        }
}