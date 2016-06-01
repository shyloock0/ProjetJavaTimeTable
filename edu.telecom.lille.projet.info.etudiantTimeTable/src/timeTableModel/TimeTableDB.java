package timeTableModel;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	Element RoomsE=new Element("Rooms");
	Element TimeTablesE=new Element("TimeTables");
	
	
	
	//creation des map: association entre id et nom des instances
	protected HashMap<Integer,Room> roomsMap; 
	protected HashMap<Integer,TimeTable> timetablesMap;
	
	
	private String file;
	/**
	 * 
	 * Constructeur de TimeTableDB. 
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public TimeTableDB(String file){
		//super();
		this.setFile(file);
		this.roomsMap=new HashMap<Integer,Room>();
		this.timetablesMap=new HashMap<Integer,TimeTable>();
		rootElt.addContent(RoomsE);
		rootElt.addContent(TimeTablesE);
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
	public HashMap<Integer,Room> getroomsMap(){
		return roomsMap;
	}
	public HashMap<Integer,TimeTable> gettimetablesMap(){
		return timetablesMap;
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
		int capacity;
		int roomid;
		int timetableid;
		String login;
		int bookid;
		Date datebegin=new Date();
		Date dateend=new Date();
		try{
			SAXBuilder sxb = new SAXBuilder();
			doc = sxb.build(new File(file));
			rootElt= doc.getRootElement();
			}catch (IOException | JDOMException e){}//bizarre
		List<Element> ListroomElt = RoomsE.getChildren("Room");
	    Iterator<Element> i = ListroomElt.iterator();
	    while(i.hasNext()){
	    	Element courant = (Element)i.next();
	    	roomid=Integer.parseInt(courant.getAttributeValue("RoomId"));
	    	capacity=Integer.parseInt(courant.getAttributeValue("Capacity"));
	    	if (roomsMap.containsKey(roomid)==false){
	    		Room abroom=new Room(roomid,capacity);
	    		roomsMap.put(roomid,abroom);
	    		} 
	    }
	    List<Element> ListtimetableElt = TimeTablesE.getChildren("TimeTable");
	    Iterator<Element> k = ListtimetableElt.iterator();
	    while(k.hasNext()){
	    	Element courantbis = (Element)k.next();
	    	timetableid=Integer.parseInt(courantbis.getAttributeValue("GroupId"));
	    	List<Element> ListbookElt = courantbis.getChildren("Book");
		    
		    if (timetablesMap.containsKey(timetableid)==false){
	    		TimeTable atimetable=new TimeTable(timetableid);
	    		timetablesMap.put(timetableid,atimetable);
	    		} 
		    Iterator<Element> l = ListbookElt.iterator();
		    while(l.hasNext()){
		    	Element courantbisbis=(Element)l.next();
		    	login=courantbisbis.getAttributeValue("Login");
		    	bookid=Integer.parseInt(courantbisbis.getAttributeValue("BookingId"));
		    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    	try {
		    		datebegin=formatter.parse(courantbisbis.getAttributeValue("DateBegin"));
		    		dateend=formatter.parse(courantbisbis.getAttributeValue("DateEnd"));
		    	} catch (ParseException e) {
		    		e.printStackTrace();
		    	roomid=Integer.parseInt(courantbisbis.getAttributeValue("Roomid"));
		    	if (timetablesMap.get(timetableid).getbooksMap().containsKey(bookid)==false){
		    		Book abook=new Book(bookid,login,datebegin,dateend,roomid);
		    		timetablesMap.get(timetableid).getbooksMap().put(bookid,abook);
		    		}
		    	}
		    	}
		    }
	    }
	
	public boolean removeRoom(int roomId){
		if (roomsMap.containsKey(roomId)==true){
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
		    return true;
		}
		return false;
	}
	

	public boolean addRoom(int roomId, int capacity){
		if(roomsMap.containsKey(roomId)){
			return false;
		}
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
		return true;		
	}
	
	public boolean addTimeTable(int timeTableId){
		if (timetablesMap.containsKey(timeTableId)){
			return false;
		}
		
		loadDB();
		//on va ajout creer et ajouter le bookhasmap
		//protected HashMap<Integer,TimeTable> timetablesMap;
		//timetablesMap.get(timeTableId).getbooksMap().put(timeTableId,timetablesMap.get(timeTableId).getbooksMap().get(key));
		
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
		return true;
	}
	
	
	
	public boolean removeTimeTable(int timeTableId){
		if (timetablesMap.containsKey(timeTableId)){
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
			return true;
		}
		return false;
		
	}
	
	public boolean removeBook(int timeTableId, int bookId){
		if(timetablesMap.get(timeTableId).getbooksMap().containsKey(bookId)){
			//meme principe que pour removeRoom()
			
			//charge la base de donnee
			loadDB();
			
			//on enleve de la map
			timetablesMap.get(timeTableId).getbooksMap().remove(bookId);
			
					
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
			return true;
		}
		return false;
		
	}

	public boolean addBook(int timeTableId, int bookId, String login, Date dateBegin, Date dateEnd, int roomId){
		if(timetablesMap.get(timeTableId).getbooksMap().containsKey(bookId)){
			return false;	
		}
		loadDB();
		
		//création de l'objet en java
		Book abook=new Book(bookId,login, dateBegin,dateEnd,roomId);
		timetablesMap.get(timeTableId).getbooksMap().put(bookId,abook);
	
		
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
				//date dans le bon format
				DateFormat df=new SimpleDateFormat("dd/MM/year HH:mm:ss");
				
				//ajout de DateBegin
				Element DateBeginElt = new Element("DateBegin");
				DateBeginElt.setText(df.format(dateBegin));
				BookElt.addContent(DateBeginElt);
				//ajout de DateEnd
				Element DateEndElt = new Element("DateEnd");
				DateEndElt.setText(df.format(dateEnd));
				BookElt.addContent(DateEndElt);
				//ajout de roomId
				Element RoomIdElt = new Element("RoomId");
				RoomIdElt.setText(Integer.toString(roomId));
				BookElt.addContent(RoomIdElt);	
				saveDB();	
			}
		}
		return true;
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
