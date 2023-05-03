import controllers.CollectionAPI
import models.Collection
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.time.LocalDate.now
import java.util.Date

private val collectionAPI = CollectionAPI()

fun main() = runMenu()

fun test() = print(now())

fun runMenu() {
    test()
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
         > |                  NOTE KEEPER APP                  |
         > -----------------------------------------------------  
         > | NOTE MENU                                         |
         > |   1) Add a Collection                             |
         > |   2) List All Collections                         |
         >     3) List Collection by designer/brand                                               |
         > |   4) Update a Collection                          |
         > |   5) Delete a Collection                          |
         > -----------------------------------------------------   
         > ==>> """.trimMargin(">")
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

fun updateCollection() = println("Updates a Collection")

fun deleteCollection() {
    listCollections()
    if (collectionAPI.numberOfCollections() > 0) {
        val id = readNextInt("Enter the ID of the collection to delete: ")
        val collectionToDelete = collectionAPI.deleteCollection(id)
        if (collectionToDelete) println("Delete Successful! Deleted collection $id \n")
    }
}

fun exitApp() = println("Exiting!!!")

