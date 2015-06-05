package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 27/04/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "proyectofinal";
    //Tablas
    private static final String TABLE_DEPTOS = "Departamento";
    private static final String TABLE_RECURSOS_NATURALES = "RecursoNatural";
    private static final String TABLE_RECURSOS_CULTURALES = "RecursoCultural";
    private static final String TABLE_CATEGORIA = "Categoria";
    private static final String TABLE_CATEGORIA_NAT = "Recurso_Nat_Cat";
    private static final String TABLE_CATEGORIA_CULT = "Recurso_Cult_Cat";
    private static final String TABLE_REC_CAT_NAT = "CategoriaRecursoNat";
    private static final String TABLE_REC_CAT_CULT = "CategoriaRecursoCult";
    //Campos
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_IMAGEN = "imageID";
    private static final String KEY_DESCRIPCION = "descripcion";
    //Parámetros
    private static final String WHERE = null;
    private static final String WHERE_ARGS = null;
    private static final String GROUPBY = null;
    private static final String HAVING = null;
    private static final String ORDER = null;

    private static final String[] COLUMNS = {KEY_ID, KEY_NOMBRE, KEY_IMAGEN, KEY_DESCRIPCION};

    Context mContext=null;

    /*Constructor*/
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    /*Crea las tablas con la clase DBDeploy*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            SQLiteDBDeploy.deploy(db, this.mContext, "proyectofinal.zip");

            // if you like to download file from remote site comment above line and uncomment below line.
            //SQLiteDBDeploy.deploy(sqlLiteDb,"http://ingenious-camel.googlecode.com/svn/trunk/SQLiteDBDeployer/assets/northwind.zip");
        } catch (IOException e) {
            Log.e("Proyectofinal", e.getMessage(), e);
            throw new Error(e.getMessage());
        }
    }
    /*Para actualizar la DB*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_DEPTOS);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_RECURSOS_NATURALES);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_RECURSOS_CULTURALES);
        onCreate(db);
    }

    /*Operaciones con Departamentos*/
    //Agrega un departamento
    public void addDepto(DeptoInfo deptoInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, deptoInfo.getName());

        db.insert(TABLE_DEPTOS, null, values);
        db.close();
    }
    //Devuelve un departamento pasando su
    public DeptoInfo getDepto(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPTOS,//Table
                COLUMNS,//Columns
                " id = ?",//Where
                new String[]{String.valueOf(id)},//Where args
                null,//groupBy
                null,//having
                null,//order
                null//limit
        );
        if(cursor != null){
            cursor.moveToFirst();
        }
        DeptoInfo depto = new DeptoInfo(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return depto;
    }
    //Lista de todos los Departamentos
    public List<DeptoInfo> getAllDeptos() {
        List<DeptoInfo> deptoInfoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DEPTOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DeptoInfo deptoInfo = new DeptoInfo();
                deptoInfo.setId(Integer.parseInt(cursor.getString(0)));
                deptoInfo.setName(cursor.getString(1));
                deptoInfo.setImageID(cursor.getString(2));
                deptoInfo.setDesc(cursor.getString(3));
                deptoInfoList.add(deptoInfo);
            } while (cursor.moveToNext());
        }
        Log.d("Departamentos","En la base hay " + deptoInfoList.size()+" Departamentos para mostrar");
        return deptoInfoList;
    }
    /*Método para Categorias*/
    //Listar todas la categorias
    public List<CategoriasInfo>  getAllCategorias(){
        List <CategoriasInfo> categorias = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CategoriasInfo categoriasInfo = new CategoriasInfo();
                categoriasInfo.setId(Integer.parseInt(cursor.getString(0)));
                categoriasInfo.setCategoria(cursor.getString(1));
                categorias.add(categoriasInfo);
            } while (cursor.moveToNext());
        }
        Log.d("Categorias","En la base hay " + categorias.size()+" Categorias para mostrar");

        return categorias;
    }
    //Listar todas las categorias de Recursos Naturales
    public List<CategoriasInfo> getCategoriasNatural(){
        List <CategoriasInfo> categoriasNaturales = new ArrayList<>();
        //Select * FROM Categoria, RecursoNatural WHERE Categoria.id=Cat_Nat.cat_id AND RecursoNatural.id=Cat_Nat.nat_id;
        String selectQuery = "SELECT * FROM Categoria, RecursoNatural, Cat_Nat WHERE Categoria.id=Cat_Nat.cat_id AND RecursoNatural.id = Cat_Nat.nat_id;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do{
                CategoriasInfo categoriasInfo = new CategoriasInfo();
                categoriasInfo.setId(Integer.parseInt(cursor.getString(0)));
                categoriasInfo.setCategoria(cursor.getString(1));
                categoriasNaturales.add(categoriasInfo);
            }while (cursor.moveToNext());
        }
        return categoriasNaturales;
    }
    //Listar todas las categorias de Recursos Culturales
    public List<CategoriasInfo> getCategoriasCultural(){
        List <CategoriasInfo> categoriasCulturales = new ArrayList<>();
        String selectQuery = "SELECT * FROM Categoria, RecursoCultural, Cat_Cult WHERE Categoria.id = Cat_Cult.cat_id AND RecursoCultural.id = Cat_Cult.cult_id GROUP BY Categoria.categoria;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        System.out.println(cursor);
        if(cursor.moveToFirst())
        {
            do{
                CategoriasInfo categoriasInfo = new CategoriasInfo();
                categoriasInfo.setId(Integer.parseInt(cursor.getString(0)));
                categoriasInfo.setCategoria(cursor.getString(1));
                categoriasCulturales.add(categoriasInfo);
            }while (cursor.moveToNext());
        }
        return categoriasCulturales;
    }
    /*Métodos de Recursos Naturales*/
    //Devuelve un Recurso Natural pasando su id
    public RecursoNaturalInfo getRecursoNatural(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPTOS,//Table
                COLUMNS,//Columns
                " id = ?",//Where
                new String[]{String.valueOf(id)},//WhereArgs
                null,//groupby
                null,//having
                null,//order
                null//limits
        );

        if(cursor != null){
            cursor.moveToFirst();
        }

        RecursoNaturalInfo recursoNaturalInfo = new RecursoNaturalInfo(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );

        return recursoNaturalInfo;
    }
    //Lista todos los Recursos Naturales
    public List<RecursoNaturalInfo> getAllRecNat(){
        List<RecursoNaturalInfo> recursoNaturalInfoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RECURSOS_NATURALES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RecursoNaturalInfo recursoNaturalInfo = new RecursoNaturalInfo();
                recursoNaturalInfo.setId(Integer.parseInt(cursor.getString(0)));
                recursoNaturalInfo.setNombre(cursor.getString(1));
                recursoNaturalInfo.setImageID(cursor.getString(2));
                recursoNaturalInfo.setDescripcion(cursor.getString(3));
                recursoNaturalInfoList.add(recursoNaturalInfo);
            } while (cursor.moveToNext());
        }
        Log.d("Recursos Naturales","En la base hay " + recursoNaturalInfoList.size()+" Recursos Naturales para mostrar");
        return recursoNaturalInfoList;
    }

    /*Recursos Culturales Methods*/
    //Devuelve un Recurso Cultural según una categoria

    //Lista todos los Recursos Culturales
    public List<RecursoCulturalInfo> getAllRecCult(){
        List<RecursoCulturalInfo> recursoCulturalInfoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RECURSOS_CULTURALES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RecursoCulturalInfo recursoCulturalInfo = new RecursoCulturalInfo();
                recursoCulturalInfo.setId(Integer.parseInt(cursor.getString(0)));
                recursoCulturalInfo.setNombre(cursor.getString(1));
                recursoCulturalInfo.setImageID(cursor.getString(2));
                recursoCulturalInfo.setDescripcion(cursor.getString(3));
                recursoCulturalInfoList.add(recursoCulturalInfo);
            } while (cursor.moveToNext());
        }
        Log.d("Recursos Naturales","En la base hay " + recursoCulturalInfoList.size()+" Recursos Naturales para mostrar");
        return recursoCulturalInfoList;
    }
}
