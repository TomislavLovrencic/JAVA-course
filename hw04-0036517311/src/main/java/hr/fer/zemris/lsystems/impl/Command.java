package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * 
 * @author Tomislav Lovrencic
 * 
 * This interface is used for doing diffrent commands on canvas.
 *
 */
public interface Command {
	/**
	 * This method is used to execute this command using given state taken from context and using painter as a drawing tool.
	 * @param ctx
	 * @param painter
	 */
	void execute(Context ctx, Painter painter);
}
