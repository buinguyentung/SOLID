package bnt.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * One solution is move writeCode() method to ICoder interface.<br>
 * Use List<ICoder> to store the engineers who could code.<br>
 * 
 * @author BNT
 *
 */
class Engineer {
	private String name;

	public Engineer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

interface ICoder {
	void writeCode();
}

class EmbeddedEngineer extends Engineer implements ICoder {
	public EmbeddedEngineer(String name) {
		super(name);
	}

	@Override
	public void writeCode() {
		System.out.println(this.getName() + " do code for embedded devices");
	}
}

class CloudEngineer extends Engineer implements ICoder {
	public CloudEngineer(String name) {
		super(name);
	}

	@Override
	public void writeCode() {
		System.out.println(this.getName() + " do code cloud-native apps");
	}
}

class TestEngineer extends Engineer {
	public TestEngineer(String name) {
		super(name);
	}
}

public class EngineerExample2 {

	public static void main(String[] args) {
		EmbeddedEngineer engineer1 = new EmbeddedEngineer("TungBN");
		CloudEngineer engineer2 = new CloudEngineer("VietVH");
		TestEngineer engineer3 = new TestEngineer("HaiHV");

		List<Engineer> engineers = new ArrayList<>();
		engineers.add(engineer1);
		engineers.add(engineer2);
		engineers.add(engineer3);

		List<ICoder> coders = new ArrayList<>();
		coders.add(engineer1);
		coders.add(engineer2);
		// coders.add(engineer3); // Error

		for (Engineer engineer : engineers) {
			System.out.println(engineer.getName());
		}

		for (ICoder coder : coders) {
			coder.writeCode();
		}
	}
}
