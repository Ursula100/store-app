package models

import java.time.LocalDate
import java.util.*

class Collection(var collectionId: Int = 2000, var cname: String, var createdBy: String, var rank: Int, var isEmpty: Boolean = true, var items: MutableSet<Item> = mutableSetOf()) {
   fun numberOfItems() =  items.size
    override fun toString(): String {
        return "Id - $collectionId: ${cname.toUpperCase()} by ${createdBy.toUpperCase()}. Contains ${numberOfItems()} items"
    }
}