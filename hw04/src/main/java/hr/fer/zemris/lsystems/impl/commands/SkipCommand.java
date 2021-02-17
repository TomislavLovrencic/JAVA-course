package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.oprpp1.math.Vector2D;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;


/**
 * This class represents skip command. It is used for skipping current state on context.
 */
public class SkipCommand implements Command {

	
	private double step;
	/**
	 * This is a basic constructor.
	 * @param color
	 */
	public SkipCommand(double step) {
		this.step = step;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		Vector2D position = ctx.getCurrentState().getCurrentPosition();
		Vector2D direction = ctx.getCurrentState().getCurrentDirection().normalized();
		
		Vector2D nextVector = position.added(direction.scaled(ctx.getCurrentState().getShiftLength()*step));
		
		ctx.getCurrentState().setCurrentPosition(nextVector);
		
	}

}
