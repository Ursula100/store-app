package models

import java.util.*

class Collection (var cname: String, var createdBy: String, var createdOn: Date, var rank: Integer, var isEmpty: Boolean = true, var items: MutableSet<Item> = mutableSetOf()) {}