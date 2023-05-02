package models

import java.util.*

class Collection(var collectionId: Int = 2000, var cname: String, var createdBy: String, var createdOn: Date, var rank: Int, var isEmpty: Boolean = true, var items: MutableSet<Item> = mutableSetOf()) {}