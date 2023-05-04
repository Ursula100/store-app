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
    private var autumn: Collection? = null
    private var feezyRitz: Collection? = null
    private var populatedCollection: CollectionAPI? = CollectionAPI()
    private var emptyCollection: CollectionAPI? = CollectionAPI()

    @BeforeEach
    fun setup() {
        summer = Collection(cname = "Summer", createdBy = "Me", rank = 1)
        autumn = Collection(cname = "Autumn", createdBy = "Me", rank = 1)
        daizy = Collection(cname = "Daizy", createdBy = "Charles Dave", rank = 1)
        justMe = Collection(cname = "JustMe", createdBy = "Penelope Wen", rank = 1)
        feezyRitz = Collection(cname = "FeezyRitz", createdBy = "Ritz Dumblemort", rank = 1)

        //adding collections to populatedCollection
        populatedCollection!!.add(summer!!)
        populatedCollection!!.add(daizy!!)
        populatedCollection!!.add(justMe!!)
        populatedCollection!!.add(feezyRitz!!)
        populatedCollection!!.add(autumn!!)
    }

    @AfterEach
    fun tearDown() {
        summer = null
        daizy = null
        justMe = null
        feezyRitz = null
        populatedCollection = null
        emptyCollection = null
        autumn = null
    }

    @Nested
    inner class AddCollection {
        @Test
        fun `adding a Collection to a populated list adds to the ArrayList`() {
            val newCollection = Collection(cname = "WinterTrend", createdBy = "Me", rank = 1)
            assertEquals(5, populatedCollection!!.numberOfCollections())
            assertTrue(populatedCollection!!.add(newCollection))
            assertEquals(6, populatedCollection!!.numberOfCollections())
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
    inner class ListCollections {
        @Test
        fun `listAllCollections returns No Collections in Store message when ArrayList is empty`() {
            assertEquals(0, emptyCollection!!.numberOfCollections())
            assertTrue(emptyCollection!!.listAllCollections().lowercase().contains("no collections"))
        }

        @Test
        fun `listAllCollections returns Collections when ArrayList is not empty`() {
            assertEquals(5, populatedCollection!!.numberOfCollections())
            val collections = populatedCollection!!.listAllCollections().lowercase()
            assertTrue(collections.contains("justme"))
            assertTrue(collections.contains("summer"))
            assertTrue(collections.contains("daizy"))
            assertTrue(collections.contains("feezyritz"))
            assertTrue(collections.contains("autumn"))
        }
    }
    @Nested
    inner class ListCollectionsByCreatedBy {
        @Test
        fun `listCollectionsByCreatedBy returns No Collections in Store when ArrayList has no collections stored` (){
            assertEquals(0, emptyCollection!!.numberOfCollectionsBy("Nike"))
            assertTrue(emptyCollection!!.listCollectionsCreatedBy("Nike").lowercase() == "no collections in store")
        }

        @Test
        fun `listCollectionsByCreatedBy returns Currently No Collections from $designer when populated ArrayList has no collections by the specified designer stored`(){
            assertEquals(0, populatedCollection!!.numberOfCollectionsBy("Nike"))
            assertTrue(populatedCollection!!.listCollectionsCreatedBy("Nike").lowercase() == "currently no collection from nike")
        }

        @Test
        fun `listCollectionsByCreatedBy returns collections from specified designer when populated ArrayList contains collections of the specified designer ignoringCase`(){
            assertEquals(2, populatedCollection!!.numberOfCollectionsBy("me"))
            assertTrue(populatedCollection!!.listCollectionsCreatedBy("Me").lowercase().contains("autumn"))
            assertTrue(populatedCollection!!.listCollectionsCreatedBy("Me").lowercase().contains("summer"))
        }
    }

    @Nested
    inner class DeleteCollections {
        @Test
        fun `deleting a collection that does not exist, returns false`() {
            assertEquals(false, emptyCollection!!.deleteCollection(2001))
            assertEquals(false, emptyCollection!!.deleteCollection(1999))
            assertEquals(false, populatedCollection!!.deleteCollection(1999))
        }

        @Test
        fun `deleting a collection that exists delete and returns true`() {
            assertEquals(5, populatedCollection!!.numberOfCollections())
            assertEquals(true, populatedCollection!!.deleteCollection(2000))
            assertEquals(4, populatedCollection!!.numberOfCollections())
            assertEquals(true, populatedCollection!!.deleteCollection(2001))
            assertEquals(3, populatedCollection!!.numberOfCollections())
        }
    }

    @Nested
    inner class UpdateCollection {
        @Test
        fun `updating a collection that does not exist returns false`(){
            assertFalse(populatedCollection!!.updateCollection(1999,"Yummy", "Me", 2 ))
            assertFalse(emptyCollection!!.updateCollection(2000,"Yess", "Tony Montana", 1))
            assertFalse(emptyCollection!!.updateCollection(1999,"Yess", "Tony Montana", 1))
        }

        @Test
        fun `updating a collection that exists returns true and updates`() {
            //check collection with id 2002 exists and check the contents
            assertEquals(justMe, populatedCollection!!.searchById(2002))
            assertEquals("JustMe", populatedCollection!!.searchById(2002)!!.cname)
            assertEquals("Penelope Wen", populatedCollection!!.searchById(2002)!!.createdBy)
            assertEquals(1, populatedCollection!!.searchById(2002)!!.rank)

            //update collection having id 2002 with new information and ensure contents updated successfully
            assertTrue(populatedCollection!!.updateCollection(2002, "Cheez", "Wen Penelope", 1))
            assertEquals("Cheez", populatedCollection!!.searchById(2002)!!.cname)
            assertEquals("Wen Penelope", populatedCollection!!.searchById(2002)!!.createdBy)
            assertEquals(1, populatedCollection!!.searchById(2002)!!.rank)
        }
    }
}