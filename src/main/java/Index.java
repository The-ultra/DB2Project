
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import static java.lang.Math.floor;

public class Index   {
    Hashtable<String, Integer> Ranges ;
    ArrayList Refrences;


    public Index(String tableName , String colName) throws Exception {
        int type = DBApp.getType(tableName,colName); //int 0 Double 1 String 2 Date 3
        this.Refrences= new ArrayList<ArrayList>();
        Hashtable<String, Integer> Ranges = new Hashtable<String, Integer>();


        String [] min_max = DBApp.getmin_max(tableName,colName);
        if(min_max == null)
            throw new Exception("Couldn't find given tablename and colName");
        else
        {
            if(Table.deserialT(tableName)==0){
            if(type== 0 || type == 1 || type == 2){
                int min = return_number(min_max[0]);
                int max = return_number(min_max[1]);
                int diffrence = max-min;
                if(diffrence >10) {
                                double step =  ((diffrence * 0.1) + min);
                                for (int i = 0; i < 10; i++) {

                                            Ranges.put((i * step) + "-" + ((i + 1) * step), i);



                                }
                        }
                        else
                             if(type==3){




        }



            }
        }
    }}


    public static int return_number(String x){
        try{
            int value = Integer.parseInt(x);
    return value;

        }
        catch(Exception e){
            int value = 0;
            for (char c : x.toCharArray())
                value += c;

             return value;


        }

    }

    public void fillbuckets(){
        Enumeration<String> type = Ranges.keys();
        int i =0;

        while(type.hasMoreElements()){
            String x = type.nextElement();
            String[] currrange = x.split("-");
            int currmin =Integer.parseInt(currrange[0]);
            int currmax =Integer.parseInt(currrange[1]);
            //get all the tuples within the currrange into the arraylist
            for(int z = currmin; i<currmax;i++){


            }


        }




        }



    }



