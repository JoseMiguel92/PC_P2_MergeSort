package mergeSort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void mergeSort(int[] list,int inicio,int fin){
        if (inicio == fin){
            return;
        }else{
            int medio = (inicio+fin)/2;
            mergeSort(list,inicio,medio);
            mergeSort(list,medio+1,fin);
            merge(list,inicio,medio+1,fin);
        }
    }
    public static void merge(int[] list, int inicio, int medio, int fin) {
        int[] izquierda = new int[medio - inicio + 2];

        for (int i = inicio; i <= medio; i++) {
            izquierda[i - inicio] = list[i];
        }
        izquierda[medio - inicio + 1] = Integer.MAX_VALUE;
        int[] derecha = new int[fin - medio + 1];

        for (int i = medio + 1; i <= fin; i++) {
            derecha[i - medio - 1] = list[i];
        }
        derecha[fin - medio] = Integer.MAX_VALUE;
        int i = 0, j = 0;

        for (int k = inicio; k <= fin; k++) {
            if (izquierda[i] <= derecha[j]) {
                list[k] = izquierda[i];
                i++;
            }
            else {
                list[k] = derecha[j];
                j++;
            }
        }
    }

    public static int getNumber(String line){
        String REGEX = "(\\w+) (\\d): (\\d+)";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);
        if(matcher.matches()){
            return Integer.parseInt(matcher.group(matcher.groupCount()));
        }
        return -1;
    }

    public static void generateFile(int[][] list) throws IOException {
        try(FileWriter writer = new FileWriter("output.txt")) {
            for (int[] contestant : list) {
                writer.write("Contestant "+contestant[0]+": "+contestant[1]);
            }
        }
    }

    public static int[] parser(String file) throws FileNotFoundException {
        int[] list;
        int total;
        String line;
        System.out.println(System.currentTimeMillis());
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            line = scanner.nextLine();
            total = Integer.parseInt(line);
            list = new int[total];
            int index = -1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                index = index + 1;
                list[index] = getNumber(line);
            }
            System.out.println("Fin parseo");
            System.out.println(System.currentTimeMillis()/1000);
        }
        return list;
    }

    public static void main(String[] args) {
        String file = "resources/contestants.txt";
        int[] list = null;
        try {
            list = parser(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mergeSort(list,0,list.length);
        System.out.println("FINAL");
    }
}
