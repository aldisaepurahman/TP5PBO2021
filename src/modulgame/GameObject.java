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
//import class graphics
import java.awt.Graphics;

/**
 *
 * @author Aldi Saepurahman
 */
//class gameobject sebagai class abstract
public abstract class GameObject {
    //buat variabel x, y, enumerasi id, x dan y velocity, serta speed enemy atau nomor pemain
    protected int x, y;
    protected ID id;
    protected int vel_x;
    protected int vel_y;
    protected int speed;
    //inisialisasi nilai semua atribut, kecuali x dan y velocity
    public GameObject(int x, int y, ID id, int speed){
        this.x = x;
        this.y = y;
        this.id = id;
        this.speed = speed;
    }
    //buat method abstract tick dan render untuk diimplementasikan di childnya
    public abstract void tick();
    public abstract void render(Graphics g);
    //getter dan setter setiap atribut
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVel_x() {
        return vel_x;
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public int getVel_y() {
        return vel_y;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
