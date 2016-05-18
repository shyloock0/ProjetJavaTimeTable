package timeTableModel;

import java.util.Date;

public class Book {
	
	//declaration des variables
	protected int BookId;
	protected int login;
	protected Date dateBegin;
	protected Date dateEnd;
	protected Room room;
	
	//initialisation des variables
	public Book(int BookId, int login, Date dateBegin,Date dateEnd, Room room){
		this.BookId=BookId;
		this.login=login;
		this.dateBegin=dateBegin;
		this.dateEnd=dateEnd;
		this.room=room;
		
	}
	//recuperation des elmts protégés
	public int getbookId(){
		return BookId;
	}
	public int getlogin(){
		return login;
	}
	public Date getDateBegin(){
		return dateBegin;
	}
	public Date getDateEnd(){
		return dateEnd;
	}
	public Room room(){
		return room;
	}

}
