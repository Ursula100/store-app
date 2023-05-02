import controllers.CollectionAPI
import models.Collection
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.util.Date

private val collectionAPI = CollectionAPI()

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addCollection()
            2 -> listCollections()
            3 -> updateCollection()
            4 -> deleteCollection()
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
         > |   2) List Collections                             |
         > |   3) Update a Collection                          |
         > |   4) Delete a Collection                          |
         > -----------------------------------------------------   
         > ==>> """.trimMargin(">")
)

fun addCollection(){
    val collectionName = readNextLine("Enter a name for the collection: ")
    val createdBy = readNextLine("Enter the creator of the collection: ")
    val addedOn = Date()
    val isAdded = collectionAPI.add(Collection(cname = collectionName, createdBy = createdBy, createdOn = addedOn, rank = 1))
    if (isAdded) println("Added Successfully\n")
    else println("Add Failed\n")
}

fun listCollections() = println("List of Collections")

fun updateCollection() = println("Updates a Collection")

fun deleteCollection() = println("Deletes a Collection")

fun exitApp() = println("Exiting...")

