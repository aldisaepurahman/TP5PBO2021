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
//import class canvas, dimension dan jframe
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Aldi Saepurahman
 */
public class Window extends Canvas {
    //buat frame baru dengan mendeclare variabel frame
    JFrame frame;
    //frame baru untuk menampilkan game
    public Window(int width, int height, String title, Game game){
        frame = new JFrame(title);
        //atur ukuran frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
    //frame ketika game ditutup
    public void CloseWindow(){
        frame.setVisible(false);
    }
}
