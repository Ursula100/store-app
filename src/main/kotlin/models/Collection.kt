package models

import utils.Utilities.formatSetString

class Collection(var collectionId: Int = 2000, var cname: String, var createdBy: String, var rank: Int, var items: MutableSet<Item> = mutableSetOf()) {
    fun numberOfItems(): Int = items.size

    private var lastItemId = 1
    private fun getItemId() = lastItemId++

    fun addItem(item: Item): Boolean {
        item.itemId = getItemId()
        return items.add(item)
    }

    fun listItems() = if (items.isEmpty()) "No items in collection" else formatSetString(items)

    fun searchItemById(id: Int): Item? {
        return items.find { item -> item.itemId == id }
    }

    fun updateItem(id: Int, iName: String, iDesc: String, material: String, category: String, price: Double): Boolean {
        val foundItem = searchItemById(id)
        if (foundItem != null) {
            foundItem.iName = iName
            foundItem.iDesc = iDesc
            foundItem.material = material
            foundItem.category = category
            foundItem.price = price
            return true
        }
        return false
    }

    fun deleteItem(id: Int): Boolean = items.removeIf { item -> item.itemId == id }

    fun findItem(index: Int): Item? {
        return if (isValidListIndex(index, items))
            items.elementAt(index)
        else null
    }

    fun isValidListIndex(index: Int, list: MutableSet<Item>): Boolean {
        return (index >= 0 && index < list.size)
    }

    override fun toString(): String = "Id - $collectionId: ${cname.uppercase()} by ${createdBy.uppercase()}. Contains ${numberOfItems()} items \n"
}
