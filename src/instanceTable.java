// file: instanceTable.java
import java.util.*;
import java.io.*;
public class instanceTable{
	// kelas ini merepresentasikan struktur data sebuah table data

	public ArrayList<instance> instanceTableList;

	public instanceTable(){
		instanceTableList = new ArrayList<instance>();
	}
        
        public instanceTable(instanceTable sumber) {
                instanceTableList = new ArrayList<instance>();
                for (int i = 0; i < sumber.size(); i++) {
                        instanceTableList.add(sumber.getRow(i));
                }
        }

	public instanceTable(instance[] values){
		instanceTableList = new ArrayList<instance>();
		for (int i = 0; i < values.length; i++){
    		instanceTableList.add(values[i]);
       	}
	}

	public void setInstanceListTable(instance[] values){
		instanceTableList = new ArrayList<instance>();
		for (int i = 0; i < values.length; i++){
    		instanceTableList.add(values[i]);
       	}
	}

	public void set(int index, instance value){
		instanceTableList.set(index, value);
	}

	public instance getRow(int row){
		return instanceTableList.get(row);
	}

	public int size(){
		return instanceTableList.size();
	}

	public void add(instance value){
		instanceTableList.add(value);
	}

	public String getElement(int row, int attribute){
		return instanceTableList.get(row).getElement(attribute);
	}

	public void printThis(){
		for (int i = 0; i < size(); i++){
			getRow(i).printThis();
			System.out.println();
		}
	}
}