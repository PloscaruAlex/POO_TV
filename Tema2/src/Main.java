import actions.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import functionality.CurrentSession;
import io.Input;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(final String[] args) throws IOException {
        String inFile = args[0];
        String outFile = args[1];

        File inputFile = new File(inFile);
        File outputFile = new File(outFile);
        boolean isCreated = outputFile.createNewFile();
        if (!isCreated) {
            return;
        }




        String result = "outputAux/" + inFile.substring(99, inFile.length() - 5) + outFile;
        Path path = Paths.get(result);
        File res = new File(result);
        if (Files.exists(path)) {
            res.delete();
        }
        res.createNewFile();





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
        objectWriter.writeValue(res, output);
    }
}
