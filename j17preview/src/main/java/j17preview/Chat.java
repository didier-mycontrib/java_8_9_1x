package j17preview;

//déclarée final , la classe Chat ne peut pas avoir de sous classe
public final class Chat implements AnimalDomestique{

	@Override
	public void sayHello() {
		System.out.print("chat miaou - ");
	}

	public void ronronner() {
		System.out.println(" ronron ");
	}
}
