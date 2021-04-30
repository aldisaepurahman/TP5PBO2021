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
//import class color dan graphics
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Aldi Saepurahman
 */
//class items turunan gameobject
public class Items extends GameObject {
    //constructor items memanggil constructor parentnya
    public Items(int x, int y, ID id, int speed){
        super(x, y, id, speed);
    }
    //method menggambar item bergerak (tidak diimplementasikan)
    @Override
    public void tick() {
        
    }
    //method menggambar posisi awal item
    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#f5c542"));
        g.fillRect(x, y, 20, 20);
    }
    
}
