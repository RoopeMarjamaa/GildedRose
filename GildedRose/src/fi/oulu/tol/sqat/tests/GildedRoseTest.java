package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;
import java.util.ArrayList;



public class GildedRoseTest {

	 @Test
	    public void testTheTruth() {
	        assertTrue(true);
	    }

	    @Test
	    public void testAgedBrieQualityIncreasesWithAge() {
	        GildedRose gildedRose = new GildedRose();
	        Item agedBrie = new Item("Aged Brie", 10, 20);
	        gildedRose.setItem(agedBrie);
	        gildedRose.oneDay(); // Simulate one day passing

	        assertEquals(21, agedBrie.getQuality());
	    }

	    @Test
	    public void testSulfurasNeverChanges() {
	        GildedRose gildedRose = new GildedRose();
	        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
	        gildedRose.setItem(sulfuras);

	        for (int i = 0; i < 10; i++) {
	            gildedRose.oneDay();
	            assertEquals(80, sulfuras.getQuality());
	            assertEquals(0, sulfuras.getSellIn());
	        }
	    }

	    @Test
	    public void testBackstagePassesQualityIncreasesProperly() {
	        GildedRose gildedRose = new GildedRose();
	        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
	        gildedRose.setItem(backstagePasses);

	        gildedRose.oneDay(); // Quality increases by 1
	        assertEquals(21, backstagePasses.getQuality());

	        backstagePasses.setSellIn(10);
	        gildedRose.oneDay(); // Quality increases by 2
	        assertEquals(23, backstagePasses.getQuality());

	        backstagePasses.setSellIn(5);
	        gildedRose.oneDay(); // Quality increases by 3
	        assertEquals(26, backstagePasses.getQuality());

	        backstagePasses.setSellIn(0);
	        gildedRose.oneDay(); // Quality drops to 0
	        assertEquals(0, backstagePasses.getQuality());
	    }

	    @Test
	    public void testQualityNeverNegative() {
	        GildedRose gildedRose = new GildedRose();
	        Item item = new Item("Test Item", 0, 1);
	        gildedRose.setItem(item);

	        gildedRose.oneDay(); // Quality should not go negative
	        assertEquals(0, item.getQuality());

	        gildedRose.oneDay(); // Quality should still not go negative
	        assertEquals(0, item.getQuality());
	    }

	    @Test
	    public void testQualityNeverExceeds50() {
	        GildedRose gildedRose = new GildedRose();
	        Item item = new Item("Test Item", 10, 50);
	        gildedRose.setItem(item);

	        gildedRose.oneDay(); // Quality should not exceed 50
	        assertEquals(50, item.getQuality());
	    }

	    @Test
	    public void testDexterityVestQualityDecreasesNormally() {
	        GildedRose gildedRose = new GildedRose();
	        Item dexterityVest = new Item("+5 Dexterity Vest", 5, 10);
	        gildedRose.setItem(dexterityVest);

	        gildedRose.oneDay(); // Quality decreases by 1
	        assertEquals(9, dexterityVest.getQuality());

	        gildedRose.oneDay(); // Quality decreases by 1
	        assertEquals(8, dexterityVest.getQuality());
	    }

	    @Test
	    public void testConjuredManaCakeQualityDecreasesTwiceAsFast() {
	        GildedRose gildedRose =  new GildedRose();
	        Item conjuredManaCake = new Item("Conjured Mana Cake", 5, 10);
	        gildedRose.setItem(conjuredManaCake);

	        gildedRose.oneDay(); // Quality decreases by 2
	        assertEquals(8, conjuredManaCake.getQuality());

	        gildedRose.oneDay(); // Quality decreases by 2
	        assertEquals(6, conjuredManaCake.getQuality());

	        conjuredManaCake.setSellIn(0);
	        gildedRose.oneDay(); // Quality decreases by 2 after sellIn date passes
	        assertEquals(4, conjuredManaCake.getQuality());
	    }

	    @Test
	    public void testUpdateQualityNormalItemQualityDecreases() {
	        GildedRose gildedRose = new GildedRose();
	        Item normalItem = new Item("+5 Dexterity Vest", 5, 10);
	        gildedRose.setItem(normalItem);

	        gildedRose.oneDay(); // Normal item, quality decreases

	        assertEquals(9, normalItem.getQuality());
	    }

	    @Test
	    public void testUpdateQualityNormalItemQualityNeverNegative() {
	        GildedRose gildedRose = new GildedRose();
	        Item normalItem = new Item("+5 Dexterity Vest", 5, 0);
	        gildedRose.setItem(normalItem);

	        gildedRose.oneDay(); // Normal item, quality never goes negative

	        assertEquals(0, normalItem.getQuality());
	    }

	    @Test
	    public void testUpdateQualityBackstagePassesQualityIncreases() {
	        GildedRose gildedRose = new GildedRose();
	        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
	        gildedRose.setItem(backstagePasses);

	        gildedRose.oneDay(); // Backstage passes, quality increases

	        assertEquals(21, backstagePasses.getQuality());
	    }

	    @Test
	    public void testUpdateQualitySulfurasQualityNeverChanges() {
	        GildedRose gildedRose = new GildedRose();
	        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
	        gildedRose.setItem(sulfuras);

	        gildedRose.oneDay(); // Sulfuras, quality never changes

	        assertEquals(80, sulfuras.getQuality()); }
	    

	    
	   
		
		
		
		 @Test
		    public void testItemsInitializationAndQualityUpdate() {
		        GildedRose gildedRose = new GildedRose();

		        // Initialize the items
		        List<Item> items = new ArrayList<Item>();
		        items.add(new Item("+5 Dexterity Vest", 10, 20));
		        items.add(new Item("Aged Brie", 2, 0));
		        items.add(new Item("Elixir of the Mongoose", 5, 7));
		        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		        items.add(new Item("Conjured Mana Cake", 3, 6));

		        // Set the items in the GildedRose
		        for (Item item : items) {
		            gildedRose.setItem(item);
		        }

		        // Perform one day's update
		        gildedRose.oneDay();

		        // Assert the updated quality of items
		        assertEquals(19, items.get(0).getQuality()); // Check +5 Dexterity Vest
		        assertEquals(1, items.get(1).getQuality());  // Check Aged Brie
		        assertEquals(6, items.get(2).getQuality());  // Check Elixir of the Mongoose
		        assertEquals(80, items.get(3).getQuality()); // Check Sulfuras
		        assertEquals(21, items.get(4).getQuality()); // Check Backstage passes
		        assertEquals(5, items.get(5).getQuality());  // Check Conjured Mana Cake
		    }
		
		
		 
		 
		 @Test
		    public void testQualityIncreaseWhenBelow50() {
		        GildedRose gildedRose = new GildedRose();

		        // Create an item with quality below 50
		        Item item = new Item("Test Item", 5, 40);
		        gildedRose.setItem(item);

		        // Perform one day's update
		        gildedRose.oneDay();

		        // Assert that the quality increased by 1
		        assertEquals(41, item.getQuality());
		    }
		
		

		
		
		@Test
		public void testUpdateQualityMethod() {
		    GildedRose gildedRose = new GildedRose();
		    Item item = new Item("Test Item", 5, 10);
		    gildedRose.setItem(item);

		    gildedRose.updateQuality(); // Call the non-static updateQuality method

		    assertEquals(9, item.getQuality());
		}

		@Test
		public void testOneDayMethod() {
		    GildedRose gildedRose = new GildedRose();
		    Item item = new Item("Test Item", 5, 10);
		    gildedRose.setItem(item);

		    gildedRose.oneDay(); // Ensure that the oneDay method works correctly

		    assertEquals(9, item.getQuality());
		}
		
		
		
		
		
		@Test
		public void exampleTest() {
			//create an inn, add an item, and simulate one day
			GildedRose inn = new GildedRose();
			inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
			inn.oneDay();
			
			//access a list of items, get the quality of the one set
			List<Item> items = inn.getItems();
			int quality = items.get(0).getQuality();
			
			//assert quality has decreased by one
			assertEquals("Failed quality for Dexterity Vest", 19, quality);
		}
	    
	    
		
		
		

		
		
	    
	    
	    @Test
	    public void testNegativeSellInValue() {
	        GildedRose gildedRose = new GildedRose();
	        Item item = new Item("Test Item", -1, 10);
	        gildedRose.setItem(item);

	        gildedRose.oneDay(); // Quality decreases as usual
	        assertEquals(9, item.getQuality());

	        gildedRose.oneDay(); // }
	    }
	    
	    
	    
	    
	    // LOOP TESTS
	    
	    @Test
	    public void testUpdateQualityConjuredItemQualityDecreasesTwiceAsFast() {
	        GildedRose gildedRose = new GildedRose();
	        Item conjuredItem = new Item("Conjured Mana Cake", 5, 10);
	        gildedRose.setItem(conjuredItem);

	        gildedRose.oneDay(); // Conjured item, quality decreases by 2

	        assertEquals(8, conjuredItem.getQuality());
	    }   
	   
	    @Test
	    public void testUpdateQualityConjuredItemQualityNeverNegative() {
	        GildedRose gildedRose = new GildedRose();
	        Item conjuredItem = new Item("Conjured Mana Cake", 5, 1);
	        gildedRose.setItem(conjuredItem);

	        gildedRose.oneDay(); // Conjured item, quality never goes negative

	        assertEquals(0, conjuredItem.getQuality());
	    }
	    
	    @Test
	    public void testUpdateQualityBackstagePassesQualityNeverExceeds50() {
	        GildedRose gildedRose = new GildedRose();
	        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
	        gildedRose.setItem(backstagePasses);

	        gildedRose.oneDay(); // Backstage passes, quality should not exceed 50

	        assertEquals(50, backstagePasses.getQuality());
	    }

	    @Test
	    public void testUpdateQualityAgedBrieQualityNeverExceeds50() {
	        GildedRose gildedRose = new GildedRose();
	        Item agedBrie = new Item("Aged Brie", 5, 50);
	        gildedRose.setItem(agedBrie);

	        gildedRose.oneDay(); // Aged Brie, quality should not exceed 50

	        assertEquals(50, agedBrie.getQuality());
	    }
	    
	    
	    
	    @Test
	    public void testUpdateQualityAgedBrieQualityIncreases() {
	        GildedRose gildedRose = new GildedRose();
	        Item agedBrie = new Item("Aged Brie", 5, 10);
	        gildedRose.setItem(agedBrie);

	        gildedRose.oneDay(); // Aged Brie, quality increases

	        assertEquals(11, agedBrie.getQuality());
	    }
	    
	    
	    
	    
	    
}
	        



/* ALKUPERAISET TESTIT package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;
import java.util.ArrayList;



public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	
	@Test
    public void testAgedBrieQualityIncreasesWithAge() {
        GildedRose gildedRose = new GildedRose();
        Item agedBrie = new Item("Aged Brie", 10, 20);
        gildedRose.setItem(agedBrie);
        gildedRose.oneDay(); // Simulate one day passing

        assertEquals(21, agedBrie.getQuality());
    }

	
	@Test
	public void testSulfurasNeverChanges() {
	    GildedRose gildedRose = new GildedRose();
	    Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
	    gildedRose.setItem(sulfuras);
	    
	    for (int i = 0; i < 10; i++) {
	        gildedRose.oneDay();
	        assertEquals(80, sulfuras.getQuality());
	        assertEquals(0, sulfuras.getSellIn());
	    }
	}
	
	
	
	@Test
	public void testBackstagePassesQualityIncreasesProperly() {
	    GildedRose gildedRose = new GildedRose();
	    Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
	    gildedRose.setItem(backstagePasses);
	    
	    gildedRose.oneDay(); // Quality increases by 1
	    assertEquals(21, backstagePasses.getQuality());

	    backstagePasses.setSellIn(10);
	    gildedRose.oneDay(); // Quality increases by 2
	    assertEquals(23, backstagePasses.getQuality());

	    backstagePasses.setSellIn(5);
	    gildedRose.oneDay(); // Quality increases by 3
	    assertEquals(26, backstagePasses.getQuality());

	    backstagePasses.setSellIn(0);
	    gildedRose.oneDay(); // Quality drops to 0
	    assertEquals(0, backstagePasses.getQuality());
	}
	
	
	
	@Test
	public void testQualityNeverNegative() {
	    GildedRose gildedRose = new GildedRose();
	    Item item = new Item("Test Item", 0, 1);
	    gildedRose.setItem(item);

	    gildedRose.oneDay(); // Quality should not go negative
	    assertEquals(0, item.getQuality());

	    gildedRose.oneDay(); // Quality should still not go negative
	    assertEquals(0, item.getQuality());
	}
	
	
	@Test
	public void testQualityNeverExceeds50() {
	    GildedRose gildedRose = new GildedRose();
	    Item item = new Item("Test Item", 10, 50);
	    gildedRose.setItem(item);

	    gildedRose.oneDay(); // Quality should not exceed 50
	    assertEquals(50, item.getQuality());
	}
	
	
	@Test
	public void testDexterityVestQualityDecreasesNormally() {
	    GildedRose gildedRose = new GildedRose();
	    Item dexterityVest = new Item("+5 Dexterity Vest", 5, 10);
	    gildedRose.setItem(dexterityVest);

	    gildedRose.oneDay(); // Quality decreases by 1
	    assertEquals(9, dexterityVest.getQuality());

	    gildedRose.oneDay(); // Quality decreases by 1
	    assertEquals(8, dexterityVest.getQuality());
	}
	
	
	
	@Test
	public void testConjuredManaCakeQualityDecreasesTwiceAsFast() {
	    GildedRose gildedRose = new GildedRose();
	    Item conjuredManaCake = new Item("Conjured Mana Cake", 5, 10);
	    gildedRose.setItem(conjuredManaCake);

	    gildedRose.oneDay(); // Quality decreases by 2
	    assertEquals(8, conjuredManaCake.getQuality());

	    gildedRose.oneDay(); // Quality decreases by 2
	    assertEquals(6, conjuredManaCake.getQuality());

	    conjuredManaCake.setSellIn(0);
	    gildedRose.oneDay(); // Quality decreases by 2 after sellIn date passes
	    assertEquals(4, conjuredManaCake.getQuality());
	}
	
	
	
	@Test
    public void testUpdateQualityNormalItemQualityDecreases() {
        GildedRose gildedRose = new GildedRose();
        Item normalItem = new Item("Normal Item", 5, 10);
        gildedRose.setItem(normalItem);

        gildedRose.updateQuality(); // Normal item, quality decreases

        assertEquals(9, normalItem.getQuality());
    }
	
	
	
	
	@Test
    public void testUpdateQualityNormalItemQualityNeverNegative() {
        GildedRose gildedRose = new GildedRose();
        Item normalItem = new Item("Normal Item", 5, 0);
        gildedRose.setItem(normalItem);

        gildedRose.updateQuality(); // Normal item, quality never goes negative

        assertEquals(0, normalItem.getQuality());
    }
	
	
	
	
	@Test
    public void testUpdateQualityBackstagePassesQualityIncreases() {
        GildedRose gildedRose = new GildedRose();
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        gildedRose.setItem(backstagePasses);

        gildedRose.updateQuality(); // Backstage passes, quality increases

        assertEquals(21, backstagePasses.getQuality());
    }
	
	
	
	@Test
    public void testUpdateQualitySulfurasQualityNeverChanges() {
        GildedRose gildedRose = new GildedRose();
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        gildedRose.setItem(sulfuras);

        gildedRose.updateQuality(); // Sulfuras, quality never changes

        assertEquals(80, sulfuras.getQuality());
    }
	
	
	
	
	@Test
	public void testNegativeSellInValue() {
	    GildedRose gildedRose = new GildedRose();
	    Item item = new Item("Test Item", -1, 10);
	    gildedRose.setItem(item);

	    gildedRose.oneDay(); // Quality decreases as usual
	    assertEquals(9, item.getQuality());

	    gildedRose.oneDay(); // Quality decreases as usual
	    assertEquals(8, item.getQuality());
	}
	
	
	
	
	 @Test
	    public void testItemsInitializationAndQualityUpdate() {
	        GildedRose gildedRose = new GildedRose();

	        // Initialize the items
	        List<Item> items = new ArrayList<Item>();
	        items.add(new Item("+5 Dexterity Vest", 10, 20));
	        items.add(new Item("Aged Brie", 2, 0));
	        items.add(new Item("Elixir of the Mongoose", 5, 7));
	        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
	        items.add(new Item("Conjured Mana Cake", 3, 6));

	        // Set the items in the GildedRose
	        for (Item item : items) {
	            gildedRose.setItem(item);
	        }

	        // Perform one day's update
	        gildedRose.oneDay();

	        // Assert the updated quality of items
	        assertEquals(19, items.get(0).getQuality()); // Check +5 Dexterity Vest
	        assertEquals(1, items.get(1).getQuality());  // Check Aged Brie
	        assertEquals(6, items.get(2).getQuality());  // Check Elixir of the Mongoose
	        assertEquals(80, items.get(3).getQuality()); // Check Sulfuras
	        assertEquals(21, items.get(4).getQuality()); // Check Backstage passes
	        assertEquals(5, items.get(5).getQuality());  // Check Conjured Mana Cake
	    }
	
	
	 
	 
	 @Test
	    public void testQualityIncreaseWhenBelow50() {
	        GildedRose gildedRose = new GildedRose();

	        // Create an item with quality below 50
	        Item item = new Item("Test Item", 5, 40);
	        gildedRose.setItem(item);

	        // Perform one day's update
	        gildedRose.oneDay();

	        // Assert that the quality increased by 1
	        assertEquals(41, item.getQuality());
	    }
	
	
	
	
	
	
	
	@Test
	public void testUpdateQualityMethod() {
	    GildedRose gildedRose = new GildedRose();
	    Item item = new Item("Test Item", 5, 10);
	    gildedRose.setItem(item);

	    gildedRose.updateQuality(); // Call the non-static updateQuality method

	    assertEquals(9, item.getQuality());
	}

	@Test
	public void testOneDayMethod() {
	    GildedRose gildedRose = new GildedRose();
	    Item item = new Item("Test Item", 5, 10);
	    gildedRose.setItem(item);

	    gildedRose.oneDay(); // Ensure that the oneDay method works correctly

	    assertEquals(9, item.getQuality());
	}
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
}



 */
	         
