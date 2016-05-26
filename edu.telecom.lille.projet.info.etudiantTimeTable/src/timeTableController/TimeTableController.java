package timeTableController;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

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
		return tTDB.gethashmapbooksMap().get(timeTableId).get(bookId).getlogin();
	}

	@Override
	public String[] roomsIdToString(){
		return null;
	}

	@Override
	public String[] roomsToString() {
		//nombre de room 
		int length;
		length=tTDB.getroomsMap().size();
		
		//les deux listes
		String[] r=new String[length];
		for(int i=0;i<length;i++){
			r[i]=String.valueOf((tTDB.getroomsMap().keySet().toArray())[i]);
		}
		return r;
	}

	@Override
	public String[] timeTablesIDToString() {
		int length;
		length=tTDB.gettimetablesMap().size();
		
		String[] ttos = new String[length];
		
		for(int i=0;i<length;i++){
			ttos[i]=String.valueOf((tTDB.gettimetablesMap().keySet().toArray())[i]);
		}
		return ttos;
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		int length;
		length=tTDB.gethashmapbooksMap().get(timeTableId).size();
		
		String[] btos = new String[length];
		
		for(int i=0;i<length;i++){
			btos[i]=String.valueOf((tTDB.gethashmapbooksMap().get(timeTableId).keySet().toArray())[i]);
		}
		return btos;
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
		return tTDB.gethashmapbooksMap().get(timeTableId).get(bookId).getroomId();
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
		int length;
		
		length =tTDB.gethashmapbooksMap().get(timeTableId).size();
		for (int i=0;i<length; i++){
			int j;
			j=(int) tTDB.gettimetablesMap().keySet().toArray()[i];
			dateBegin.put(j,tTDB.gethashmapbooksMap().get(timeTableId).get(j).getDateBegin());
			dateEnd.put(j, tTDB.gethashmapbooksMap().get(timeTableId).get(j).getDateEnd());
		}
		
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		return tTDB.removeBook(timeTableId, bookId);
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
		int length;
		length=tTDB.gethashmapbooksMap().get(timeTableId).size();
		return (int) tTDB.gettimetablesMap().keySet().toArray()[length-1];
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
