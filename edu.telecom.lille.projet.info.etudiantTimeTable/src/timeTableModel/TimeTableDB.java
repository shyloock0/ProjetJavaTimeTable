package timeTableModel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * Cette classe gére la base de données d'emplois du temps. Elle doit permettre de sauvegarder et charger les emplois du temps ainsi que les salles à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier TimeTableDB.xml.
 * @see <a href="../../TimeTableDB.xml">TimeTableDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class TimeTableDB {
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	Element rootElt1=new Element("Rooms");
	Element rootElt2=new Element("TimeTables");
	private String file;
	/**
	 * 
	 * Constructeur de TimeTableDB. 
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public TimeTableDB(String file){
		//TODO	À modifier
		//super();// cette classe n'est pas hériter!????
		this.setFile(file);
	}
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public String getFile() {
		return file;
	}
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public void setFile(String file) {
		this.file = file;
	}
		
	
	public void saveDB(){
		try{
			XMLOutputter sortie =new XMLOutputter(Format.getPrettyFormat());
			sortie.output(sortie,setFile("file"));

		}
		catch (IOException e){}
	}
	
	
	public void loadDB(){
		org.jdom2.Document document = null;//création d'un document vide
		Element rootElt1;
		Element rootElt2;
		SAXBuilder sxb =new SAXBuilder();
		try{
			document = sxb.build(InputSource("timeTableDB"));
			
		}
		catch(Exception e){}	
	}
	

	public void addRoom(int roomId, int capacity){
			String room = Integer.toString (roomId);
			Room room= new Room(roomId,capacity);
			
			
			//					JDOM
			//creation d'une sous classe Room de Rooms
			Element RoomElt1 = new Element("Room");
			RoomElt1.addContent(rootElt1);
			//creation d'une sous classe RoomId de Room avec pour contenue roomId
			Element IdRoomElt1 = new Element("RoomId");
			IdRoomElt1.setText(Integer.toString(roomId));
			RoomElt1.addContent(IdRoomElt1);
			//creation d'une sous classe Capacity de Room avec pour contenue capacity
			Element  CapacityRoomElt1 = new Element("Capacity");
			CapacityRoomElt1.setText(Integer.toString(capacity));
			RoomElt1.addContent(CapacityRoomElt1);
			saveDB();
			
	}
	
	
	public void removeRoom(int roomId){
		loadDB();
		
		
		
	}

	
	public void addTimeTable(int timeTableId){
		String timeTable = Integer.toString(timeTableId);
		TimeTable timeTable= new TimeTable(timeTableId);
		///JDOM
		
		saveDB();
		
	}
	
	
	
}
