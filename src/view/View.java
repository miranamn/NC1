package view;

import model.Loader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface View {

    public void init() throws InterruptedException, IOException, ParseException, ClassNotFoundException;
}
