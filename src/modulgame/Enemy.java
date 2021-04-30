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
//class turunan dari gameobject
public class Enemy extends GameObject {
    //constructor enemy dengan memanggil constructor parentnya
    public Enemy(int x, int y, ID id, int speed){
        super(x, y, id, speed);
    }
    //buat tampilan enemy bergerak di layar game
    @Override
    public void tick() {
        x += vel_x;
        
        x = Game.clamp(x, 0, Game.WIDTH - 60);
    }
    //gambar enemynya berwarna merah
    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#ff0000"));
        g.fillRect(x, y, 20, 20);
    }
    
}
