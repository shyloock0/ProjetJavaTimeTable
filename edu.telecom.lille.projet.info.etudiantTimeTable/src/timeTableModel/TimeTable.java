package timeTableModel;

import java.util.LinkedHashMap;
/**
 * 
 * @author sarah and jonathan
 *
 */

public class TimeTable {
	//créations des variables 
	protected int timeTableId;
	protected LinkedHashMap<Integer,Book> booksMap;
	/**
	 * 
	 * @param timeTableId
	 */
	
	
	//initialisations des variables
	public TimeTable(int timeTableId){
		this.timeTableId=timeTableId;
		this.booksMap=new LinkedHashMap<Integer,Book>();
	}
	/**
	 * 
	 * @return
	 */
	
	
	//recupérations des données
	public LinkedHashMap<Integer,Book> getbooksMap(){
		return booksMap;
	}
	/**
	 * 
	 * @return
	 */

	public int gettimeTableId(){
		return timeTableId;
	}
	
}
