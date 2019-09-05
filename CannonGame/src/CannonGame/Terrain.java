package CannonGame;

import java.util.LinkedList;
import java.util.Queue;
import static CannonGame.CannonGame.applet;

public class Terrain {
	int tHeight;
	int tWidth;
	int offset;
	int[] heightmap;

	Terrain() {
		this.tWidth = applet.width;
		this.tHeight = applet.height;
		this.offset = applet.height / 4;
	}

	void generate() {
		heightmap = new int[tWidth];
		heightmap[0] = (int) (offset * 2 + applet.random(tHeight * 0.25f));
		heightmap[tWidth - 1] = (int) (offset * 2 + applet.random(tHeight * 0.25f));
		heightmap = new int[tWidth];
		// left terrain point
		heightmap[0] = (int) applet.random(tHeight);
		// right terrain point
		heightmap[tWidth - 1] = (int) applet.random(tHeight);
		// make a queue
		Queue<Integer> q = new LinkedList<Integer>();

		// add first segment (left and right x terrain point)
		q.add(0);
		q.add(tWidth - 1);
		q.add(offset);
		// generates terrain until distance between points is not divisable by 2
		while (!q.isEmpty()) {
			// set x0 value to that which was first in queue and remove that which it was
			// set to from the queue (0)
			int x0 = q.remove();
			// set x1 value to that which was first in queue and remove that which it was
			// set to from the queue (width-1)
			int x1 = q.remove();
			// the x-coord for mid point is avearge of the two points
			int mid = (x0 + x1) / 2;
			heightmap[mid] = (int) ((heightmap[x0] + heightmap[x1]) / 2 + applet.random(-offset, offset));
			// set roughness value to that which was first in queue and remove that which it
			// was set to from the queue (offset)
			int roughness = q.remove();
			// the y-coord for mid point is avearge of left and right y-coord point + a
			// random offset
			heightmap[mid] = (int) ((heightmap[x0] + heightmap[x1]) / 2 + applet.random(-roughness, roughness));
			// continue if divisable by 2
			if (x1 - mid > 1) {
				// add the left segment (made up of the middle point as the "new" right point)
				q.add(x0);
				q.add(mid);

				q.add(roughness / 2); // divide roughness for a more smooth curve
				// add the right segment (made up of the middle point as the "new" left point)
				q.add(mid);
				q.add(x1);
				q.add(roughness / 2); // divide roughness for a more smooth curve
			}
		}
	}

	void displayTerrain() {
		// loop through length of array
		for (int i = 0; i < heightmap.length - 1; i++) {
			// the vertical line from the botoom of the screen to the current terrain point
			applet.line(i, applet.height, i, heightmap[i]);
			// the line from current terrain point to the next terrain point
			applet.line(i, heightmap[i], i + 1, heightmap[i + 1]);
		}
	}

	boolean terrainCollisionSquare(SquareBall squareBall) {
		float midX = (squareBall.corners[0].x + squareBall.corners[2].x) / 2;
		float midY = (squareBall.corners[0].y + squareBall.corners[2].y) / 2;
		float diameter = (float) Math.sqrt(2 * squareBall.size * squareBall.size);

		float maxY = midY + diameter / 2;

		int minX = (int) Math.floor(midX - diameter / 2);
		int maxX = (int) Math.ceil(midX + diameter / 2);

		for (int x = minX; x <= maxX; ++x) {
			// skip if x outside screen
			if (x < 0 || x >= heightmap.length)
				continue;
			int y = heightmap[x];

			// skip if below lowest point
			if (y > maxY)
				continue;

			float dx = x - midX;
			float dy = y - midY;
			// if dist lower than radius then there is collision
			float dist = (float) Math.sqrt(dx * dx + dy * dy);
			if (dist <= diameter / 2) {
				return true;
			}
		}
		// no collision between minX and maxX
		return false;
	}
}
