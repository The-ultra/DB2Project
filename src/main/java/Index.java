
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

public class Index   {
    ArrayList Ranges ;
    Hashtable<String, String> ht2;

    ArrayList Refrences;


    public Index(String tableName , String colName) throws Exception {

        this.Ranges = new ArrayList<Object>();
        this.Refrences= new ArrayList<ArrayList>();
        String [] min_max = DBApp.getmin_max(tableName,colName);
        if(min_max == null)
            throw new Exception("Couldn't find given tablename and colName");
        else
        {
            int min = return_number(min_max[0]);
            int max = return_number(min_max[1]);
            int diffrence = max-min;
            if(diffrence%10== 0){

            }
            else if(diffrence <10){
                Hashtable<String, String> ht2 = new Hashtable<String, String>();
                for(int i=0 ; i<diffrence ;i++){
                   // ht2.put(i+"-"+(i+1),Refrences.get(i);

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


    }
