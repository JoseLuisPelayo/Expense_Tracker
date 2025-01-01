package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IJsonManager {
    public <T> List<T> jsonDataToArrayList(File file, Class<T> myClass);

    public boolean listToJson(File file, ArrayList listClass) throws IOException;


}
