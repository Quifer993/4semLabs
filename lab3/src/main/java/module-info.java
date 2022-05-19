module ru.nsu.fit.oop.zolotorevskii.lab3.lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3 to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model;
    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3.model to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers;
    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Controllers to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer;
    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Viewer to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants;
    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3.Constants to javafx.fxml;
}