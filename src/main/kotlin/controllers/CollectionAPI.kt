package controllers

import models.Collection

class CollectionAPI {
    private var collections = ArrayList<Collection>()

    private var lastId = 2000
    private fun nextId() = lastId++


    fun add(collection: Collection): Boolean {
        collection.collectionId = nextId()
        return collections.add(collection)
    }

    fun listAllCollections(): String =
        if  (collections.isEmpty()) "No collections in store"
        else collections.joinToString (separator = "\n") { collection -> collection.toString() }

    fun listCollectionsCreatedBy(designer : String): String =
        if  (collections.isEmpty()) "No collections in store"
        else collections.filter{collection: Collection -> collection.createdBy.equals(designer, ignoreCase = true)}
                        .joinToString(separator = "\n"){collection -> collection.toString()}
                        .ifBlank {"Currently no collection from $designer"}

    fun deleteCollection(id: Int): Boolean = collections.removeIf { collection -> (collection.collectionId == id) && (collection.items.size==0) }

    fun updateCollection(id: Int, name: String, by: String, rank: Int): Boolean {
        val toUpdate = searchById(id)
        if (toUpdate != null && toUpdate.items.size==0) {
            toUpdate.cname = name
            toUpdate.createdBy = by
            toUpdate.rank = rank
            return true
        }
        return false
    }

    fun numberOfCollections(): Int = collections.size

    fun numberOfCollectionsBy(designer: String): Int =
        collections.count { collection: Collection -> collection.createdBy.equals(designer, ignoreCase = true) }

    fun findCollection(index: Int): Collection? {
        return if (isValidListIndex(index, collections))
            collections[index]
        else null
    }

    fun searchById(id : Int): Collection? =  collections.find{ note -> note.collectionId == id }

    //utility method to determine if an index is valid in list.
    fun isValidListIndex (index: Int, list: List<Any>): Boolean = (index >= 0 && index < list.size)

}
