package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;


import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents Newton-Raphson fractals. Implementation of this class is  multithreaded.
 * It  uses more threads for seperation of works for faster results. 
 */
public class NewtonParallel {
	
	/**
	 * @author Tomislav Lovrencic
	 *
	 * This static class represents a signle job that some thread is suppose to do.
	 */
	public static class PosaoIzracun implements Runnable {
		/**
		 * final double value of convergenceTreshold.
		 */
		static final double  convergenceTreshold =  0.001;
		/**
		 * final double value of rootTreshold.
		 */
		static final double rootTreshold = 0.002;
		
		/**
		 * minimum real value.
		 */
		double reMin;
		/**
		 * maximum real value.
		 */
		double reMax;
		/**
		 * minimum imaginary value.
		 */
		double imMin;
		/**
		 * maximum imaginary value.
		 */
		double imMax;
		/**
		 * width of window.
		 */
		int width;
		/**
		 * height of window.
		 */
		int height;
		/**
		 * calculated minimum y value.
		 */
		int yMin;
		/**
		 * calculated maximum y value.
		 */
		int yMax;
		/**
		 * array to store results.
		 */
		short[] data;
		/**
		 * boolean cancel.
		 */
		AtomicBoolean cancel;
		/**
		 * rooted polynomial.
		 */
		ComplexRootedPolynomial polyRooted;
		/**
		 * polynomial.
		 */
		ComplexPolynomial poly;
		/**
		 * instance of empty job.
		 */
		public static PosaoIzracun NO_JOB = new PosaoIzracun();
		
		/**
		 *  This is a private constructor.
		 */
		private PosaoIzracun() {
		}
		
		
		/**
		 * This is a basic constructor.
		 * @param reMin
		 * @param reMax
		 * @param imMin
		 * @param imMax
		 * @param width
		 * @param height
		 * @param yMin
		 * @param yMax
		 * @param m
		 * @param data
		 * @param cancel
		 * @param polyRooted
		 * @param poly
		 */
		public PosaoIzracun(double reMin, double reMax, double imMin,
				double imMax, int width, int height, int yMin, int yMax, 
				short[] data, AtomicBoolean cancel,ComplexRootedPolynomial polyRooted,ComplexPolynomial poly) {
			super();
			this.reMin = reMin;
			this.reMax = reMax;
			this.imMin = imMin;
			this.imMax = imMax;
			this.width = width;
			this.height = height;
			this.yMin = yMin;
			this.yMax = yMax;
			this.data = data;
			this.cancel = cancel;
			this.polyRooted = polyRooted;
			this.poly = poly;
			
		}

		/**
		 * This is a job that this thread has to execute.
		 */
		public void run() {

			for(int y =yMin; y <= yMax; y++) {
				if(cancel.get()) break;
				for(int x = 0; x < width; x++) {
				 Complex c = Newton.map_to_complex_plain(x, y, 0, width, 0, height, reMin, reMax, imMin, imMax);
				 Complex zn = c;
				 Complex znold = new Complex();
				 int iters = 0;
				 
				 do {
					 znold = zn;
					 zn = zn.sub(poly.apply(zn).divide(poly.derive().apply(zn)));
					 iters++;
				 } while(zn.sub(znold).module() > convergenceTreshold && iters<16*16*16);
				 
				 int index = polyRooted.indexOfClosestRootFor(zn, rootTreshold);
				 data[y*width + x] =(short) (index + 1);

				}
			}
		}
		
	}
	
	/**
	 * @author Tomislav Lovrencic
	 * This static class is used to calculate appropriate values for each pixel and color it and display it to user.
	 */
	public static class MyProducer implements IFractalProducer {
		
		/**
		 * number of tracks.
		 */
		private int brojTraka;
		
		/**
		 * number of workers.
		 */
		private int brojRadnika;
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
		 * 
		 * @param r
		 * @param traka
		 * @param radnici
		 */
		public MyProducer(List<Complex> r,int traka,int radnici) {
			this.brojRadnika = radnici;
			this.brojTraka = traka;
			roots = new Complex[r.size()];
			for(int i=0;i<r.size();i++) {
				roots[i] = r.get(i);
			}
		}

		/**
		 * This method is used to calculate values for each pixel and color the image to show it to the user.
		 * It creates multiple threads and multiple jobs for seperation of works.
		 */
		public void produce(double reMin, double reMax, double imMin, double imMax,
				int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {

			System.out.println("Zapocinjem izracun...");
			
			final BlockingQueue<PosaoIzracun> queue = new LinkedBlockingQueue<PosaoIzracun>();
			int brojY = height / brojTraka;
			
			short[] data = new short[width * height];
			ComplexRootedPolynomial polyRooted = new ComplexRootedPolynomial(Complex.ONE, roots);
			ComplexPolynomial poly = polyRooted.toComplexPolynom();
			int m = poly.order()+1;
			
			Thread[] radnici = new Thread[brojRadnika];
			for(int i=0;i<radnici.length;i++) {
				radnici[i] = new Thread(new Runnable() {
					public void run() {
						while(true) {
							PosaoIzracun p = null;
							try {
								p = queue.take();
								if(p==PosaoIzracun.NO_JOB) break;
							} catch (InterruptedException e) {
								continue;
							}
							p.run();
						}
						
					}
				});
			}
			
			for(int i = 0; i < radnici.length; i++) {
				radnici[i].start();
			}
			
			if(brojTraka > height) brojTraka = height;
			for(int i=0;i<brojTraka;i++) {
				int yMin = i * brojY;
				int yMax = yMin + brojY - 1;
				if(i == brojTraka - 1) {
					yMax = height -1;
				}
				PosaoIzracun posao = new PosaoIzracun(reMin, reMax, imMin, imMax, width, height, yMin, yMax, data, cancel,polyRooted,poly);
				while(true) {
					try {
						queue.put(posao);
						break;
					}catch(InterruptedException e) {
						continue;
					}
				}
			}
			
			
			for(int i = 0; i < radnici.length; i++) {
				while(true) {
					try {
						queue.put(PosaoIzracun.NO_JOB);
						break;
					} catch (InterruptedException e) {
					}
				}
			}
			
			for(int i = 0; i < radnici.length; i++) {
				while(true) {
					try {
						radnici[i].join();
						break;
					} catch (InterruptedException e) {
					}
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
		
		int brojW = -1;
		int brojT = -1;
		int brojacTracks = 0;
		int brojacWorkers = 0;
		
		for(int i=0;i<args.length;i++) {
			if(args[i].contains("--workers=") && brojacWorkers < 1) {
				brojW = Integer.parseInt(args[i].split("=")[1]);
				if(args[i].split("=")[0].length() != 9) {
					System.out.println("Arguments are not given properly!");
					System.exit(1);
				}
				brojacWorkers++;
			}
			else if(args[i].contains("--tracks=") && brojacTracks < 1) {
				brojT = Integer.parseInt(args[i].split("=")[1]);
				if(args[i].split("=")[0].length() != 8) {
					System.out.println("Arguments are not given properly!");
					System.exit(1);
				}
				brojacTracks++;
			}
			else if(args[i].contains("-w") && brojacWorkers < 1) {
				brojW = Integer.parseInt(args[i+1]);
				if(args[i].length() != 2) {
					System.out.println("Arguments are not given properly!");
					System.exit(1);
				}
				brojacWorkers++;
				i++;
			}
			else if(args[i].contains("-t") && brojacTracks < 1) {
				brojT = Integer.parseInt(args[i+1]);
				if(args[i].length() != 2) {
					System.out.println("Arguments are not given properly!");
					System.exit(1);
				}
				brojacTracks++;
				i++;
			}
			else {
				System.out.println("Arguments are not given properly!");
				System.exit(1);
			}
		}
		
		
		if(brojT == -1) brojT = 4 * Runtime.getRuntime().availableProcessors();
		if(brojW == -1) brojW = Runtime.getRuntime().availableProcessors();
		
		
		System.out.println("U choose to go for -> "+brojT+ " tracks");
		System.out.println("U choose to go for -> "+brojW+ " workers");
		
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
				FractalViewer.show(new MyProducer(roots,brojT,brojW));
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
}
