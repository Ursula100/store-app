@file:Suppress("NAME_SHADOWING")

package utils

import models.Collection
import models.Item

object Utilities {

    @JvmStatic
    fun formatListString(collection: ArrayList<Collection>): String =
        collection
            .joinToString(separator = "\n") { collection -> collection.toString() }

    @JvmStatic
    fun formatSetString(items: Set<Item>): String =
        items
            .joinToString(separator = "\n") { item -> item.toString() }
}
