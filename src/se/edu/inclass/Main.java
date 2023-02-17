package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();


        System.out.println();
        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);

//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));
        printData(tasksData);
        printDataUsingStreams(tasksData);
    }
    private static int countDeadlinesUsingStream(ArrayList<Task> Tasks){
        int count = (int) Tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();

        return count;
    }
    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    public static void printDataUsingStreams(ArrayList<Task> Tasks){
        System.out.println("\nUsing Streams:");
        Tasks.stream()
                .forEach(System.out::println);
    }
    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Sanity Check");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> Tasks){
        System.out.println("\nWith Stream!");
        Tasks.stream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Without stream!");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
}
