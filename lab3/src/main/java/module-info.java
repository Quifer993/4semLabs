module ru.nsu.fit.oop.zolotorevskii.lab3.lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.fit.oop.zolotorevskii.lab3.lab3 to javafx.fxml;
    exports ru.nsu.fit.oop.zolotorevskii.lab3.lab3;
}