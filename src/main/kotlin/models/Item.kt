package models

class Item(var itemId: Int = 1, var iName: String, var iDesc: String, var material: String, var category: String, var price: Double) {
    override fun toString(): String = "$itemId: $iName, desc: $iDesc, material: $material, category: $category, price: $price \n"
}
