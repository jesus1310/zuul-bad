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
    private boolean puedoCoger;

    /**
     * Constructor for objects of class Item
     */
    public Item(String nombreObjeto, float pesoObjeto, boolean puedoCoger)
    {
        this.nombreObjeto = nombreObjeto;
        this.pesoObjeto = pesoObjeto;
        this.puedoCoger = puedoCoger;
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
     * Método que nos dice si se puede coger el objeto
     */
    public boolean sePuedeCoger(){
        return puedoCoger;
    }
    
    /**
     * Método para mostrar las caracteristicas del objeto
     */
    public String toString(){
        return "Nombre del objeto: " + nombreObjeto + "\nPeso del objeto: " + pesoObjeto + " KG";
    }
}

