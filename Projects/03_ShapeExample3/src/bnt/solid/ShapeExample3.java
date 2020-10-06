package bnt.solid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bnt.solid.IShape;
import bnt.solid.Circle;
import bnt.solid.Square;

/**
 * Data-driven. <br>
 * Use Java Comparator to make ShapeComparer. <br>
 * 
 * This solution welcomes new Shapes, and new sorting rules. <br>
 * 
 * @author Admin
 *
 */
class ShapeComparer implements Comparator<IShape> {
	private Map<Class<?>, Integer> priorities = new HashMap<>();

	public ShapeComparer() {
		priorities.put(Circle.class, 1);
		priorities.put(Square.class, 2);
	}

	public int getPriority(Class<?> c) {
		if (priorities.containsKey(c)) {
			return priorities.get(c);
		}
		return 0;
	}

	@Override
	public int compare(IShape o1, IShape o2) {
		int p1 = getPriority(o1.getClass());
		int p2 = getPriority(o2.getClass());
		return p1 - p2;
	}
}

interface IShape {
	void draw();
}

class Square implements IShape {
	private int side;

	Square(int side) {
		this.side = side;
	}

	@Override
	public void draw() {
		System.out.println("Draw Square " + side);
	}
}

class Circle implements IShape {
	private int radius;

	Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		System.out.println("Draw Circle " + radius);
	}
}

public class ShapeExample3 {

	public static void drawAllShapes(List<IShape> shapes) {
		// Sort by priority
		ShapeComparer shapeComparer = new ShapeComparer();
		Collections.sort(shapes, shapeComparer);
		for (IShape shape : shapes) {
			shape.draw();
		}
	}

	public static void main(String[] args) {
		List<IShape> shapes = new ArrayList<>();
		IShape shape = new Circle(1);
		shapes.add(shape);
		shape = new Square(2);
		shapes.add(shape);
		shape = new Circle(3);
		shapes.add(shape);
		shape = new Square(4);
		shapes.add(shape);
		shape = new Square(5);
		shapes.add(shape);
		shape = new Circle(6);
		shapes.add(shape);

		drawAllShapes(shapes);
	}

}
