package controllers

import models.Collection
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

class CollectionAPITest {
    private var summer: Collection? = null
    private var daizy: Collection? = null
    private var justMe: Collection? = null
    private var feezyRitz: Collection? = null
    private var populatedCollection: CollectionAPI? = CollectionAPI()
    private var emptyCollection: CollectionAPI? = CollectionAPI()

    @BeforeEach
    fun setup() {
        summer = Collection(cname = "Summer", createdBy = "Me", rank = 1)
        daizy = Collection(cname = "Daizy", createdBy = "Charles Dave", rank = 1)
        justMe = Collection(cname = "JustMe", createdBy = "Penelope Wen", rank = 1)
        feezyRitz = Collection(cname = "FeezyRitz", createdBy = "Ritz Dumblemort", rank = 1)

        //adding collections to populatedCollection
        populatedCollection!!.add(summer!!)
        populatedCollection!!.add(daizy!!)
        populatedCollection!!.add(justMe!!)
        populatedCollection!!.add(feezyRitz!!)
    }

    @AfterEach
    fun tearDown() {
        summer = null
        daizy = null
        justMe = null
        feezyRitz = null
        populatedCollection = null
        emptyCollection = null
    }

    @Nested
    inner class AddCollection {
        @Test
        fun `adding a Collection to a populated list adds to the ArrayList`() {
            val newCollection = Collection(cname = "WinterTrend", createdBy = "Me", rank = 1)
            assertEquals(4, populatedCollection!!.numberOfCollections())
            assertTrue(populatedCollection!!.add(newCollection))
            assertEquals(5, populatedCollection!!.numberOfCollections())
            assertEquals(newCollection, populatedCollection!!.findCollection(populatedCollection!!.numberOfCollections() - 1))
        }

        @Test
        fun `adding a Collection to an empty list adds to the ArrayList`() {
            val newCollection = Collection(cname = "WinterTrend", createdBy = "Me", rank = 1)
            assertEquals(0, emptyCollection!!.numberOfCollections())
            assertTrue(emptyCollection!!.add(newCollection))
            assertEquals(1, emptyCollection!!.numberOfCollections())
            assertEquals(newCollection, emptyCollection!!.findCollection(emptyCollection!!.numberOfCollections() - 1))
        }
    }

    @Nested
    inner class ListNotes {
        @Test
        fun `listAllCollections returns No Collections in Store message when ArrayList is empty`() {
            assertEquals(0, emptyCollection!!.numberOfCollections())
            assertTrue(emptyCollection!!.listAllCollections().lowercase().contains("no collections"))
        }

        @Test
        fun `listAllCollections returns Collections when ArrayList is not empty`() {
            assertEquals(4, populatedCollection!!.numberOfCollections())
            val notesString = populatedCollection!!.listAllCollections().lowercase()
            assertTrue(notesString.contains("justme"))
            assertTrue(notesString.contains("summer"))
            assertTrue(notesString.contains("daizy"))
            assertTrue(notesString.contains("feezyritz"))
        }
    }
}