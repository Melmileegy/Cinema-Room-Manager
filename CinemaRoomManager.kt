package cinema

import java.util.*
import kotlin.text.*

val scanner = Scanner(System.`in`)

fun main() {
    // write your code here
    var ticketPrice = 10
    println("Enter the number of rows:")
    val rows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = scanner.nextInt()
    val cinema = MutableList(rows) {MutableList(seatsPerRow) {"S"}}
    var noOfPurchasedTickets = 0
    var currentIncome = 0

    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    
    var menuItem = scanner.nextInt()

    while(menuItem != 0) {
        when(menuItem) {
            1 -> showTheSeats(rows, seatsPerRow, cinema)
            2 -> {
                println("\nEnter a row number:")
                var rowNum = scanner.nextInt()
                println("Enter a seat number in that row:")
                var seatNum = scanner.nextInt()

                while(rowNum > rows || seatNum > seatsPerRow) {
                    println() 
                    println("Wrong input!")
                    println()
                    println("\nEnter a row number:")
                    rowNum = scanner.nextInt()
                    println("Enter a seat number in that row:")
                    seatNum = scanner.nextInt()
                }

                while(cinema[rowNum - 1][seatNum - 1] == "B") {
                    println() 
                    println("That ticket has already been purchased!")
                    println()
                    println("\nEnter a row number:")
                    rowNum = scanner.nextInt()
                    println("Enter a seat number in that row:")
                    seatNum = scanner.nextInt()
                }

                if(rows * seatsPerRow <= 60 || rowNum <= rows / 2) {
                    ticketPrice = 10
                    println("Ticket price: $${ticketPrice}")
                } else {
                    ticketPrice = 8
                    println("Ticket price: $${ticketPrice}")
                }

                cinema[rowNum - 1][seatNum - 1] = "B"
                noOfPurchasedTickets++ 
                currentIncome += ticketPrice
            }
            3 -> showStats(noOfPurchasedTickets, rows, seatsPerRow, currentIncome)
        }

        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        menuItem = scanner.nextInt()
    }
}

fun showTheSeats(rows: Int, seatsPerRow: Int, cinema: MutableList<MutableList<String>>) {
    println("\nCinema:")
    
    for(i in 1..seatsPerRow) {
        print(" " + i)
    }
    
    for(i in 1..rows) {
        print("\n${i} ${cinema[i - 1].joinToString(" ")}")
    }
    println()
}

fun smallRoomTotalIncome(rows: Int, seats: Int, ) = print("Total income: $${rows * seats * 10}")

fun largeRoomTotalIncome(rows: Int, seats: Int, ) = print("Total income: $${rows / 2 * seats * 10 + (rows / 2 + rows % 2) * seats * 8}")

fun showStats(noOfPerchasedTickets: Int, rows: Int, seats: Int, currentIncome: Int) {
    val percentage = noOfPerchasedTickets.toDouble() / ((rows * seats).toDouble()) * 100.0
    println()
    println("Number of purchased tickets: ${noOfPerchasedTickets}")
    println("Percentage: " + "%,.2f".format(percentage) + "%")
    println("Current income: $${currentIncome}")
    if(rows * seats <= 60) {
        smallRoomTotalIncome(rows, seats)
    } else {
        largeRoomTotalIncome(rows, seats)
    }
}
