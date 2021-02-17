package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.oprpp1.custom.collections.Dictionary;
import hr.fer.oprpp1.math.Vector2D;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;

/**
 * @author Tomislav Lovrencic
 *
 * This class is used for implementation of  {@link LSystemBuilder}. This class is creating an 
 * instance of {@link LSystem} by its method build. After creating it this class is used for drawing
 * and creating fractals by its given parameters in constructor.
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder{
	
	/** 
	 * Dictionary of productions.
	 */
	private Dictionary<Character ,String> productions;
	
	/**
	 * Dictionary of commands.
	 */
	private Dictionary<Character,String> commands;
	
	/**
	 * Starting angle for our turtle state.
	 */
	private double angle;
	
	
	/**
	 *  Starting vector.
	 */
	private Vector2D origin;
	
	/**
	 *  Starting string od productions.
	 */
	private String axiom;
	
	/**
	 *  unit Length.
	 */
	private double unitLength;
	
	/**
	 *  unit length degree scaler.
	 */
	private double unitLengthDegreeScaler;
	
	
	/** 
	 * This is a basic constructor.
	 */
	public LSystemBuilderImpl() {
		productions = new Dictionary<>();
		commands = new Dictionary<>();
		angle = 0;
		origin = new Vector2D(0,0);
		axiom = "";
		unitLength = 0.1;
		unitLengthDegreeScaler = 1;
	}
	
	
	

	@Override 
	public LSystem build() {
		return new LSystemImplementation();
	}

	@Override
	public LSystemBuilder configureFromText(String[] arg0) {
		for(int i=0;i<arg0.length;i++) {
			String[] elems = arg0[i].split("\\s+");
			if(elems[0].equals("")) continue;
			try {
				switch(elems[0]) {
				case "origin":
					setOrigin(Double.valueOf(elems[1]),Double.valueOf(elems[2]));
					break;
				case "angle":
					setAngle(Double.valueOf(elems[1]) * Math.PI / 180);
					break;
				case "unitLength":
					setUnitLength(Double.valueOf(elems[1]));
					break;
				case "unitLengthDegreeScaler":
					String[] c = elems[2].split("");
					String v = "";
					if(c.length != 1) {
						v=c[1];
					}
					else {
						v=elems[3];
					}
					setUnitLengthDegreeScaler(Double.valueOf(elems[1]) / Double.valueOf(v));
					break;
				case "command":
					if(elems[2].equals("push") || elems[2].equals("pop")) {
						registerCommand(elems[1].charAt(0), elems[2]);
					}
					else {
						registerCommand(elems[1].charAt(0), elems[2]+" " + elems[3]);
					}
					break;
				case "axiom":
					setAxiom(elems[1]);
					break;
				case "production":
					registerProduction(elems[1].charAt(0), elems[2]);
					break;
				default : 
					break;
				}
				
			} catch(NumberFormatException e) {
				System.out.println("Number format error!");
			}
			
		}
		return this;
	}

	@Override
	public LSystemBuilder registerCommand(char arg0, String arg1) {
		this.commands.put( arg0, arg1);
		return this;
	}

	@Override
	public LSystemBuilder registerProduction(char arg0, String arg1) {
		this.productions.put( arg0, arg1);
		return this;
		
	}

	@Override
	public LSystemBuilder setAngle(double arg0) {
		this.angle = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setAxiom(String arg0) {
		this.axiom = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setOrigin(double arg0, double arg1) {
		this.origin = new Vector2D(arg0,arg1);
		return this;
	}

	@Override
	public LSystemBuilder setUnitLength(double arg0) {
		this.unitLength = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double arg0) {
		this.unitLengthDegreeScaler = arg0;
		return this;
	}
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This class represents an implementation of {@link LSystem}. Its used to generate needed string that is 
	 * used by method draw for executing appropriate command that is located in dictionary commands.
	 *
	 */
	public  class LSystemImplementation implements LSystem {

		@Override
		public void draw(int arg0, Painter arg1) {
			TurtleState state = new TurtleState(origin, new Vector2D(1,0).rotated(angle).normalized(), Color.black, unitLength*Math.pow(unitLengthDegreeScaler, arg0));
			Context ctx = new Context(state);
			
			String tek = generate(arg0);
			
			for(int i=0;i<tek.length();i++) {
				String com = commands.get(tek.charAt(i));
				if(com != null ) {
					String[] hlp = com.split("\\s+");
					
					try {
						switch(hlp[0]) {
						
							case "draw":
								DrawCommand draw = new DrawCommand(Double.valueOf(hlp[1]));
								draw.execute(ctx, arg1);
								break;
							case "skip":
								SkipCommand skip = new SkipCommand(Double.valueOf(hlp[1]));
								skip.execute(ctx, arg1);
								break;
							case "scale":
								ScaleCommand scale = new ScaleCommand(Double.valueOf(hlp[1]));
								scale.execute(ctx, arg1);
								break;
							case "rotate":
								RotateCommand rotate = new RotateCommand(Double.valueOf(hlp[1]) * Math.PI / 180);
								rotate.execute(ctx, arg1);
								break;
							case "push":
								PushCommand push = new PushCommand();
								push.execute(ctx, arg1);
								break;
							case "pop":
								PopCommand pop = new PopCommand();
								pop.execute(ctx, arg1);
								break;
							case "color":
								Color clr = Color.decode("#"+hlp[1]);
								ColorCommand color = new ColorCommand(clr);
								color.execute(ctx, arg1);
								break;
							default:
								break;
							}
					} catch(NumberFormatException e) {
						System.out.println("Number format error!");
					}
					
				}
			}
			
		}

		@Override
		public String generate(int arg0) {
			String b = "";
			StringBuilder result = new StringBuilder();
			
			if(arg0 == 0) return axiom;

			b = generate(arg0-1);
			
			for(int i=0;i<b.length();i++) {
				if(productions.get(b.charAt(i)) == null) {
					result.append(b.charAt(i));
				}
				else {
					result.append(productions.get(b.charAt(i)));
				}
			}
		
			return result.toString();
			
			
			/*
			StringBuilder s = new StringBuilder();
			s.append(axiom);
			char c;
			int index = 0;
		
			while(index < arg0) {

				for(int i=0;i<s.length();i++) {
					if(productions.get(s.charAt(i)) != null) {
						c = s.charAt(i);
						s.replace(i, i+1, productions.get(s.charAt(i)));
						i+=productions.get(c).length()-1;
						
					}
				}
				index++;
		
			}
			return s.toString();*/
		}
		
		
	}

}
