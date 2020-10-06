package bnt.solid;

import java.util.ArrayList;
import java.util.List;

interface Shape {
	void draw();
}

class Square implements Shape {
	private int side;

	Square(int side) {
		this.side = side;
	}

	@Override
	public void draw() {
		System.out.println("Draw Square " + side);
	}
}

class Round implements Shape {
	private int radius;

	Round(int radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		System.out.println("Draw Round " + radius);
	}
}

/**
 * This solution satisfies OCP on one simple requirement: <br>
 * draw all shapes in the list. <br>
 * 
 * What if we want to add more shapes? Good
 * What if we want to draw Round before Square? Not good anymore! 
 * 
 * @author Admin
 *
 */
public class ShapeExample {

	public static void drawAllShapes(List<Shape> shapes) {
		for (Shape shape : shapes) {
			shape.draw();
		}
	}

	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<>();
		Shape shape = new Round(1);
		shapes.add(shape);
		shape = new Square(2);
		shapes.add(shape);
		shape = new Round(3);
		shapes.add(shape);
		shape = new Square(4);
		shapes.add(shape);

		drawAllShapes(shapes);
	}

}
