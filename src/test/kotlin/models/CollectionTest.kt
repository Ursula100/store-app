package models

import org.junit.jupiter.api.*

class CollectionTest {
    private var emptyC: Collection? = null
    private var populatedC: Collection? = null
    private var item1: Item? = null
    private var item2: Item? = null

    @BeforeEach
    fun setup() {
        populatedC = Collection(cname = "Active", createdBy = "Me", rank = 1)
        emptyC = Collection(cname = "Street wear", createdBy = "Charles Dave", rank = 1)
        item1 = Item(iName = "shoes", iDesc = "running shoes", material = "mixed", category = "Men", price = 150.00)
        item2 = Item(iName = "trouser", iDesc = "cargo trouser", material = "mixed", category = "Unisex", price = 45.00)

        populatedC!!.addItem(item1!!)
        populatedC!!.addItem(item2!!)

    }

    @AfterEach
    fun tearDown() {
        populatedC = null
        emptyC = null
        item1 = null
        item2 = null
    }

    @Nested
    inner class AddItem {
        @Test
        fun `adding an Item to a an empty Collection adds to the Collection`() {
            val newItem = Item(iName = "T-shirt", iDesc = "Round neck", material = "cotton", category = "Unisex", price = 15.00)
            Assertions.assertEquals(0, emptyC!!.numberOfItems())
            Assertions.assertTrue(emptyC!!.addItem(newItem))
            Assertions.assertEquals(1, emptyC!!.numberOfItems())
            Assertions.assertEquals(newItem, emptyC!!.findItem(emptyC!!.numberOfItems() - 1))
        }

        @Test
        fun `adding an item to a non-empty Collection adds to the Collection`() {
            val newItem = Item(iName = "T-shirt", iDesc = "Round neck", material = "cotton", category = "Unisex", price = 15.00)
            Assertions.assertEquals(2, populatedC!!.numberOfItems())
            Assertions.assertTrue(populatedC!!.addItem(newItem))
            Assertions.assertEquals(3, populatedC!!.numberOfItems())
            Assertions.assertEquals(newItem, populatedC!!.findItem(populatedC!!.numberOfItems() - 1))
        }
    }

    @Nested
    inner class DeleteItem {
        @Test
        fun `deleting a item that does not exist from collection, returns false`() {
            Assertions.assertEquals(false, emptyC!!.deleteItem(2000))
            Assertions.assertEquals(false, emptyC!!.deleteItem(1999))
            Assertions.assertEquals(false, populatedC!!.deleteItem(1999))
            Assertions.assertEquals(false, populatedC!!.deleteItem(3000))
        }

        @Test
        fun `deleting a collection that exists delete and returns true`() {
            Assertions.assertEquals(2, populatedC!!.numberOfItems())
            Assertions.assertEquals(true, populatedC!!.deleteItem(1))
            Assertions.assertEquals(1, populatedC!!.numberOfItems())
            Assertions.assertEquals(true, populatedC!!.deleteItem(2))
            Assertions.assertEquals(0, populatedC!!.numberOfItems())
        }
    }

    @Nested
    inner class ListItems {
        @Test
        fun `listItems returns No items in Collection message when ArrayList is empty`() {
            Assertions.assertEquals(0, emptyC!!.numberOfItems())
            Assertions.assertTrue(emptyC!!.listItems().lowercase().contains("no items in collection"))
        }

        @Test
        fun `listItems returns items when ArrayList is not empty`() {
            Assertions.assertEquals(5, populatedC!!.numberOfItems())
            val items = populatedC!!.listItems().lowercase()
            Assertions.assertTrue(items.contains("shoes"))
            Assertions.assertTrue(items.contains("trouser"))
        }
    }
}