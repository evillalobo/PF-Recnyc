package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

/**
 * Created by Eduardo on 01/06/2015.
 */
public class CategoriasInfo {
    int id;
    String categoria;
    /*Constructores*/
    public CategoriasInfo() {
    }

    public CategoriasInfo(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public CategoriasInfo(String categoria) {
        this.categoria = categoria;
    }
    /*Getters y Setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
