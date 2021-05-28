import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.*;
import static java.lang.Math.floor;

public class Index extends Vector<Hashtable> {
    String name;

    public Index(String tableName, String columns){
        Vector<Hashtable> index = new Vector<Hashtable>(9);
        Iterator it = index.iterator();
        int count= 0;
        while(it.hasNext()){





        }
this.serialG();
    } //after init size = dimension-1




    public int DecideIndexLocation(String tableName, String columns,Object A) throws IOException {
    Object min = DBApp.getMin(tableName,columns);
    Object max = DBApp.getMax(tableName,columns);



        if(DBApp.getType(tableName,columns)== 0 ||DBApp.getType(tableName,columns)== 2 ){ //int or string(not sure, but it's code should be identical)
       int distance = ((int) max - (int) min);
       double step = 0.1* distance;
       if (distance>10){
         for(int i = 0; i<this.size()-1;i++){
             if(i==this.size()){
                 if((int)A>= step* this.size()-2 + (int)min && //minimum constraint
                         (int) A<= (int)max ){ //maximum constraint
                     return i;
                 }
             }
                 else{
                     if((int)A>= (i*step) + (int)min &&  //minimum constraint
                             (int) A<(int)min + (step*(i+1)) ){ //maximum constraint
                     return i;
                 }


             }

         }

       }

    } else

        if(DBApp.getType(tableName,columns)== 1) { //Double
            BigDecimal dmin = BigDecimal.valueOf((double) min);
            BigDecimal dmax = BigDecimal.valueOf((double) max);
            BigDecimal doubleA = BigDecimal.valueOf((double) A);
            double distance = (double)max - (double)min;
            BigDecimal step = BigDecimal.valueOf( distance*0.1);
            for(int i = 0; i<this.size()-1;i++){
                int minConstraint = (doubleA).compareTo((dmin.add(step.multiply(BigDecimal.valueOf(i)))));
                if(i==this.size()-1){

                    if ((minConstraint == 1 || minConstraint == 0) &&
                            (((doubleA).compareTo(dmax) == 0 || ((doubleA).compareTo(dmax) ) == -1 ))){
                        return i;
                    }
                }

                else{


            if ((((doubleA).compareTo((dmin))) == 1 || ((doubleA).compareTo((dmin))) == 0) &&
                    ((doubleA).compareTo((dmin.add( step.multiply( BigDecimal.valueOf(i+1) ))))) == -1) {
                return i;
            }


        }
        }
            } else

        if(DBApp.getType(tableName,columns)== 3){ //Date

            Date dmin = ((Date) min);
            Date dmax = ((Date) max);
            long distance = (dmax.getTime() - (dmin.getTime()));

            TimeUnit time = TimeUnit.DAYS;



            int step =  (int) floor(0.1*distance);

            for(int i = 0; i<this.size()-1;i++){
//                long minConstraint =  ;
//                if(i==this.size()-1){
//
//
//                    }
//                }
//
//                else{
//
//
//                    if ( {
//                        return i;
//                    }
//
//
//                }
           }




        }

//        if(DBApp.getType(tableName,columns)== 2){ //String
//            int smin = (int) min;
//
//
//
//
//
//        } else

//
//
//        for(int i = 0 ; i < this.size() ; i++){
//            switch(A) {
//
//
//
//
//
//                default:
//                    // code block
//
//
//
//            }
//
//
//
//
//
//
//
//        }








        return 0;
    }















    public void serialG(){
        try
        {
            String dataDirPath = "src/main/resources/data/";
            String filename = dataDirPath+this.name+".ser";


            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);


            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }

}
