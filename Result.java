import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;
/**
 * This "Result" program calculates result of students and displays it on the standard output.
 * The input() function opens a dialogue box for the user to select a CSV File.
 * The PECGrades(CSVParser) function calculates the grades of the students listed in the CSV File
 * according to the grading method provided in the prospectus of PEC University of Technology and displays them
 * on the standard output.
 */

public class Result
{
    public static void main(String args[])
    {       
       PECGrades(input());
    }
    public static CSVParser input()
    {
        /**
         * Takes in input as a CSV File.
         * Format of CSV FIle should be- SID , Name, Marks - as the coloumn headers.
         */
        FileResource fr = new FileResource();
        return fr.getCSVParser();  //Returns a parsed CSV File.
    }    
    public static void PECGrades(CSVParser parser)
    {
        
        double sum1=0,mean1,sum2=0,count=0,sDeviation=0,var=0;        
        for(CSVRecord currStudent: parser)
        {
            double currMarks=Double.parseDouble(currStudent.get("Marks"));
            sum1+=currMarks;
            count++;
            sum2+=(currMarks*currMarks);            
        }
        mean1 = (float)sum1/count;  //Mean of the data.
        var = ((float)sum2/count) - (mean1*mean1);   //Variance of the data.
        sDeviation = Math.sqrt(var);      //Standard Deviation of the data.         
        int a=0;
        for(CSVRecord currStudent: parser)
        {
            /**
             * Conditions for grading according to the prospectus.
             * Displays the grades against student SID and student Name .
             */
            double currMarks=Double.parseDouble(currStudent.get("Marks"));
            if((currMarks>(mean1+1.5*sDeviation)) && (a<=(10.0*count)/100.0))
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   A+");
            }
            else if((currMarks>(mean1+1.0*sDeviation)) && (a>(10.0*count)/100.0))
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   A");
            }
            else if(currMarks>(mean1+0.5*sDeviation)) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   B+");
            }

            else if(currMarks>mean1) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   B");
            }
            else if(currMarks>(mean1-0.5*sDeviation)) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   C+");
            }
            else if(currMarks>(mean1-1.0*sDeviation)) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   C");
            }
            else if(currMarks>(mean1-1.5*sDeviation)) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   D");
            }
            else if(currMarks<=(mean1-1.5*sDeviation)) 
            {         
                a++;
                System.out.println(a+". "+currStudent.get("SID") +"  "+currStudent.get("Name")+"   F");
            }         
                 
        }          
        
    }
}
