
import java .io.*;
import java.util.ArrayList;
import java .util.Scanner;


public class TodoListApp{
    public static void main(String[] args){
    

        Menu menu = new Menu();

    }
}
class Menu{

    private final String defaultFile = "default_tasks.txt";
private final Scanner scanner = new Scanner(System.in);
private final ArrayList<String> arrayList = new ArrayList<>();
    protected int choice;
    protected boolean running = true;
    
    public Menu(){

        loadFromTheDefaultFile();

        while(running){
            System.out.println("********** My ToDo List **********");
        System.out.println("1.Add Task");
        System.out.println("2.Load");
        System.out.println("3.View Tasks");
        System.out.println("4.Save the Tasks");
        System.out.println("5.Delete Tasks");
        System.out.println("6.Quit");
        System.out.print("Enter Your Choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        if(choice>0 && choice<7){
            switch(choice){
                case 1 -> addTask();
                case 2 -> LoadFromFile();
                case 3 -> view();
                case 4 -> {
                    System.out.print("Enter file path you want to save: ");
                    String filename = scanner.nextLine();
                    try {
                        saveToFile(filename);
                        System.out.println("File saved Successfully!");
                    } catch (IOException e) {
                        System.out.println("ERROR saving tasks! "+e.getMessage());
                    }
                }
                case 5 -> delete();
                case 6 ->{
                    System.out.print("Are you sure you want to quit(y/n): ");
                    String confirm = scanner.nextLine();
                    if(confirm.equals("y")){
                    view();
                    saveTodefaultFile();
                 running = false;}

                }
            }
            
        }else{
            System.out.println("Invalid Choice!");
            scanner.nextLine();
        }
        System.out.println();
    }
    }
    private void addTask(){
        System.out.print("Enter Your Task:");
        String task = scanner.nextLine();
        arrayList.add(task);
        saveTodefaultFile();
        System.out.println("Task added Successfully!");
    }
    private void view(){
        if(arrayList.isEmpty()){
        System.out.println("There is no Task to show!");

        }else{
            System.out.println("\nYour Task ->");
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println((i+1)+" "+arrayList.get(i));
            }
        }
    }
    private void delete(){
        if(arrayList.isEmpty()){
        System.out.println("There was NO Task To Delete!");
        }else{
            view();
            System.out.print("\n Enter the task that you want to delete: ");
            
            if(scanner.hasNextInt()){
                int index = scanner.nextInt();
                scanner.nextLine();

                if(index >0 && index<=arrayList.size()){
                    String removedTask = arrayList.remove(index -1);
                    saveTodefaultFile();
                    System.out.println("Successfully Removed \""+ removedTask +"\" Task");
                }else{
                    System.out.println("Please enter the number between 1 - "+ arrayList.size());
                }

            }else{
                System.out.println("Please Enter the Number of task that you want to delete! ");
                scanner.nextLine();
            }

        }
    }
    private void LoadFromFile(){
        String fileName ;
        System.out.print("Copy and paste File path here:");
        fileName = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader( fileName ))) {
            String line;
            while((line = reader.readLine()) != null){
                arrayList.add(line);
            }
            System.out.println("Tasks loaded from file Successfully!");
            
        } catch (IOException e) {
            System.out.println("No saved file found on file or ERROR reading!");
        }

    }
    private void saveToFile(String filename)throws IOException{
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            
            for (String task : arrayList) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("File Saved Successfully!");
            
        } catch (IOException e) {
            System.out.println("ERROR Saving tasks "+e.getMessage());
        }


    }
    private void loadFromTheDefaultFile(){
        File file = new File(defaultFile);
        if(!file.exists()){
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line=reader.readLine())!=null){
                arrayList.add(line);
            }
            System.out.println("Tasks auto loaded from default file!");
            
        } catch (IOException e) {
            System.out.println("ERROR loading default file! "+e.getMessage());
        }
    }
    private void saveTodefaultFile(){
        try {
            saveToFile(defaultFile);
        }catch(IOException e){
            System.out.println("ERROR saving to this File! " +e.getMessage());
        }
    }
}