package model;

import java.util.List;

public interface PlayObserver {
	
    void update(Grid grid, List<Point> highlightPoints, List<Line> highlightLines);
}
