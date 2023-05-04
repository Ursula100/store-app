package utils

import models.Collection
import models.Item
import models.Note

object Utilities {

    /**
     * @author Siobhan Drohan and Mairead Meagher
     */

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(collectionsToFormat: List<Collection>): String =
        collectionsToFormat
            .joinToString(separator = "\n") { collection ->  "$collection" }

    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Item>): String =
        itemsToFormat
            .joinToString(separator = "\n") { item ->  "\t$item" }

}