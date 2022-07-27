package trial;

import java.io.*;

import java.util.Scanner;
import java.util.StringTokenizer;
public class pri {

	private demo1[] sI = new demo1[200000];
	
	private String Emp_ID,First_Name,Last_Name,Gender,E_Mail,Fathers_Name,Mothers_Name,Date_of_Birth,Age ;
	private String Date_of_Joining,Salary,SSN,Phone_No;
	int reccount = 0;
	int reccount1=0;
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
	    		
			System.out.println("WAITING...");
	}
	
	public void add(){
		String data = Emp_ID +","+  First_Name +","+ Last_Name +","+ Gender +","+ E_Mail +","+ Fathers_Name +","+ Mothers_Name +","+ Date_of_Birth +","+ Age +"," + Date_of_Joining + "," +  Salary + "," + SSN + "," + Phone_No ;
			try{			
				RandomAccessFile recordfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
				recordfile.seek(recordfile.length());
				long pos = recordfile.getFilePointer();
				recordfile.writeBytes(data+"\n");
				recordfile.close();
				
				RandomAccessFile indexfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//index.txt","rw");
				indexfile.seek(indexfile.length());
				indexfile.writeBytes(Emp_ID+","+pos+"\n");
				indexfile.close();
				
				RandomAccessFile indexfile1 = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//index1.txt","rw");
				indexfile1.seek(indexfile1.length());
				indexfile1.writeBytes(SSN+","+pos+"\n");
				indexfile1.close();
			}
			catch(IOException e){
				System.out.println(e);
			} 
	}  	
		
	public void unPack(){
		try{
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt"));
    		String line;
                try{
                while((line = reader.readLine())!= null){
                	
                	if(line.contains("*")) {
						continue;
						}
                	
                	int count = 0;
                	StringTokenizer st = new StringTokenizer(line,",");
                	
                	while (st.hasMoreTokens()){
           	    	 count += 1;
           	    	 if(count==1) {
           	    		 System.out.println("Emp_ID: "+st.nextToken());
           	    		 System.out.println("First_Name: "+st.nextToken());
	           	    	 System.out.println("Last_Name: "+st.nextToken());
	           	    	 System.out.println("Gender: "+st.nextToken()); 
	           	    	 System.out.println("E_Mail: "+st.nextToken());
	           	    	 System.out.println("Fathers_Name: "+st.nextToken());
	           	    	 System.out.println("Mothers_Name: "+st.nextToken());
	          	    	 System.out.println("Date_of_Birth : "+st.nextToken());
	          	    	 System.out.println(" Age: "+st.nextToken());
	          	    	 System.out.println("Date_of_Joining : "+st.nextToken());
	         	    	 System.out.println("Salary : "+st.nextToken());
	         	    	 System.out.println("SSN : "+st.nextToken());
	         	    	 System.out.println("Phone_No : "+st.nextToken());
	           	    	 System.out.println();
           	    	 }
           	    	
           	    	 else {
           	    		 break;
           	    	}
                   }
                 }
                }
                catch(Exception e){return;}
    		}
			catch(IOException e){
				return;
			}
	}
	    
	     
	  public  void indexing() 
	  {
	         try{
	        RandomAccessFile recfile=new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
	        
	        RandomAccessFile indexfile=new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//index.txt","rw");
	        String line;
	        long pos=recfile.getFilePointer();
	        while((line = recfile.readLine())!=null)
	        {
	            if(line.contains("*")) 
	            {
	            	continue;
		        }
	            String[] columns=line.split(",");
	         
	            indexfile.writeBytes(columns[0]+","+pos+"\n");
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
	  @SuppressWarnings("resource")
		public void priIndex(){

			String line,prikey = null,pos = null;
			int count = 0;
			int sIIndex = 0;
			reccount=0;
			RandomAccessFile indexfile;
			try {
				indexfile = new RandomAccessFile("C://Users//SURAJ//Desktop//File-Structures-master//index.txt","rw");

				try {
					
					while((line = indexfile.readLine())!= null){
	                                     if(line.contains("*")){
	                                    	 	continue;
	                                     }
						count = 0;
						StringTokenizer st = new StringTokenizer(line,",");
						while (st.hasMoreTokens()){
						 count+=1;
						 if(count==1)
					    prikey = st.nextToken();
						 pos = st.nextToken();                 
					    }
						sI[sIIndex] = new demo1();
						sI[sIIndex].setRecPos(pos);
						sI[sIIndex].setprikey(prikey);
						reccount++;
						sIIndex++;
						if(sIIndex==200000)
                        {	                                      
                            break;    
                        }                    
	                  }
				} 
				catch (IOException e) {	
					e.printStackTrace();
					return;
				}
			} 
			catch (FileNotFoundException e) {	
				e.printStackTrace();
				return;
			} 
			
			System.out.println("total records" + reccount);
			if (reccount==1) { 
				return;
				}
			sortIndex();
		}
	  
		public void sortIndex() {
			demo1 temp = new demo1();
			
			for(int i=0; i<reccount; i++)
			    {	
					for(int j=i+1; j<reccount; j++) {
						if(sI[i].getprikey().compareTo(sI[j].getprikey())  > 0)
			            {
			                temp.setprikey(sI[i].getprikey()); 
					        temp.setRecPos(sI[i].getRecPos());
					
				        	sI[i].setprikey(sI[j].getprikey());
				        	sI[i].setRecPos(sI[j].getRecPos());
					
				        	sI[j].setprikey(temp.getprikey());
				        	sI[j].setRecPos(temp.getRecPos());
			            }
					}			
				}		
		}
		
		
        int binarySearch(demo1 s[], int l, int r, String prikey) {
	    	int mid;
	    	while (l<=r) { 
	    		mid = (l+r)/2;
	    		if(s[mid].getprikey().compareTo(prikey)==0) {return mid;}
	    		if(s[mid].getprikey().compareTo(prikey)<0) l = mid + 1;
	    		if(s[mid].getprikey().compareTo(prikey)>0) r = mid - 1;
	    	}
	    	return -1;
	    }
	            
    public void search(){
        System.out.println("Enter the Emp ID to search: ");
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				String Emp_ID = scanner.next();
				System.out.println(reccount);
				int pos = binarySearch(sI, 0, reccount-1, Emp_ID);
				
				if (pos == -1) {
					System.out.println("Record not found in the record file");
					return ;
				}
				
				RandomAccessFile recordfile;
				try {
					recordfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
					try {
						recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
						String record = recordfile.readLine();
						StringTokenizer st = new StringTokenizer(record,",");
						int length = record.length();
						int count = 0;
						                            
	                	while (st.hasMoreTokens()){
	                		     count+=1;  
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
			                  	    	 	
			                  	    	 	System.out.println("Do you want to modify????? Y/N");
			                  	    	 	String modi = scanner.next();
			                  	    	 	
    			                  	    	 	if ( modi.equalsIgnoreCase("y"))
    			                  	    	 	{
    			                  	    	 		System.out.println("What do you want to change");
    			                  	    	 		System.out.println("Enter your choice");
    			                  	    	 		System.out.println("1.Emp_ID \n 2. First_Name \n 3.Last_Name");

    			                  	    	           int choice = scanner.nextInt();
    			                  	    	           switch(choice)
    			                  	    	           {
	    			                  	    	           case 1 :System.out.println("Enter the Emp_ID ");
	    			                  	    	           tmp_Emp_ID=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 2 :System.out.println("Enter the First_Name ");
	    			                  	    	           tmp_First_Name=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 3: System.out.println("Enter the Last_Name ");
	    			                  	    	           tmp_Last_Name=scanner.next();
	    			                  	    	           break;
	    			                  	    	           
	    			                  	    	           case 4 :System.out.println("Enter the Gender ");
	    			                  	    	           tmp_Gender=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 5 :System.out.println("Enter the E_Mail ");
	    			                  	    	           tmp_E_Mail=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 6: System.out.println("Enter the Fathers_Name ");
	    			                  	    	           tmp_Fathers_Name=scanner.next();
	    			                  	    	           break;
	    			                  	    	           
	    			                  	    	           case 7 :System.out.println("Enter the Mothers_Name ");
	    			                  	    	           tmp_Mothers_Name=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 8 :System.out.println("Enter the Date_of_Birth ");
	    			                  	    	           tmp_Date_of_Birth=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 9: System.out.println("Enter the Age ");
	    			                  	    	           tmp_Age=scanner.next();
	    			                  	    	           break;
	    			                  	    	           
	    			                  	    	           case 10 :System.out.println("Enter the Date_of_Joining ");
	    			                  	    	           tmp_Date_of_Joining=scanner.next();
	    			                  	    	     
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 11 :System.out.println("Enter the Salary ");
	    			                  	    	           tmp_Salary=scanner.next();
	    			                  	    	           break;
	    			                  	    	     
	    			                  	    	           case 12: System.out.println("Enter the SSN ");
	    			                  	    	           tmp_SSN=scanner.next();
	    			                  	    	           break;
	    			                  	    	           
	    			                  	    	           case 13: System.out.println("Enter the Phone_No ");
	    			                  	    	           tmp_Phone_No=scanner.next();
	    			                  	    	           break;
    			                  	    	           }
    			                  	    	           long pointer = recordfile.getFilePointer();
    			                  	    	           System.out.println("offset: "+ pointer);
    			                  	    	           String pack = tmp_Emp_ID +","+  tmp_First_Name +","+ tmp_Last_Name +","+ tmp_Gender +","+ tmp_E_Mail +","+ tmp_Fathers_Name +","+ tmp_Mothers_Name +","+ tmp_Date_of_Birth +","+ tmp_Age +"," + tmp_Date_of_Joining + "," +  tmp_Salary + "," + tmp_SSN + "," + tmp_Phone_No ;
    			                  	    	          if( pack.length()>length)
    			                  	    	          {
    			                  	    	        	
    			                   						if(reccount==1) {
    			                       						pointer = 0;
    			                       					}
    			                       					else {
    			                       						pointer = pointer-(length+1);
    			                       					}
    			                   						
    			                   						recordfile.seek(pointer);
    			                   						recordfile.writeBytes("*");//deleting a record//marking it as deleted
    			                   						recordfile.seek(recordfile.length());
	    			                  	    	         recordfile.writeBytes(pack+"\n");
	    			                  	    	         recordfile.close();
    			                  	    	          }
    			                  	    	        else {
    			                   						if(reccount==1) {
    			                       						pointer = 0;
    			                       					}
    			                       					else {
    			                       						pointer = pointer-(length+1);
    			                       					}
    			                       					
    			                    					recordfile.seek(pointer);
    			            							recordfile.writeBytes(pack);
    			            							recordfile.close();	
    			            						}
    			                  	    	              
    			                  	    	 	}
    			                  	    	 	else
    			                  	    	 	{
    			                  	    	 		System.out.println("ok done");
			                  	    	 	}
    			                  	    	
				                  	    }	
	                  	    	 
	                  	    	 else
	                  	    		 break;
	                       }
	                	recordfile.close();
	                	
					 }
						catch (NumberFormatException e) {
						e.printStackTrace();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
											
            	catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
    }
	 public  void delete() throws IOException {
	         System.out.println("Enter the primary key to delete record ");
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(System.in);
						String prikey = scanner.next();
						int pos = binarySearch(sI, 0, reccount-1, prikey);
						System.out.println("WAIT FOR FEW SECONDS....: ");	
						if (pos == -1) {
							System.out.println("Record not found in the record file");
							return ;
						}

						 RandomAccessFile recordfile = new RandomAccessFile ("C://Users//SURAJ//Desktop//File-Structures-master//records//humanresources.txt","rw");
							try {
								recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
                                recordfile.writeBytes("*");
                                recordfile.close();                    
	                           }    
	                                                            
							catch (NumberFormatException e) {
								e.printStackTrace();
							} 
							catch (IOException e) {		
								e.printStackTrace();
							}
					}

	}
	