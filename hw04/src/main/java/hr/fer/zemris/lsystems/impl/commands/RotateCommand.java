package hr.fer.zemris.lsystems.impl.commands;


import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * This class represents rotate command. It is used for rotating current state of context.
 */
public class RotateCommand implements Command{
	
	private double angle;
	/**
	 * This is a basic constructor.
	 * @param color
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setCurrentDirection(ctx.getCurrentState().getCurrentDirection().rotated(angle).normalized());
	
	}

}
