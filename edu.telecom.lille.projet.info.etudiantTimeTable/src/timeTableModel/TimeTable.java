package timeTableModel;

import java.util.LinkedHashMap;

public class TimeTable {
	//créations des variables 
	protected int timeTableId;
	protected LinkedHashMap<Integer,Book> booksMap;
	
	
	//initialisations des variables
	public TimeTable(int timeTableId){
		this.timeTableId=timeTableId;
		this.booksMap=new LinkedHashMap<Integer,Book>();
	}
	
	
	//recupérations des données
	public LinkedHashMap<Integer,Book> getbooksMap(){
		return booksMap;
	}

	public int gettimeTableId(){
		return timeTableId;
	}
}
