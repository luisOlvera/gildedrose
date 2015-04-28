package gildedrose;

import org.junit.Ignore;
import org.junit.Test;



import static org.junit.Assert.*;

public class GildedRoseTest {


    Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 0, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 0, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),

            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

    GildedRose app = null;

    @Test
    public void fechaCaducidadVencida_delQuality2(){
        int days = 2;
        items = new Item[] { new Item("+5 Dexterity Vest", 0, 20),
                new Item("Elixir of the Mongoose", 0, 7)
        }  ;
         app = new GildedRose(items);
        for (int i = 0; i < days; i++) {
            app.updateQuality(i);
            imprimirItems(i);
        }
        assertEquals(18 ,items[0].quality);
        assertEquals(5 ,items[1].quality);
    }

    private void imprimirItems( int dia) {
        System.out.println("-------- day " + dia + " --------");
        System.out.println("name, sellIn, quality ,fechaVencimiento");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }

    @Test
    public void neverNegativeQuality() {
        int days = 6;
        items = new Item[] { new Item("+5 Dexterity Vest", 0, 10),
                new Item("Elixir of the Mongoose", 0, 6)
        }  ;
        app = new GildedRose(items);
        for (int i = 0; i < days; i++) {
            app.updateQuality(i);
            imprimirItems(i);
        }
        assertEquals(0 ,items[0].quality);
        assertEquals(0 ,items[1].quality);
    }

}