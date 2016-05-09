import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

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
    private float energia;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        anteriores = new Stack<Room>();
        mochila = new ArrayList<>();
        pesoActual = 0;
        energia = 10;
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
        else if (!puedeSalir() && currentRoom.getDescription().contains("sureste") && direction.equals("west")){
            System.out.println("No puedes salir hasta que tengas las llaves");
        }
        else {
            anteriores.push(currentRoom);
            currentRoom = nextRoom;
            energia -= pesoActual * 2;
            printLocationInfo();
            System.out.println();
        }
    }

    /**
     * Método para evitar la repeticion de codigo
     */
    public void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Método para fijar la habitacion actual
     */
    public void setCurrentRoom(Room room){
        if (currentRoom != null){
            anteriores.push(currentRoom);
        }
        currentRoom = room;
    }

    /**
     * Método para volver a la última habitación visitada
     */
    public void backLastRoom(){
        if (!anteriores.empty()){
            currentRoom = anteriores.pop();
            printLocationInfo();
            energia -= pesoActual * 2;
            System.out.println("La energía está al " + energia * 10 + "%");
        }
        else {
            System.out.println("Has llegado al punto de partida, no puedes retroceder más");
            printLocationInfo();
        }
    }

    /**
     * Método para coger un objeto de la sala en la que nos encontramos
     */
    public void takeItem(String nombreItem){
        if (nombreItem != null){
            Item itemEncontrado = currentRoom.buscarItem(nombreItem);
            if (itemEncontrado != null){
                if (pesoActual < PESO_MAXIMO - itemEncontrado.getPesoObjeto() && itemEncontrado.sePuedeCoger()){
                    mochila.add(itemEncontrado);
                    currentRoom.borrarItem(itemEncontrado);
                    pesoActual += itemEncontrado.getPesoObjeto();
                }
                else if (pesoActual + itemEncontrado.getPesoObjeto() >= PESO_MAXIMO && itemEncontrado.sePuedeCoger()){
                    System.out.println("No se puede coger el objeto de esta sala porque sobrepasarías el peso máximo");
                }
                else if (!itemEncontrado.sePuedeCoger()){
                    System.out.println("No se puede coger el objeto de esta sala porque está anclado a la pared");
                }
            }
        }
        else {
            System.out.println("Debes escribir el nombre del objeto que quieres coger");
        }
    }

    /**
     * Método para dejar un objeto en la sala en la que nos encontramos
     */
    public void dropItem(String nombreItem){
        if (nombreItem != null){
            for (int i = 0; i < mochila.size(); i++){
                if (mochila.get(i).getNombreObjeto().equals(nombreItem)){
                    currentRoom.addObjeto(mochila.get(i));
                    pesoActual -= mochila.get(i).getPesoObjeto();
                    mochila.remove(i);
                }
            }
        }
        else {
            System.out.println("Debes escribir el nombre del objeto que quieres dejar");
        }
    }

    /**
     * Método para mostrar inventario
     */
    public void muestraInventario(){
        if (mochila.size()>0){
            for (Item objeto : mochila){
                System.out.println(objeto.toString() + "\n");
            } 
            System.out.println("Peso actual: " + pesoActual + "\nPeso máximo permitido: " + PESO_MAXIMO);
        }
        else {
            System.out.println("No tienes ningún objeto en la mochila");
        }
    }

    /**
     * Método para que el jugador se mueva de forma aleatoria
     * a una de las posibles salidas de la sala en la que esta
     */
    public void movimientoAleatorio(){
        Random rnd = new Random();
        String[] listaSalidas = currentRoom.getExitString().split(" ");
        setCurrentRoom(currentRoom.getExit(listaSalidas[rnd.nextInt(listaSalidas.length)]));
    }

    /**
     * Método que muestra la habitacion en la que se encuentra el jugador
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Método que devuelve si el jugador puede salir
     */
    public boolean puedeSalir(){
        boolean puedeSalir = false;
        for (int i = 0; i < mochila.size(); i++){
            if (mochila.get(i).getNombreObjeto().equals("llaves")){
                puedeSalir = true;
            }
        }
        return puedeSalir;
    }

    /**
     * Método que devuelve la energia que le queda al jugador
     */
    public float getEnergiaRestante(){
        return energia;
    }

    /**
     * Método que recupera la energía del jugador
     */
    public void comer(){
        energia = 10;
    }
}
