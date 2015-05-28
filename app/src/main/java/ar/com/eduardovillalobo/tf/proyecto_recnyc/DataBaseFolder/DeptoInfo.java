package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 27/04/2015.
 */
public class DeptoInfo {
    private int id;
    private String name;
    private String imageID;
    private String desc;

    /*Constructores*/
    public DeptoInfo(){

    }

    public DeptoInfo(int id, String name, String imageID, String desc) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
        this.desc = desc;
    }

    public DeptoInfo(String name, String imageID, String desc){
        this.name = name;
        this.imageID = imageID;
        this.desc = desc;
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

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }
}
