import controllers.CollectionAPI
import models.Collection
import models.Item
import utils.ScannerInput.readNextDouble
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import kotlin.system.exitProcess

private val collectionAPI = CollectionAPI()

fun main() = runMenu()

// fun test() = print(now())

fun runMenu() {
    // test()
    do {
        when (val option = mainMenu()) {
            1 -> addCollection()
            2 -> listCollections()
            3 -> listCollectionsByBrand()
            4 -> updateCollection()
            5 -> deleteCollection()
            6 -> findCollectionById()
            7 -> addItemToCollection()
            8 -> listItemsInCollection()
            9 -> updateItemInCollection()
            10 -> deleteItemInCollection()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}
fun mainMenu() = readNextInt(
    """ 
         > -----------------------------------------------------  
         > |                  STORE APP                        |
         > -----------------------------------------------------  
         > | COLLECTION MENU                                   |
         > |   1) Add a Collection                             |
         > |   2) List All Collections                         |
         > |   3) List Collection by designer/brand            |
         > |   4) Update a Collection                          |
         > |   5) Delete a Collection                          |
         > |   6) Search collection by ID                      |
         > -----------------------------------------------------
         > | ITEM MENU                                         |
         > |   7) Add an item to a collection                  |
         > |   8) List items in a collection                   |
         > |   9) Update an item in a collection               |
         > |   10) List items in a collection                   |
         > -----------------------------------------------------   
         > ==>> Choose an option:  """.trimMargin(">")
)

fun addCollection() {
    val collectionName = readNextLine("Enter a name for the collection: ")
    val createdBy = readNextLine("Enter the creator of the collection: ")
    val isAdded = collectionAPI.add(Collection(cname = collectionName, createdBy = createdBy, rank = 1))
    if (isAdded) println("Added Successfully\n")
    else println("Add Failed\n")
}

fun listCollections() {
    if (collectionAPI.numberOfCollections()> 0)
        print("There are/is : ${collectionAPI.numberOfCollections()} collection(s) in store \n")
    println(collectionAPI.listAllCollections())
}

fun listCollectionsByBrand() {
    val brand = readNextLine("Enter category: ")
    if (collectionAPI.numberOfCollectionsBy(brand)> 0)
        print("There are/is ${collectionAPI.numberOfCollectionsBy(brand)} collection(s) from $brand \n")
    println(collectionAPI.listCollectionsCreatedBy(brand))
}

fun findCollectionById() = chooseCollection()
fun updateCollection() {
    listCollections()
    if (collectionAPI.numberOfCollections() > 0) {
        val collectionId = readNextInt("Enter the ID of the collection to update: ")
        if (collectionAPI.searchById(collectionId) != null) {
            val name = readNextLine("Enter name of the collection: ")
            val from = readNextLine("Enter collection brand/designer: ")
            val rank = readNextInt("Enter the rank of the collection: ")
            if (collectionAPI.updateCollection(collectionId, name, from, rank))
                println("Update Successful")
            else println("Update Failed")
        } else println("There is no Collection with id: $collectionId \n")
    }
}

fun deleteCollection() {
    listCollections()
    if (collectionAPI.numberOfCollections() > 0) {
        val id = readNextInt("Enter the ID of the collection to delete: ")
        val collectionToDelete = collectionAPI.deleteCollection(id)
        if (collectionToDelete) println("Delete Successful! Deleted collection $id \n")
        else println("Delete Unsuccessful! No collection with ID: $id \n")
    }
}

private fun addItemToCollection() {
    val collection = chooseCollection()
    collection?.listItems()
    if (collection != null) {
        val name = readNextLine("Enter the name of the item: ")
        val desc = readNextLine("Enter a description for the item: ")
        val mat = readNextLine("Enter the material of the item: ")
        val cat = readNextLine("Enter the name of the item: ")
        val price = readNextDouble("Enter the price of the item (for example 15.00): ")
        val newItem = Item(iName = name, iDesc = desc, material = mat, category = cat, price = price)
        if (collection.addItem(newItem))
            println("Successfully added item with ID ${newItem.itemId}!")
        else println("Add UnSuccessful")
    }
}

private fun listItemsInCollection() = chooseCollection()?.listItems()

private fun updateItemInCollection() {
    listCollections()
    val collection: Collection? = chooseCollection()
    if (collection != null) {
        collection.listItems()
        if (collection.numberOfItems() > 0) {
            val item: Item? = collection.searchItemById(readNextInt(("Enter the id of the item to update")))
            if (item != null) {
                val name = readNextLine("Enter new name: ")
                val desc = readNextLine("Enter new description: ")
                val category = readNextLine("Enter category: ")
                val material = readNextLine("Enter material: ")
                val price = readNextDouble("Enter new price (ex. 84.99): ")
                if (collection.updateItem(item.itemId, name, desc, category, material, price))
                    println("Item contents updated")
                else
                    println("Item contents NOT updated")
            } else {
                println("No Item matches provided ID")
            }
        }
    } else println("No collection with provided ID")
}

private fun deleteItemInCollection() {
    listCollections()
    val collection = chooseCollection()
    if (collection != null) {
        collection.listItems()
        if (collection.numberOfItems() > 0) {
            val item: Item? = collection.searchItemById(readNextInt(("Enter the id of the item to delete: ")))
            if (item != null) {
                if (collection.deleteItem(item.itemId)) println("Delete Successful!")
                else println("Delete NOT Successful")
            }
        }
    } else println("No collection with provided ID")
}

private fun chooseCollection(): Collection? = collectionAPI.searchById(readNextInt("Enter the ID of the collection: "))

fun exitApp() {
    println("Exiting...")
    exitProcess(0)
}
