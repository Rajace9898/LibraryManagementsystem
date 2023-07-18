package Services;

import Repositories.BookRepo;
import Resources.Book;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {

   public static void addBook() {
       Book book = new Book();
       BookRepo bookRepo = new BookRepo();
       System.out.println("Enter the details of the book: Title, Author, ISBN number and Copies");
       Scanner sc = new Scanner(System.in);

       String title = sc.nextLine();
       String author =sc.nextLine();
       Integer isbn =sc.nextInt();
       sc.nextLine();
       Integer copies =sc.nextInt();
       book.setTitle(title);
       book.setISBN(isbn);
       book.setAuthor(author);
       book.setCopies(copies);
       try {
           bookRepo.addBook(book);
           System.out.println("Book has been added.");
       } catch (Exception e) {
           System.out.println("Unable to add book");
       }


    }

    public static void removeBookByISBN()  {

        BookRepo bookRepo = new BookRepo();
        System.out.println("Enter the ISBN number of the book to remove:");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        try {
            bookRepo.removeBookByISBN(isbn);
            System.out.println("Book with ISBN no "+ isbn+" is removed");
        } catch (SQLException e) {
            System.out.println("Unable to remove the book");
        }


    }
    public static void findBookByAuthor() {
        System.out.println("Enter the author:");
        Scanner sc= new Scanner(System.in);
        String author = sc.nextLine();
        BookRepo bookRepo = new BookRepo();

        try {
            System.out.println("The searched book is: ");
            System.out.println(bookRepo.findBookByAuthor(author).toString());
        } catch (Exception e) {
            System.out.println("Unable to find Book by "+author);
        }

    }
    public static void findBookByTitle() {
        System.out.println("Enter the title:");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        BookRepo bookRepo = new BookRepo();
        System.out.println("The searched book is: ");

        try {
            System.out.println(bookRepo.findBookByTitle(title).toString());
        } catch (SQLException e) {
            System.out.println("Unable to find book by "+ title);
        }
    }
    public static void findBookByISBN() {
        System.out.println("Enter the ISBN:");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        BookRepo bookRepo = new BookRepo();


        try {
            System.out.println(bookRepo.findBookByISBN(isbn).toString());
        } catch (SQLException e) {
            System.out.println("unable to find book by the ISBN no: "+isbn);
        }
    }

    public static void getBooks(){
        List<Book> bookList;
        BookRepo bookRepo = new BookRepo();
        try {
            bookList= bookRepo.getBooks();
            if(bookList.size()>0){
                System.out.println(" ");
                System.out.println("The list of books are:");
                for (Book book: bookList
                ) {
                    System.out.println(book.toString());
                }
            }else throw new Exception();

        } catch (Exception e) {
            System.out.println("Unable to retrieve books");
        }

    }
    public static void updateBook()  {
        System.out.println("enter the isbn number of the book which needs to be updated:");
        Scanner sc = new Scanner(System.in);
        Integer isbn = sc.nextInt();
        sc.nextLine();
        BookRepo bookRepo = new BookRepo();

        try {
            Book book= bookRepo.findBookByISBN(isbn);
            System.out.println(book.toString());
            System.out.println("enter the Updated Title, Author and Copies: ");
            String title, author;
            int copies;
            title = sc.nextLine();
            author= sc.nextLine();
            copies = sc.nextInt();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCopies(copies);
            bookRepo.updateBook(book);
            System.out.println("The book has been updated");
        } catch (SQLException e) {
            System.out.println("Unable to update book");
        }


    }
    public  static  void getTotalNumberOfBooks(){
       BookRepo bookRepo = new BookRepo();
        try {
           int number = bookRepo.getTotalNumberOfBooks();
            System.out.println("The total Number of books in the library are: "+number);
        } catch (Exception e) {
            System.out.println("Unable to get total number of books in the library");

        }

    }
    public static void getListOfAvailableBooks(){ //??
       BookRepo bookRepo = new BookRepo();
        try {
            List<Book> bookList = bookRepo.getListOfAvailableBooks();
            System.out.println("the list of available books are:");
            for (Book book : bookList ) {
                System.out.println(book.toString());


            }
        } catch (Exception e) {
            System.out.println("Unable to fetch available books");

        }

    }


}
