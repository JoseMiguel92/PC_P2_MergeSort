package mergeSort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void sort(Contestant[] list, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            sort(list, inicio, medio);
            sort(list, medio + 1, fin);
            merge(list, inicio, medio, fin);
        }
    }

    public static void merge(Contestant[] list, int inicio, int medio, int fin) {
        int tamIzquierda = medio - inicio + 1;
        int tamDerecha = fin - medio;

        Contestant[] izquierda = new Contestant[tamIzquierda];
        Contestant[] derecha = new Contestant[tamDerecha];

        for (int i = 0; i < tamIzquierda; ++i) {
            izquierda[i] = list[inicio + i];
        }
        for (int j = 0; j < tamDerecha; ++j) {
            derecha[j] = list[medio + 1 + j];
        }
        int i = 0, j = 0;
        int k = inicio;
        while (i < tamIzquierda && j < tamDerecha) {
            if (izquierda[i].compareTo(derecha[j]) < 0) {
                list[k] = izquierda[i];
                i++;
            } else {
                list[k] = derecha[j];
                j++;
            }
            k++;
        }
        while (i < tamIzquierda) {
            list[k] = izquierda[i];
            i++;
            k++;
        }
        while (j < tamDerecha) {
            list[k]=derecha[j];
            j++;
            k++;
        }
    }

    public static int getNumber(String line) {
        String REGEX = "(\\w+) (\\d+): (\\d+)";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(matcher.groupCount()));
        }
        return -1;
    }

    public static int getId(String line) {
        String REGEX = "(\\w+) (\\d+):";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(matcher.groupCount()));
        }
        return -1;
    }

    public static void generateFile(Contestant[] list,String outputFilename) throws IOException {
        try (FileWriter writer = new FileWriter(outputFilename)) {
            writer.write(String.valueOf(list.length)+"\n");
            for (Contestant contestant : list) {
                writer.write(contestant.toString()+"\n");
            }
        }
    }

    public static Contestant[] parser(String file) throws FileNotFoundException {
        Contestant[] list;
        int total;
        String line;
        double start = System.currentTimeMillis();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            line = scanner.nextLine();
            total = Integer.parseInt(line);
            list = new Contestant[total];
            int index = -1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                index = index + 1;
                Contestant contestant = new Contestant(getId(line),getNumber(line));
                list[index] = contestant;
            }
            System.out.println(System.currentTimeMillis()-start);
        }
        return list;
    }

    public static void main(String[] args) {
        String file = "resources/contestants.txt";
        String outputFilename = "output.txt";
        Contestant[] list = null;
        try {
            list = parser(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sort(list, 0, list.length-1);
        try{
            generateFile(list,outputFilename);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
