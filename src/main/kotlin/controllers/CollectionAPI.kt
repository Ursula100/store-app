package controllers

import models.Collection

class CollectionAPI {
    private var collections = ArrayList<Collection>()

    private var lastId = 2000
    private fun getId() = lastId++

    fun add(collection: Collection): Boolean {
        return collections.add(collection)
    }

    fun numberOfCollections(): Int {
        return collections.size
    }

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
