package timeTableModel;

import java.util.Date;

public class Book {
	
	//declaration des variables
	protected int bookId;
	protected String login;
	protected Date dateBegin;
	protected Date dateEnd;
	protected int roomId;
	
	//initialisation des variables
	public Book(int bookId, String login, Date dateBegin,Date dateEnd,int roomId){
		this.bookId=bookId;
		this.login=login;
		this.dateBegin=dateBegin;
		this.dateEnd=dateEnd;
		this.roomId=roomId;
	}
	//recuperation des elmts protégés
	public int getbookId(){
		return bookId;
	}
	public String getlogin(){
		return login;
	}
	public Date getDateBegin(){
		return dateBegin;
	}
	public Date getDateEnd(){
		return dateEnd;
	}
	public int getroomId(){
		return roomId;
	}

}
