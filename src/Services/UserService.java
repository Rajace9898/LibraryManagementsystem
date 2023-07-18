package Services;

import Repositories.UserRepo;
import Resources.User;
import Main.Main;
import java.util.Scanner;



public class UserService {



    public static void userRegister()  {
        User user = new User();
        UserRepo userRepo = new UserRepo();

        System.out.println("enter Name, Password, Phone and Address of the new user");
        Scanner sc = new Scanner(System.in);
        user.setName(sc.nextLine());
        user.setPassword(sc.nextLine());
        user.setPhone(sc.nextLine());
        user.setAddress(sc.nextLine());

        userRepo.addUser(user);
        System.out.println("User has been registered");

    }

    public static void viewUserDetails() {
        UserRepo userRepo = new UserRepo();
        User user = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name of the user");
        String name = sc.nextLine();
        try {
            user = userRepo.getUserByName(name);
            if(user==null){
                throw new Exception();
            }else {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println("unable to view user details");
        }

    }

    public static void userLogin()  {
        User user =  new User();
        UserRepo userRepo = new UserRepo();
        System.out.println("enter name and password");
        Scanner sc = new Scanner(System.in);
        String name, password;
        name = sc.nextLine();
        password = sc.nextLine();

        try {
            user = userRepo.getUserByName(name);
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    System.out.println("Login Successful!!");
                    Main.user = user.getName();
                    System.out.println("Welcome-Back " + Main.user);
                }else {
                    throw new Exception();
            }

        } catch (Exception e) {
            System.out.println("incorrect user name or password");
            Main.frontPage();
        }


    }
}
