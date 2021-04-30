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
/*
import class random, canvas, color, font, graphics, bufferstrategy, ioexception
url, audioinputstream, audiosystem, clip, lineunavailableexception, dan
unsupportedaudiofileexception
*/
import java.util.Random;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Aldi Saepurahman
 */
//class turunan canvas dan mengimplementasikan interface runnable
public class Game extends Canvas implements Runnable{
    //buat window baru dengan ukuran 800x600px
    Window window;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    //buat koneksi baru
    dbConnection dbcon = new dbConnection();
    //buat atribut clip background music
    private Clip clip = null;
    //atribut score pemain
    private int score = 0;
    //atribut waktu permainan
    private int time;
    //atribut penghitung banyak item yang dimakan
    private int count = 0;
    //atribut penghitung lama permainan
    private int waktu = 0;
    //atribut pengecek arah musuh sudah melewati batas atau belum
    private boolean xEnemyAxis = false;
    //atribut difficulty permainan
    private String difficulty;
    //atribut thread
    private Thread thread;
    //atribut pengecek gameloop berjalan atau tidak
    private boolean running = false;
    //atribut handler object
    private Handler handler;
    //atribut username pemain
    private String username;
    //atribut enumerasi permainan
    public enum STATE{
        Game,
        GameOver
    };
    //atribut status awal permainan
    public STATE gameState = STATE.Game;
    //constructor game
    public Game(String username, String difficulty, int idxPlayer){
        //set window baru
        window = new Window(WIDTH, HEIGHT, "Modul praktikum 5", this);
        //set handler object
        handler = new Handler();
        //set username pemain
        this.username = username;
        //set keyboard inputan game
        this.addKeyListener(new KeyInput(handler, this));
        //jika game berjalan
        if(gameState == STATE.Game){
            //gambar 2 item awal
            handler.addObject(new Items(100,150, ID.Item, 0));
            handler.addObject(new Items(200,350, ID.Item, 0));
            //jika level easy, waktu permainan awal 20 menit dan speed musuh sebanyak 3
            if (difficulty.equals("Easy")) {
                this.time = 20;
                handler.addObject(new Enemy(250,300, ID.Enemy, 3));
            }
            //jika level normal, waktu permainan awal 10 menit dan speed musuh sebanyak 5
            else if (difficulty.equals("Normal")) {
                this.time = 10;
                handler.addObject(new Enemy(250,300, ID.Enemy, 5));
            }
            //jika level easy, waktu permainan awal 5 menit dan speed musuh sebanyak 10
            else if (difficulty.equals("Hard")) {
                this.time = 5;
                handler.addObject(new Enemy(250,300, ID.Enemy, 10));
            }
            //jika mode 1 pemain, gambar player pertama
            if (idxPlayer == 0) {
                handler.addObject(new Player(200,200, ID.Player, 1));
            }
            //jika mode 2 pemain, gambar player kedua pula
            else{
                handler.addObject(new Player(200,200, ID.Player, 1));
                handler.addObject(new Player(100,500, ID.Player, 2));
            }
        }
        //tampung difficulty pilihan
        this.difficulty = difficulty;
        //jika masih dimulai, play background music
        if (gameState == STATE.Game) {
            playMusic("/run.wav");
        }
    }
    //implementasikan method start dari thread, dan buat thread serta set game dimulai
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    //implementasikan method stop dari thread, dan stop thread serta set game berakhir
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //implementasi method run dari runnable
    @Override
    public void run() {
        //atur variabel penghitung waktu, delta dan timer permainan, serta frame nya
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //selama game masih berjalan
        while(running){
            //set waktu sekarang
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            //jika deltanya masih lebih dari 1, gambar objectnya
            while(delta >= 1){
                tick();
                delta--;
            }
            //cetak object awal ketika game masih berjalan
            if(running){
                render();
                frames++;
            }
            //cek timernya dan jumlahkan setiap detiknya
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                //jika masih berjalan
                if(gameState == STATE.Game){
                    //jika waktu permainan masih tersisa, maka semakin berkurang
                    if(time>0){
                        time--;
                        //tambahkan lama waktu permainannya
                        waktu++;
                    }else{
                        //jika tidak, maka game over
                        gameState = STATE.GameOver;
                    }
                }
            }
        }
        //stop threadnya
        stop();
    }
    //gambar objectnya dan cek setiap objectnya
    private void tick(){
        handler.tick();
        //jika game masih berjalan
        if(gameState == STATE.Game){
            //set variabel player dan musuh
            GameObject[] playerObject = new GameObject[2];
            GameObject enemyObject = null;
            //cari dalam object handler
            for(int i=0;i< handler.object.size(); i++){
                //jika objectnya player, masukkan kedalam gameobject
                if(handler.object.get(i).getId() == ID.Player){
                    if (handler.object.get(i).getSpeed() == 1) {
                        playerObject[0] = handler.object.get(i);
                    }
                    else if (handler.object.get(i).getSpeed() == 2) {
                        playerObject[1] = handler.object.get(i);
                    }
                }
                //jika musuh, masukkan kedalam gameobject
                else if(handler.object.get(i).getId() == ID.Enemy){
                    enemyObject = handler.object.get(i);
                }
            }
            //jika player pertama ada
            if(playerObject[0] != null){
                //cek itemnya
                for(int i=0;i< handler.object.size(); i++){
                    //jika ada item
                    if(handler.object.get(i).getId() == ID.Item){
                        //jika salahsatu pemain memakan item
                        if(checkCollision(playerObject[0], handler.object.get(i)) ||
                           (playerObject[1] != null &&
                           checkCollision(playerObject[1], handler.object.get(i)))){
                            //mulai backsoundnya
                            playSound("/Eat.wav");
                            //keluarkan item dari object handler
                            handler.removeObject(handler.object.get(i));
                            //buat function random
                            Random rand = new Random();
                            //jika level easy
                            if (this.difficulty.equals("Easy")) {
                                //random score antara 1-5, dan tambahan waktu antara 5-10
                                this.score = this.score + (rand.nextInt(5)+1);
                                this.time = this.time + (rand.nextInt(10)+5);
                            }
                            //jika level normal
                            else if (this.difficulty.equals("Normal")) {
                                //random score antara 5-10, dan tambahan waktu antara 1-5
                                this.score = this.score + (rand.nextInt(10)+5);
                                this.time = this.time + (rand.nextInt(5)+1);
                            }
                            //jika level hard
                            else if (this.difficulty.equals("Hard")) {
                                //random score antara 10-20, dan tambahan waktu antara 1-3
                                this.score = this.score + (rand.nextInt(20)+10);
                                this.time = this.time + (rand.nextInt(3)+1);
                            }
                            //hitung object yang dimakan
                            count++;
                            //jika sudah keduanya dimakan, gambar item baru diposisi random
                            if (count > 1) {
                                int xAxis = rand.nextInt((WIDTH-100)+10);
                                int yAxis = rand.nextInt((HEIGHT-100)+10);
                                handler.addObject(new Items(xAxis, yAxis, ID.Item, 0));
                            }
                            break;
                        }
                    }
                }
                //jika object masih didalam game
                if (enemyObject.getX() <= WIDTH && enemyObject.getX() >= 0) {
                    //jika salahsatu player menyentuh musuh, game over
                    if (checkCollision(playerObject[0], enemyObject) ||
                        (playerObject[1] != null &&
                        checkCollision(playerObject[1], enemyObject))) {
                        gameState = STATE.GameOver;
                    }
                    //jika tidak
                    else{
                        //jika berada di posisi x = 20, tandai harus maju kekanan
                        if (enemyObject.getX() < 20) {
                            this.xEnemyAxis = true;
                        }
                        //jika berada di posisi x = 700, tandai harus maju kekiri
                        else if (enemyObject.getX() > WIDTH-100) {
                            this.xEnemyAxis = false;
                        }
                    }
                }
                //jika harus kekiri, musuh bergerak ke kiri sebanyak speednya
                if (this.xEnemyAxis) {
                    enemyObject.setVel_x(enemyObject.getSpeed());
                }
                //jika harus kenanan, musuh bergerak ke kanan sebanyak speednya
                else{
                    int speed = enemyObject.getSpeed() * -1;
                    enemyObject.setVel_x(speed);
                }
            }
        }
    }
    //fungsi pengecek item atau musuh disentuh pemain
    public static boolean checkCollision(GameObject player, GameObject item){
        //pengecek dinyatakan dengan nilai false
        boolean result = false;
        //ukuran player dan item
        int sizePlayer = 50;
        int sizeItem = 20;
        //mendapatkan posisi player beserta ukurannya
        int playerLeft = player.x;
        int playerRight = player.x + sizePlayer;
        int playerTop = player.y;
        int playerBottom = player.y + sizePlayer;
        //mendapatkan posisi musuh atau item beserta ukurannya
        int itemLeft = item.x;
        int itemRight = item.x + sizeItem;
        int itemTop = item.y;
        int itemBottom = item.y + sizeItem;
        //jika salah satu sisi player atau item bertabrakan, pengecek menjadi true
        if((playerRight > itemLeft ) &&
        (playerLeft < itemRight) &&
        (itemBottom > playerTop) &&
        (itemTop < playerBottom)
        ){
            result = true;
        }
        //kembalikan nilai pengecek
        return result;
    }
    //method render object awal
    private void render(){
        //buat buffer
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        //gambar background canvas
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.decode("#F1f3f3"));
        g.fillRect(0, 0, WIDTH, HEIGHT);
                
        
        //jika game masih berjalan, tampilkan papan skor dan waktu
        if(gameState ==  STATE.Game){
            //render objectnya
            handler.render(g);
            
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            g.setFont(newFont);

            g.setColor(Color.BLACK);
            g.drawString("Score: " +Integer.toString(waktu+score), 20, 20);

            g.setColor(Color.BLACK);
            g.drawString("Time: " +Integer.toString(time), WIDTH-120, 20);
        }
        //jika game berakhir, tampilkan skor akhirnya
        else{
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 3F);
            g.setFont(newFont);

            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", WIDTH/2 - 120, HEIGHT/2 - 30);

            currentFont = g.getFont();
            Font newScoreFont = currentFont.deriveFont(currentFont.getSize() * 0.5F);
            g.setFont(newScoreFont);

            g.setColor(Color.BLACK);
            g.drawString("Score: " +Integer.toString(waktu+score), WIDTH/2 - 50, HEIGHT/2 - 10);
            
            g.setColor(Color.BLACK);
            g.drawString("Press Space to Continue", WIDTH/2 - 100, HEIGHT/2 + 30);
        }
        //buang canvas sebelumnya
        g.dispose();
        bs.show();
    }
    //method set score pemain
    public void setScore(){
        //ambil data pemain dari database
        Object[] dataPlayer = dbcon.checkPlayer(username);
        //jika ditemukan
        if (dataPlayer[0] != null) {
            //jika skor saat ini lebih besar dari di database
            if ((int)dataPlayer[2] < (this.score+this.waktu)) {
                //update skornya
                dbcon.updatePlayer((int)dataPlayer[0], (this.score+this.waktu));                
            }
        }
        //jika tidak ditemukan, insert sebagai data baru
        else{            
            dbcon.insertPlayer(this.username, (this.score+this.waktu));
        }
    }
    //method untuk object yang bergerak
    public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{
            return var;
        }
    }
    //method menutup game
    public void close(){
        window.CloseWindow();
    }
    //method menyalakan backsound
    public void playSound(String filename){
        try {
            // Open an audio input stream.
            URL url = this.getClass().getResource(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    //method menyalakan background music
    public void playMusic(String filename){
        try {
            // Open an audio input stream.
            URL url = this.getClass().getResource(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //loop berulang-ulang
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
    }
    //method ketika background music dimatikan
    public void stopSound(){
        System.out.println("stop");
        //stop dan tutup musicnya
        clip.stop();
        clip.close();
        clip = null;
    }
}
