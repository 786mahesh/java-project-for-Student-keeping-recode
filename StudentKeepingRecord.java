import java.io.*;
import java.util.*;

class Student
{
    int enroll, age;
    String name, branch;

    Student(int enroll, String name, int age, String branch)
    {
        this.enroll = enroll;
        this.name = name;
        this.age = age;
        this.branch = branch;
    }

    public String toString()
     {
        return
        "Enrollment no  ->  " + enroll + "\n" +
        "Name           ->  " + name + "\n" +
         "Age            ->  " + age + "\n" +
         "Branch         ->  " + branch + "\n" +
         "     User added Successfully....\n";
    }
}

class StudentKeepingRecord 
{
    ArrayList<Student> StudentList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public void AddStudent() 
    {
        int id = StudentList.size();
        System.out.print("Enter Enrollment No: ");
        int enroll = input.nextInt();
        input.nextLine(); // consume newline

        System.out.print("Enter Name of Student: ");
        String name = input.nextLine();

        System.out.print("Enter Age of Student: ");
        int age = input.nextInt();
        input.nextLine(); // consume newline again

        System.out.print("Enter Branch of Student: ");
        String branch = input.nextLine();

        Student s = new Student(enroll, name, age, branch);
        StudentList.add(s);

        System.out.println("\n" + s);
    }
    public void saveInformation() 
    {
        try 
        {
            FileWriter fw = new FileWriter("Student.txt", true); // true = append mode
            BufferedWriter bw = new BufferedWriter(fw);
    	    int i=1;
            for (Student s : StudentList)
            {
                bw.write("Enrollment no  -> " + s.enroll + "\n");
                bw.write("Name           -> " + s.name + "\n");
                bw.write("Age            -> " + s.age + "\n");
                bw.write("Branch         -> " + s.branch + "\n");
                bw.write("--------------------------------------------------\n");
            }
    
            bw.flush();  
            bw.close();  
    
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void showallStudents()
    {
        try
        {
            FileReader fr = new FileReader("Student.txt");
            BufferedReader br = new BufferedReader(fr);
            String data;
            while ((data=br.readLine())!=null)
            {
                System.out.println(data);   
            }
            br.close();
            fr.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void searchByEnroll()
     {
        System.out.print("Enter Student Enrollment No: ");
        String enrollToSearch = input.nextLine();
    
        boolean found = false;
    
        try 
        {
            FileReader fr = new FileReader("Student.txt");
            BufferedReader br = new BufferedReader(fr);
    
            String line;
            String studentData = ""; 
    
            while ((line = br.readLine()) != null) 
            {
                if (line.startsWith("Enrollment no"))
                {
                    if (line.contains(enrollToSearch))
                    {
                        found = true;
                        studentData += line + "\n";
                        for (int i = 0; i < 4; i++) 
                        {
                            line = br.readLine();
                            if (line != null) 
                            {
                                studentData += line + "\n";
                            }
                        }
                        break;
                    }
                }
            }
    
            br.close();
            if (found) 
            {
                System.out.println("\nStudent Found:\n");
                System.out.println(studentData);
            } 
            else 
            {
                System.out.println("Student not found in the file.");
            }
    
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void updateByEnroll()
    {
        System.out.print("Enter Enrollment No to Update: ");
        String enroll = input.nextLine();

        try
        {
            FileReader fr = new FileReader("Student.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String updatedData = "";
            boolean found = false;
            boolean updated = false;

        while ((line = br.readLine()) != null)
        {
            if (!updated && line.contains("Enrollment no") && line.contains(enroll))
            {
                    found = true;
                    updated = true;

                   // Skip next 4 lines: Name, Age, Branch, Separator
                    br.readLine();
                    br.readLine();
                    br.readLine();
                    br.readLine();

                  // Take new inputs
		            System.out.print("Enter New Enrollment no: ");
                    int newenroll = input.nextInt();
		            input.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = input.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter New Branch: ");
                    String newBranch = input.nextLine();

                   // Add updated student info
                    updatedData += "Enrollment no  -> " + newenroll + "\n";
                    updatedData += "Name           -> " + newName + "\n";
                    updatedData += "Age            -> " + newAge + "\n";
                    updatedData += "Branch         -> " + newBranch + "\n";
                    updatedData += "--------------------------------------------------\n";
            }
            else
            {
                updatedData += line + "\n";
            }
        }

        br.close();

        if (found)
        {
            FileWriter fw = new FileWriter("Student.txt");
            fw.write(updatedData);
            fw.close();
            System.out.println("Record updated successfully.");
        }
        else
        {
            System.out.println("Student with that enrollment number not found.");
        }
    }
    catch (Exception e)
    {
        System.out.println(e);
    }
}

public void deleteByEnroll()
{
    System.out.print("Enter Enrollment No to Delete: ");
    String enroll = input.nextLine();

    try
    {
        FileReader fr = new FileReader("Student.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        String updatedData = "";
        boolean found =false;
        while ((line = br.readLine()) != null)
        {
            if (line.contains("Enrollment no") && line.contains(enroll))
            {
                found = true;
                br.readLine();
                br.readLine();
                br.readLine();
                br.readLine();
            }
            else
            {
                updatedData += line + "\n";
            }
        }
        
        br.close();

        if (found)
        {
            FileWriter fw = new FileWriter("Student.txt");
            fw.write(updatedData);
            fw.close();
            System.out.println("Record deleted successfully.");
        }
        else
        {
            System.out.println("Student not found.");
        }
    }
    catch (Exception e)
    {
        System.out.println("Error: " + e.getMessage());
    }
}
  
public static void main(String args[]) 
{
    StudentKeepingRecord skr = new StudentKeepingRecord();
    Scanner input = new Scanner(System.in);

    while (true)
     {
        System.out.println("\t\t\t\t\t****** Student Keeping Record ******* ");
        System.out.println("\t\t\t*1 - ADD NEW STUDENT*");
        System.out.println("\t\t\t*2 - SHOW ALL STUDENT INFORMATION*");
        System.out.println("\t\t\t*3 - SEARCH STUDENT INFORMATION USING ENROLLMENT NO*");
        System.out.println("\t\t\t*4 - UPDATE STUDENT INFORMATION*");
        System.out.println("\t\t\t*5 - DELETE A STUDENT INFORMATION*");
        System.out.println("\t\t\t*6 - EXIT SYSTEM*");
        System.out.print("\n\nEnter choice: ");
        int choice = input.nextInt();

        switch (choice) 
        {
            case 1:
                skr.AddStudent();
                skr.saveInformation();
                break;
            case 2:
                skr.showallStudents();
                break;
            case 3:
                skr.searchByEnroll();
                break;
            case 4:
                skr.updateByEnroll();
                break;
            case 5:
                skr.deleteByEnroll();
                break;
            case 6:
                System.out.println("\nThank you for using the Student Record System...");
                System.exit(0); 
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}