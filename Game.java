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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room inicial, oeste, suroeste, este, noreste, sureste, cruce, salida;

        // create the rooms
        inicial = new Room("en la sala inicial");
        inicial.addObjeto(new Item("café", 0.10f));
        oeste = new Room("en la sala oeste");
        oeste.addObjeto(new Item("linterna sin pilas", 0.50f));
        suroeste = new Room("en la sala suroeste");
        suroeste.addObjeto(new Item("pilas", 0.25f));
        este = new Room("en la sala este");
        este.addObjeto(new Item("bolsa con comida", 2.5f));
        noreste = new Room("en la sala noreste");
        noreste.addObjeto(new Item("tarjeta para abrir puertas", 0.05f));
        sureste = new Room("en la sala sureste");
        sureste.addObjeto(new Item("portátil", 2f));
        cruce = new Room("en un cruce de pasillos");
        cruce.addObjeto(new Item("caja con periódicos", 5f));
        salida = new Room("fuera. Has encontrado la salida. Puedes volver a entrar o salir del juego escribiendo 'quit'");
        salida.addObjeto(new Item("llaves para volver a entrar", 0.75f));
        

        // initialise room exits
        inicial.setExit("west",oeste);
        inicial.setExit("east",cruce);
        inicial.setExit("southwest",sureste);
        oeste.setExit("east",inicial);
        oeste.setExit("south",suroeste);
        suroeste.setExit("norte",oeste);
        este.setExit("west",cruce);
        noreste.setExit("south",cruce);
        sureste.setExit("norte",cruce);
        sureste.setExit("noroeste",inicial);
        sureste.setExit("west",salida);
        cruce.setExit("norte",noreste);
        cruce.setExit("east",este);
        cruce.setExit("sur",sureste);
        cruce.setExit("west",inicial);
        salida.setExit("east",sureste);
        currentRoom = inicial;  // start game outside
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
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
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

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            printLocationInfo();
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
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
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
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

    /**
     * Método para evitar la repeticion de codigo
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }
}
