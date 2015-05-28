package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 26/05/2015.
 */
public class RecursoNaturalInfo {
    private int id;
    private String nombre;
    private String descripcion;
    private String imageID;

    /*Constructores*/
    public RecursoNaturalInfo() {
    }

    public RecursoNaturalInfo(int id, String nombre, String imageID, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageID = imageID;
    }

    public RecursoNaturalInfo(String nombre, String imageID, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageID = imageID;
    }
    /*Getters y Setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
