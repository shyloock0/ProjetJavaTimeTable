package timeTableModel;
/**
 * 
 * @author sarah et jonathan
 *
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
	 * @return 
	 */
	
	//récupérations des variables
	public int getroomId(){
		return roomId;
	}
	/**
	 * 
	 * @return
	 */
	
	public int getcapacity(){
		return capacity;
	}
	
}

