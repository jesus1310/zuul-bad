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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southeastExit;
    private Room northwestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast, Room northwest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)
            southeastExit = southeast;
        if(northwest != null)
            northwestExit = northwest;
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
        if(salidaPosible.equals("north")) {
            salida = northExit;
        }
        if(salidaPosible.equals("east")) {
            salida = eastExit;
        }
        if(salidaPosible.equals("south")) {
            salida = southExit;
        }
        if(salidaPosible.equals("west")) {
            salida = westExit;
        }
        if(salidaPosible.equals("southeast")) {
            salida = southeastExit;
        }
        if(salidaPosible.equals("northwest")) {
            salida = northwestExit;
        }
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
