package trial;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
public class sec {


		private demo2[] sI = new demo2[186760];
			
		private String Emp_ID,First_Name,Last_Name,Gender,E_Mail,Fathers_Name,Mothers_Name,Date_of_Birth,Age ;
		private String Date_of_Joining,Salary,SSN,Phone_No;
		int reccount = 0;

		public void getData(){
		    		@SuppressWarnings("resource")
		    		Scanner scanner = new Scanner(System.in);
		    		System.out.println("Enter the Emp_ID: ");
		    		Emp_ID = scanner.next();
		    		System.out.println("Enter the First_Name: ");
		    		First_Name = scanner.next();
		    		System.out.println("Enter the Last_Name: ");
		    		Last_Name = scanner.next();
		    		System.out.println("Enter the Gender: ");
		    		Gender = scanner.next();
		    		System.out.println("Enter the E_Mail: ");
		    		E_Mail = scanner.next();
		    		System.out.println("Enter the Fathers_Name: ");
		    		Fathers_Name = scanner.next();
		    		System.out.println("Enter the Mothers_Name: ");
		    		Mothers_Name = scanner.next();
		    		System.out.println("Enter the Date_of_Birth: ");
		    		Date_of_Birth = scanner.next();
		    		System.out.println("Enter the Age: ");
		    		Age = scanner.next();
		    		System.out.println("Enter the Date_of_Joining: ");
		    		Date_of_Joining = scanner.next();
		    		System.out.println("Enter the Salary:  ");
		    		Salary = scanner.next();
		    		System.out.println("Enter the SSN:  ");
		    		SSN = scanner.next();
		    		System.out.println("Enter the Phone no:  ");
		    		Phone_No = scanner.next();
				System.out.println("WAITING");
		}
		public void add(){
			String data = Emp_ID +","+  First_Name +","+ Last_Name +","+ Gender +","+ E_Mail +","+ Fathers_Name +","+ Mothers_Name +","+ Date_of_Birth +","+ Age +"," + Date_of_Joining + "," +  Salary + "," + SSN + "," + Phone_No ;

			try{			
				RandomAccessFile recordfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
				recordfile.seek(recordfile.length());
				long pos = recordfile.getFilePointer();
				recordfile.writeBytes(data+"\n");
				recordfile.close();
								
				RandomAccessFile indexfile1 = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//index1.txt","rw");
				indexfile1.seek(indexfile1.length());
				indexfile1.writeBytes(SSN+","+pos+"\n");
				indexfile1.close();
			}
			catch(IOException e){
				System.out.println(e);
			} 
				
		 
		}                     
		@SuppressWarnings("resource")
		public void priIndex(){

			String line,seckey = null,pos = null;
			int count = 0;
			int sIIndex = 0;
			reccount=0;
			RandomAccessFile indexfile;
			try {
				indexfile = new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//index1.txt","rw");

				try {
					
					while((line = indexfile.readLine())!= null){
	                                     if(line.contains("*")) 
	                                     {
	                                    	 	continue;
	                                     }
						count = 0;
						StringTokenizer st = new StringTokenizer(line,",");
						while (st.hasMoreTokens()){
						 count+=1;
						 if(count==1)
					    seckey = st.nextToken();
						 pos = st.nextToken();                 
					    }
						sI[sIIndex] = new demo2();
						sI[sIIndex].setRecPos(pos);
						sI[sIIndex].setseckey(seckey);
						reccount++;
						sIIndex++;
	                                        if(sIIndex==200000)
	                                        {	                                      
	                                            break;    
	                                        }
	                                }
				} catch (IOException e) {	
					e.printStackTrace();
					return;
				}
			} catch (FileNotFoundException e) {	
				e.printStackTrace();
				return;
			} 
			
			System.out.println("total records" + reccount);
			if (reccount==1) { return;}
			sortIndex();
		}
			
			
		public void sortIndex() {
			demo2 temp = new demo2();
			
			for(int i=0; i<reccount; i++)
			    {	
					for(int j=i+1; j<reccount; j++) {
						if(sI[i].getseckey().compareTo(sI[j].getseckey())  > 0)
			            {
			                temp.setseckey(sI[i].getseckey()); 
					        temp.setRecPos(sI[i].getRecPos());
					
				        	sI[i].setseckey(sI[j].getseckey());
				        	sI[i].setRecPos(sI[j].getRecPos());
					
				        	sI[j].setseckey(temp.getseckey());
				        	sI[j].setRecPos(temp.getRecPos());
			            }
					}			
				}		
		}
		
		        public void search(){
		        	 System.out.println("Enter the SSN to search: ");
		             @SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
		             String ssn = scanner.next();
		             
		             
		             int oripos = binarySearch(sI, 0, reccount-1, ssn);
		             
		             if (oripos == -1) {
		                 System.out.println("Record not found in the record file");
		                 return ;
		             }
		             
		             int pos = oripos;
		             
		             do {
		                 readFile(pos);
		                 pos--;
		                 if (pos < 0) { break;}
		             }while(sI[pos].getseckey().compareTo(ssn)==0);
		             
		             pos = oripos + 1 ;
		             
		             while(sI[pos].getseckey().compareTo(ssn)==0 && pos > reccount-1) {
		                 readFile(pos);
		                 pos++;
		             }
		        }
		 public void readFile(int pos) {
		            
		            RandomAccessFile recordfile;
		            try {
		                recordfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
		                try {
		                    recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
		                    String record = recordfile.readLine();
		                    StringTokenizer st = new StringTokenizer(record,",");
		                    
		                    int count = 0;
		                       
		                    while (st.hasMoreTokens()){
		                             count += 1;
		                               if(count==1){
		                            	   String tmp_Emp_ID = st.nextToken();			                  	    	
 				                  	    	 
                                           if(tmp_Emp_ID.contains("*"))
                                           {
                                               System.out.println("it has been deleted");
                                               break;
                                           }
                                           System.out.println("Emp_ID: "+tmp_Emp_ID);
				                  	         this.Emp_ID = tmp_Emp_ID;
				                  	    	
				                  	        String tmp_First_Name = st.nextToken();
			                  	    	    System.out.println("First_Name: "+tmp_First_Name);
			                  	    	    this.First_Name = tmp_First_Name;
			                  	    	
			                  	    	    String tmp_Last_Name = st.nextToken();
			                  	    	    System.out.println("Last_Name: "+tmp_Last_Name);
			                     	      	this.Last_Name = tmp_Last_Name;
			                     	       
			                     	      	String tmp_Gender= st.nextToken();
			                     	      	System.out.println("Gender: "+tmp_Gender);
			                     	      	this.Gender = tmp_Gender;
			                  	    	
			                     	        String tmp_E_Mail = st.nextToken();
			                     	        System.out.println("E_Mail: "+tmp_E_Mail);
			                     	        this.E_Mail = tmp_E_Mail;
			                     	     
			                     	        String tmp_Fathers_Name = st.nextToken();
			                     	        System.out.println("Fathers_Name: "+tmp_Fathers_Name);
			                     	        this.Fathers_Name = tmp_Fathers_Name;
			                     	        
			                     	        String tmp_Mothers_Name = st.nextToken();
			                     	        System.out.println("Mothers_Name: "+tmp_Mothers_Name);
			                     	        this.Mothers_Name = tmp_Mothers_Name;
			                     	        
			                     	        String tmp_Date_of_Birth = st.nextToken();
			                     	        System.out.println("Date_of_Birth: "+tmp_Date_of_Birth);
			                     	        this.Date_of_Birth = tmp_Date_of_Birth;
			                     	        	 
			                     	        String tmp_Age = st.nextToken();
			                     	        System.out.println("Age: "+tmp_Age);
			                     	        this.Age = tmp_Age;
			                     	        
			                     	        String tmp_Date_of_Joining = st.nextToken();
			                     	        System.out.println("Date_of_Joining: "+tmp_Date_of_Joining);
			                     	        this.Date_of_Joining = tmp_Date_of_Joining;
			                     	        
			                     	        String tmp_Salary = st.nextToken();
			                     	        System.out.println("Salary: "+tmp_Salary);
			                     	        this.Salary = tmp_Salary;
			                     	        
			                     	        String tmp_SSN = st.nextToken();
			                     	        System.out.println("SSN: "+tmp_SSN);
			                     	        this.SSN = tmp_SSN;
			                     	        
			                     	        String tmp_Phone_No = st.nextToken();
			                     	        System.out.println("Phone_No: "+tmp_Phone_No);
			                     	        this.Phone_No = tmp_Phone_No;
			                  	    	 	System.out.println();
				                  	    	 }		                     	     			  				                  	    	 			                  	   
	                  	    	 else
	                  	    		 break;
		                       }
		                    
		                    recordfile.close();
		                } 
		                    catch (NumberFormatException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                } 
		                catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		                
		                
		                }
		                                        
		                catch (FileNotFoundException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		 }

		        int binarySearch(demo2 s[], int l, int r, String ssn) {
		    	
		    	int mid;
		    	while (l<=r) {
		            
		    		mid = (l+r)/2;
		    		if(s[mid].getseckey().compareTo(ssn)==0) {return mid;}
		    		if(s[mid].getseckey().compareTo(ssn)<0) l = mid + 1;
		    		if(s[mid].getseckey().compareTo(ssn)>0) r = mid - 1;
		    	}
		    	return -1;
		    }

		        public  void indexing() 
				  {
				         try{
				        RandomAccessFile recfile=new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
				        
				        RandomAccessFile indexfile=new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//index1.txt","rw");
				        String line;
				        long pos=recfile.getFilePointer();
				        while((line = recfile.readLine())!=null)
				        {
				            if(line.contains("*")) 
				            {
				            	continue;
					        }
				            String[] columns=line.split(",");
				         
				            indexfile.writeBytes(columns[11]+","+pos+"\n");
				            pos=recfile.getFilePointer();
				        } 
				        
				        indexfile.close();
				        recfile.close();

				        }
				    
				    catch(IOException e)
				    {
				        System.out.println(e);
				    }
				  }

		 public   void delete() throws IOException {
			 indexing();
		     
		     System.out.println("Enter the ssn to delete: ");
		     @SuppressWarnings("resource")
		     Scanner scanner = new Scanner(System.in);
		     String ssn = scanner.next();
		     String ans = "n";
		     int pos;
		     int oripos = binarySearch(sI, 0, reccount-1, ssn);
		     System.out.println("WAIT FOR FEW SECONDS....: ");
		     if (oripos == -1) {
		         System.out.println("Record not found in the record file");
		         return ;
		     }
		     
		     pos = oripos;
		     
		     do {
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, ssn);
		         }
		         pos--;
		         if (pos < 0) { break;}
		     }while(sI[pos].getseckey().compareTo(ssn)==0);
		         
		     pos = oripos + 1 ;
		     
		     while(sI[pos].getseckey().compareTo(ssn)==0 && pos > reccount-1){
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, ssn);
		             break;
		         }
		         pos++;
		         if (pos > reccount-1) { break;}
		     }
		}
		 
	 public void markDelete(int pos, String ssn) {
	     try {
	         RandomAccessFile recordfilee = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
	         @SuppressWarnings("resource")
	         RandomAccessFile indexfilee = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//index1.txt","rw");
	     
	             recordfilee.seek(Long.parseLong(sI[pos].getRecPos()));
	             recordfilee.writeBytes("*");
	             System.out.println("Done");
	             recordfilee.close();
	             String line;
	             String indexName;
	             long indexPos = 0;
	             long delPosi;
	            
	             while((line = indexfilee.readLine())!=null) {
	                 if(line.contains("*"))
	                     continue;
	                 StringTokenizer st = new StringTokenizer(line,",");
	                 delPosi = indexfilee.getFilePointer();
	                 delPosi = delPosi - (line.length()+2);
	                 
	                 while(st.hasMoreTokens()) {
	                     indexName=st.nextToken();
	                     indexPos= Long.parseLong(st.nextToken());
	                     
	                     if(indexName.equals(ssn) && indexPos == Long.parseLong(sI[pos].getRecPos()) ) {
	                         indexfilee.seek(delPosi);
	                         indexfilee.writeBytes("*");
	                         indexfilee.close();
	                         System.out.println("Deleted");
	                         indexing();
	                         return;
	                     }
	                 }
	             }
             }
	         
	         catch (Exception e) {
	             e.printStackTrace();
	         }
		 }
	

	}
