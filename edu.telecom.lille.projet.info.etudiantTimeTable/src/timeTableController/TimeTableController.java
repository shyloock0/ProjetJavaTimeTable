package timeTableController;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

import timeTableModel.Book;
import timeTableModel.Room;
import timeTableModel.TimeTable;
import timeTableModel.TimeTableDB;
/**
 * Cette classe est le contrôleur d'emplois du temps que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données d'emplois du temps que vous allez créer.
 * Elle contient toutes les fonctions de l'interface ITimeTableController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * @param <TimeTable>
 * 
 */

//TODO Classe à modifier

public class TimeTableController<TimeTable> implements ITimeTableController{

	/**
	 * Contient une instance de base de données d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	/**
	 * Constructeur de controleur d'emplois du temps créant la base de données d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de données d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		this.tTDB=tTDB;
	}

	@Override
	public String getTeacherLogin(int timeTableId, int bookId) {
		return tTDB.gettimetablesMap().get(timeTableId).getbooksMap().get(bookId).getlogin();
	}

	@Override
	public String[] roomsIdToString(){
		int length;
		length=tTDB.getroomsMap().size();
		String[] string=new String[length];
		int j=0;
		for(Entry<Integer,Room> entry  :  tTDB.getroomsMap().entrySet()){
			Integer cle= entry.getKey();
			string[j]=Integer.toString(cle);
			j++;
			}
		return string;
	}

	@Override
	public String[] roomsToString() {
		int length;
		length=tTDB.getroomsMap().size();
		String[] string=new String[length];
		String[] interm=new String[2];
		for (int i=0;i<length;i++){
			interm[0]=roomsIdToString()[i];
			interm[1]=Integer.toString((tTDB.getroomsMap().get((Integer.parseInt(interm[0]))).getcapacity()));
			string[i]=interm[0]+" , "+interm[1];
		}
		
		return string;
	}

	@Override
	public String[] timeTablesIDToString() {
		int length;
		length=tTDB.gettimetablesMap().size();
		String[] string=new String[length];
		int j=0;
		for(Entry<Integer, timeTableModel.TimeTable> entry: tTDB.gettimetablesMap().entrySet() ){
			Integer cle= entry.getKey();
			string[j]=Integer.toString(cle);
			j++;
			}
		return string;
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		int length;
		length=tTDB.gettimetablesMap().get(timeTableId).getbooksMap().size();
		String[] string=new String[length];
		int j=0;
		for(Entry<Integer, Book> entry: tTDB.gettimetablesMap().get(timeTableId).getbooksMap().entrySet() ){
			Integer cle= entry.getKey();
			string[j]=Integer.toString(cle);
			j++;
			}
		return string;
	}

	@Override
	public boolean addRoom(int roomId, int capacity) {	
		return tTDB.addRoom(roomId,capacity);
	}

	@Override
	public boolean removeRoom(int roomId) {
		return tTDB.removeRoom(roomId);
	}

	@Override
	public int getRoom(int timeTableId, int bookId) {
		return tTDB.gettimetablesMap().get(timeTableId).getbooksMap().get(bookId).getroomId();
	}

	@Override
	public boolean addTimeTable(int timeTableId) {
		return tTDB.addTimeTable(timeTableId);
	}

	@Override
	public boolean removeTimeTable(int timeTableId) {
		return false;
	}

	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		return tTDB.addBook(timeTableId, bookingId, login, dateBegin, dateEnd, roomId);
	}

	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		for(Entry<Integer,Book> entry  :  tTDB.gettimetablesMap().get(timeTableId).getbooksMap().entrySet())           {
			Integer           cle           =           entry.getKey();
			Book           valeur           =           entry.getValue();
			dateBegin.put(cle,valeur.getDateBegin());
			dateEnd.put(cle, valeur.getDateEnd());
		}
		
		
		/*for (int i=0;i<length; i++){
			int j;
			j=(int) (tTDB.gettimetablesMap().keySet().toArray())[i];
			dateBegin.put(j,tTDB.gettimetablesMap().get(timeTableId).getbooksMap().get(j).getDateBegin());
			dateEnd.put(j, tTDB.gettimetablesMap().get(timeTableId).getbooksMap().get(j).getDateEnd());
		}*/
		
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		return tTDB.removeBook(timeTableId, bookId);
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
		int j=0;
		for(Entry<Integer, Book> entry: tTDB.gettimetablesMap().get(timeTableId).getbooksMap().entrySet() ){
			Integer cle= entry.getKey();
			j=cle;
			}
		return j;
	}

	@Override
	public boolean saveDB() {
		tTDB.saveDB();
		return true;
	}

	@Override
	public boolean loadDB() {
		tTDB.loadDB();
		return true;
	}
	
	

}
