package timeTableModel;

import java.util.Date;
/**
 * 
 * @author sarah et jonathan
 *
 * On créer l'objet reservation qui contient 4 elements: son numero et sa capacité et ses dates de débuts et de fins
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
	 * @return le numero de reservation
	 */
	public int getbookId(){
		return bookId;
	}
	/**
	 * 
	 * @return l'identifiant d'un user
	 */
	public String getlogin(){
		return login;
	}
	/**
	 * 
	 * @return la login du professeur
	 */
	public Date getDateBegin(){
		return dateBegin;
	}
	/**
	 * 
	 * @return la date de début
	 */
	public Date getDateEnd(){
		return dateEnd;
	}
	/**
	 * 
	 * @return la date de fin
	 */
	public int getroomId(){
		return roomId;
	}

}
