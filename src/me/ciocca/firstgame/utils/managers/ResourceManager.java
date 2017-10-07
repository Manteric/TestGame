package me.ciocca.firstgame.utils.managers;

public abstract class ResourceManager {

    protected int count = 1;

    public void addReference(){
        //Ogni volta che viene chiamata una texture aumenta il counter, così sappiamo quante ne dobbiamo cancellare
        count++;
    }
    public boolean removeReference(){
        //Ogni volta che ne toglie una abbassa il counter, arrivato a zero returna
        count--;
        return count == 0;
    }
}
