import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.*;
import static java.lang.Math.floor;

public class Index {
    String name;
    int count = 0;
    String tableName;
    String column;
    int columnNum;
    Vector<Hashtable<Integer, Bucket>> k;

    public Index(String tableName, String column) throws IOException {
        this.k = new Vector<Hashtable<Integer, Bucket>>();
    this.tableName = tableName;
    this.column = column;
    this.columnNum = DBApp.coloumnnum(this.tableName,this.column);
        int tSize = Table.deserialT(tableName);



        for(int i = 0;i< 9;i++){
            Hashtable<Integer,Bucket> s = new Hashtable<Integer,Bucket>() ;
            k.add(i,s);
        }

        if (tSize != 0) {
            fillIndex(tSize);
        }




this.serialG();
    } //after init size = dimension-1



    public void fillIndex(int size) throws IOException {
        ComB comp = new ComB();
        for (int i = 0; i < size; i++) {
            while (DBApp.checkdeleted(this.tableName, i)) {
                i++;
                size++;
            }
            Page p = Page.deserialP(this.tableName + i);
            for (int j = 0; j < p.size(); j++) {
                ArrayList tuple = new ArrayList();
                tuple = p.get(j);



                addToIndex(tuple);





                }
    }

        }

 public void addToIndex(Object A) throws IOException {
     int g = decideIndexLocation(A);

 }

    public int decideIndexLocation(Object A) throws IOException {
    Object min = DBApp.getMin(tableName,this.column);
    Object max = DBApp.getMax(tableName,this.column);



        if(DBApp.getType(tableName,this.column)== 0 ){ //int
       int distance = ((int) max - (int) min);
       double step = 0.1* distance;
       if (distance>10){
         for(int i = 0; i<k.size()-1;i++){
             if(i==k.size()){
                 if((int)A>= step* k.size()-2 + (int)min && //minimum constraint
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

        if(DBApp.getType(tableName,this.column)== 1) { //Double
            BigDecimal dmin = BigDecimal.valueOf((double) min);
            BigDecimal dmax = BigDecimal.valueOf((double) max);
            BigDecimal doubleA = BigDecimal.valueOf((double) A);
            double distance = (double)max - (double)min;
            BigDecimal step = BigDecimal.valueOf( distance*0.1);
            for(int i = 0; i<k.size()-1;i++){
                int minConstraint = (doubleA).compareTo((dmin.add(step.multiply(BigDecimal.valueOf(i)))));
                if(i==k.size()-1){

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
            if(DBApp.getType(tableName,this.column)== 2){ //string
                int sMin = turnIntoAscii((String)min);
                int sMax = turnIntoAscii((String) min);
                int value = turnIntoAscii((String) A);
                int distance = sMax - sMin;
                double step = distance*0.1;

                if (distance>10){
                    for(int i = 0; i<k.size()-1;i++){
                        if(i==k.size()){
                            if(value>= step* k.size()-2 + sMin && //minimum constraint
                                    value<= sMax ){ //maximum constraint
                                return i;
                            }
                        }
                        else{
                            if(value>= (i*step) + sMin &&  //minimum constraint
                                    value<sMin + (step*(i+1)) ){ //maximum constraint
                                return i;
                            }
                        }

                    }

                }



            }else

        if(DBApp.getType(tableName,this.column)== 3) { //Date

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateMin = (Date) min;
            Date dateMax = (Date) max;

            int yMin = dateMin.getYear();
            int mMin = dateMin.getMonth();
            int dMin = dateMin.getDay();

            int yMax = dateMax.getYear();
            int mMax = dateMax.getMonth();
            int dMax = dateMax.getDate();

            int minvalue = (yMin * 10000) + (mMin * 100) + dMin;
            int maxvalue = (yMax * 10000) + (mMax * 100) + dMax;

            int distance = maxvalue - minvalue;
            double step = distance *0.1;

            int y = ((Date)A).getYear() *10000;
            int m = ((Date)A).getMonth() *100;
            int d = ((Date)A).getDay();
            int value = y  + m  + d;

            for (int i = 0; i < 9; i++) {
                if (value < (minvalue + (step * i)))
                    return i;
            }





        }
        return -1;
    }



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












    public int turnIntoAscii(String word){
        int sum = 0;
        char[] ascii1 = word.toCharArray();
        for(int i = 0 ; i<ascii1.length;i++){
          sum = sum + (int) ascii1[i];

        }

        return 1;
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
