package timeTableModel;

import java.util.LinkedHashMap;
/**
 * 
 * @author sarah and jonathan
 *Cette classe crée l'entité emploi du temps. Un emploi du temps est composé de reservation, d'un numero
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
	 * @return une hashmap permettant de donner acces a toutes les reservations de l'emploi du temps
	 */
	
	
	//recupérations des données
	public LinkedHashMap<Integer,Book> getbooksMap(){
		return booksMap;
	}
	/**
	 * 
	 * @return le numero de l'emploi du temps
	 */

	public int gettimeTableId(){
		return timeTableId;
	}
	
}
