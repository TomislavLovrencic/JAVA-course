package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * This class represents scale command. It is used for scaling current state on context.
 */
public class ScaleCommand implements Command {
	
	private double factor;
	/**
	 * This is a basic constructor.
	 * @param color
	 */
	public ScaleCommand(double factor) {
		this.factor = factor;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setShiftLength(ctx.getCurrentState().getShiftLength()*factor);
		
	}

}
