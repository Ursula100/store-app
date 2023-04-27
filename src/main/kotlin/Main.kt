import utils.ScannerInput
import utils.ScannerInput.readNextInt

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

fun addCollection() = println("Adds a Collection")

fun listCollections() = println("List of Collections")

fun updateCollection() = println("Updates a Collection")

fun deleteCollection() = println("Deletes a Collection")

fun exitApp() = println("Exiting...")

