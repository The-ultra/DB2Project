import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

public class Bucket extends Vector<Object> {
  Vector<Object> buck;
  public Bucket() {

  }

  public void addToBucket(Object A){
    if(this.checkFull()){
      buck.add(A);

    }
  }


  public boolean checkFull(){
    //full = false --- can accept entries=true
    boolean res=false;
    Properties prop = new Properties();
    String fileName = "src/main/resources/DBApp.config";
    InputStream is = null;
    try {
      is = new FileInputStream(fileName);
    } catch (FileNotFoundException ex) {

    }
    try {
      prop.load(is);
      int max = Integer.parseInt(prop.getProperty("MaximumKeysCountinIndexBucket"));
      if (this.size()==max){
        res= true;
      }
      else
        res= false;
    }
    catch (IOException ex) {


    }

    return res;
  }





}