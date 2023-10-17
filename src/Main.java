import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Movie> M = new ArrayList<>();
        ArrayList<Movie> G = new ArrayList<>();
        FileWriter fileWriter = new FileWriter("Pomiar.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ToSort sort = new ToSort();
        int i = 0;
        int j = 0;
        int n = 1000000;
        bufferedWriter.write("Size;set1-Quicksort;set1-Mergesort;set1-Bucketsort;set2-Quicksort;set2-Mergesort;set2-Bucketsort;");
        bufferedWriter.newLine();
        while (i < n) {
                BufferedReader br = new BufferedReader(new FileReader("src/set1.csv"));
                String line = "";
                Pattern p = Pattern.compile("(\\,[1-9]\\.[0-9])");
                while ((line = br.readLine()) != null) {
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        String v = line.replace(m.group(), "");
                        String k = m.group().replaceAll(",", "");
                        if ((i < n) & (k.length() > 0) && (v.length() > 0)) {
                            M.add(new Movie(v, Float.parseFloat(k)));
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
        while (j < n)
        {
                BufferedReader br1 = new BufferedReader(new FileReader("src/set2.csv"));
                String line1 = "";
                while ((line1 = br1.readLine()) != null) {
                    String[] games = line1.split(",");
                    if ((j < n) && (games.length > 12) && (games[0] != "") && (games[10] != "") && (!(games[10].matches("Critic_Score")))) {
                        if (Float.parseFloat(games[10]) != 0) {
                            G.add(new Movie(games[0], Float.parseFloat(games[10])));
                            j++;
                        }
                    }
                }
        }
        for (int k = 10; k < 1000004; k = k *10) {
            ArrayList<Movie> x = new ArrayList<Movie>(M.subList(0, k));
            ArrayList<Movie> y = new ArrayList<Movie>(M.subList(0, k));
            ArrayList<Movie> z = new ArrayList<Movie>(M.subList(0, k));
            ArrayList<Movie> a = new ArrayList<>(G.subList(0, k));
            ArrayList<Movie> b = new ArrayList<>(G.subList(0, k));
            ArrayList<Movie> c = new ArrayList<>(G.subList(0, k));
            long start = System.nanoTime();
            sort.QuickSort(x, 0, x.size() - 1);
            long stop = System.nanoTime();
            long start1 = System.nanoTime();
            sort.MergeSort(y);
            long stop1 = System.nanoTime();
            long start2 = System.nanoTime();
            sort.BucketSort(z, 10);
            long stop2 = System.nanoTime();
            float f = 0;
            float sum = 0;
            for (Movie t : x) {
                sum += t.rating;
                f++;
            }
            System.out.println("------------------------------");
            System.out.println("IMDb Largest Review Dataset");
            System.out.println("Current size:" + y.size());
            System.out.println("Execution time:");
            System.out.println("Quicksort: " + (stop - start) + " ms");
            System.out.println("Mergesort: " + (stop1 - start1) + " ms");
            System.out.println("Bucketsort: " + (stop2 - start2) + " ms");
            System.out.println("Mean: " + sum / f);
            System.out.println("Median:" + (x.get(x.size() / 2).rating + x.get(x.size() / 2 + 1).rating) / 2);
            long Start = System.nanoTime();
            sort.QuickSort(a, 0, a.size() - 1);
            long Stop = System.nanoTime();
            long Start1 = System.nanoTime();
            sort.MergeSort(b);
            long Stop1 = System.nanoTime();
            long Start2 = System.nanoTime();
            sort.BucketSort(c, 98);
            long Stop2 = System.nanoTime();
            f = 0;
            sum = 0;
            for (Movie t : a)
            {
                sum += t.rating;
                f++;
            }
            System.out.println("------------------------------");
            System.out.println("Metacritic Dataset");
            System.out.println("Current size:" + a.size());
            System.out.println("Execution time:");
            System.out.println("Quicksort: " + (Stop - Start) + " ms");
            System.out.println("Mergesort: " + (Stop1 - Start1) + " ms");
            System.out.println("Bucketsort: " + (Stop2 - Start2) + " ms");
            System.out.println("Mean: " + sum / f);
            System.out.println("Median:" + (a.get(a.size() / 2).rating + a.get(a.size() / 2 + 1).rating) / 2);
            bufferedWriter.write(k + ";" + (stop - start) + ";" + (stop1 - start1) + ";" + (stop2 - start2) + ";" + (Stop - Start) + ";" + (Stop1 - Start1) + ";" + (Stop2 - Start2) + ";");
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}




