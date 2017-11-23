package KernelHangAMan;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Mekhalfa Taki Eddine on 19/04/2017.
 */

public class Words {
    private static List<Word> words;
    private static List<Integer> randomeHelper;


    public static void initWords(String DbfileName) throws IOException{
        words = new ArrayList<>(500);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(DbfileName));
            String line;
            while ((line = in.readLine()) != null) {
                String[] parameters = line.split(";");
                words.add(new Word(parameters[2].toUpperCase(), HintType.valueOf(parameters[0]), parameters[1]));
                randomeHelper = IntStream.range(0, words.size())
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        } finally {
            if (in != null) in.close();
        }
    }
    public static List<Word> generateRandom(int numberOfWords) {
        if (numberOfWords > words.size() || numberOfWords <= 0) throw new IllegalArgumentException();
        List<Word> result;
        Collections.shuffle(randomeHelper);
        result = randomeHelper.stream()
                .map(e -> words.get(e))
                .limit(numberOfWords)
                .collect(Collectors.toCollection(ArrayList::new));
        return result;

    }

}
