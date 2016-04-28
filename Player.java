import java.util.ArrayList;
import java.util.Stack;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> anteriores;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        anteriores = new Stack<Room>();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            anteriores.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }

    /**
     * M�todo para evitar la repeticion de codigo
     */
    public void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * M�todo para fijar la habitacion actual
     */
    public void setCurrentRoom(Room room){
        if (currentRoom != null){
            anteriores.push(currentRoom);
        }
        currentRoom = room;
    }

    /**
     * M�todo para volver a la �ltima habitaci�n visitada
     */
    public void backLastRoom(){
        if (!anteriores.empty()){
            currentRoom = anteriores.pop();
            printLocationInfo();
        }
        else {
            System.out.println("Has llegado al punto de partida, no puedes retroceder m�s");
            printLocationInfo();
        }
    }
}
