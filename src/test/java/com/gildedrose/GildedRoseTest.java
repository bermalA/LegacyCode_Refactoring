package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item createAndUpdate(int sellIn, int quality, String name) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    @Test
    void testFrameworkWorks() {
        Item item = createAndUpdate(0,0, "foo");
        assertEquals("foo", item.name);
    }


    @Test
    public void systemLowerValue () {
       Item item = createAndUpdate(15,25, "foo");
        assertEquals(14, item.sellIn);
        assertEquals(24, item.quality);
    }

    @Test
    public void qualityDegradesTwiceFast(){
        Item item = createAndUpdate(0,17, "foo");
        assertEquals(15, item.quality);
    }

    @Test
    public void qualityNeverNegative (){
        Item item = createAndUpdate(0,0, "foo");
        assertEquals(0, item.quality);
    }

    @Test
    public void agedBrieIncreasesQuality(){
        Item item = createAndUpdate(15,25, "Aged Brie");
        assertEquals(26, item.quality);
    }

    @Test
    public void qualityNever50 () {
        Item item = createAndUpdate(15,52, "foo");
        assertEquals(51, item.quality);
        Item brie = createAndUpdate(0,50, "Aged Brie");
        assertEquals(50, brie.quality);
        Item BSPass = createAndUpdate(0,48,"Backstage passes to a TAFKAL80ETC concert");
        assertEquals(50, BSPass.quality);
        item = createAndUpdate(1,49, "Backstage passes to a TAFKAL80ETC concert");
        assertEquals(50, item.quality);
    }

    @Test
    public void SulfurasTheLegendary(){
        Item item = createAndUpdate(1,25, "Sulfuras, Hand of Ragnaros");
        assertEquals(1, item.sellIn);
        assertEquals(25, item.quality);
    }

    @Test
    public void BackstagePassesIncreaseQuality(){
        Item item = createAndUpdate(15,25, "Backstage passes to a TAFKAL80ETTE");
        assertEquals(26, item.quality);
    }

    @Test
    public void BackstagePassesIncreaseQuality10Days(){
        Item item = createAndUpdate(10,25, "Backstage passes to a TAFKAL80ETTE");
        assertEquals(27, item.quality);
    }

    @Test
    public void BackstagePassesIncreaseQuality5Days(){
        Item item = createAndUpdate(15,25, "Backstage passes to a TAFKAL80ETTE");
        assertEquals(28, item.quality);
    }

    @Test
    public void BackstagePassesQualityOver(){
        Item item = createAndUpdate(0,25,"Backstage passes to a TAFKAL80ETTE");
        assertEquals(0, item.quality);
    }

    @Test
    public void BrieNeverExpires (){
        Item item = createAndUpdate(0,25,"Aged Brie");
        assertEquals(-1, item.sellIn);
        assertEquals(27, item.quality);
    }
}
