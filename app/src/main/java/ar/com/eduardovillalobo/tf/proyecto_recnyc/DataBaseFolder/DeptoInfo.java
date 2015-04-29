package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 27/04/2015.
 */
public class DeptoInfo {
    int id;
    String name;
    /*Constructores*/
    public DeptoInfo(){

    }

    public DeptoInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeptoInfo(String name){
        this.name = name;
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
}
