package tp.mod.compute.impl;

import tp.mod.compute.BasicComputer;

public class BasicComputerImpl implements BasicComputer {

	@Override
	public double square(double x) {
		return x * x;
	}

	@Override
	public double add(double x, double y) {
		return x + y;
	}

}
