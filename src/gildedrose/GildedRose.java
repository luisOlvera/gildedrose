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
                    if (is_DownLimitQuality(i)) {
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

                del_SellIn(i);

                if (isSellInOut(i)) {
                    if (!isNameAged_Brie(i)) {
                        if (!is_NameBackstagePasses(i)) {
                            delQuality(i);
                        } else {
                            qualityConvert0(i);
                        }
                    } else {
                        addQuality(i);
                    }
                }
            }
        }
    }

    private void qualityConvert0(int i) {
        items[i].quality = 0;
    }

    private boolean isSellInOut(int i) {
        return items[i].sellIn < 0;
    }

    private void del_SellIn(int i) {
        if (!is_NameSulfuras(i)) {
            items[i].sellIn = items[i].sellIn - 1;
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
        if (is_DownLimitQuality(i)) {
            items[i].quality = items[i].quality + 1;

        }
    }

    private boolean is_DownLimitQuality(int i) {
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
