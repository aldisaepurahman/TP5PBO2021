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
//import class keyadapter, keyevent dan state pada class game
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modulgame.Game.STATE;

/**
 *
 * @author Aldi Saepurahman
 */
//class tombol inputan turunan adapter keyboard
public class KeyInput extends KeyAdapter{
    //buat variabel class handler dan game
    private Handler handler;
    Game game;
    //instansiasi variabel game dan handler dari parameter
    public KeyInput(Handler handler, Game game){
        this.game = game;
        this.handler = handler;
    }
    //method ketika tombol ditekan
    public void keyPressed(KeyEvent e){
        //ambil nilai keyboard yang ditekan
        int key = e.getKeyCode();
        //jika game masih berjalan
        if(game.gameState == STATE.Game){
            //dapatkan data player
            for(int i = 0;i<handler.object.size();i++){
                GameObject tempObject = handler.object.get(i);
                //jika id object adalah player
                if(tempObject.getId() == ID.Player){
                    //jika player pertama
                    if (tempObject.getSpeed() == 1) {
                        //jika menekan tombol W, maka akan bergerak keatas
                        if(key == KeyEvent.VK_W){
                            tempObject.setVel_y(-5);
                        }
                        //jika menekan tombol S, maka akan bergerak kebawah
                        if(key == KeyEvent.VK_S){
                            tempObject.setVel_y(+5);
                        }
                        //jika menekan tombol A, maka akan bergerak kekiri
                        if(key == KeyEvent.VK_A){
                            tempObject.setVel_x(-5);
                        }
                        //jika menekan tombol D, maka akan bergerak kekanan
                        if(key == KeyEvent.VK_D){
                            tempObject.setVel_x(+5);
                        }
                    }
                    //jika player kedua
                    else if (tempObject.getSpeed() == 2) {
                        //jika menekan tombol atas, maka akan bergerak keatas
                        if(key == KeyEvent.VK_UP){
                            tempObject.setVel_y(-5);
                        }
                        //jika menekan tombol bawah, maka akan bergerak kebawah
                        if(key == KeyEvent.VK_DOWN){
                            tempObject.setVel_y(+5);
                        }
                        //jika menekan tombol kiri, maka akan bergerak kekiri
                        if(key == KeyEvent.VK_LEFT){
                            tempObject.setVel_x(-5);
                        }
                        //jika menekan tombol kanan, maka akan bergerak kekanan
                        if(key == KeyEvent.VK_RIGHT){
                            tempObject.setVel_x(+5);
                        }
                    }
                }
            }
            
        }
        //jika game selesai
        if(game.gameState == STATE.GameOver){
            //jika ditekan tombol spasi
            if(key == KeyEvent.VK_SPACE){
                //set score pemain kedalam database
                game.setScore();
                //stop background music
                game.stopSound();
                //masuk ke main menu
                new Menu().setVisible(true);
                //tutup game
                game.close();
            }
        }
        //jika yang ditekan tombol esc, maka keluar dari aplikasi
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }   
    }
    
    
    //method ketika tombol selesai ditekan
    public void keyReleased(KeyEvent e){
        //ambil nilai keyboard yang ditekan
        int key = e.getKeyCode();
        //dapatkan data player
        for(int i = 0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            //jika id object adalah player
            if(tempObject.getId() == ID.Player){
                //jika player pertama
                if (tempObject.getSpeed() == 1) {
                    //jika menekan tombol W, maka akan berhenti bergerak keatas
                    if(key == KeyEvent.VK_W){
                        tempObject.setVel_y(0);
                    }
                    //jika menekan tombol S, maka akan berhenti bergerak kebawah
                    if(key == KeyEvent.VK_S){
                        tempObject.setVel_y(0);
                    }
                    //jika menekan tombol A, maka akan berhenti bergerak kekiri
                    if(key == KeyEvent.VK_A){
                        tempObject.setVel_x(0);
                    }
                    //jika menekan tombol D, maka akan berhenti bergerak kekanan
                    if(key == KeyEvent.VK_D){
                        tempObject.setVel_x(0);
                    }
                }
                //jika player kedua
                else if (tempObject.getSpeed() == 2) {
                    //jika menekan tombol atas, maka akan berhenti bergerak keatas
                    if(key == KeyEvent.VK_UP){
                        tempObject.setVel_y(0);
                    }
                    //jika menekan tombol bawah, maka akan berhenti bergerak kebawah
                    if(key == KeyEvent.VK_DOWN){
                        tempObject.setVel_y(0);
                    }
                    //jika menekan tombol kiri, maka akan berhenti bergerak kekiri
                    if(key == KeyEvent.VK_LEFT){
                        tempObject.setVel_x(0);
                    }
                    //jika menekan tombol kanan, maka akan berhenti bergerak kekanan
                    if(key == KeyEvent.VK_RIGHT){
                        tempObject.setVel_x(0);
                    }
                }
            }
        }
    }
}
