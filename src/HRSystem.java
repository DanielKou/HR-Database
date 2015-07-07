/**
 * Program:     HR System
 * By:          Daniel Kou
 * Date:        November 13, 2013
 * Purpose: 	This program simulates a human resource system. It allows the user to add/remove employees from the company
 *              directory, sort the employees by a variety of factors, modify the employee data, and save/load their work.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;


public class HRSystem {

	////////////IO////////////
	
    //Name:         saveFile method
    //Param:        Employee ArrayList
    //Purpose:      Writes employee information onto a text file to be read later
	
	static int employeeCount;
	
	
    public static void saveFile(ArrayList<Employee> list) throws FileNotFoundException {
        PrintWriter p = new PrintWriter("record.txt"); //indicates which textfile
        StringTokenizer x;
        
        
        try { //outputs employee info
            for (Employee i: list) {
            	p.write("Entry ");
            	x = new StringTokenizer(i.getFirstName(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                x = new StringTokenizer(i.getLastName(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                p.write(i.getGender() + " ");
                p.write(i.getAge() + " ");
                
                x = new StringTokenizer(i.getAddress(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                p.write(String.valueOf(i.getSalary()) + " ");
                
                x = new StringTokenizer(i.getPhoneNumber(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                x = new StringTokenizer(i.getDepartment(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                x = new StringTokenizer(i.getJobTitle(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                x = new StringTokenizer(i.getStatus(), " ");
                while (x.hasMoreTokens()) {
                	p.write(x.nextToken() + "-");                	
                }
                p.write(" ");
                
                p.write(i.getEmail() + " ");
                if (!(i.getSin() == null)) { //in case SIN was not entered
                    p.write(1 + " ");
                    p.write(i.getSin() + " ");
                }
                else
                    p.write(0 + " ");
            }
        } finally {
                p.close(); //closes the PrintWriter
        }
    }
    
    //Name:         readFile method
    //Param:        Employee ArrayList
    //Param:		File record
    //Purpose:      Reads the text file (from previously saved employee info) and inputs the information onto the Employee ArrayList
    private static void readFile(ArrayList<Employee> list, File record) throws FileNotFoundException {
        Scanner r = new Scanner(record); //reads the record file
        String temp;
        StringTokenizer x;
        
        try {
            while (r.hasNext("Entry")) { //adds information to an employee object variable
            	r.next();
            	
                Employee newPerson = new Employee();
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken() + " ";
                }
                newPerson.setFirstName(temp);
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken() + " ";
                }
                newPerson.setLastName(temp);
                
                newPerson.setGender(r.next().charAt(0));
                newPerson.setAge(r.nextInt());
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken() + " ";
                }
                newPerson.setAddress(temp);
                
                newPerson.setSalary(Double.valueOf(r.next()));
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken();
                }
                newPerson.setPhoneNumber(temp);
                
                newPerson.setEmployeeNumber(list.size() + 1);
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken();
                }
                newPerson.setDepartment(temp);
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken();
                }
                newPerson.setJobTitle(temp);
                
                temp = r.next();
                x = new StringTokenizer (temp, "-");
                temp = "";
                while (x.hasMoreTokens()) {
                	temp += x.nextToken();
                }
                newPerson.setStatus(temp);
                
                newPerson.setEmail(r.next());
                if (r.next().equals("1"))
                    newPerson.setSin(r.next());
                
                list.add(newPerson); //adds employee object to ArrayList
            }
        } finally {
            r.close(); //closes the Scanner
        }
    }
    
    ////////////MENUS////////////
    
    //Name:         printMenu method
    //Param:        N/A
    //Purpose:      Outputs a menu in the console window so the user knows the options available
    public static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("\t1. Hire Employee");
        System.out.println("\t2. Fire Employee");
        System.out.println("\t3. Display Employees");
        System.out.println("\t4. Sort Employees");
        System.out.println("\t5. Calculate Salaries");
        System.out.println("\t6. Change Employee Data");
        System.out.println("\t7. Promote/Demote Employee");
        System.out.println("\t8. Save Changes");
        System.out.println("\t9. Quit");
        System.out.print("Choose an option (1-9): ");
    }
    
    //Name:         printSortMenu method
    //Param:        N/A
    //Purpose:      Prints the sort menu, so users know how they can sort the Employee ArrayList
    private static void printSortMenu() {           
        System.out.println("Sort Menu:");
        System.out.println("\t1. Sort By First Name (A-Z)");
        System.out.println("\t2. Sort By Last Name (A-Z)");
        System.out.println("\t3. Sort By Salary");
        System.out.println("\t4. Sort By Age");
        System.out.println("\t5. Sort By Employee Number");
        System.out.println("\t6. Reverse Order");
        System.out.println("\t7. Return To Main Menu");
        System.out.print("Choose an option (1-7): ");
    }
    
    //Name:         printModifyMenu method
    //Param:        N/A
    //Purpose:      Prints the modify menu, which is called when the user wants to modify employee info. The modify menu
    //                      shows the user what may be changed.
    private static void printModifyMenu() {
        System.out.println("Modify Menu:");
        System.out.println("\t1. Change First Name");
        System.out.println("\t2. Change Last Name");
        System.out.println("\t3. Change Gender");
        System.out.println("\t4. Change Age");
        System.out.println("\t5. Change Address");
        System.out.println("\t6. Change SIN");
        System.out.println("\t7. Change Salary");
        System.out.println("\t8. Change Phone Number");
        System.out.println("\t9. Change Department");
        System.out.println("\t10. Change Job Title");
        System.out.println("\t11. Change Status");
        System.out.println("\t12. Change Email");
        System.out.println("\t13. Return To Main Menu");
        System.out.print("Choose an option (1-13): ");
    }
    
    ////////////SORTS & REVERSE////////////
    
    //Name:         reverse method
    //Param:        Employee ArrayList
    //Purpose:      Orders the ArrayList in reverse order
    private static void reverse(ArrayList <Employee> list) {
        
    	int length = list.size();
    	
        for (int x = 0; x < length; x++)
        {
            list.add(x, list.get(length - 1)); //takes last index and moves it to first
            list.remove(length); //removes the last index
        }
    }

    
    //Name:         sortAge method
    //Param:        Employee ArrayList
    //Purpose:      Sorts the ArrayList by age
    private static void sortAge(ArrayList <Employee> list) {
    	
    	int length = list.size();
    
        //bubble sort - compares adjacent integers. If right int is smaller, switch the order of the 2
	    for (int x = 0; x < length; x++)
	    {
            for (int y = 1; y < length; y++)
            {   //sets variables to the ages
                int age1 = list.get(y).getAge();
                int age2 = list.get(y - 1).getAge();
                
                if (age1 < age2) //switches order if right one is less
                {
                    Employee temp = list.get(y);
                    list.set(y, list.get(y-1));
                    list.set(y - 1, temp);
                        
                }
            }
	    }
    }

    
    //Name:         sortSalary method
    //Param:        Employee ArrayList
    //Purpose:      Sorts the ArrayList by salary
    private static void sortSalary(ArrayList <Employee> list) {
            
        int length = list.size();
            
        //bubble sort
        for (int x = 0; x < length; x++)
        {
            for (int y = 1; y < length; y++)
            {
                double money1 = list.get(y).getSalary();
                double money2 = list.get(y - 1).getSalary();
                
                if (money1 < money2) //switches if money2 is greater
                {
                    Employee temp = list.get(y);
                    list.set(y, list.get(y-1));
                    list.set(y - 1, temp);
                        
                }
                    
            }
        }
    }

    
    //Name:         sortLastName method
    //Param:        Employee ArrayList
    //Purpose:      Sorts the ArrayList by last name
    private static void sortLastName(ArrayList <Employee> list) {
            
    	int length = list.size();
    
        //bubble sort
	    for (int x = 0; x < length; x++)
	    {
            for (int y = 1; y < length; y++)
            {
                String name1 = list.get(y).getLastName();
                String name2 = list.get(y - 1).getLastName();
                
                if (name1.compareTo(name2) < 0)
                {
                    Employee temp = list.get(y);
                    list.set(y, list.get(y-1));
                    list.set(y - 1, temp);
                        
                }
                    
            }
	    }  
    }

    
    //Name:         sortFirstName method
    //Param:        Employee ArrayList
    //Purpose:      Sorts the ArrayList by first name
    private static void sortFirstName(ArrayList <Employee> list) {
            
        int length = list.size();
    
        //bubble sort
        for (int x = 0; x < length; x++)
        {
            for (int y = 1; y < length; y++)
            {
                String name1 = list.get(y).getFirstName();
                String name2 = list.get(y - 1).getFirstName();
                
                if (name1.compareTo(name2) < 0)
                {
                    Employee temp = list.get(y);
                    list.set(y, list.get(y-1));
                    list.set(y - 1, temp);
                        
                }
                    
            }
        }
    }
    
    
    private static void sortEmployeeNum (ArrayList <Employee> list){
    	
    	int length = list.size();
        
        //bubble sort
        for (int x = 0; x < length; x++)
        {
            for (int y = 1; y < length; y++)
            {
                int num1 = list.get(y).getEmployeeNumber();
                int num2 = list.get(y - 1).getEmployeeNumber();
                
                if (num1 < num2) //switches if num2 is greater
                {
                    Employee temp = list.get(y);
                    list.set(y, list.get(y-1));
                    list.set(y - 1, temp);
                        
                }
                    
            }
        }
    }
    
    ////////////OTHER METHODS////////////
    
    //Name:         findEmployee method
    //Param:        Employee ArrayList, first name, last name
    //Purpose:      checks for the index of a certain employee in the employee ArrayList.
    //                      Returns the index of that employee (or -1 if the employee is not found)
    private static int findEmployee(ArrayList <Employee> list, String first, String last) {
            
        int length = list.size();
        
        for (int x = 0; x < length; x++)
        {       //checks through all employees if first name matches
        	String s = list.get(x).getFirstName();
                if (s.equals(first))
                {       //then checks if last name matches
                        if (list.get(x).getLastName().equals(last))
                        {
                                return x; //return index of employee
                        }
                }
        }
        return (-1); //if employee is not found
    }


    //Name:         displayEmployees method
    //Param:        Employee ArrayList
    //Purpose:      Displays a general table showing all the employees
    private static void displayEmployees(ArrayList <Employee> list) {
        //if ArrayList is empty
        if (list.isEmpty())
        {
            System.out.println("\nNo employees have been entered");
        }
        else
        {
            System.out.print ("\nEmployee\t\tLast Name\tFirst Name\n");
            for (int x = 0; x < list.size(); x++)
            {
            	if (list.get(x).getLastName().length() < 4)
                System.out.print ((x+1) + "\t\t\t" + list.get(x).getLastName() + "\t\t\t" + list.get(x).getFirstName() + "\n");
            	else if (list.get(x).getLastName().length() < 8)
            		System.out.print ((x+1) + "\t\t\t" + list.get(x).getLastName() + "\t\t" + list.get(x).getFirstName() + "\n");
            	else if (list.get(x).getLastName().length() < 12)
            		System.out.print ((x+1) + "\t\t\t" + list.get(x).getLastName() + "\t" + list.get(x).getFirstName() + "\n");

            
            }
    }
    }
    
    
    //Name:         displayEmployeesInfo method
    //Param:        Employee ArrayList, index of employee
    //Purpose:      The user selects which employee's information they would like to view. The program then displays
    //                      this information
    private static void displayEmployeesInfo (ArrayList <Employee> list, int index)
    {        
    	if (index <= list.size()){
    		System.out.println ("\nLast Name\tFirst Name\tGender\tAge\t\tAddress");
            
            //if statements properly format the format
            if (list.get(index).getLastName().length() < 4)
                System.out.print (list.get(index).getLastName() + "\t\t");
            else if (list.get(index).getLastName().length() < 8)
                System.out.print (list.get(index).getLastName() + "\t\t");
            else if (list.get(index).getLastName().length() < 12)
                System.out.print (list.get(index).getLastName() + "\t");
            else
                System.out.print (list.get(index).getLastName());
            
             if (list.get(index).getFirstName().length() < 4)
                System.out.print (list.get(index).getFirstName() + "\t\t\t");
             else  if (list.get(index).getFirstName().length() < 8)
                System.out.print (list.get(index).getFirstName() + "\t\t");
             else  if (list.get(index).getFirstName().length() < 12)
                System.out.print (list.get(index).getFirstName() + "\t");
             else 
                System.out.print (list.get(index).getFirstName());
             
             
             System.out.println (list.get(index).getGender() + "\t" + list.get(index).getAge() + "\t\t" + list.get(index).getAddress());
            
             System.out.print ("\nSIN: ");
             System.out.print (list.get(index).getSin());
            
             System.out.print ("\t\tEmployee Number: ");
             DecimalFormat a = new DecimalFormat ("000"); //formats the employee number so 3 digits are shown
             System.out.print (a.format(list.get(index).getEmployeeNumber()));
            
             DecimalFormat b = new DecimalFormat ("$###,###,###.00"); //formats salary with "$" and commas to represent digits
             System.out.print ("\t\tSalary: ");
             System.out.println (b.format(list.get(index).getSalary()));
             
             System.out.print ("\nDepartment: ");
             System.out.print(list.get(index).getDepartment());
             
             System.out.print ("\t\tJob Title: ");
             System.out.print (list.get(index).getJobTitle());

             System.out.print ("\t\tStatus: ");
             System.out.println (list.get(index).getStatus());
             
             System.out.print ("\nPhone Number: ");
             System.out.print (list.get(index).getPhoneNumber());
            
             System.out.print ("\t\tEmail: ");
             System.out.print (list.get(index).getEmail());
            
             System.out.println("\n\n\n");
    	}
    	else{
    		System.out.println("Invalid number entered");
    	}
        
    }
    
    

    //Name:         calculateSalaries method
    //Param:        Employee ArrayList
    //Purpose:      Calculates the company's total salary expense. Returns the total expense.
    private static double calculateSalaries(ArrayList <Employee> list) {
    	
        double total = 0;
        
        //goes through all employees' salaries
        for (int x = 0; x < list.size(); x++)
        {
            total += list.get(x).getSalary();
        }
        return total;
    }
    
    //Name:         checkSIN method
    //Param:        SIN 
    //Purpose:      Checks if the entered SIN is valid. Returns boolean whether it is valid.
    private static boolean checkSIN (String SIN) {
    	
        int x, total = 0, odd = 0, even;
        //looks at the even digits starting at the 2nd digit on the left
        if (SIN.length() != 9)
        {
            return false;
        }
        
        for (x = 1 ; x < 9 ; x += 2)
        {
            //converts the char into an int, and the doubles it
            even = (int) (SIN.charAt (x) - '0') * 2;
            //adds each digit of the doubled number into the total
            total += even % 10;
            //if there are 2 digits after doubling
            if (even / 10 > 0)
                total += (even / 10) % 10;
        }
        //looks at the odd digits
        for (x = 0 ; x < 9 ; x += 2)
        {
            //converts each char into an int and then adds them together
            odd += (int) (SIN.charAt (x) - '0');
        }
        //adds the odd digits to the total sum
        total += odd;
        
        //checks if it is multiple of 10
        if (total % 10 == 0)
            return true;
        else
            return false;
    }
    
    //Name:			setEmployeeData method
    //Param:		Employee, selected option, scanner
    //Purpose:		Uses setters to change employee data, depending on which option was selected.
    private static void setEmployeeData(Employee e, int choice, Scanner s) {
    	String temp;
    	switch (choice) {
	        case 1: System.out.println("Enter the first name to set: ");
	        	temp = s.nextLine();
	        	while (temp.matches("[0-9]+")) {
	        		System.out.println("Enter the first name to set: ");
		        	temp = s.nextLine();
	        	}
	        	e.setFirstName(temp);
	            break;
	        case 2: System.out.println("Enter the last name to set: ");
		        temp = s.nextLine();
	        	while (temp.matches("[0-9]+")) {
	        		System.out.println("Enter the last name to set: ");
		        	temp = s.nextLine();
	        	}
	            e.setLastName(temp);
	            break;
	        case 3: System.out.println("Enter the gender to set (m for male/f for female): ");
	            e.setGender(Character.toUpperCase(s.next().charAt(0)));
	            break;
	        case 4: System.out.println("Enter the age to set: ");
	            e.setAge(s.nextInt());
	            s.nextLine();
	            break;
	        case 5: System.out.println("Enter the address to set: ");
	            e.setAddress(s.nextLine());
	            break;
	        case 6: System.out.println("Enter the SIN to set (enter 0 if the SIN is unavailable): ");
	        	int tempSin = s.nextInt();
	        	boolean validSIN;
	        	
	        	if (tempSin != 0) {
	        		do
	                { 	//checks if SIN is valid
	                    validSIN = checkSIN(Integer.toString(tempSin)); //calls checkSIN method
	                    if (validSIN) {
	                        e.setSin(String.valueOf(tempSin));
	                    }
	                    else //if SIN is invalid
	                    {
	                        System.out.println ("\nThat is an invalid SIN. Please enter another.");
	                        tempSin = s.nextInt();
	                    }
	                } while (!validSIN);
	        	}
	        		
	            break;
	        case 7: System.out.println("Enter the salary to set:");
	        	System.out.print("$");
	            e.setSalary(Double.parseDouble(s.next()));
	            s.nextLine();
	            break;
	        case 8: System.out.println("Enter the phone number to set (e.g. 905-314-1592): ");
		        temp = s.nextLine();
	        	while (temp.matches("[a-zA-Z]+")) {
	        		System.out.println("Enter the phone number to set (e.g. 905-314-1592): ");
		        	temp = s.nextLine();
	        	}
	            e.setPhoneNumber(temp);
	            break;
	        case 9: System.out.println("Enter the department to set (e.g. customer service): ");
		        temp = s.nextLine();
	        	while (temp.matches("[0-9]+")) {
	        		System.out.println("Enter the department to set (e.g. customer service): ");
		        	temp = s.nextLine();
	        	}
	            e.setDepartment(temp);
	            break;
	        case 10: System.out.println("Enter the job title to set: ");
		        temp = s.nextLine();
	        	while (temp.matches("[0-9]+")) {
	        		System.out.println("Enter the job title to set: ");
		        	temp = s.nextLine();
	        	}
	            e.setJobTitle(temp);
	            break;
	        case 11: System.out.println("Enter the status to set (e.g. on_vacation, working_full_time, etc.): ");
		        temp = s.nextLine();
	        	while (temp.matches("[0-9]+")) {
	        		System.out.println("Enter the status to set (e.g. on_vacation, working_full_time, etc.): ");
		        	temp = s.nextLine();
	        	}
	            e.setStatus(temp);
	            break;
	        case 12: System.out.println("Enter the email to set (emailID@example.com): ");
	            e.setEmail(s.next());
	            break;
	        case 13: break;
	        default: System.out.println("Invalid option.  Returning to main menu.");
	            break;
	        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
            
        ArrayList<Employee> database = new ArrayList<Employee>();
        
        try { //if there is data in the text file, read it before proceeding
        	File record = new File ("record.txt");
            if (record.length() != 0)
                readFile(database, record);
        } catch (FileNotFoundException e) { //displays appropriate message if the file could not be found
        	System.out.println("Record file not found.  Unable to load database values.");
        }
        
        double totalSalary;
        boolean running = true;
        int option, whichEmployee, modifyOption, sortOption, tempIndex;
        int wrongCount = 0, employeeCount = 0;
        String tempFirst, tempLast;
        Scanner s = new Scanner (System.in);
        
        String actualPass = "Password";
        System.out.print("Welcome to the HR Program\nEnter the password: "); //asks user to input password
        
        while (wrongCount < 5) {
        
	        String pass =  s.next();
	        
	        if (pass.equals(actualPass)) { //if the password is correct 
	            try {                   
	                while (running) {
	                    printMenu(); //calls printMenu method                                           
	                    option = s.nextInt();
	                    
	                    
	                    switch (option) {
	                            //hire employees
	                            case 1:
	                            	Employee man = new Employee(); //user inputs employee information
	                            	s.nextLine();
	                            	
	                                //set variables for the new employee object
	                                setEmployeeData(man, 1, s);
	                                setEmployeeData(man, 2, s);
	                                setEmployeeData(man, 3, s);
	                                setEmployeeData(man, 4, s);
	                                setEmployeeData(man, 5, s);
	                                setEmployeeData(man, 6, s);
	                                setEmployeeData(man, 7, s);
	                                setEmployeeData(man, 8, s);
	                                setEmployeeData(man, 9, s);
	                                setEmployeeData(man, 10, s);
	                                setEmployeeData(man, 11, s);
	                                setEmployeeData(man, 12, s);
	                                
	                                man.setEmployeeNumber(employeeCount + 1);
	                                employeeCount++;
	                                
	                                database.add(man); //adds employee object to ArrayList
	                                
	                                System.out.println (man.getFirstName() + " " + man.getLastName() + " was hired.");
	                                
	                                break;
	                            
	                            //fire employees
	                            case 2: 
	                                //asks for employee name

	                            	s.nextLine();
	                                System.out.print ("\nLast Name: ");
	                                tempLast = s.next() + " ";
	                                System.out.print ("First Name: ");
	                                tempFirst = s.next() + " ";
	                                
	                                tempIndex = findEmployee(database, tempFirst, tempLast); //checks if that employee exists
	                                if  (tempIndex != -1) { //if employee is found
	                                    database.remove(tempIndex); //fires employee
	                                    System.out.println ("\n" + tempFirst + " "  + tempLast + " was fired!");
	                                }
	                                else //if employee is not found
	                                    System.out.println ("\n" + tempFirst + " "  + tempLast + " was not found.");
	                                break;
	                            
	                            //display employee info
	                            case 3: displayEmployees(database); //displays employees information
	                                if (!database.isEmpty()) //if data has been entered
	                                {
	                                    System.out.println ("\nWhich employee's information do you wish to view?"); //asks for employee index
	                                    whichEmployee = s.nextInt() - 1;
	                                    displayEmployeesInfo(database, whichEmployee);
	                                }
	                                break;
	                    
	                            
	                            //sorts employees
	                            case 4: printSortMenu();
	                            sortOption = s.nextInt();
	                            switch (sortOption) { //sorts ArrayList based on user input
	                                case 1: sortFirstName(database);//sort by First Name
	                                    break;
	                                case 2: sortLastName(database); //sort by Last Name
	                                    break;
	                                case 3: sortSalary(database);   //sort by Salary
	                                    break;
	                                case 4: sortAge(database);              //sort by Age
	                                    break;
	                                case 5:
	                                	sortEmployeeNum(database);
	                                	break;
	                                case 6: reverse(database);              //Reverse order
	                                    break;
	                                case 7: break;
	                                default: System.out.println("\nInvalid option.  Returning to main menu."); //in case of invalid input
	                                    break;
	                            }
	                            break;
	                                    
	                            //calculates total salary expense
	                            case 5: totalSalary = calculateSalaries(database);
	                                System.out.println("\nThe total salary expense will be: " + totalSalary);
	                                break;
	                            
	                            //modify employee data
	                            case 6: 
	                                //Asks for which employee

	                            	s.nextLine();
	                                System.out.print ("Last Name: ");
	                                tempLast = s.next() + " ";
	                                System.out.print ("\nFirst Name: ");
	                                tempFirst = s.next() + " ";
	                                tempIndex = findEmployee(database, tempFirst, tempLast);
	                                
	                                if (tempIndex != -1) //if employee is found
	                                {
	                                	
	                                		printModifyMenu(); //calls printModifyMenu method
	                                        modifyOption = s.nextInt();
	                                        s.nextLine();
	                                        setEmployeeData(database.get(tempIndex), modifyOption, s); //sets the variable that the user wants to change
	                                        System.out.println (tempFirst + " " + tempLast + " was modified.");
	                                    
	                                }
                                    else //if employee is not found
                                        System.out.println (tempFirst + " "  + tempLast + " was not found.");
                                    break;
	                                    
	                            //promote or demote employees   
	                            case 7:
	                                //asks for employee first and last name to locate index
	                            	s.nextLine();
	                                System.out.print ("Last Name: ");
	                                tempLast = s.next() + " ";
	                                System.out.print ("\nFirst Name: ");
	                                tempFirst = s.next() + " ";
	                                //looks for employee
	                                tempIndex = findEmployee(database, tempFirst, tempLast);
	                                if  (tempIndex != -1) { //if employee is found
	                                    System.out.println ("Enter the employee's new salary: ");
	                                    database.get(tempIndex).setSalary(Double.valueOf(s.next()));
	                                    System.out.println ("Enter the employee's new job title: ");
	                                    database.get(tempIndex).setJobTitle(s.next());
	                                }
	                                else //if employee is not found
	                                    System.out.println (tempFirst + " "  + tempLast + " was not found.");
	                                break;
	                            
	                            //saves the array list to a text file
	                            case 8: 
	                            	try {
	                                    saveFile(database); //calls saveFile method
	                                } catch (FileNotFoundException e) { //displays appropriate message if the record file could not be found
	                                    System.out.println("Error: record file not found.  Make sure the record file is in its original folder, or make a new text file called 'record.txt'.");
	                                }
	                                break;
	                            
	                            //ends the program
	                            case 9: running = false;
	                                break;
	                            default: System.out.println("Invalid option."); //in case of invalid user input
	                                break;
	                    }
	                }
	            } finally {
                    s.close(); 
	            }
	            
	        }
	        //if inputed password is incorrect
	        else {
                System.out.println("The password you entered was incorrect.  Please try again."); //end of program
                wrongCount++;
                if (wrongCount == 5)
                    System.out.println("You have incorrectly entered the password 5 times.  The program will now terminate.");
	        }
	    }
    }
}