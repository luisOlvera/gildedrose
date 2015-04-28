package gildedrose;

/**
 * Created by luisolvera on 27/04/15.
 */
class GildedRose {



    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality( int days) {
        if (days>0) {
            for (int i = 0; i < items.length; i++) {
                if (!isNameAged_Brie(i)
                        && !is_NameBackstagePasses(i)) {
                    delQuality(i);
                } else {
                    if (is_limitQuality(i)) {
                        items[i].quality = items[i].quality + 1;

                        if (is_NameBackstagePasses(i)) {
                            if (items[i].sellIn < 11) {
                                addQuality(i);
                            }

                            if (items[i].sellIn < 6) {
                                addQuality(i);
                            }
                        }
                    }
                }

                if (!is_NameSulfuras(i)) {
                    items[i].sellIn = items[i].sellIn - 1;
                }

                if (items[i].sellIn < 0) {
                    if (!isNameAged_Brie(i)) {
                        if (!is_NameBackstagePasses(i)) {
                            delQuality(i);
                        } else {
                            items[i].quality = items[i].quality - items[i].quality;
                        }
                    } else {
                        addQuality(i);
                    }
                }
            }
        }
    }

    private void delQuality(int i) {
        if (items[i].quality > 0) {
            if (!is_NameSulfuras(i)) {
                items[i].quality = items[i].quality - 1;
            }
        }
    }

    private void addQuality(int i) {
        if (is_limitQuality(i)) {
            items[i].quality = items[i].quality + 1;
        }
    }

    private boolean is_limitQuality(int i) {
        return items[i].quality < 50;
    }

    private boolean is_NameSulfuras(int i) {
        return items[i].name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean is_NameBackstagePasses(int i) {
        return items[i].name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isNameAged_Brie(int i) {
        return items[i].name.equals("Aged Brie");
    }

}
