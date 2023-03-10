import java.io.*;
import java.util.*;

public class Main {
    private static final String NAME_FILE = "airports.csv";
    public static void main(String[] args) {
        int num_column = Integer.parseInt(args[0])-1;
        long start=0,finish = 0;

        String str = "";

        HashMap<Integer, String> map = new HashMap<>();

        Scanner in = new Scanner(System.in);

        map = fillDate(num_column);

        while (!str.equals("!quit"))
        {
            System.out.println("Enter the line: ");
            str = in.next().toLowerCase();
            start = System.currentTimeMillis();
            searchElementInFile(searchElement(str,map),num_column);
            finish = System.currentTimeMillis();
            System.out.println("Time has passed, ms: " + (finish -start));

        }

    }

    private static HashMap<Integer,String> fillDate(int num_column)
    {
        HashMap<Integer, String> map = new HashMap<>();
        try(BufferedReader fileReader
                    = new BufferedReader(new FileReader(NAME_FILE)))
        {
            String line = "";

            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.toLowerCase().split(",");
                if(tokens.length > num_column)
                {
                    map.put(Integer.parseInt(tokens[0]),tokens[num_column].replace("\"",""));
                }
                else {
                    System.out.println("Error!");
                    System.out.println("Invalid column number");
                    System.exit(-1);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
            System.out.println("Failed to connect to the file!");
            System.exit(-1);
        }

        return map;
    }

    private static HashMap<Integer,String> searchElement(String str,HashMap<Integer, String> map)
    {
        HashMap<Integer, String> resultmap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().startsWith(str))
            {
                resultmap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultmap;
    }

    private static void searchElementInFile(HashMap<Integer, String> map,int num_column)
    {
        int countElement = 0;

        try(BufferedReader fileReader
                    = new BufferedReader(new FileReader(NAME_FILE)))
        {
            String line = "";

            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(",");
                if(map.containsKey(Integer.parseInt(tokens[0])))
                {
                    System.out.println(tokens[num_column] + Arrays.toString(tokens));
                    countElement++;
                }

            }
            map.clear();
        }
        catch (IOException e) {
            System.out.println("Error!");
            System.out.println("Failed to connect to the file!");
            System.exit(-1);
        }


        System.out.format("Number of elements found : %s\t", countElement);

    }
}

