package utils;
import soldier.ArmedUnit;
import soldier.ArmedUnitSquad;

public interface VisitorClassicForArmedUnit {
	void visit(ArmedUnit s);
	void visit(ArmedUnitSquad a);
}
 