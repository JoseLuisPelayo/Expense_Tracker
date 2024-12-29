package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    public static <T> List<T> jsonDataToArrayList(File file, Class<T> myClass) throws IOException {
        try {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        if (!file.exists()) file.createNewFile();

        if (file.length() == 0) return new ArrayList<>();

        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class ,myClass));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean ListToJson(File file, ArrayList listClass) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
            mapper.writeValue(file, listClass);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
