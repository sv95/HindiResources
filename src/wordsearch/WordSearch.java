package wordsearch;

import wordsearch.type.HindiWord;
import wordsearch.util.HindiLetterGenerator;

import static java.lang.String.format;

import java.io.*;
import java.util.*;

public class WordSearch {

    static class Grid {
        int numAttempts;
        int overlaps;
        String[][] cells = new String[nRows][nCols];
        List<String> solutions = new ArrayList<>();
    }

    final static int[][] dirs =
        // N, E, S, W, NE, SE, SW, NW
        // {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
        // E, S, NE, SE
        {{0, -1}, {1, 0}, {1, 1}, {1, -1}};

    private static int nRows;
    private static int nCols;
    private static int gridSize;
    private static int minWords;
    private static int longestWordLength;
    private static int wordCount;
    private static final List<HindiWord> wordsList = new ArrayList<>();
    private static Grid wordsearch;

    final static Random rand = new Random();

    public static void main(String[] args) {
        try {
            takeInput();
            calculateSize();
            wordsearch = createWordSearch();
            System.out.println("Attempts: " + wordsearch.numAttempts);
            System.out.println("Overlaps: " + wordsearch.overlaps);
            printResult(wordsearch, "out/creations/wordsearch_solution.txt");
            fillInGaps();
            printResult(wordsearch, "out/creations/wordsearch.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Words file not found");
        }
    }

    public static void takeInput() throws FileNotFoundException {
        wordCount = 0;
        // Create list of words
        Scanner s = new Scanner(new File("resources/wordsearch/hindi_words.txt"));
        while (s.hasNext()){
            wordsList.add(new HindiWord(s.next()));
            wordCount++;
        }
        s.close();
        minWords = wordsList.size();
    }

    private static void calculateSize() {
        System.out.println("Calculating size");
        int i;
        longestWordLength = 0;
        for(i =0; i < wordsList.size(); i++){
            if(wordsList.get(i).length() > longestWordLength){
                longestWordLength = wordsList.get(i).length();
            }
        }
        nCols = longestWordLength * 2;
        nRows = nCols + (nCols/3);
        gridSize = nCols * nRows;
        System.out.println("Size is " + nCols + "x" + nRows);
    }

    static Grid createWordSearch() {
        Grid grid = null;
        int numAttempts = 0;

        outer:
        while (++numAttempts < 100) {
            Collections.shuffle(wordsList);

            grid = new Grid();
//            int messageLen = placeMessage(grid, HIDDEN_WORD);

            int wordsPlaced = 0;
            int cellsFilled = 0;
            for (HindiWord word : wordsList) {
                int newlyFilledCells = tryPlaceWord(grid, word);
                cellsFilled += newlyFilledCells;
                wordsPlaced += newlyFilledCells>0?1:0;
                if (wordsPlaced == wordCount) {
                    if (grid.solutions.size() >= minWords) {
                        grid.numAttempts = numAttempts;
                        break outer;
                    } else break; // grid is full but we didn't pack enough words, start over
                }
            }
        }

        return grid;
    }

    static int placeMessage(Grid grid, HindiWord msg) {
        int messageLen = msg.length();
        if (messageLen > 0 && messageLen < gridSize) {
            int gapSize = gridSize / messageLen;

            for (int i = 0; i < messageLen; i++) {
                int pos = i * gapSize + rand.nextInt(gapSize);
                grid.cells[pos / nCols][pos % nCols] = "" + msg.charAt(i);
            }
            return messageLen;
        }
        return 0;
    }

    static int tryPlaceWord(Grid grid, HindiWord word) {
        int randDir = rand.nextInt(dirs.length);
        int randPos = rand.nextInt(gridSize);

        for (int dir = 0; dir < dirs.length; dir++) {
            dir = (dir + randDir) % dirs.length;

            for (int pos = 0; pos < gridSize; pos++) {
                pos = (pos + randPos) % gridSize;

                int lettersPlaced = tryLocation(grid, word, dir, pos);
                if (lettersPlaced > 0)
                    return lettersPlaced;
            }
        }
        return 0;
    }

    static int tryLocation(Grid grid, HindiWord word, int dir, int pos) {

        int r = pos / nCols;
        int c = pos % nCols;
        int len = word.length();

        //  check bounds
        if ((dirs[dir][0] == 1 && (len + c) > nCols)
            || (dirs[dir][0] == -1 && (len - 1) > c)
            || (dirs[dir][1] == 1 && (len + r) > nRows)
            || (dirs[dir][1] == -1 && (len - 1) > r))
            return 0;

        int rr, cc, i, overlaps = 0;

        // check cells
        for (i = 0, rr = r, cc = c; i < len; i++) {
            if (grid.cells[rr][cc] != null && !grid.cells[rr][cc].equals(word.charAt(i)))
                return 0;
            cc += dirs[dir][0];
            rr += dirs[dir][1];
        }

        // place
        for (i = 0, rr = r, cc = c; i < len; i++) {
            if (word.charAt(i).equals(grid.cells[rr][cc])) {
                overlaps++;
                grid.overlaps++;
            } else {
                grid.cells[rr][cc] = word.charAt(i);
            }
            if (i < len - 1) {
                cc += dirs[dir][0];
                rr += dirs[dir][1];
            }
        }

        int lettersPlaced = len - overlaps;
        if (lettersPlaced > 0) {
            grid.solutions.add(format("%-10s (%d,%d)(%d,%d)", word, c, r, cc, rr));
        }

        return lettersPlaced;
    }

    private static void fillInGaps() {
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                if (wordsearch.cells[r][c] == null) {
                    wordsearch.cells[r][c] = HindiLetterGenerator.generate();
                }
            }
        }
    }

    static void printResult(Grid grid, String filePath) {
        StringBuilder sb = generatePrintableOutput(grid);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));) {
            writer.write(sb.toString());
        } catch (IOException ioe) {
            System.out.println("Caught ioe when printing result");
            ioe.printStackTrace();
        }
    }

    static StringBuilder generatePrintableOutput(Grid grid) {
        final StringBuilder sb = new StringBuilder();

        if (grid == null || grid.numAttempts == 0) {
            sb.append("No grid to display");
            return sb;
        }
        int size = grid.solutions.size();

        sb.append(",0,1,2,3,4,5,6,7,8,9");
        for (int r = 0; r < nRows; r++) {
            sb.append(String.format("%n%d,", r));
            for (int c = 0; c < nCols; c++) {
                final String cellVal = grid.cells[r][c];
                if (cellVal == null) {
                    sb.append(",");
                } else {
                    sb.append(String.format("%s,", cellVal));
                }
            }
        }

/*
        sb.append("\n");

        for (int i = 0; i < size - 1; i += 2) {
            sb.append(String.format("%s   %s%n", grid.solutions.get(i),
                              grid.solutions.get(i + 1)));
        }
        if (size % 2 == 1) {
            sb.append(String.format(grid.solutions.get(size - 1)));
        }
*/

        return sb;
    }

}
