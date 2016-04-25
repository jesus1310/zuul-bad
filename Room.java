import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> salidas;
    private String nombreObjeto;
    private float pesoObjeto;
    private ArrayList<Item> objetos;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
        objetos = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        salidas.put(direction,neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * M�todo que devuelve un objeto Room asociado a una cadena pasada por parametro
     * Si no hay ningun objeto asociado a la cadena devuelve null
     */
    public Room getExit(String salidaPosible){
        Room salida = null;
        salida = salidas.get(salidaPosible);
        return salida;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exitString = "";
        for (String key : salidas.keySet()) {
            exitString += key + " ";
        }
        return exitString;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String descripcion = "Est�s " + description + "\nSalidas posibles: " + getExitString();
        descripcion += "\nEn esta sala hay: ";
        for (Item objetoEnSala : objetos){
            descripcion += objetoEnSala.toString(); 
        }
        return descripcion;
    }

    /**
     * M�todo para a�adir objetos a la lista
     */
    public void addObjeto(Item objeto){
        objetos.add(objeto);
    }
}
