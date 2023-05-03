import controllers.CollectionAPI
import models.Collection
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.time.LocalDate.now
import java.util.Date

private val collectionAPI = CollectionAPI()

fun main() = runMenu()

//fun test() = print(now())

fun runMenu() {
    //test()
    do {
        when (val option = mainMenu()) {
            1 -> addCollection()
            2 -> listCollections()
            3 -> listCollectionsByBrand()
            4 -> updateCollection()
            5 -> deleteCollection()
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
         > | STORE APP MENU                                    |
         > |   1) Add a Collection                             |
         > |   2) List All Collections                         |
         >     3) List Collection by designer/brand                                               |
         > |   4) Update a Collection                          |
         > |   5) Delete a Collection                          |
         > -----------------------------------------------------   
         > ==>> Choose an option:  """.trimMargin(">")
)

fun addCollection(){
    val collectionName = readNextLine("Enter a name for the collection: ")
    val createdBy = readNextLine("Enter the creator of the collection: ")
    val isAdded = collectionAPI.add(Collection(cname = collectionName, createdBy = createdBy, rank = 1))
    if (isAdded) println("Added Successfully\n")
    else println("Add Failed\n")
}

fun listCollections() {
    if(collectionAPI.numberOfCollections()>0)
        print("There are/is : ${collectionAPI.numberOfCollections()} collection(s) in store \n")
    println(collectionAPI.listAllCollections())
}

fun listCollectionsByBrand(){
    val brand = readNextLine("Enter category: ")
    if(collectionAPI.numberOfCollectionsBy(brand)>0)
        print("There are/is ${collectionAPI.numberOfCollectionsBy(brand)} collection(s) from $brand \n")
    println(collectionAPI.listCollectionsCreatedBy(brand))
}

fun updateCollection(){
    listCollections()
    if (collectionAPI.numberOfCollections() > 0) {
        val collectionId = readNextInt("Enter the ID of the collection to update: ")
        if (collectionAPI.searchById(collectionId)!=null) {
            val name = readNextLine("Enter name of the collection: ")
            val from = readNextLine("Enter collection brand/designer: ")
            val rank  = readNextInt("Enter the rank of the collection: ")
            if (collectionAPI.updateCollection(collectionId, name, from, rank))
                println("Update Successful")
            else println("Update Failed")
        }
        else println("There is no Collection with id: $collectionId \n")
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

fun exitApp() = println("Exiting!!!")

