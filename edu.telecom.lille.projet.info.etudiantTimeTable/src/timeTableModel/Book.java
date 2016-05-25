package timeTableModel;

import java.util.Date;

public class Book {
	
	//declaration des variables
	protected int BookId;
	protected String login;
	protected Date dateBegin;
	protected Date dateEnd;
	protected int roomId;
	
	//initialisation des variables
	public Book(int BookId, String login, Date dateBegin,Date dateEnd,int room){
		this.BookId=BookId;
		this.login=login;
		this.dateBegin=dateBegin;
		this.dateEnd=dateEnd;
		this.roomId=room;
	}
	//recuperation des elmts protégés
	public int getbookId(){
		return BookId;
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
	public int room(){
		return roomId;
	}

}
