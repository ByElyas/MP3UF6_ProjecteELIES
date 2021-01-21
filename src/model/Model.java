/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Vehicle;
import model.Conductor;

/**
 *
 * @author eliesfatsini
 */
public class Model {

//    private String urlBD = "jdbc:mysql://localhost:3306/projecteMP3";
//    private String userBD = "alumne";
//    //Esta feo aixo de posar hardcoded una password pero suo bastant
//    private String passwordUserBD = "alumne";
//
//    private String bdDriver = "com.mysql.jdbc.Driver";
//    public static final String table_header[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Numero Vehicle"};
//    public static final ArrayList<Vehicle> data = new ArrayList<Vehicle>(); 
    public Model() throws SQLException {

        //MOGUT AL CONTROLADOR DIRECTE
        //Afegir vehicles
//        data.add(new Vehicle("Mazda", "RX-7 FC", 1989, 6));
//        data.add(new Vehicle("Nissan", "Skyline GTR R32", 1991, 22));
//        data.add(new Vehicle("Toyota", "Corolla Trueno AE86", 1986, 86));
//        data.add(new Vehicle("Nissan", "Silvia S15", 1998, 66));
//        data.add(new Vehicle("Audi", "Quattro Sport", 1988, 15));
//        data.add(new Vehicle("BMW", "M3 GTR", 2005, 19));
//        Afegir conductors
//        dataConductor.add(new Conductor("Pepe", "Viyuela", 45, 6589));
//        dataConductor.add(new Conductor("Paul", "Walker", 47, 2254));
//        dataConductor.add(new Conductor("Ian", "Lewis", 24, 222));
//        dataConductor.add(new Conductor("Alex", "Cañizares", 21, 1574));
//        dataConductor.add(new Conductor("Frank", "Williams", 53, 1));
    }

    /**
     *
     * @return
     */
//    public Connection getConnection() throws SQLException {
////        Connection con;
////        con = DriverManager.getConnection(urlBD, userBD, passwordUserBD);
////        return con;
//          
//
//    }
//    public void closeConnection() throws SQLException {
//        Connection con = this.getConnection();
//        con.close();

    public ObjectContainer getObjecteDB() {
        return dbOBJECTES;
    }

//    public ObjectContainer getConductorDB() {
//        return dbCONDUCTORS;
//    }
    //Vehciles
    private Collection<Vehicle> data = new TreeSet<>();
    private Collection<Vehicle> dataOrd = new TreeSet<>(new VehicleOrdenatMarca());

    ObjectContainer dbOBJECTES = Db4oEmbedded.openFile("objectes.yap");

    public Collection<Vehicle> getData() {
        return data;
    }

    public Collection<Vehicle> getDataOrd() {
        return dataOrd;
    }

    //metode generic per a insertar dades --- tambè serveix per a conductor
    public static <T> void insertar(T a, Collection<T> col) {
        col.add(a);
    }

    public void insertarVehicle(Vehicle ve) {
        Model.insertar(ve, data);
        Model.insertar(ve, dataOrd);
//        Connection conV = DriverManager.getConnection(urlBD, userBD, passwordUserBD);
//        String query = " INSERT INTO vehicle VALUES (?, ?, ?, ?) ";
//        
//        PreparedStatement preparedStmt = conV.prepareStatement(query);
//        preparedStmt.setInt(1, numero);
//        preparedStmt.setString(2, model);
//        preparedStmt.setInt(3, any);
//        preparedStmt.setString(4, marca);
//        
//        preparedStmt.execute();
//        
//        conV.close();
    }

    public void insertarObjecteBD(Object o) {
        dbOBJECTES.store(o);
    }

    public void editarVehicleBD(Vehicle v, String marca, String model, int any) {
        v.set4_marca_Vehicle(marca);
        dbOBJECTES.store(v);
        v.set2_model_Vehicle(model);
        dbOBJECTES.store(v);
        v.set3_any_Vehicle(any);
        dbOBJECTES.store(v);

    }

    //metode generic per a eliminar dades 
    public static <T> void eliminar(T a, Collection<T> col) {
        col.remove(a);
    }

    public void buidarCol() {
        data.clear();
        dataOrd.clear();
        dataConductor.clear();
        dataOrdConductor.clear();
    }

    public void eliminarObjecteBD(Object v) {
        dbOBJECTES.delete(v);
    }

//    public void actualitzarVehicle(String marca, String model, int any, int numero) {
//
//    }
    class VehicleOrdenatMarca implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
//            return o1.get4_marca_Vehicle().compareTo(o2.get4_marca_Vehicle());
            int p;
            p = o1.get4_marca_Vehicle().compareTo(o2.get4_marca_Vehicle());
            if (p != 0) {
                return p;
            }
            return o1.get2_model_Vehicle().compareTo(o2.get2_model_Vehicle());
        }
    }

    //Conductor
    private Collection<Conductor> dataConductor = new TreeSet<>();
    private Collection<Conductor> dataOrdConductor = new TreeSet<>(new ConductorOrdenatNom());

    public Collection<Conductor> getDataConductor() {
        return dataConductor;
    }

    public Collection<Conductor> getDataOrdConductor() {
        return dataOrdConductor;
    }

    public void insertarConductor(Conductor co) {
        Model.insertar(co, dataConductor);
        Model.insertar(co, dataOrdConductor);

    }

    public void editarConductorBD(Conductor c, String nom, String cognom, int edat) {
        c.set2_cognom_Conductor(cognom);
        dbOBJECTES.store(c);
        c.set3_edat_Conductor(edat);
        dbOBJECTES.store(c);
        c.set4_nom_Conductor(nom);
        dbOBJECTES.store(c);

    }

    public void tancarBD() {
        dbOBJECTES.close();
    }

    class ConductorOrdenatNom implements Comparator<Conductor> {

        @Override
        public int compare(Conductor o1, Conductor o2) {
            // return o1.get4_nom_Conductor().compareTo(o2.get4_nom_Conductor());
            int p;
            p = o1.get4_nom_Conductor().compareTo(o2.get4_nom_Conductor());
            if (p != 0) {
                return p;
            }
            return o1.get2_cognom_Conductor().compareTo(o2.get2_cognom_Conductor());
        }

    }

}
