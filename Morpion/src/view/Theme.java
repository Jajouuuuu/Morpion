package view;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Représente le thème graphique utilisé dans la vue du Morpion Solitaire.
 * Cette classe est immuable et utilisée pour définir les couleurs associées
 * aux différentes parties du jeu.
 *
 * @param backline   Couleur de fond des lignes.
 * @param point      Couleur des points.
 * @param number     Couleur des numéros associés aux points.
 * @param line       Couleur des lignes.
 * @param highlight  Couleur des éléments en surbrillance.
 */
@SuppressWarnings("exports")
public record Theme(Color backline, Color point, Color number, Color line, Color highlight) implements Serializable {

}
