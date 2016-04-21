/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String nombreObjeto;
    private float pesoObjeto;

    /**
     * Constructor for objects of class Item
     */
    public Item(String nombreObjeto, float pesoObjeto)
    {
        this.nombreObjeto = nombreObjeto;
        this.pesoObjeto = pesoObjeto;
    }

    /**
     * Metodo que devuelve el nombre del objeto
     */
    public String getNombreObjeto(){
        return nombreObjeto;
    }

    /**
     * Metodo que devuelve el peso del objeto
     */
    public float getPesoObjeto(){
        return pesoObjeto;
    }
    
    /**
     * Método para mostrar las caracteristicas del objeto
     */
    public String muestraDatos(){
        return "Nombre del objeto: " + nombreObjeto + "\nPeso del objeto: " + pesoObjeto;
    }
}

