package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the implementation for the IJsonManager interface.
 * It provides methods to convert JSON data to a list of objects and
 * convert a list of objects to JSON data
 */
public class JsonManager implements IJsonManager{

    /**
     * Converts JSON data from a file to a List of objects of the specifies class.
     *
     * @param file the file containeng the JSON data
     * @param <T> the type of the objects in the list
     * @param myClass the class of the objects in the list
     * @return return a list of objects of the specified class or an empty list if an error occurs or the file is empty
     */
    public <T> List<T> jsonDataToArrayList(File file, Class<T> myClass) {
        try {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        if (!file.exists()) file.createNewFile();

        if (file.length() == 0) return new ArrayList<>();

        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class ,myClass));

        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Converts a list of objects to JSON data and writes it into a file.
     *
     * @param file the file to write the JSON data
     * @param listClass the list of objects to convert to JSON data
     * @return true if the file was successfully written to the file, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean listToJson(File file, ArrayList listClass) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            mapper.writeValue(file, listClass);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
            return false;
        }
    }
}
