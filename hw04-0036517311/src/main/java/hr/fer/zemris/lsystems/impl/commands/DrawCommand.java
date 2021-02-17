package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.oprpp1.math.Vector2D;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
/**
 * This class represents draw command. It is used for drawing a line from current state to next state,
 * which is calculated with given step.
 */
public class DrawCommand implements Command {
	
	
	private double step;
	/**
	 * This is a basic constructor.
	 * @param color
	 */
	public DrawCommand(double step) {
		this.step = step;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		Vector2D position = ctx.getCurrentState().getCurrentPosition();
		Vector2D direction = ctx.getCurrentState().getCurrentDirection().normalized();
		
		Vector2D nextVector = position.added(direction.scaled(ctx.getCurrentState().getShiftLength()*step));
		
		painter.drawLine(position.getX(), position.getY(), nextVector.getX(), nextVector.getY(), ctx.getCurrentState().getColor(), 1f);
		ctx.getCurrentState().setCurrentPosition(nextVector);
	
     
	}

}
