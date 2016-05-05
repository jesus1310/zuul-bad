import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player jugador;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        jugador = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room inicial, oeste, suroeste, este, noreste, sureste, cruce, salida;

        // create the rooms
        inicial = new Room("en la sala inicial");
        inicial.addObjeto(new Item("cafe", 0.10f, true));
        oeste = new Room("en la sala oeste");
        oeste.addObjeto(new Item("linterna", 0.50f, true));
        suroeste = new Room("en la sala suroeste");
        suroeste.addObjeto(new Item("pilas", 0.25f, true));
        este = new Room("en la sala este");
        este.addObjeto(new Item("comida", 2.5f, true));
        noreste = new Room("en la sala noreste");
        noreste.addObjeto(new Item("tarjeta", 0.05f, true));
        sureste = new Room("en la sala sureste");
        sureste.addObjeto(new Item("portatil", 2f, true));
        cruce = new Room("en un cruce de pasillos");
        cruce.addObjeto(new Item("estanteria", 15f, false));
        salida = new Room("fuera. Has encontrado la salida. Puedes volver a entrar o salir del juego escribiendo 'quit'");
        salida.addObjeto(new Item("llaves", 0.75f, true));

        // initialise room exits
        inicial.setExit("west",oeste);
        inicial.setExit("east",cruce);
        inicial.setExit("southwest",sureste);
        oeste.setExit("east",inicial);
        oeste.setExit("south",suroeste);
        suroeste.setExit("norte",oeste);
        este.setExit("west",cruce);
        noreste.setExit("south",cruce);
        sureste.setExit("north",cruce);
        sureste.setExit("northwest",inicial);
        sureste.setExit("west",salida);
        cruce.setExit("north",noreste);
        cruce.setExit("east",este);
        cruce.setExit("south",sureste);
        cruce.setExit("west",inicial);
        salida.setExit("east",sureste);
        jugador.setCurrentRoom(inicial);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type " +  Option.HELP.getComando() + " if you need help.");
        System.out.println();
        jugador.printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch(commandWord){
            case HELP:
            printHelp();
            break;

            case GO:
            jugador.goRoom(command);
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;

            case LOOK:
            jugador.printLocationInfo();
            break;

            case EAT:
            System.out.println("You have eaten now and you are not hungry any more");
            break;

            case BACK:
            jugador.backLastRoom();
            break;

            case TAKE:
            jugador.takeItem(command.getSecondWord());
            break;

            case DROP:
            jugador.dropItem(command.getSecondWord());
            break;

            case ITEMS:
            jugador.muestraInventario();
            break;

            default:
            System.out.println("I don't know what you mean...");
            return false;
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.printCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
