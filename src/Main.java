import controller.Controller;
import model.Loader;
import model.TrackListLoader;
import org.json.simple.parser.ParseException;
import view.ConsoleView;
import view.View;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, ParseException, ClassNotFoundException {
        Controller controller = new Controller();
        View view = new ConsoleView(controller);
        Loader list = new TrackListLoader(view);
        controller.setList(list);
        
        view.init();
    }
}

