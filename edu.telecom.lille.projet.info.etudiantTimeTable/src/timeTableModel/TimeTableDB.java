package timeTableModel;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

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
	//création du fichier doc (Document)
	Element rootElt=new Element("TimeTablesDB");
	org.jdom2.Document doc = new Document(rootElt);
	
	//creation des map: association entre id et nom des instances
	HashMap<Integer,Room> roomsMap; 
	HashMap<Integer,TimeTable> timetablesMap;
	HashMap<Integer, Book> booksMap;
	
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
		this.roomsMap=new HashMap<Integer,Room>();
		this.timetablesMap=new HashMap<Integer,TimeTable>();
		this.booksMap=new HashMap<Integer,Book>();
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
		
	//sauvergarde
	public void saveDB(){
		try{
			XMLOutputter sortie =new XMLOutputter(Format.getPrettyFormat());
			sortie.output(doc,new FileOutputStream(file));
		}catch (IOException e){}
	}
	
	public void showDB(){
		try{
			XMLOutputter sortie =new XMLOutputter(Format.getPrettyFormat());
			sortie.output(doc,System.out);
		}catch (IOException e){}
	}
	
	
	public void loadDB(){
		try{
			SAXBuilder sxb = new SAXBuilder();
			doc = sxb.build(new File(file));
			rootElt= doc.getRootElement();
			}catch (IOException | JDOMException e){}//bizarre
		}
	
	public void removeRoom(int roomId){
		//destruction de l'objet java dans la map
		roomsMap.remove(roomId);
		
		//charge la base de donnee
		loadDB();
		
		Element roomsElt= rootElt.getChild("Rooms");
	    List<Element> ListroomElt = roomsElt.getChildren("Room");
	    Iterator<Element> i = ListroomElt.iterator();
	    //On parcours la liste grâce à un iterator
	    
	    while(i.hasNext()){
	    	Element courant = (Element)i.next();
	        //Si l’étudiant possède l'Element en question on applique
	        //les modifications.
	    	
	    	if(courant.getChild("RoomId").getText().equals(Integer.toString(roomId))){
	          //On supprime l'Element en question en passant par sont parrent
	          courant.detach();
	    	}
	    }
	    
	    //enregistre les modif dans la base de donnees
	    saveDB();
	}
	

	public void addRoom(int roomId, int capacity){
		
		loadDB();
		//creation de l'objet java
		Room abroom=new Room(roomId,capacity);
		roomsMap.put(roomId,abroom);
		
		//melange de deux techniques
		Element RoomsElt= rootElt.getChild("Rooms");
		System.out.println(RoomsElt);
		
		
		//					JDOM
		//creation d'une sous classe Room de Rooms
		Element RoomElt = new Element("Room");
		RoomsElt.addContent(RoomElt);
		//creation d'une sous classe RoomId de Room avec pour contenue roomId
		Element IdRoomElt = new Element("RoomId");
		IdRoomElt.setText(Integer.toString(roomId));
		RoomElt.addContent(IdRoomElt);
		//creation d'une sous classe Capacity de Room avec pour contenue capacity
		Element  CapacityRoomElt = new Element("Capacity");
		CapacityRoomElt.setText(Integer.toString(capacity));
		RoomElt.addContent(CapacityRoomElt);
		
		saveDB();
		
			
	}
	

	
	public void addTimeTable(int timeTableId){
		loadDB();
		
		//creation de l'instance
		TimeTable atimetable;
		atimetable=new TimeTable(timeTableId);
		timetablesMap.put(timeTableId, atimetable);
		
		
		//melange de deux techniques
		Element TimeTablesElt= rootElt.getChild("TimeTables");
			
		Element TimeTableElt = new Element("TimeTable");
		TimeTablesElt.addContent(TimeTableElt);
		
		//creation de la sous classe GroupID
		Element GroupIdElt = new Element("GroupId");
		GroupIdElt.setText(Integer.toString(timeTableId));
		TimeTableElt.addContent(GroupIdElt);
		
		//creation de la sous classe Books
		Element BooksElt = new Element("Books");
		TimeTableElt.addContent(BooksElt);

		saveDB();	
	}
	
	
	
	public void removeTimeTable(int timeTableId){
		//meme principe que pour removeRoom()
		timetablesMap.remove(timeTableId);
		
		//charge la base de donnee
		loadDB();
				
		Element TimeTablesElt= rootElt.getChild("TimeTables");
		List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
		Iterator<Element> i = ListTimeTableElt.iterator();
		//On parcours la liste grâce à un iterator
		while(i.hasNext()){
			Element courant = (Element)i.next();
			//Si l’étudiant possède l'Element en question on applique les modifications.
			if(courant.getChild("GroupId").getText().equals(Integer.toString(timeTableId))){
				//On supprime l'Element en question
				courant.detach();
				}
			}
			    
		//enregistre les modif dans la base de donnees
		saveDB();
		}
	
	public void removeBook(int timeTableId, int bookId){
		//meme principe que pour removeRoom()
		
				//charge la base de donnee
				loadDB();
				
				//on enleve de la map
				booksMap.remove(bookId);
						
				Element TimeTablesElt= rootElt.getChild("TimeTables");
				List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
				Iterator<Element> i = ListTimeTableElt.iterator();
				//On parcours la liste grâce à un iterator
				while(i.hasNext()){
					Element courant = (Element)i.next();
					//Si on est dans le bonne emploi du temps
					if(courant.getChild("GroupId").getText().equals(Integer.toString(timeTableId))){
						//on reitere le processus
						
						//on creer la liste des Elt ded reservation de courant
						List<Element> ListBookElt = courant.getChildren("Book");
						Iterator<Element> k = ListBookElt.iterator();
						
						while(k.hasNext()){
							Element courantbis = (Element)k.next();
							
							if(courantbis.getChild("BookingId").getText().equals(Integer.toString(bookId))){
								courantbis.detach();
							}
						}
					}
					    
				//enregistre les modif dans la base de donnees
				saveDB();
				}
		
	}

	public void addBook(int timeTableId, int bookId, String login, Date dateBegin, Date dateEnd, int roomId){
		loadDB();
		
		//création de l'objet en java
		Book abook=new Book(bookId,login, dateBegin,dateEnd,roomId);
		booksMap.put(bookId,abook);
		
		// on ajout la reservation dans l'emploi du temps associé en java
		//timetablesMap.get(timeTableId).addBook(abook);
	
		
		//melange de deux techniques
		Element TimeTablesElt= rootElt.getChild("TimeTables");
		
		//on recupere le bonne emploi du temps
		List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
		Iterator<Element> i = ListTimeTableElt.iterator();

		while(i.hasNext()){
			Element courant = (Element)i.next();
			if(courant.getChild("GroupId").getText().equals(Integer.toString(timeTableId))){
				
				//creation d'une classe book
				Element BookElt = new Element("Book");
				courant.addContent(BookElt);
				//ajout de BookId
				Element BookIdElt = new Element("BookingId");
				BookIdElt.setText(Integer.toString(bookId));
				BookElt.addContent(BookIdElt);
				//ajout de login
				Element LoginElt = new Element("Login");
				LoginElt.setText(login);
				BookElt.addContent(LoginElt);
				//ajout de DateBegin
				Element DateBeginElt = new Element("DateBegin");
				DateBeginElt.setText("11 000 1110");
				BookElt.addContent(DateBeginElt);
				//ajout de DateEnd
				Element DateEndElt = new Element("DateEnd");
				DateEndElt.setText("11 000 11110");
				BookElt.addContent(DateEndElt);
				//ajout de roomId
				Element RoomIdElt = new Element("RoomId");
				RoomIdElt.setText(Integer.toString(roomId));
				BookElt.addContent(RoomIdElt);	
			}

		saveDB();
		}
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
