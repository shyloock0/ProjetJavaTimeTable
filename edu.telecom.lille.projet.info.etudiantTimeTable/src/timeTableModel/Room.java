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
	public int capacity(){
		return capacity;
	}
	
	// suppression ou ajout dans la DB
	void addroom(){
		Text t1=doc.create.TextNode(roomId);
		Text t2=doc.create.TextNode(capacity);
				
	}

}
