package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;




/**
 * @author Tomislav Lovrencic
 *
 * This class represents Newton-Raphson fractals. Implementation of this class is  sequential (non-multithreaded).
 * It only uses a main thread to calcucalte and show the user fractals.
 */
public class Newton {
	
	/**
	 * @author Tomislav Lovrencic
	 * This static class is used to calculate appropriate values for each pixel and color it.
	 */
	public static class MyProducer implements IFractalProducer {
		
		/**
		 * Array of roots.
		 */
		Complex[] roots;
		/**
		 * final double value of convergenceTreshold.
		 */
		public static final double  convergenceTreshold =  0.001;
		/**
		 * final double value of rootTreshold.
		 */
		public static final double rootTreshold = 0.002;
		
		
		/**
		 * This is a basic constructor.
		 * @param r
		 */
		public MyProducer(List<Complex> r) {
			roots = new Complex[r.size()];
			for(int i=0;i<r.size();i++) {
				roots[i] = r.get(i);
			}
		}

		/**
		 * This method is used to calculate values for each pixel and color the image to show it to the user.
		 *
		 */
		public void produce(double reMin, double reMax, double imMin, double imMax,
				int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {

			System.out.println("Zapocinjem izracun...");
			
			short[] data = new short[width * height];
			ComplexRootedPolynomial polyRooted = new ComplexRootedPolynomial(Complex.ONE, roots);
			ComplexPolynomial poly = polyRooted.toComplexPolynom();
			int m = poly.order()+1;
			
			
			int offset = 0;
			for(int y = 0; y < height; y++) {
				if(cancel.get()) break;
				for(int x = 0; x < width; x++) {
				 Complex c = map_to_complex_plain(x, y, 0, width, 0, height, reMin, reMax, imMin, imMax);
				 Complex zn = c;
				 Complex znold = new Complex();
				 int iters = 0;
				 
				 do {
					 znold = zn;
					 zn = zn.sub(poly.apply(zn).divide(poly.derive().apply(zn)));
					 iters++;
				 } while(zn.sub(znold).module() > convergenceTreshold && iters<16*16*16);
				 
				 int index = polyRooted.indexOfClosestRootFor(zn, rootTreshold);
				 data[offset] =(short) (index + 1);
				 offset++;
				}
			}
			
			System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
			observer.acceptResult(data, (short)m, requestNo);
			
		}
		
	}
	
	/**
	 * This is a main method.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Complex> roots = new ArrayList<Complex>();
		
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
		System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
		
		int brojac = 1;
		
		while(true) {
			 
			System.out.print("Root "+brojac+"> ");
			String line = sc.nextLine();
			
			brojac++;
			
			if(line.equals("done")) {
				System.out.println("Image of fractal will appear shortly. Thank you!");
				FractalViewer.show(new MyProducer(roots));
				sc.close();
				break;
			}
			
			Complex number = Complex.parseComplexNumber(line);
			if(number != null) {
				roots.add(number);
			}
			else {
				System.out.println("Something went wrong while parsing. Correct form (x (+|-) iy)!");
			}
		
			
			
		}
	
	}
	
	
	
	/**
	 * This method is used to convert a position on image into a complex number.
	 * @param x
	 * @param y
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 * @param remin
	 * @param remax
	 * @param immin
	 * @param immax
	 * @return
	 */
	public static Complex map_to_complex_plain(int x, int y, int xmin,int  xmax,int  ymin,int  ymax,double  remin,double  remax,double  immin,double  immax) {
		double cre = x / (xmax-1.0) * (remax - remin) + remin;
		double cim = (ymax-1.0-y) / (ymax-1) * (immax - immin) + immin;
		
		return new Complex(cre,cim);
		
		
	}
	
	
}
