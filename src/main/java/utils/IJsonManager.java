package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines the contract to manage JSON data.
 * It provides methods to convert JSON data to a list of objects and
 * convert a list of objects to JSON data
 */
public interface IJsonManager {
    /**
     * Converts JSON data to a List of objects of the specified class.
     *
     * @param file the file containeng the JSON data
     * @param myClass the class of the objects in the list
     * @param <T> the type of objects in the list
     * @return a list of objects of the specified class
     */
    public <T> List<T> jsonDataToArrayList(File file, Class<T> myClass);

    /**
     * Converts a list of objects to JSON data and writes it into a file.
     *
     * @param file the file to write the JSON data
     * @param listClass the list of objects to convert to JSON data
     * @return true if the file was successfully written to the file, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean listToJson(File file, ArrayList listClass) throws IOException;
}
