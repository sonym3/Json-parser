/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunday3;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 1895268
 */
public class Sunday3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
      
        
         String json = FileReader.loadFileIntoString("json/Manager.json", "UTF-8");
         JSONObject mainObject = JSONObject.fromObject(json);
         int var;
         int mid;
         mid = mainObject.getInt("manager_number");
  
    
         String lname;         
         lname = mainObject.getString("last_name");     
         String ID= mid +  " - " + lname;
         
         String date;
         date = mainObject.getString("date_hire");   
         int year = Integer.parseInt(date.substring(date.lastIndexOf(" ")+1)) ;
         
         
         JSONArray department = mainObject.getJSONArray("departments");
         
         int numberEmployees=0;
         double months=0;
         String[] dept = new String[5];
         String[] current = new String[5];
         for(int i=0;i<department.size();i++){
             
             JSONObject details = department.getJSONObject(i);
             numberEmployees=numberEmployees+ details.getInt("numberEmployees");
             months = months + details.getInt("months");
             
             String d_id = details.getString("department_id");
             String d_name = details.getString("department_name");
             dept[i] = d_id + " - " + d_name;
             
             Boolean currentValue = details.getBoolean("current");
             if(currentValue)
                current[i] = "Is Current";
             else
                 current[i] = "Is Not Current";
             
             
         }
         
         /*
         System.out.println(ID);
         System.out.println(year);
         System.out.println(numberEmployees);
         System.out.println(months/department.size());*/

           JSONObject mainOjectOut = new JSONObject();
           mainOjectOut.accumulate("ID", ID);
           mainOjectOut.accumulate("year_hire", year);
           mainOjectOut.accumulate("totalEmployee", numberEmployees);
           mainOjectOut.accumulate("averageMonths", months/department.size());
           
           JSONArray departmentsOut = new JSONArray();
           JSONObject singleDetailsOut = new JSONObject();
           
          for(int j=0;j<5;j++){
              singleDetailsOut.accumulate("department", dept[j]);
              singleDetailsOut.accumulate("current", current[j]);
              departmentsOut.add(singleDetailsOut);
              singleDetailsOut.clear();
          }

          
           mainOjectOut.accumulate(" departments ", departmentsOut);
          // System.out.println(departmentsOut);
         
         FileWriter.saveStringIntoFile("json/ManagerResult.json", mainOjectOut.toString());
              
        }
        
        }
    

