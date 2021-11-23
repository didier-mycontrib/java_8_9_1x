package tp.mod.compute;

import tp.mod.compute.impl.BasicComputerImpl;

public interface BasicComputer {
	public static BasicComputer buildBasicComputer() {
		return new BasicComputerImpl();
	}
	public double square(double x);
	public double add(double x,double y);
}
