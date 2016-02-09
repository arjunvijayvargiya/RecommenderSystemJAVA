/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendersystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @Arjun Vijayvargiya and Bikkumala Karthik
 * 
 */
public class RecommenderSystem {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BufferedReader br = null;
        int matrix[][]=new int[944][1683];
        try{
            br = new BufferedReader(new FileReader("C:\\Users\\ravi\\Desktop\\data.txt"));
            for(long i=0;i<100000;i++)
            {
                String line=br.readLine();
                StringTokenizer st=new StringTokenizer(line);
                String starray[]=new String[4];
                int k=0;
                while(st.hasMoreTokens())
                {
                   starray[k++]=st.nextToken();
                }  
                int userid=Integer.parseInt(starray[0]);
                int itemid=Integer.parseInt(starray[1]);
                int rating=Integer.parseInt(starray[2]);
                matrix[userid][itemid]=rating;
            }
            /*for(int i=1;i<944;i++)
            {
                for(int j=1;j<1683;j++)
                {
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.print("\n");
            }
            */
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a userid");
            int userinput=in.nextInt();
            //System.out.println("Enter a itemid");
            //int iteminput=in.nextInt();
            double predictarray[]=new double[1683];
            int cnt=0;
            int iteminput;
            int movieid[]=new int[1683];
            for(int p=1;p<1683;p++)
            {
             if(matrix[userinput][p]==0)
             { iteminput=p;
             
              double similarity[]=new double[944];
                for(int i=1;i<944;i++)
                {int n=0;
                      if(matrix[i][iteminput]!=0)
                      {
                          
                          double sigmax=0;
                          double sigmay=0;
                          double sigmax2=0;
                          double sigmay2=0;
                          double sigmaxy=0;
                          for(int j=1;j<1683;j++)
                          {
                              if(matrix[i][j]!=0 && matrix[userinput][j]!=0)
                              {   n++;
                                  sigmaxy=sigmaxy+matrix[userinput][j]*matrix[i][j];  //xy
                                  sigmax2=sigmax2+matrix[userinput][j]*matrix[userinput][j]; //x2
                                  sigmay2=sigmay2+matrix[i][j]*matrix[i][j];  //y2
                                  sigmax=sigmax+matrix[userinput][j];
                                  sigmay=sigmay+matrix[i][j];
                              }
                          }
                          double num=(n*sigmaxy)-(sigmax*sigmay);
                          double den=Math.sqrt(((n*sigmax2)-(sigmax*sigmax))*((n*sigmay2)-(sigmay*sigmay)));
                          similarity[i]=num/den;
                      }
                }
                //summation of userinput for every movie he has rated
                double meanall[]=new double[944];
                
                for(int j=1;j<944;j++)
                {int num=0;
                  for(int i=1;i<1683;i++)
                  {
                    if(matrix[j][i]!=0)
                    {   
                        num++;
                        meanall[j]=meanall[j]+matrix[j][i];
                    }
                  }
                  meanall[j]=meanall[j]/num;
                }
                double prednum=0.0;
                double predden=0.0;
                for(int i=1;i<944;i++)
                {
                    prednum=prednum+(similarity[i]*(matrix[i][iteminput]-meanall[i]));
                    predden=predden+similarity[i];
                }
                double predicted=meanall[userinput]+(prednum/predden);
                //System.out.println("Sisjhfsjfhdsjfhdsfkjsdhfjdsfhjsdfh");
                /*for(int i=0;i<944;i++)
                {
                    System.out.print(similarity[i]+" ");
                }*/
                //System.out.print("\n");
                //System.out.println("predden="+predden);
                //System.out.println("the predicted value by the system is"+predicted);
                if(Double.isNaN(predicted))
                    continue;
               predictarray[cnt]=predicted;
               movieid[cnt]=p;
               cnt++;
            }
            }
            /*System.out.println("printing the predicted values");
            for(int i=0;i<cnt;i++)
            {
               System.out.println("movie:"+movieid[i]+"predicted value"+predictarray[i]);
            }*/
            for(int i=0;i<cnt;i++)
            {    for(int j=0;j<cnt-i;j++)
                {
                    if(predictarray[j]<predictarray[j+1])
                    {
                        double tempd=predictarray[j];
                        predictarray[j]=predictarray[j+1];
                        predictarray[j+1]=tempd;
                        int tempi=movieid[j];
                        movieid[j]=movieid[j+1];
                        movieid[j+1]=tempi;
                    }
                }
            }
            System.out.println("the recommended movie for the user"+userinput);
            for(int i=0;i<10;i++)
            {
              System.out.println("movie:"+(i+1)+")"+movieid[i]);
            }
           }catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        
    }
    
}
