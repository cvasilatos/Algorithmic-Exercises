package net.etc.edu.algo;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * This simple app takes as input the path to a file with the following syntax:
 * <p>
 * 3|78<br>
 * 4|7765<br>
 * 3|82<br>
 * 2|78<br>
 * 4|14<br>
 * 3|78<br>
 * 2|78<br>
 * 4|12<br>
 * <p>
 * and outputs the same list in the same order but giving additional information
 * about the second part. The output of the above would be:
 * <p>
 * 3|78[1 of 2]<br>
 * 4|7765[1 of 3]<br>
 * 3|82[2 of 2]<br>
 * 4|14[2 of 3]<br>
 * 3|78[1 of 2]<br>
 * 4|12[3 of 3]<br>
 * <p>
 * So it reports how many times the first part exists, excluding first parts
 * that exist only with same second part i.e. the number 2, or parts that exist
 * once.
 *
 * The result is reported to a file at the same path of the incoming file.
 *
 * The complexity of the algorithm is O(n)
 * <p>
 *
 * @author <a href="mailto:christoforos.vasilatos@gmail.com">Christoforos Vasilatos</a>
 */
public class Exercise {

    /**
     * Keeps the input as is
     */
    private static List<Entry<Integer, Integer>> inputList = new ArrayList<>();

    /**
     * Keeps the first part with the list of the second parts
     */
    private static Map<Integer, Map<Integer, Integer>> secondPart = new HashMap<>();

    private static final String RESULT_FILE_SUFFIX = ".result";

    public static void main(String[] b) throws Exception {
        long start = System.currentTimeMillis();
        try (Stream<String> lines = Files.lines(Paths.get(b[0]))) {
            lines.forEachOrdered(line -> {
                String[] pair = line.split("\\|");
                Integer key = Integer.parseInt(pair[0]);
                Integer value = Integer.parseInt(pair[1]);

                inputList.add(new AbstractMap.SimpleEntry<Integer, Integer>(key, value));

                Map<Integer, Integer> map = new HashMap<>();
                if (secondPart.containsKey(key)) {
                    map = secondPart.get(key);
                }
                if (map.putIfAbsent(value, map.size() + 1) == null) {
                    secondPart.put(key, map);
                }
            });
        }
        try (PrintWriter writer = new PrintWriter(new File(b[0] + RESULT_FILE_SUFFIX))) {
            inputList.forEach(obj -> {
                if (secondPart.get(obj.getKey()).size() > 1) {
                    writer.println(obj.getKey() + "|" + obj.getValue()
                    + "[" + (secondPart.get(obj.getKey()).get(obj.getValue()))
                    + " of "
                    + secondPart.get(obj.getKey()).size() + "]");
                }
            });
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

}