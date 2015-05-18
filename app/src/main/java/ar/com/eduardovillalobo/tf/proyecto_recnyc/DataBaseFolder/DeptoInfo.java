package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 27/04/2015.
 */
public class DeptoInfo {
    private int id;
    private String name;
    private String imageID;

    /*Constructores*/
    public DeptoInfo(){

    }

    public DeptoInfo(int id, String name, String imageID) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
    }

    public DeptoInfo(String name, String imageID){
        this.name = name;
        this.imageID = imageID;
    }

    /*Getters y Setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
