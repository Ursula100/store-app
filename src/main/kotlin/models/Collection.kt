package models

class Collection(var collectionId: Int = 2000, var cname: String, var createdBy: String, var rank: Int, var isEmpty: Boolean = true, var items: MutableSet<Item> = mutableSetOf()) {
   private fun numberOfItems(): Int =  items.size

    private var lastItemId = 1
    private fun getItemId() = lastItemId++

    fun addItem(item: Item) : Boolean {
        item.itemId = getItemId()
        return items.add(item)
    }
    override fun toString(): String {
        return "Id - $collectionId: ${cname.uppercase()} by ${createdBy.uppercase()}. Contains ${numberOfItems()} items"
    }
}