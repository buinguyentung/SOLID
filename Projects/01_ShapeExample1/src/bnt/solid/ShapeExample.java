package bnt.solid;

import java.util.ArrayList;
import java.util.List;

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

/**
 * This solution satisfies OCP on one simple requirement: <br>
 * draw all shapes in the list. <br>
 * 
 * What if we want to add more shapes? Good
 * What if we want to draw Circle before Square? Not good anymore! 
 * 
 * @author Admin
 *
 */
public class ShapeExample {

	public static void drawAllShapes(List<IShape> shapes) {
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
