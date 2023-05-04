package utils

import models.Collection
import models.Item

object Utilities {

    @JvmStatic
    fun formatListString(collection: ArrayList<Collection>): String =
        collection
            .joinToString(separator = "\n") { collection ->  "$collection" }

    @JvmStatic
    fun formatSetString(items: Set<Item>): String =
        items
            .joinToString(separator = "\n") { item -> item.toString() }
    @JvmStatic
    fun isValidListIndex (index: Int, list: List<Any>): Boolean = (index >= 0 && index < list.size)

}