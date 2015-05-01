package com.handycartaxi.taxiappproject; /**
 * Created by Joan on 22-Apr-15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    final static String LOG = "com.handycartaxi.taxiappproject.DatabaseHelper";

    final static int DATABASE_VERSION = 10;

    final static String DATABASE_NAME = "handycarapp";

    public final static String TABLE_ASIGNADO = "asignado";
    public final static String TABLE_COMPANIA = "compania";
    public final static String TABLE_ESTATUSTAXI = "estatustaxi";
    public final static String TABLE_PEDIDO = "pedido";
    public final static String TABLE_TAXI = "taxi";
    public final static String TABLE_TAXISTA = "taxista";
    public final static String TABLE_TIPOTAXI = "tipotaxi";
    public final static String TABLE_USUARIOLOGIN = "usuariologin";
    public final static String TABLE_VEHICULO = "vehiculo";
    public final static String TABLE_ESTUDIANTES = "estudiante";



    final private Context mContext;


    public final static String KEY_ID= "_id";
    public final static String KEY_NOMBRE= "nombre";
    public final static String KEY_CARRERA= "carrera";
    public final static String KEY_MATRICULA = "matricula";
    public final static String KEY_FOTO = "foto";
    public final static String KEY_SEXO= "sexo";
    public final static String KEY_FECHANAC= "fechaNac";

//COLUMNAS TABLA ASIGNADO
    public final static String KEY_ID_ASIGNADO = "_id_asignado";
    public final static String ID_PEDIDO = "_id_pedido";
    public final static String ID_TAXI= "_id_taxi";
    public final static String TIEMPO = "tiempo";
    public final static String EXITOSO = "exitoso";
    public final static String A_HABILITADO = "habilitado";

    public final static String[] columnasTableAsignado = {KEY_ID_ASIGNADO, ID_PEDIDO, ID_TAXI, TIEMPO, EXITOSO, A_HABILITADO};

//COLUMNAS TABLA COMPANIA
    public final static String KEY_ID_COMPANIA = "_id_compania";
    public final static String NOMBRE_COMPANIA = "nombre_compania";

    public final static String[] columnasTableCompania = {KEY_ID_COMPANIA, NOMBRE_COMPANIA};

//COLUMNAS TABLA ESTATUSTAXI
    public final static String KEY_ID_ESTATUSTAXI = "_id_es";
    public final static String DESCRIPCION = "descripcion";

    public final static String[] columnasTableEstatusTaxi = {KEY_ID_ESTATUSTAXI, DESCRIPCION};

//COLUMNAS TABLA PEDIDO
    public final static String KEY_ID_PEDIDO = "_id_pedido";
    public final static String LATITUD = "latitud";
    public final static String LONGITUD = "longitud";
    public final static String CANCELADO = "cancelado";
    public final static String HABILITADO = "habilitado";

    public final static String[] columnasTablePedido = {KEY_ID_PEDIDO, LATITUD, LONGITUD, CANCELADO, HABILITADO};

    //COLUMNAS TABLA TAXI
    public final static String KEY_ID_TAXI = "_id_taxi";
    public final static String ID_TAXISTA = "_id_taxista";
    public final static String UNIDAD = "unidad";
    public final static String ID_COMPANIA = "_id_compania";
    public final static String ID_VEHICULO = "_id_vehiculo";
    public final static String ID_TIPOTAXI = "_id_tipotaxi";
    public final static String ID_ESTATUS = "_id_estatus";

    public final static String[] columnasTableTaxi = {KEY_ID_TAXI, ID_TAXISTA, UNIDAD, ID_COMPANIA, ID_VEHICULO, ID_TIPOTAXI, ID_ESTATUS};

    //COLUMNAS TABLA TAXISTA
    public final static String KEY_ID_TAXISTA = "_id_taxista";
    public final static String NOMBRE_TAXISTA = "nombre_taxista";
    public final static String CEDULA = "cedula";
    public final static String TELEFONO = "telefono";
    public final static String ID_FOTO = "_id_foto";
    public final static String ID_USUARIOLOGIN = "_id_usuariologin";

    public final static String[] columnasTableTaxista = {KEY_ID_TAXISTA, NOMBRE_TAXISTA, CEDULA, TELEFONO, ID_FOTO, ID_USUARIOLOGIN};

   //COLUMNAS TABLA TIPOTAXI
    public final static String KEY_ID_TIPOTAXI = "_id_tipoaxi";
    public final static String NOMBRE_TIPOTAXI = "nombre_tipotaxi";

    public final static String[] columnasTableTipoTaxi = {KEY_ID_TIPOTAXI, NOMBRE_TIPOTAXI};

    //COLUMNAS TABLA USUARIOLOGIN
    public final static String KEY_ID_USUARIOLOGIN = "_id_tipoaxi";
    public final static String USUARIO = "usuario";
    public final static String CONTRASENA = "contrasena";

    public final static String[] columnasTableUsuarioLogin = {KEY_ID_USUARIOLOGIN, USUARIO, CONTRASENA};

    //COLUMNAS TABLA VEHICULO
    public final static String KEY_ID_VEHICULO = "_id_vehiculo";
    public final static String COLOR_VEHICULO = "color_vehiculo";
    public final static String FOTO_VEHICULO = "foto_vehiculo";
    public final static String TIPO_VEHICULO = "tipo_vehiculo";
    public final static String PLACA_VEHICULO = "placa_vehiculo";
    public final static String MARCA = "marca";
    public final static String ANO = "ano";

    public final static String[] columnasTableVehiculo = {KEY_ID_VEHICULO, COLOR_VEHICULO, FOTO_VEHICULO, TIPO_VEHICULO, PLACA_VEHICULO, MARCA, ANO};



    private static final String CREATE_TABLE_ESTUDIANTES = "CREATE TABLE "
            + TABLE_ESTUDIANTES + "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_NOMBRE + " TEXT,"+ KEY_CARRERA + " TEXT," + KEY_MATRICULA
            + " TEXT," + KEY_FOTO + " INTEGER," + KEY_SEXO + " TEXT," + KEY_FECHANAC
            + " TEXT" + ")";

    private static final String CREATE_TABLE_ASIGNADO = "CREATE TABLE "
            + TABLE_ASIGNADO + "("+ KEY_ID_ASIGNADO + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ID_PEDIDO + " INTEGER,"+ ID_TAXI + " INTEGER," + TIEMPO
            + " INTEGER," + EXITOSO + " BIT," + A_HABILITADO + " BIT" + " )";

    private static final String CREATE_TABLE_COMPANIA = "CREATE TABLE "
            + TABLE_COMPANIA + "("+ KEY_ID_COMPANIA + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NOMBRE_COMPANIA + " TEXT"+" )";

    private static final String CREATE_TABLE_ESTATUSTAXI = "CREATE TABLE "
            + TABLE_ESTATUSTAXI + "("+ KEY_ID_ESTATUSTAXI + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ DESCRIPCION + " TEXT"+" )";

    private static final String CREATE_TABLE_PEDIDO = "CREATE TABLE "
            + TABLE_PEDIDO + "("+ KEY_ID_PEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ LATITUD + " DECIMAL,"+ LONGITUD + " DECIMAL,"+
            CANCELADO + " BIT, " + HABILITADO + " BIT"+" )";

    private static final String CREATE_TABLE_TAXI = "CREATE TABLE "
            + TABLE_TAXI + "("+ KEY_ID_TAXI + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ID_TAXISTA + " INT,"+ UNIDAD + " INT,"+
            ID_COMPANIA + " INT, " + ID_VEHICULO + " INT, " + ID_TIPOTAXI + "INT" + ID_ESTATUS+" INT"+" )";

    private static final String CREATE_TABLE_TAXISTA = "CREATE TABLE "
            + TABLE_TAXISTA + "("+ KEY_ID_TAXISTA + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NOMBRE_TAXISTA + " TEXT,"+ CEDULA + " TEXT,"+
            TELEFONO + " INT, " + ID_FOTO + " INT, " + ID_USUARIOLOGIN + "INT" + " )";

    private static final String CREATE_TABLE_TIPOTAXI = "CREATE TABLE "
            + TABLE_TIPOTAXI + "("+ KEY_ID_TIPOTAXI + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NOMBRE_TIPOTAXI + " TEXT" + " )";

    private static final String CREATE_TABLE_USUARIOLOGIN = "CREATE TABLE "
            + TABLE_TAXISTA + "("+ KEY_ID_USUARIOLOGIN + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ USUARIO + " TEXT,"+ CONTRASENA + " TEXT" + " )";

    private static final String CREATE_TABLE_VEHICULO = "CREATE TABLE "
            + TABLE_VEHICULO + "("+ KEY_ID_VEHICULO + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLOR_VEHICULO + " TEXT,"+ FOTO_VEHICULO + " TEXT,"
            + TIPO_VEHICULO + " TEXT," + PLACA_VEHICULO + " TEXT" + MARCA + " TEXT" + ANO + " INT )";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ESTUDIANTES);
        db.execSQL(CREATE_TABLE_ASIGNADO);
        db.execSQL(CREATE_TABLE_COMPANIA);
        db.execSQL(CREATE_TABLE_ESTATUSTAXI);
        db.execSQL(CREATE_TABLE_PEDIDO);
        db.execSQL(CREATE_TABLE_TAXI);
        db.execSQL(CREATE_TABLE_TAXISTA);
        db.execSQL(CREATE_TABLE_TIPOTAXI);
        db.execSQL(CREATE_TABLE_USUARIOLOGIN);
        db.execSQL(CREATE_TABLE_VEHICULO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDIANTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASIGNADO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAXI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAXISTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOTAXI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOLOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICULO);


        onCreate(db);
    }

    public void deleteDatabase() {

        mContext.deleteDatabase(DATABASE_NAME);
    }

}
