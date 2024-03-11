package newtonraphson;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.nfunk.jep.JEP;


public class Grafica {
	
	public Grafica() {
	}
	
	JEP jep;

	public void graficar(ArrayList<Double> g, String funcion) {
		XYSeries series = new XYSeries(funcion);
	
		for(int i = 0; i<g.size(); i++) {
			jep = new JEP();
			this.jep.addStandardFunctions();
			this.jep.addStandardConstants();
			this.jep.addComplex();
			this.jep.addVariable("x", g.get(i));
			this.jep.parseExpression(funcion);
			double r = this.jep.getValue();
			
			series.add((double) g.get(i),r);
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Newton-Raphson", "x", "y", dataset, PlotOrientation.VERTICAL,
				true, false, false);

		ChartFrame frame = new ChartFrame("Newton-Raphson", chart);
		
		frame.pack();
		frame.setSize(800,460);
		frame.setLocation(30,130);
		ImageIcon icono = new ImageIcon("Icono/logo.png");
		frame.setIconImage(icono.getImage());
		frame.setVisible(true);
	}
}
