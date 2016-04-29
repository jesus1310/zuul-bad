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
    public static final float PESO_MAXIMO = 4;
    private float pesoActual;
    private ArrayList<Item> mochila;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        anteriores = new Stack<Room>();
        mochila = new ArrayList<>();
        pesoActual = 0;
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

    /**
     * M�todo para coger un objeto de la sala en la que nos encontramos
     */
    public void takeItem(String nombreItem){
        Item itemEncontrado = currentRoom.buscarItem(nombreItem);
        if (pesoActual < PESO_MAXIMO - itemEncontrado.getPesoObjeto() && itemEncontrado.sePuedeCoger()){
            mochila.add(itemEncontrado);
            currentRoom.borrarItem(itemEncontrado);
            pesoActual += itemEncontrado.getPesoObjeto();
        }
        else if (pesoActual + itemEncontrado.getPesoObjeto() >= PESO_MAXIMO){
            System.out.println("No se puede coger el objeto de esta sala porque sobrepasar�as el peso m�ximo");
        }
        else if (!itemEncontrado.sePuedeCoger()){
            System.out.println("No se puede coger el objeto de esta sala porque est� anclado a la pared");
        }
    }

    /**
     * M�todo para dejar un objeto en la sala en la que nos encontramos
     */
    public void dropItem(String nombreItem){
        for (int i = 0; i < mochila.size(); i++){
            if (mochila.get(i).getNombreObjeto().equals(nombreItem)){
                currentRoom.addObjeto(mochila.get(i));
                pesoActual -= mochila.get(i).getPesoObjeto();
                mochila.remove(i);
            }
        }
    }

    /**
     * M�todo para mostrar inventario
     */
    public void muestraInventario(){
        if (mochila.size()>0){
            for (Item objeto : mochila){
                System.out.println(objeto.toString() + "\n");
            } 
            System.out.println(pesoActual);
        }
        else {
            System.out.println("No tienes ning�n objeto en la mochila");
        }
    }
}
