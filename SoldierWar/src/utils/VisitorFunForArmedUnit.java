package utils;
import soldier.ArmedUnit;
import soldier.ArmedUnitSquad;

public interface VisitorFunForArmedUnit<T> {
	T visit(ArmedUnit s);
	T visit(ArmedUnitSquad a);
	T compos(T x1, T x2);
}
