package timeTableController;

import java.awt.print.Book;
import java.util.Date;
import java.util.Hashtable;

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
		// Pourquoi on a timetableid???
		return tTDB.getbooksMap().get(bookId).getlogin();
	}

	@Override
	public String[] roomsIdToString(){
		String[] roomIdString;
		int length;
		
		length=tTDB.getroomsMap().size();
		roomIdString= new String[length];
		
		/*for (int i=0;i<length;i++){
			roomIdString[i]=Integer.toString(tTDB.getroomsMap().)
		}
		*/
		
		return null;
	}

	@Override
	public String[] roomsToString() {
		// TODO Auto-generated method stub tout les informations()
		return null;
	}

	@Override
	public String[] timeTablesIDToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRoom(int roomId, int capacity) {
		tTDB.addRoom(roomId, capacity);// manque le cas ou c'est pas possible
		return true;
	}

	@Override
	public boolean removeRoom(int roomId) {
		tTDB.removeRoom(roomId);//manque le cas ou c'est pas possible
		return false;
	}

	@Override
	public int getRoom(int timeTableId, int bookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addTimeTable(int timeTableId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTimeTable(int timeTableId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		tTDB.addBook(timeTableId, bookingId, login, dateBegin, dateEnd, roomId);
		return false;
	}

	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		tTDB.removeBook(timeTableId, bookId);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
		// TODO Auto-generated method stub
		return 0;
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
