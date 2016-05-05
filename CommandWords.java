import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private ArrayList<Option> comandos;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        comandos = new ArrayList<Option>();
        comandos.add(Option.GO);
        comandos.add(Option.QUIT);
        comandos.add(Option.HELP);
        comandos.add(Option.LOOK);
        comandos.add(Option.EAT);
        comandos.add(Option.BACK);
        comandos.add(Option.TAKE);
        comandos.add(Option.DROP);
        comandos.add(Option.ITEMS);
        comandos.add(Option.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean comandoEncontrado = false;
        for (int i = 0; i < comandos.size() && !comandoEncontrado; i++){
            if (comandos.get(i).getComando().equals(aString)){
                comandoEncontrado = true;
            }
        }
        return comandoEncontrado;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        String cadena = "Comandos: ";
        for (Option option : comandos) {
            cadena += option.getComando() + ", ";
        }
        System.out.println(cadena);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option valorDevuelto = Option.UNKNOWN;
        boolean comandoEncontrado = false;
        for (int i = 0; i < comandos.size() && !comandoEncontrado; i++){
            if (comandos.get(i).getComando().equals(commandWord)){
                comandoEncontrado = true;
                valorDevuelto = comandos.get(i);
            }
        }
        return valorDevuelto;
    }
}
