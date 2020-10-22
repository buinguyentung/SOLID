package bnt.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * This architecture does not confort LSP.<br>
 * At first, EmbeddedEngineer and CloudEngineer are derivatives of Engineer.<br>
 * Then we add TestEngineer. However, TestEngineer cannot write any code.<br>
 * List<Engineer> iterator would throw a RuntimeException.<br>
 * 
 * @author BNT
 *
 */
class Engineer {
	private String name;

	public Engineer(String name) {
		this.name = name;
	}

	public void writeCode() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class EmbeddedEngineer extends Engineer {
	public EmbeddedEngineer(String name) {
		super(name);
	}

	@Override
	public void writeCode() {
		System.out.println(this.getName() + " do code for embedded devices");
	}
}

class CloudEngineer extends Engineer {
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

	@Override
	public void writeCode() {
		throw new RuntimeException("I cannot code");
	}
}

public class EngineerExample1 {
	public static void main(String[] args) {
		List<Engineer> engineers = new ArrayList<>();
		engineers.add(new EmbeddedEngineer("TungBN"));
		engineers.add(new CloudEngineer("VietVH"));
		engineers.add(new TestEngineer("HaiHV"));

		for (Engineer engineer : engineers) {
			System.out.println(engineer.getName());
			engineer.writeCode();
		}
	}
}
