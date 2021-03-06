package model.interpreter.commands;

public class WhileCommand extends ConditionalCommand {

	@Override
	public void Execute(String[] array) {
		PredicateCommand tmp = (PredicateCommand)commands.get(0).getC();
		commands.get(0).calculate();
		
		while(tmp.getBool() != 0) {
			for (int i = 1; i < commands.size(); i++) { commands.get(i).calculate(); }
			
			commands.get(0).calculate();
		}
	}
}