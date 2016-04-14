import java.util.HashMap;

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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> salidas;

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
     * Método que devuelve un objeto Room asociado a una cadena pasada por parametro
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
        if(getExit("north") != null) {
            exitString += "north ";
        }
        if(getExit("east") != null) {
            exitString += "east ";
        }
        if(getExit("south") != null) {
            exitString += "south ";
        }
        if(getExit("west") != null) {
            exitString += "west ";
        }
        if(getExit("southeast") != null){
            exitString += "southeast ";
        }
        if(getExit("northwest") != null){
            exitString += "northwest ";
        }

        return exitString;
    }
}
