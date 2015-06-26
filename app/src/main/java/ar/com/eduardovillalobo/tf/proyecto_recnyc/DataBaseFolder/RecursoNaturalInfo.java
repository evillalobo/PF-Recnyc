package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 26/05/2015.
 */
public class RecursoNaturalInfo {
    private int id;
    private String nombre;
    private String descripcion;
    private String imageID;
    private String c_lat;
    private String c_long;

    /*Constructores*/
    public RecursoNaturalInfo() {
    }

    public RecursoNaturalInfo(int id, String nombre, String imageID, String descripcion, String c_lat, String c_long) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageID = imageID;
        this.c_lat = c_lat;
        this.c_long = c_long;
    }

    public RecursoNaturalInfo(String nombre, String imageID, String descripcion, String c_lat, String c_long) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imageID = imageID;
        this.c_lat = c_lat;
        this.c_long = c_long;
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

    public String getC_lat() {
        return c_lat;
    }

    public void setC_lat(String c_lat) {
        this.c_lat = c_lat;
    }

    public String getC_long() {
        return c_long;
    }

    public void setC_long(String c_long) {
        this.c_long = c_long;
    }
}
