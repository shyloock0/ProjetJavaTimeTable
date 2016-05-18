package timeTableModel;

public class TimeTable {
	//créations des variables 
	protected int timeTableId;
	protected int listBooking []= new int [50];
	
	//initialisations des variables
	public TimeTable(int timeTableId){
		this.timeTableId=timeTableId;
	}
	
	
	//recupérations des données
	public int[] getlistBooking(){
		return listBooking;
	}
	public int gettimeTableId(){
		return timeTableId;
	}
	
	//ajout ou suppression d'une réservation
	void addBook(Book book){
		for (int i = 0;i<49;i++){
			if (listBooking[i]!=0){
				listBooking[i]=book.BookId;
				System.out.println("La réservation "+book+" a été prise en compte");
				return;
			}	
		}
		System.out.println("L'emploi du temps est trop chargé. Vous ne pouvez plus faire de réservation!!");
		return;	
	}
	void removeBook(Book book){
		for (int i = 0;i<49;i++){
			if (listBooking[i]==book.BookId){
				listBooking[i]=0;
				System.out.println("La réservation "+book+" a été supprimée");
				return;
			}	
		}
		System.out.println("Cette réservation n'existe pas!! elle ne peut donc pas être supprimée");
		return;	
	}
	
	
	//ajout et suppression dans la base de données
	
	
	
	

}
