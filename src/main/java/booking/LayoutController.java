package src.main.java.booking;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//to update the seatTaken, can concatenate to the String then update the line
public class LayoutController {
    private static int noOfRows = 0;
    private static int noOfColumns = 0;
     
//public void displayLayout
    public static void displayLayout(String cinemaID, LocalDateTime timing) {
        
        List<SeatBooked>  movieList = SeatBooked_Controller.getSeatBooked();
        String seatsTaken = "";
        int flag = 0;
     
        for(int i = 0; i < movieList.size(); i++){
            //get the 2 variable: cinemaID and timing of the movie
            LocalDateTime dt1 = movieList.get(i).getTiming();
            LocalDateTime dt2 = timing;
            String tmpCinemaID = movieList.get(i).getCinemaID();
  
            if(tmpCinemaID.compareTo(cinemaID)==0 && dt1.compareTo(dt2)==0){
                noOfRows = movieList.get(i).getNoOfRows();
                noOfColumns = movieList.get(i).getNoOfColumns();
                seatsTaken = movieList.get(i).getSeatsTaken();

                List<String> items = Arrays.asList(seatsTaken.split("&"));

           //initialize layout
           char[][] Layout = new char[noOfRows][noOfColumns];
           for(i=0; i<noOfRows; i++) {
               //System.out.println(number);number++;
               for(int j=0; j<noOfColumns; j++) {
                   Layout[i][j] = '0';
               }
           }

           //mark the layout with the seats taken
              for(i=0; i<items.size(); i++) {
                String[] temp = items.get(i).split("!");
                int row = Integer.parseInt(temp[0])-1;
                int column = Integer.parseInt(temp[1])-1;
                Layout[row][column] = 'X';
              }

            //print layout
            for(i=0; i<noOfRows; i++) {
                for(int j=0; j<noOfColumns; j++) {
                    System.out.print(Layout[i][j]+" ");
                }
                System.out.println();
            }


                flag = 1;
                break;
            }
        }
        if(flag==0){
            System.out.println("No such movie or timing");
        }
    }

   
   
}