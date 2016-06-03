package timeTableModel;
/**
 * 
 * @author sarah et jonathan
 *Cette classe crée l'entité salle. Elle est composé de un numéro( d'identité ) et d'une capacité
 */

public class Room {
	//création des variables
	protected int roomId;
	protected int capacity;
	/**
	 * 
	 * @param roomId
	 * @param capacity
	 */
	
	//initialisation des variables

	public Room(int roomId, int capacity){
		this.roomId=roomId;
		this.capacity=capacity;
	}
	/**
	 * 
	 * @return le numéro de la chambre
	 */
	
	//récupérations des variables
	public int getroomId(){
		return roomId;
	}
	/**
	 * 
	 * @return le nombre maximun de personnes que la chambre peut contenir
	 */
	
	public int getcapacity(){
		return capacity;
	}
	
}

