module ru.nsu.fit.oop.zolotorevskii.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens ru.nsu.fit.oop.zolotorevskii.lab5 to javafx.fxml;
    opens ru.nsu.fit.oop.zolotorevskii.lab5.Controller to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab5.Controller;
    exports ru.nsu.fit.oop.zolotorevskii.lab5;
    exports ru.nsu.fit.oop.zolotorevskii.lab5.Viewer;
    opens ru.nsu.fit.oop.zolotorevskii.lab5.Viewer to javafx.fxml;
}