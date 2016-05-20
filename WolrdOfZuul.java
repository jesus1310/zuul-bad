/**
 * Write a description of class WolrdOfZuul here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WolrdOfZuul
{
    private static Game juego;
    
    /**
     * Método main para ejecutar el juego
     */
    public static void main(String[] args){
        juego = new Game();
        juego.play();
    }
}
