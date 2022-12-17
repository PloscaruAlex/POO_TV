import actions.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import functionality.CurrentSession;
import io.Input;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * The main function which is called in each test.
     *
     * @param args the input file and the output file paths
     * @throws IOException the io exception
     */
    public static void main(final String[] args) throws IOException {
        String inFile = args[0];
        String outFile = args[1];

        File inputFile = new File(inFile);
        File outputFile = new File(outFile);
        boolean isCreated = outputFile.createNewFile();
        if (!isCreated) {
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(inputFile, Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        CurrentSession currentSession = CurrentSession.getInstance();
        currentSession.startWith(inputData);
        currentSession.setOutput(output);
        for (Action action : currentSession.getActions()) {
            action.doAction();
        }
        currentSession.stopSession();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(outputFile, output);
    }
}
