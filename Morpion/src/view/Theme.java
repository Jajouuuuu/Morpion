package view;

import java.io.Serializable;

import javafx.scene.paint.Color;

public record Theme(Color backline, Color point, Color number, Color line, Color highlight) implements Serializable {

}
