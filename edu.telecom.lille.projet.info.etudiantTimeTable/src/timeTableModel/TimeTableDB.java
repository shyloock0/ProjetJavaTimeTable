package timeTableModel;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.util.List;
import java.util.Date;
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
	Element rootElt=new Element("TimeTableDB");
	org.jdom2.Document doc = new Document(rootElt);

	
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
		
	//sauvergarde
	public void saveDB(){
		try{
			XMLOutputter sortie =new XMLOutputter(Format.getPrettyFormat());
			sortie.output(doc,new FileOutputStream(file));
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
	    	
	    	if(courant.getChild("RoomId").getText()==Integer.toString(roomId)){
	          //On supprime l'Element en question en passant par sont parrent
	          roomsElt.removeContent(courant);
	    	}
	    }
	    
	    //enregistre les modif dans la base de donnees
	    saveDB();
	}
	

	public void addRoom(int roomId, int capacity){
		
		loadDB();
		
		//melange de deux techniques
		Element RoomsElt= rootElt.getChild("Rooms");
			
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
		
		//charge la base de donnee
		loadDB();
				
		Element TimeTablesElt= rootElt.getChild("TimeTables");
		List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
		Iterator<Element> i = ListTimeTableElt.iterator();
		//On parcours la liste grâce à un iterator
		while(i.hasNext()){
			Element courant = (Element)i.next();
			//Si l’étudiant possède l'Element en question on applique les modifications.
			if(courant.getChild("timeTableId").getText()==Integer.toString(timeTableId)){
				//On supprime l'Element en question en passant par sont parrent
				TimeTablesElt.removeContent(courant);
				}
			}
			    
		//enregistre les modif dans la base de donnees
		saveDB();
		}
	
	public void removeBook(int timeTableId, int bookId){
		//meme principe que pour removeRoom()
		
				//charge la base de donnee
				loadDB();
						
				Element TimeTablesElt= rootElt.getChild("TimeTables");
				List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
				Iterator<Element> i = ListTimeTableElt.iterator();
				//On parcours la liste grâce à un iterator
				while(i.hasNext()){
					Element courant = (Element)i.next();
					//Si on est dans le bonne emploi du temps
					if(courant.getChild("timeTableId").getText()==Integer.toString(timeTableId)){
						//on reitere le processus
						
						//on creer la liste des Elt ded reservation de courant
						List<Element> ListBookElt = courant.getChildren("Book");
						Iterator<Element> k = ListBookElt.iterator();
						
						while(k.hasNext()){
							Element courantbis = (Element)k.next();
							
							if(courantbis.getChild("bookId").getText()==Integer.toString(bookId)){
								courant.removeContent(courantbis);
							}
						}
					}
					    
				//enregistre les modif dans la base de donnees
				saveDB();
				}
		
	}

	public void addBook(int timeTableId, int bookId, String login, Date dateBegin, Date dateEnd, int roomId){
		loadDB();
		
		//melange de deux techniques
		Element TimeTablesElt= rootElt.getChild("TimeTables");
		
		//on recupere le bonne emploi du temps
		List<Element> ListTimeTableElt = TimeTablesElt.getChildren("TimeTable");
		Iterator<Element> i = ListTimeTableElt.iterator();

		while(i.hasNext()){
			Element courant = (Element)i.next();
			if(courant.getChild("timeTableId").getText()==Integer.toString(timeTableId)){
				//creation d'une classe book
				Element BookElt = new Element("Book");
				courant.addContent(BookElt);
				//ajout de BookId
				Element BookIdElt = new Element("BookId");
				BookIdElt.setText(Integer.toString(bookId));
				BookElt.addContent(BookIdElt);
				//ajout de login
				Element LoginElt = new Element("Login");
				LoginElt.setText(login);
				BookElt.addContent(LoginElt);
				//ajout de DateBegin
				Element DateBeginElt = new Element("DateBegin");
					//problem conversion de date :DateBeginElt.setText(Date.toString(dateBegin)));
				BookElt.addContent(DateBeginElt);
				//ajout de DateBegin
				Element DateEndElt = new Element("DateEnd");
					//problem conversion de date :DateEndElt.setText(Date.toString(dateEnd)));
				BookElt.addContent(DateEndElt);
				//ajout de roomId
				Element RoomIdElt = new Element("RoomId");
				BookIdElt.setText(Integer.toString(roomId));
				BookElt.addContent(RoomIdElt);	
			}

		saveDB();
		}
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
