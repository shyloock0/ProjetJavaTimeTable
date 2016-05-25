package timeTableModel;

public class TimeTable {
	//créations des variables 
	protected int timeTableId;
	protected int listBooking []= new int [500];
	
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
	void addBooking(int bookId){
		for (int i = 0;i<499;i++){
			if (listBooking[i]!=0){
				listBooking[i]=bookId;
				System.out.println("La réservation "+bookId+" a été prise en compte");
				return;
			}	
		}
		System.out.println("L'emploi du temps est trop chargé. Vous ne pouvez plus faire de réservation!!");
		return;	
	}
	void removeBooking(int bookId){
		for (int i = 0;i<499;i++){
			if (listBooking[i]==bookId){
				listBooking[i]=0;
				System.out.println("La réservation "+bookId+" a été supprimée");
				return;
			}	
		}
		System.out.println("Cette réservation n'existe pas!! elle ne peut donc pas être supprimée");
		return;	
	}
	
	
	
	
	
	

}
