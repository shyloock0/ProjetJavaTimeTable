package timeTableModel;

public class Room {
	//création des variables
	protected int roomId;
	protected int capacity;
	
	//initialisation des variables
	public Room(int roomId, int capacity){
		this.roomId=roomId;
		this.capacity=capacity;
	}
	
	
	//récupérations des variables
	public int getroomId(){
		return roomId;
	}
	
	public int getcapacity(){
		return capacity;
	}
}

