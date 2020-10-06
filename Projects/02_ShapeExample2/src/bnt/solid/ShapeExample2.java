package bnt.solid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Use Java Comparable as sorting abstraction. <br>
 * It satisfies the sorting requirement, but what if we add more shapes? <br>
 * We have to modify every compareTo() method on each class. \<br>
 * 
 * This design does not follow OCP. <br>
 * 
 * @author Admin
 *
 */
interface IShape extends Comparable<IShape> {
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

	@Override
	public int compareTo(IShape o) {
		if (o.getClass() == Circle.class) {
			return 1;
		} else {
			return 0;
		}
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

	@Override
	public int compareTo(IShape o) {
		if (o.getClass() == Square.class) {
			return -1;
		} else {
			return 0;
		}
	}
}

public class ShapeExample2 {
	public static void drawAllShapes(List<IShape> shapes) {
		Collections.sort(shapes);
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
