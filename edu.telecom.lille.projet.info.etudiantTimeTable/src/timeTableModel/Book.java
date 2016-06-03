package timeTableModel;

import java.util.Date;
/**
 * 
 * @author sarah et jonathan
 *
 *Création de l'instance 
 */
public class Book {
	//declaration des variables
	protected int bookId;
	protected String login;
	protected Date dateBegin;
	protected Date dateEnd;
	protected int roomId;
	
	//initialisation des variables
	/**
	 * 
	 * @param bookId
	 * @param login
	 * @param dateBegin
	 * @param dateEnd
	 * @param roomId
	 */
	public Book(int bookId, String login, Date dateBegin,Date dateEnd,int roomId){
		this.bookId=bookId;
		this.login=login;
		this.dateBegin=dateBegin;
		this.dateEnd=dateEnd;
		this.roomId=roomId;
	}
	//recuperation des elmts protégés
	/**
	 * 
	 * @return int
	 */
	public int getbookId(){
		return bookId;
	}
	/**
	 * dddddddddddddddddd
	 * @return String
	 */
	public String getlogin(){
		return login;
	}
	/**
	 * 
	 * @return Date
	 */
	public Date getDateBegin(){
		return dateBegin;
	}
	/**
	 * 
	 * @return Date
	 */
	public Date getDateEnd(){
		return dateEnd;
	}
	/**
	 * 
	 * @return int
	 */
	public int getroomId(){
		return roomId;
	}

}
