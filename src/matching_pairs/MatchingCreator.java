package matching_pairs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class MatchingCreator {

    private static final Map<String, String> pairsMap = Pairs.fruits;
    private static final String pairTemplatePath = "resources/matching_pairs/matching_pair_template.txt";
    private static final String jmtTemplatePath = "resources/matching_pairs/matching_template.jmt";
    private static final String outputJmtPath = "resources/matching_pairs/matching_pairs_output.jmt";
    private static final String TITLE = "Fruits";

    private static final String FILENAME_PLACEHOLDER = "REPLACE_WITH_IMAGE_FILENAME";
    private static final String ALT_TEXT_PLACEHOLDER = "REPLACE_WITH_IMAGE_ALT_TEXT";
    private static final String HINDI_PLACEHOLDER = "REPLACE_WITH_HINDI_WORD";
    private static final String TITLE_PLACEHOLDER = "REPLACE_STRING_WITH_TITLE";
    private static final String PAIRS_PLACEHOLDER = "REPLACE_STRING_WITH_PAIRS";

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(outputJmtPath)) {
            out.println(generateFullJmt());
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't write to jmt file at " + outputJmtPath);
            e.printStackTrace();
        }
    }

    private static String generateFullJmt() {
        String pairsAsJmt = generatePairs();
        try {
            final String jmtTemplate = Files.readString(Paths.get(jmtTemplatePath));
            return jmtTemplate.replaceAll(TITLE_PLACEHOLDER, TITLE).replaceAll(PAIRS_PLACEHOLDER, pairsAsJmt);
        } catch (IOException e) {
            System.out.println("Couldn't read full jmt template at " + jmtTemplatePath);
            e.printStackTrace();
        }
        return "Looks like something went wrong";
    }

    private static String generatePairs() {
        StringBuilder pairsSB = new StringBuilder();

        try {
            final String singlePairTemplate = Files.readString(Paths.get(pairTemplatePath));
            for (Map.Entry<String, String> pair : pairsMap.entrySet()) {
                pairsSB.append(
                    singlePairTemplate
                        .replaceAll(FILENAME_PLACEHOLDER, pair.getKey())
                        .replaceAll(ALT_TEXT_PLACEHOLDER, pair.getKey())
                        .replaceAll(HINDI_PLACEHOLDER, pair.getValue())
                );
                pairsSB.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Couldn't read single pair template at " + pairTemplatePath);
            e.printStackTrace();
        }

        return pairsSB.toString();
    }

}
