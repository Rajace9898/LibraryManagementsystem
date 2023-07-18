package Services;

import Main.Main;
import Repositories.ReserveAndBurrowRepo;
import Resources.Book;
import Resources.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ReserveAndBurrowService {

    public static void reserveBook() {
        System.out.println("Enter the ISBN number of the book you want to reserve:");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        try {
            reserveAndBurrowRepo.reserveBook(isbn);
            System.out.println("Book reserved");
        } catch (Exception e) {
            System.out.println("Unable to reserve book");
        }


    }

    public static void issueBook() {
        System.out.println("Enter the ISBN number of the book you want to issue");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        try {
            reserveAndBurrowRepo.issueBook(isbn);


        } catch (Exception e) {
            System.out.println("unable to issue book.");
        }


    }

    public static void returnBook() {
        System.out.println("Enter the ISBN number of the book you want to return");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        try {
            reserveAndBurrowRepo.returnBook(isbn);
            System.out.println("Book returned");

        } catch (Exception e) {
            System.out.println("Unable to return book");
            System.out.println(e);
        }


    }
    public static void viewBooksBurrowedByUser(){
        System.out.println("Enter the name of the user: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        List<Book> bookList = new ArrayList<>();

        try {
            bookList= reserveAndBurrowRepo.viewBooksBurrowedByUser(name);
            System.out.printf("The list of burrowed books by "+ name + "are: ");
            for (Book book:bookList
            ) {
                System.out.printf(book.toStringWOCopies());
            }
        } catch (Exception e) {
            System.out.println("Unable to retrieve books burrowed by "+name);
        }

    }
    public static void viewBooksBurrowedBySelf(){

        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        List<Book> bookList = new ArrayList<>();

        try {
            bookList= reserveAndBurrowRepo.viewBooksBurrowedByUser(Main.user);
            if(bookList.size()==0){
               throw new Exception();
            }else {
                System.out.println("The list of burrowed books by "+ Main.user + " are: ");
                for (Book book:bookList
                ) {
                    System.out.printf(book.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("User has not burrowed any books");
        }

    }

    public static void cancelReservationByIsbn(){
        System.out.println("Enter the ISBN number of the book you want to cancel reservation:");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
        try {
            reserveAndBurrowRepo.cancelReservationByIsbn(isbn);
            System.out.println("reservation of ISBN no:"+isbn+" canceled");
        } catch (Exception e) {
            System.out.println("Unable to cancel reservation by ISBN");
        }


    }
    public static void getBurrowedBooks(){
        try {

            ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
            List<Book> bookList = reserveAndBurrowRepo.getBurrowedBooks();
            if(bookList.size()==0){
                System.out.println("No books have been burrowed");
            }else {
                System.out.println("The list of burrowed books are: ");
                for (Book book: bookList
                ) {
                    System.out.println(book.toStringWOCopies());
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to get burrowed books");
            System.out.println(e);
        }
    }
    public static void getReservedBooks() {
        try {

            ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
            List<Book> bookList = reserveAndBurrowRepo.getReservedBooks();
            System.out.println("The list of reserved books are: ");
            for (Book book : bookList
            ) {
                System.out.println(book.toStringWOCopies());
            }
        } catch (Exception e) {
            System.out.println("Unable to get reserved books");
            System.out.println(e);
        }
    }
    public static void viewTransactionByUser(){
        System.out.println("Enter the user name and phone number to view transaction: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String phone = sc.nextLine();
        try {
            List<Transaction> transactionList = new ArrayList<>();
            transactionList= ReserveAndBurrowRepo.getTransactions(name,phone);
            if(transactionList.size()==0){
                System.out.println("there are no transactions");
            }else {
                for (Transaction transaction: transactionList
                     ) {
                    System.out.println(transaction.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to view transaction history");
        }
    }

    }
