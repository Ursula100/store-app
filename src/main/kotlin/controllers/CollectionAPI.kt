package controllers

import models.Collection

class CollectionAPI {
    private var collections = ArrayList<Collection>()

    private var lastId = 2000
    private fun getId() = lastId++

    fun add(collection: Collection): Boolean {
        return collections.add(collection)
    }

}
