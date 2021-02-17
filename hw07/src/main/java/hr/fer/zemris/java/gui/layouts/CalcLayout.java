package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import hr.fer.zemris.java.gui.calc.model.CalculatorInputException;


/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a layout for calculator.
 */
public class CalcLayout implements java.awt.LayoutManager2 {
	/**
	 * This is a gap between buttons.
	 */
	private int gap;
	
	/**
	 *  This is  maxHeight.
	 */
	private int maxHeight = 0;
	
	/**
	 *  This is minWidth.
	 */
	private int maxWidth  = 0;
	
	/**
	 * This is map consisted of components anf their position.
	 */
	private Map<Component,RCPosition> mapa;
	
	/**
	 * This is a basic constructor.
	 */
	public CalcLayout() {
		this.gap = 0;
		this.mapa = new HashMap<>();
	}
	
	/**
	 * This is a constructor for layout with given gap.
	 * @param gap
	 */
	public CalcLayout(int gap) {
		if(gap < 0) {
			throw new CalculatorInputException();
		}
		this.gap = gap;
		this.mapa = new HashMap<>();
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		mapa.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return calculateSize(parent, "pref");

	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return calculateSize(parent, "min");
	}

	@Override
	public void layoutContainer(Container parent) {
		double x = 0;
		double y = 0;
		
		for(Component c : parent.getComponents()) {
			
			RCPosition pos = mapa.get(c);
			
			double prefWidth = (parent.getWidth() - gap * 6.0) / 7;
			double prefHeight = (parent.getHeight() - gap * 4.0) / 5;
			
			if(pos.getRow() == 1 && pos.getColumn() == 1) {
				c.setBounds((int) x, (int) y, (int) (5 * prefWidth  +(gap * 4)),(int) prefHeight);
			}
			else {
				x =  (prefWidth + gap) * (pos.getColumn()-1);
				y = (prefHeight + gap) * (pos.getRow()-1);
				c.setBounds((int) x,(int) y, (int) prefWidth +1, (int) prefHeight+1);
			}

		}

	}
	
	/**
	 * This method is used to check for validations for RCPosition.
	 * @param constraints
	 */
	public void checkForValidation(Object constraints) {
			RCPosition p = (RCPosition) constraints;
			if(p.getColumn() < 1 || p.getColumn() > 7 || p.getRow() < 1 ||
					p.getRow() > 5) {
				throw new CalcLayoutException();
			}
			if(p.getRow() == 1) {
				if(p.getColumn() > 1 && p.getColumn() < 6) {
					throw new CalcLayoutException();
				}
			}
		
			for(Entry<Component, RCPosition> elem : mapa.entrySet()) {
				if(elem.getValue().getColumn() == p.getColumn() && 
						elem.getValue().getRow() == p.getRow()) {
					throw new CalcLayoutException();
				}
			}
			
			
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if(comp == null || constraints == null) throw new NullPointerException();
		
		if(constraints instanceof RCPosition) {
			
			checkForValidation(constraints);
			mapa.put(comp, (RCPosition) constraints);
			return;
		}
		if(constraints instanceof String) {
			
			RCPosition p = RCPosition.parse((String) constraints);
			if(p == null) throw new IllegalArgumentException();
			for(Entry<Component, RCPosition> elem : mapa.entrySet()) {
				if(elem.getValue().equals(p)) {
					throw new CalcLayoutException();
				}
				
			}
			checkForValidation(p);
			mapa.put(comp, p);
			return;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return calculateSize(target, "max");
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {

	
	}
	
	public Dimension calculateSize(Container c,String helper) {
		Dimension dim = new Dimension(0,0);
		maxHeight = Arrays.asList(c.getComponents()).stream()
				.mapToInt((a) -> {
					if(helper.equals("min")) {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getMinimumSize().height - 4*gap)/5;
						}
						return a.getMinimumSize().height;
					}else if(helper.equals("max")) {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getMaximumSize().height - 4*gap)/5;
						}
						return a.getMaximumSize().height;
					}
					else {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getPreferredSize().height - 4*gap)/5;
						}
						return a.getPreferredSize().height;
					}
					
				})
				.max().getAsInt();
		maxWidth = Arrays.asList(c.getComponents()).stream()
				.mapToInt((a) -> {
					if(helper.equals("min")) {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getMinimumSize().width - 4*gap)/5;
						}
						return a.getMinimumSize().width;
					}else if(helper.equals("max")) {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getMaximumSize().width - 4*gap)/5;
						}
						return a.getMaximumSize().width;
					} else {
						if(mapa.get(a).getColumn() == 1 && mapa.get(a).getRow()==1) {
							return (a.getPreferredSize().width - 4*gap)/5;
						}
						return a.getPreferredSize().width;
					}
				})
				.max().getAsInt();
		
		
		Insets ins = c.getInsets();
		
		
		dim.height += maxHeight*5 + 4*gap + ins.bottom + ins.top;
		dim.width += maxWidth*7 + 6*gap+ ins.left + ins.right;
		
		return dim;
	}

}
