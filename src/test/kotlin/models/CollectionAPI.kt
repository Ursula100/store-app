package models

import controllers.CollectionAPI
import org.junit.jupiter.api.*

class CollectionAPI {
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
}