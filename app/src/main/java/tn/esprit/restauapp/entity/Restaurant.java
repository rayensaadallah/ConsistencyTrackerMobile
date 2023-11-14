package tn.esprit.restauapp.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_restaurant")
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "lieu")
    private String lieu;

    @ColumnInfo(name = "image")
    private String imagePathResto;


    @Override
    public String toString() {
        return "Restaurant{" +
                "uid=" + uid +
                ", Name='" + Name + '\'' +
                ", lieu='" + lieu + '\'' +
                ", imagePathResto='" + imagePathResto + '\'' +

                '}';
    }

    public Restaurant(String name, String lieu, String imagePathResto) {
        Name = name;
        this.lieu = lieu;
        this.imagePathResto = imagePathResto;

    }

    public Restaurant(int uid, String name, String lieu, String imagePathResto) {
        this.uid = uid;
        Name = name;
        this.lieu = lieu;
        this.imagePathResto = imagePathResto;

    }

    public Restaurant() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImagePathResto() {
        return imagePathResto;
    }

    public void setImagePathResto(String imagePathResto) {
        this.imagePathResto = imagePathResto;
    }


}