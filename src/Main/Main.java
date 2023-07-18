package Main;


import java.util.Scanner;
import static Services.FineService.*;
import static Services.ReserveAndBurrowService.*;
import static Services.UserService.*;
import static Services.BookService.*;

public class Main {

    public static String user;

    public static void main(String[] args) {


       frontPage();


    }

    public static void frontPage() { //navigation for front page
        System.out.println(" ");
        System.out.println("************ Welcome To The Library ************");
        System.out.println("Press 1 to login ");
        System.out.println("Press 2 to Register");
        System.out.println("Press 0 to exit");

        Scanner sc = new Scanner(System.in);
        int state = sc.nextInt();

        switch (state) {
            case 1:
                    userLogin();
                    secondPage();
            case 2:
                    userRegister();
                    frontPage();
            case 0:
                System.out.println("Thank-you for visiting");
            default:
                System.out.println("incorrect choice");
                frontPage();


        }

    }
    public static void secondPage(){
        System.out.println(" ");
        System.out.println("Press 1 to go to user page");
        System.out.println("Press 2 to go to Book Management page");
        System.out.println("Press 3 to view statistics");
        System.out.println("Press 0 to LogOut");
        int state = 100;

        Scanner sc = new Scanner(System.in);
        state = sc.nextInt();
        switch (state){
            case 1:
                userPage();
                secondPage();

            case 2:
                manageBooks();
                secondPage();
            case 3:
                statPage();
                secondPage();
            case 0:
                Main.user=null;
                frontPage();
            default:
                System.out.println("incorrect choice. try again");
                secondPage();
        }

    }
    public static void userPage () {

        System.out.println(" ");
        System.out.println("Press 1 to view details:");
        System.out.println("Press 2 to view book list");
        System.out.println("Press 3 to Issue a Book");
        System.out.println("Press 4 to Return a Book");
        System.out.println("Press 5 to view Burrowed books");
        System.out.println("Press 6 to Reserve a Book");
        System.out.println("Press 7 to Cancel a Reservation");
        System.out.println("Press 8 to View Fines Owed");
        System.out.println("Press 9 to Pay Fines");
        System.out.println("Press 0 to back");

        int state = 100;

        Scanner sc = new Scanner(System.in);
        state = sc.nextInt();
        switch (state) {
            case 1:
                viewUserDetails();
                userPage();
            case 2:
                getBooks();
                userPage();
            case 3:
                issueBook();
                userPage();
            case 4:
                returnBook();
                userPage();
            case 5:
                viewBooksBurrowedBySelf();
                userPage();
            case 6:
                reserveBook();
                userPage();
            case 7:
                cancelReservationByIsbn();
                userPage();
            case 8:
                viewFineforCurrentUser();
                userPage();
            case 9:
                payFines();
                userPage();

            case 0:
                secondPage();

            default:
                System.out.println("incorrect choice. try again");
                userPage();


        }


    }



    private static void manageBooks() {  // navigation for book management page
        System.out.println(" ");
        System.out.println("Welcome to the Book Management page");
        System.out.println("Press 1 to view Books List:");
        System.out.println("Press 2 to add a Book");
        System.out.println("Press 3 to Remove a book");
        System.out.println("Press 4 to Update Book");
        System.out.println("Press 5 to Search Book by Title");
        System.out.println("Press 6 to Search Book by Author");
        System.out.println("Press 7 to Search Book by ISBN number");
        System.out.println("Press 0 to Return to Front Page");

        int state = 100;
        Scanner sc =new Scanner(System.in);
        state=sc.nextInt();

        switch (state) {
            case 1:
                    getBooks();
                    manageBooks();
            case 2:
                    addBook();
                    manageBooks();
            case 3:
                    removeBookByISBN();
                    manageBooks();
            case 4:
                    updateBook();
                    manageBooks();
            case 5:
                    findBookByTitle();
                    manageBooks();
            case 6:
                    findBookByAuthor();
                    manageBooks();
            case 7:
                    findBookByISBN();
                    manageBooks();
            case 0:
                frontPage();
            default:
                System.out.println("incorrect choice");
                manageBooks();



        }
    }


    private static void statPage() {
        System.out.println(" ");
        System.out.println("***********--Welcome to the Statistics Page--*************");
        System.out.println("Press 1 to view list of all books in the library");
        System.out.println("Press 2 to view list of all burrowed books in the library");
        System.out.println("Press 3 to view list of all reserved books in the library");
        System.out.println("Press 4 to View total number of books in the library");
        System.out.println("Press 5 to view list of available books");
        System.out.println("Press 6 to view Fines owed by the users");
        System.out.println("Press 7 to view transaction history");
        System.out.println("Press 0 to go back to the front page");
        int i = 100;
        Scanner sc = new Scanner(System.in);
        i=sc.nextInt();

        switch (i){
            case 1:
                getBooks();
                statPage();
            case 2:
                getBurrowedBooks();
                statPage();
            case 3:
                getReservedBooks();
                statPage();

            case 4:
                getTotalNumberOfBooks();
                statPage();

            case 5:
                getListOfAvailableBooks();
                statPage();
            case 6:
                viewFinesOwedByUsers();
                statPage();
            case 7:
                viewTransactionByUser();
                statPage();
            case 0:
                frontPage();

        }
    }



}



