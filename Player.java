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
}
