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
//class player turunan gameobject
public class Player extends GameObject{
    //constructor player yang memanggil constructor parentnya
    public Player(int x, int y, ID id, int noPlayer){
        super(x, y, id, noPlayer);
    }
    //gambar karakter player agar bisa bergerak
    @Override
    public void tick() {
        x += vel_x;
        y += vel_y;
        
        x = Game.clamp(x, 0, Game.WIDTH - 60);
        y = Game.clamp(y, 0, Game.HEIGHT - 80);

    }
    //gambar kondisi awal player
    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#3f6082"));
        g.fillRect(x, y, 50, 50);
    }
}
