package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();


        System.out.println();
        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        printDeadlinesInSortedOrder(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        ArrayList<Task> filteredList = filterTaskListUsingStreams(tasksData, "11");
        System.out.println("\nTask filtered by String:");
        printData(filteredList);

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

    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<Task> Tasks, String filterString) {
        ArrayList<Task> filteredList;
        filteredList = (ArrayList<Task>) Tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
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
    public static void printDeadlinesInSortedOrder(ArrayList<Task> Tasks){
        System.out.println("\nDeadlines in sorted order");
        Tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
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
