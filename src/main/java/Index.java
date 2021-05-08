
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

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
            if(type== 0){
                int min = return_number(min_max[0]);
                int max = return_number(min_max[1]);
                int diffrence = max-min;
                if(diffrence >10) {
                    if (diffrence % 10 == 0) {
                        int step = (int) ((diffrence * 0.1) + min);
                        for (int i = 0; i < 10; i++) {
                            Ranges.put((i * step) + "-" + ((i + 1) * step), i); //90 not includin

                        }
                    } else {
                        if (diffrence % 10 == 0) {
                            int step = (int) ((diffrence * 0.1) + min);
                            for (int i = 0; i < 10; i++) {
                                Ranges.put((i * step) + "-" + ((i + 1) * step), i);

                            }


                        }

                    }
                }


            }else
                if(type==1){

            }else
                if(type==2){

                }else
                    if(type==3){

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


    }
