package Services;

import Main.Main;
import Repositories.FineRepo;
import Resources.FineView;
import java.sql.SQLException;
import java.util.List;

public class FineService {
   public static void  viewFineforCurrentUser(){
   FineRepo fineRepo = new FineRepo();
      try {
          List<FineView> fineViewList=  fineRepo.viewFineByName(Main.user);
          if(fineViewList.size()==0){
              System.out.println("You dont owe any fines. Thanks for returning books on time");
          }else {
              for (FineView fine:fineViewList
              ) {
                  System.out.println(fine.toString2());
              }
          }

      } catch (SQLException e) {
         System.out.println("Unable to view Fines Owed");
      }


   }

   public static void viewFinesOwedByUsers(){
      FineRepo fineRepo = new FineRepo();

      try {
       List<FineView> fineViewList=  fineRepo.viewFinesOwedByUsers();
       if(fineViewList.size()==0){
           System.out.println("No users owe fines");
       }else {
           System.out.println("The list of Users who Owe fines:");
           for (FineView fineView : fineViewList
           ) {
               System.out.println(fineView.toString());
           }
       }

      } catch (Exception e) {
         System.out.println("Unable to view fines Owed");
      }
   }
   public static void payFines(){
      FineRepo fineRepo = new FineRepo();
      try {
         fineRepo.payFines(Main.user);
          System.out.println("Fines has been paid");
      } catch (Exception e) {
         System.out.println("Unable to pay fines");
      }
   }
}
