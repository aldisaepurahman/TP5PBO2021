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
//import class graphics dan linkedlist
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Aldi Saepurahman
 */
public class Handler {
    //deklarasi variabel list gameobject
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    //method untuk menggambar ulang object
    public void tick(){
        for(int i=0;i<object.size(); i++){
            GameObject tempObject = object.get(i);
            //panggil method tick dari gameobject
            tempObject.tick();
        }
    }
    //method untuk menggambar object awal
    public void render(Graphics g){
        for(int i=0;i<object.size(); i++){
            GameObject tempObject = object.get(i);
            //panggil method render dari gameobject
            tempObject.render(g);
        }
    }
    //method menambah object baru
    public void addObject(GameObject object){
        this.object.add(object);
    }
    //method mengurangi object yang diminta
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
