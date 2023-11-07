package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {
    private static List<Item> items = new ArrayList<Item>();

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6)); 

        updateQuality();
    }

    public static void updateQuality() {
        for (Item item : items) {
            if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
                item.setSellIn(item.getSellIn() - 1);

                if (item.getName().equals("Aged Brie")) {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                } else if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    // Update the sellIn value first
                    if (item.getSellIn() < 0) {
                        item.setQuality(0); // Quality drops to 0 after the concert
                    } else {
                        if (item.getQuality() < 50) {
                            item.setQuality(item.getQuality() + 1);

                            if (item.getSellIn() <= 10) {
                                item.setQuality(item.getQuality() + 1);
                            }

                            if (item.getSellIn() <= 5) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }
                    }
                } else {
                    if (item.getQuality() > 0) {
                        item.setQuality(item.getQuality() - 1);

                        if (item.getSellIn() < 0) {
                            item.setQuality(item.getQuality() - 1);
                        }
                    }

                    if (item.getQuality() < 0) {
                        item.setQuality(0);
                    }
                }
            }
        }
    }

    // Constructor
    public GildedRose() {
        items = new ArrayList<Item>();
    }

    // Getter
    public List<Item> getItems() {
        return items;
    }

    // Setter
    public void setItem(Item item) {
        items.add(item);
    }

    // Update one day
    public void oneDay() {
        updateQuality();
    }
}
