/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), TAKE("take"), DROP("drop"), ITEMS("items"), UNKNOWN("unknown");
    
    private String comando;
    
    private Option(String comando){
        this.comando = comando;
    }
    
    /**
     * Método para devolver el comando
     */
    public String getComando(){
        return comando;
    }
}
