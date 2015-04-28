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
                    addQuality( i);
                }
                del_SellIn(i);
                if (isSellInOut(i)) {
                    if (!isNameAged_Brie(i)) {
                            delQuality(i);
                    } else {
                        addQuality(i);
                    }
                }
            }
        }
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
        int degradeQuality=0;
        if (items[i].quality > 0) {
            if (!is_NameSulfuras(i))
                degradeQuality = 1;
            if (is_NameConjured(i))
                degradeQuality =2;
        }
       if (isSellInOut(i) && is_NameBackstagePasses(i))
                   degradeQuality=items[i].quality;

        items[i].quality -= degradeQuality;
    }

    private void addQuality(int i) {
        int valorQuality=1;
        if (is_DownLimitQuality(i)) {
            if (is_NameBackstagePasses(i))
                valorQuality+= addBonusQualityToBackstage(i);
            items[i].quality = items[i].quality + valorQuality;
        }
    }

    private int addBonusQualityToBackstage(int i) {
        int bonusQuality =0;
        if (items[i].sellIn < 11)
            bonusQuality= 1;
        if (items[i].sellIn < 6)
            bonusQuality= 2;
        return bonusQuality;

    }

    private boolean is_NameConjured(int i) {
        return items[i].name.equals("Conjured Mana Cake");
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
