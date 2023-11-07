package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GildedRoseTest {

    private GildedRose gildedRose;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        gildedRose = new GildedRose();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testUpdateQualityForNormalItem() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        gildedRose.setItem(item); // Use setItem instead of addItem
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(19, item.getQuality());
    }

    @Test
    public void testUpdateQualityForAgedBrie() {
        Item item = new Item("Aged Brie", 2, 0);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(1, item.getSellIn());
        assertEquals(1, item.getQuality());
    }

    @Test
    public void testUpdateQualityForSulfuras() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(0, item.getSellIn());
        assertEquals(80, item.getQuality());
    }

    @Test
    public void testUpdateQualityForBackstagePasses() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(14, item.getSellIn());
        assertEquals(21, item.getQuality()); 
    }

    @Test
    public void testUpdateQualityForConjuredItem() {
        Item item = new Item("Conjured Mana Cake", 3, 6);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(2, item.getSellIn());
        assertEquals(5, item.getQuality());
    }
    
    
    @Test
    public void testQualityDecreasesNormally() {
        Item item = new Item("Normal Item", 10, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(19, item.getQuality());
    }

    @Test
    public void testQualityDegradesTwiceAsFastAfterSellByDate() {
        Item item = new Item("Expired Item", 0, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(-1, item.getSellIn());
        assertEquals(18, item.getQuality());
    }

    @Test
    public void testQualityNeverNegative() {
        Item item = new Item("Zero Quality Item", 5, 0);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(4, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testQualityNeverExceeds50() {
        Item item = new Item("High Quality Item", 5, 50);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(4, item.getSellIn());
        assertEquals(49, item.getQuality());
    }

    @Test
    public void testAgedBrieQualityIncreases() {
        Item item = new Item("Aged Brie", 2, 0);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(1, item.getSellIn());
        assertEquals(1, item.getQuality());
    }

    @Test
    public void testSulfurasNeverChanges() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(0, item.getSellIn());
        assertEquals(80, item.getQuality());
    }

    @Test
    public void testBackstagePassesQualityIncreases() {
        Item item = new Item("Backstage passes to a Concert", 15, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(14, item.getSellIn());
        assertEquals(19, item.getQuality());
    }

    @Test
    public void testBackstagePassesQualityIncreaseWith10DaysOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(22, item.getQuality());
    }

    @Test
    public void testBackstagePassesQualityIncreaseWith5DaysOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(4, item.getSellIn());
        assertEquals(23, item.getQuality());
    }

    @Test
    public void testBackstagePassesQualityDropsToZeroAfterConcert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(-1, item.getSellIn());
        assertEquals(0, item.getQuality());
    }
    
    @Test
    public void testAgedBrieQualityIncrease() {
        Item item = new Item("Aged Brie", 5, 30);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(4, item.getSellIn());
        assertEquals(31, item.getQuality());
    }

    @Test
    public void testBackstagePassesQualityIncreaseGreaterThan10() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(14, item.getSellIn());
        assertEquals(21, item.getQuality());
    }
    
    @Test
    public void testBackstagePassesQualityIncreaseBetween6And10() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(22, item.getQuality());
    }
    
    @Test
    public void testBackstagePassesQualityIncreaseLessThan6() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(4, item.getSellIn());
        assertEquals(23, item.getQuality());
    }
    
    @Test
    public void testRegularItemQualityDecreasePositiveSellIn() {
        Item item = new Item("Regular Item", 10, 30);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(29, item.getQuality());
    }
    
    @Test
    public void testRegularItemQualityDecreaseNegativeSellIn() {
        Item item = new Item("Regular Item", -1, 30);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(-2, item.getSellIn());
        assertEquals(28, item.getQuality());
    }

    @Test
    public void testRegularItemQualityNeverBelow0() {
        Item item = new Item("Regular Item", 10, 0);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testAgedBrieQualityNeverAbove50() {
        Item item = new Item("Aged Brie", 10, 50);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(9, item.getSellIn());
        assertEquals(50, item.getQuality());
    }

    @Test
    public void testSulfurasItemQualityAndSellInUnchanged() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
        gildedRose.setItem(item);
        gildedRose.oneDay();
        assertEquals(10, item.getSellIn());
        assertEquals(80, item.getQuality());
    }

    @Test
    public void testGetItems() {
        GildedRose gildedRose = new GildedRose();
        Item item1 = new Item("Item 1", 10, 20);
        Item item2 = new Item("Item 2", 5, 30);
        gildedRose.setItem(item1);
        gildedRose.setItem(item2);

        List<Item> items = gildedRose.getItems();

        assertEquals(2, items.size());
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
    }
    
    @Test
    public void testUpdateQualitySetQualityToZero() {
        GildedRose gildedRose = new GildedRose();
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        gildedRose.setItem(backstagePasses);

        gildedRose.oneDay(); // Call the updateQuality method

        assertEquals(0, backstagePasses.getQuality());
    }
    
    @Test
    public void testQualityUpdateForItems() {
        GildedRose gildedRose = new GildedRose();

        // Initialize items
        Item item1 = new Item("+5 Dexterity Vest", 10, 20);
        Item item2 = new Item("Aged Brie", 2, 0);
        Item item3 = new Item("Elixir of the Mongoose", 5, 7);
        Item item4 = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item item5 = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        Item item6 = new Item("Conjured Mana Cake", 3, 6);

        gildedRose.setItem(item1);
        gildedRose.setItem(item2);
        gildedRose.setItem(item3);
        gildedRose.setItem(item4);
        gildedRose.setItem(item5);
        gildedRose.setItem(item6);

        // Update quality using the oneDay method
        gildedRose.oneDay();

        // Check if the quality values have been updated as expected
        assertEquals(19, item1.getQuality());
        assertEquals(1, item2.getQuality());
        assertEquals(6, item3.getQuality());
        assertEquals(80, item4.getQuality()); // Sulfuras quality should remain unchanged
        assertEquals(21, item5.getQuality());
        assertEquals(5, item6.getQuality());
    }

    @Test
    public void testSellInUpdateForItems() {
        GildedRose gildedRose = new GildedRose();

        // Initialize items
        Item item1 = new Item("+5 Dexterity Vest", 10, 20);
        Item item2 = new Item("Aged Brie", 2, 0);
        Item item3 = new Item("Elixir of the Mongoose", 5, 7);
        Item item4 = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item item5 = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        Item item6 = new Item("Conjured Mana Cake", 3, 6);

        gildedRose.setItem(item1);
        gildedRose.setItem(item2);
        gildedRose.setItem(item3);
        gildedRose.setItem(item4);
        gildedRose.setItem(item5);
        gildedRose.setItem(item6);

        // Update the sellIn values using the oneDay method
        gildedRose.oneDay();

        // Check if the sellIn values have been updated as expected
        assertEquals(9, item1.getSellIn());
        assertEquals(1, item2.getSellIn());
        assertEquals(4, item3.getSellIn());
        assertEquals(0, item4.getSellIn()); // Sulfuras sellIn should remain unchanged
        assertEquals(14, item5.getSellIn());
        assertEquals(2, item6.getSellIn());
    }
    @Test
    public void testOMGHAIOutput() {
        GildedRose.main(null); // Call the main method
        String output = outputStream.toString();
        
        // Trim the trailing newline character and compare
        assertEquals("OMGHAI!", output.trim());
    }

    // MUTATIONS
    
    

    

   

    @Test
    public void testBackstagePassesQualityUpdateWithPositiveSellInMutant() {
        GildedRose gildedRose = new GildedRose();
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20);
        gildedRose.setItem(backstagePasses);

        // Apply a mutant that changes the conditional boundary
        backstagePasses.setSellIn(1);

        // Update the quality using the oneDay method
        gildedRose.oneDay();

        // Verify that quality is updated as expected when sellIn is positive
        assertEquals(23, backstagePasses.getQuality());
    }
    
 
    
}
    
    
    
    
