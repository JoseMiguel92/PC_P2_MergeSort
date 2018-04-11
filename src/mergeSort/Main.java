package mergeSort;

import java.io.*;
import java.util.Scanner;

public class Main {
    public void mergeSort(int[] list,int inicio,int fin){
        if (inicio == fin){
            return;
        }else{
            int medio = (inicio+fin)/2;
            mergeSort(list,inicio,medio);
            mergeSort(list,medio+1,fin);
            merge(list,inicio,medio+1,fin);
        }
    }
    public void merge(int[] list, int inicio, int medio, int fin) {
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
    public static void main(String[] args) throws IOException {
        String line = null;
        try (Scanner scanner = new Scanner(new FileReader("resources/contestants.txt"))) {
            line = scanner.nextLine();
            int total = Integer.parseInt(line);
            int[] list = new int[total];
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}
