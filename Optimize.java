import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Optimize {

    public static void main(String[] args) {
        // Load usernames from file
        List<String> usernames = loadUsernamesFromFile("mydata.txt");

        // Create the hash table
        int tableSize = 37;
        LPHashTable hashTable = new LPHashTable(tableSize);

        int minProbes = Integer.MAX_VALUE;
        int combinations = 0;

        // Generate all possible weight combinations
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    for (int l = 0; l <= 4; l++) {
                        for (int m = 0; m <= 4; m++) {
                            for (int n = 0; n <= 4; n++) {
                                for (int o = 0; o <= 4; o++) {
                                    for (int p = 0; p <= 4; p++) {
                                        for (int q = 0; q <= 4; q++) {
                                            int[] weights = {i, j, k, l, m, n, o, p, q};
                                            hashTable.setWeights(weights);
                                            int probes = insertUsernames(hashTable, usernames);
                                            if (probes < minProbes) {
                                                minProbes = probes;
                                                combinations = 1;
                                            } else if (probes == minProbes) {
                                                combinations++;
                                            }
                                            hashTable.empty(); // Clear the hash table for the next combination
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Write the results to a file
        writeResultsToFile(minProbes, combinations);
    }

    private static List<String> loadUsernamesFromFile(String filename) {
        List<String> usernames = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new java.io.File(filename));
            while (scanner.hasNextLine()) {
                String username = scanner.nextLine().trim();
                usernames.add(username);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    private static int insertUsernames(LPHashTable hashTable, List<String> usernames) {
        int probes = 0;
        for (String username : usernames) {
            hashTable.insert(username);
            probes += hashTable.getProbeCount();
            hashTable.resetProbeCount();
        }
        return probes;
    }

    private static void writeResultsToFile(int minProbes, int combinations) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"));
            writer.write(minProbes + " " + combinations);
            System.out.println(minProbes + " " + combinations);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
