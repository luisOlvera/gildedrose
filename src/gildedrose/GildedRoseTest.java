package gildedrose;

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
        simulePassTime(days);
        assertQuality(18,0);
        assertQuality(5,1);

    }

     private void simulePassTime(int days) {
        app = new GildedRose(items);
        for (int i = 0; i < days; i++) {
            app.updateQuality(i);
            imprimirItems(i);
        }
    }

    private void imprimirItems(int dia) {
        System.out.println("-------- day " + dia + " --------");
        System.out.println("name, sellIn, quality ,fechaVencimiento");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println();
    }

    private void assertQuality(int qualityExpected, int itemToQuality) {
        assertEquals(qualityExpected ,items[itemToQuality].quality);
    }

    @Test
    public void neverNegativeQuality() {
        int days = 6;
        items = new Item[] { new Item("+5 Dexterity Vest", 0, 10),
                new Item("Elixir of the Mongoose", 0, 6)
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
    }

    @Test
    public void addQualityAged_Brie_with_PassTime() {
        int days = 2;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0)
        } ;
        simulePassTime(days);
        assertQuality(9, 0);
        assertQuality(5, 1);
        assertQuality(1, 2);
    }

    @Test
    public void addQualityNeverMore50() {
        int days = 30;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0)
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
        assertQuality(50, 2);
    }

    @Test
    public void sulfuras_neverChange() {
        int days = 30;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
        assertQuality(50, 2);
        assertQuality(80, 3);
        assertSellin(2,3);

    }

    private void assertSellin(int sellInExpected, int itemToCompare) {
        assertEquals(sellInExpected ,items[itemToCompare].sellIn);
    }

    @Test
    public void backstage_AddQuality_1_WhenSellin_More_10() {
        int days = 9;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 18, 1)
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
        assertQuality(14, 2);
        assertQuality(80, 3);
        assertSellin(2,3);
        assertQuality(9, 4);
        assertSellin(10, 4);
    }

    @Test
    public void backstage_AddQuality_2_WhenSellin_Less_11() {
        int days = 10;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 18, 1)
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
        assertQuality(16, 2);
        assertQuality(80, 3);
        assertSellin(2,3);
        assertQuality(11, 4);
        assertSellin(9, 4);
    }

    @Test
    public void backstage_AddQuality_3_WhenSellin_Less_6() {
        int days = 14;
        items = new Item[] { new Item("+5 Dexterity Vest", 2, 10),
                new Item("Elixir of the Mongoose", 2, 6),
                new Item("Aged Brie", 2, 0),
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 18, 1)
        } ;
        simulePassTime(days);
        assertQuality(0, 0);
        assertQuality(0, 1);
        assertQuality(24, 2);
        assertQuality(80, 3);
        assertSellin(2,3);
        assertQuality(19, 4);
        assertSellin(5, 4);
    }
}