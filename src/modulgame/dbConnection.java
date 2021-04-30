/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
Saya Aldi Saepurahman mengerjakan evaluasi Tugas Praktikum 5 dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak
melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
*/
package modulgame;
//import class Connection, drivermanager, resultset, statement dan tablemodel
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Aldi Saepurahman
 */
public class dbConnection {
    //variabel connection dan statement
    public static Connection con;
    public static Statement stm;
    //buat koneksi baru ke database
    public void connect(){
        //coba koneksikan ke database dengan memasukkan nama database, username, password
        try {
            String url ="jdbc:mysql://localhost/db_gamepbo";
            String user="root";
            String pass="";
            //ambil driver library jdbc
            Class.forName("com.mysql.jdbc.Driver");
            //konfigurasikan semua data konfigurasi database
            con = DriverManager.getConnection(url,user,pass);
            //buat statement baru
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            //jika koneksi gagal
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }
    //fungsi mengambil data pemain yang ada di database berdasarkan username
    public Object[] checkPlayer(String username){
        //buat array object
        Object[] dataPlayer = new Object[3];
        try{
            //koneksi ke database
            connect();
            //buat sintaks select data username pemain dan execute
            String sql = "Select * from highscore WHERE Username = '"+ username +"'";
            ResultSet res = stm.executeQuery(sql);
            //jika data pertama ditemukan, masukkan kedalam array
            if (res.first() != false) {
                dataPlayer[0] = res.getInt("id");
                dataPlayer[1] = res.getString("Username");
                dataPlayer[2] = res.getInt("Score");
            }
        }catch(Exception e){
            //jika proses select data gagal
            System.err.println("Read gagal " +e.getMessage());
        }
        //kembalikan array object
        return dataPlayer;
    }
    //fungsi menampilkan data kedalam tabel
    public DefaultTableModel readTable(){
        //buat tabel baru
        DefaultTableModel dataTabel = null;
        try{
            //buat nama kolomnya
            Object[] column = {"No", "Username", "Score"};
            //koneksi ke database
            connect();
            //set nama kolomnya
            dataTabel = new DefaultTableModel(null, column);
            //ambil semua data highscore berdasarkan highscore tertinggi dan execute
            String sql = "Select * from highscore ORDER BY Score DESC";
            ResultSet res = stm.executeQuery(sql);
            //variabel urutan data
            int no = 1;
            //perulangan sebanyak data hasil execute, dan masukkan kedalam array dan tabel
            while(res.next()){
                Object[] hasil = new Object[3];
                hasil[0] = no;
                hasil[1] = res.getString("Username");
                hasil[2] = res.getString("Score");
                no++;
                dataTabel.addRow(hasil);
            }
        }catch(Exception e){
            //jika proses select gagal
            System.err.println("Read gagal " +e.getMessage());
        }
        //kembalikan data tabel
        return dataTabel;
    }
    //method insert pemain baru
    public void insertPlayer(String username, int score){
        try{
            //buat koneksi untuk eksekusi query
            connect();
            //buat query insert data pemain baru
            String query = "INSERT INTO highscore (Username, Score) "
                     + "VALUES ("
                     + "'"+ username +"','"+ score +"')";
            //tampung status eksekusi querynya
            int sukses = stm.executeUpdate(query);
        }catch(Exception e){
            //jika koneksi gagal, tampilkan pesan error
            System.err.println("Insert gagal " +e.getMessage());
        }
    }
    //method update pemain yang sudah ada dengan score tertinggi
    public void updatePlayer(int id, int score){
        try{
            //buat koneksi untuk eksekusi query
            connect();
            //buat query update data pemain
            String query = "UPDATE highscore SET "
                     + "Score = '"+ score +"' WHERE id = '"+ id +"'";
            //tampung status eksekusi querynya
            int sukses = stm.executeUpdate(query);
        }catch(Exception e){
            //jika koneksi gagal, tampilkan pesan error
            System.err.println("Update gagal " +e.getMessage());
        }
    }
}
