package gildedrose;

import java.util.Date;

/**
 * Created by luisolvera on 27/04/15.
 */
public class Item {


    public String name;

    public int sellIn;

    public int quality;


    public Item(String name, int sellIn, int quality ) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;

    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality ;
    }
}
