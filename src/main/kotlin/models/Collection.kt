package models

class Collection(var collectionId: Int = 2000, var cname: String, var createdBy: String, var rank: Int, var isEmpty: Boolean = true, var items: MutableSet<Item> = mutableSetOf()) {
   fun numberOfItems() =  items.size
    override fun toString(): String {
        return "Id - $collectionId: ${cname.uppercase()} by ${createdBy.uppercase()}. Contains ${numberOfItems()} items"
    }
}