package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManager implements IJsonManager{
    public <T> List<T> jsonDataToArrayList(File file, Class<T> myClass) {
        try {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        if (!file.exists()) file.createNewFile();

        if (file.length() == 0) return new ArrayList<>();

        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class ,myClass));

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean listToJson(File file, ArrayList listClass) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            mapper.writeValue(file, listClass);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            return false;
        }
    }



}
