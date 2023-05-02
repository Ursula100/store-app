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

    fun listByCreatedBy(designer : String): String =
        if  (collections.isEmpty()) "No collections in store"
        else collections.filter{collection: Collection -> collection.createdBy == designer}
                        .joinToString(separator = "\n"){collection -> collection.toString()}
                        .ifBlank {"Currently no collection from $designer"}

    fun numberOfCollections(): Int = collections.size

    fun findCollection(index: Int): Collection? {
        return if (isValidListIndex(index, collections))
            collections[index]
        else null
    }


    //utility method to determine if an index is valid in list.
    fun isValidListIndex (index: Int, list: List<Any>): Boolean{
        return (index >= 0 && index < list.size)
    }

}
