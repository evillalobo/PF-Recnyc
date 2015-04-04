package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import java.io.Serializable;

/**
 * Created by Eduardo on 17/03/2015.
 */
public class MainOptionInfo implements Serializable {
    public int featured_src;
    public String title;
    public String descripcion;
    int id;

    public MainOptionInfo(){

    }

    public MainOptionInfo(int featured_src, String title, String descripcion, int i){
        this.featured_src = featured_src;
        this.title = title;
        this.descripcion = descripcion;
        this.id = id;
    }

    public int getFeatured_src() {
        return featured_src;
    }

    public void setFeatured_src(int featured_src) {
        this.featured_src = featured_src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
