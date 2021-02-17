package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * This class represents color command. It is used for changing color at current state of context.
 */
public class ColorCommand implements Command {
	
	private Color color;
	
	/**
	 * This is a basic constructor.
	 * @param color
	 */
	public ColorCommand(Color color) {
		this.color = color;
	}
	

	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);
		
	}

}
