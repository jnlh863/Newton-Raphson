package newtonraphson;

import org.lsmp.djep.djep.DJep;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.nfunk.jep.Node;
import org.nfunk.jep.JEP;


public class nr2 {
	
	private String funcion = "";
	private double puntoInicial = 0.0;
	private double error = 0.0;
	
	
	String primeraD = "";
	String segundaD = "";
	
	JEP jep;
	DJep djep;
	Node nodeFuncion;
	Node nodoDerivada;
	Node nodoDerivada2;
	
	public nr2() {
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public double getPuntoInicial() {
		return puntoInicial;
	}

	public void setPuntoInicial(double puntoInicial) {
		this.puntoInicial = puntoInicial;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}
	
	
	public ArrayList<Double> newtonraphsonMejorado() {
		try {
			
			this.djep = new DJep();
			this.djep.addStandardFunctions();
			this.djep.addStandardConstants();
			this.djep.addComplex();
			this.djep.setAllowAssignment(true);
			this.djep.setAllowUndeclared(true);
			this.djep.setImplicitMul(true);
			this.djep.addStandardDiffRules();
			
			this.nodeFuncion = this.djep.parse(this.funcion);
			Node diff = this.djep.differentiate(nodeFuncion, "x");
			this.nodoDerivada = this.djep.simplify(diff);
			primeraD = this.djep.toString(this.nodoDerivada);
			
			
			this.nodeFuncion = this.djep.parse(primeraD);
			Node diff2 = this.djep.differentiate(nodeFuncion, "x");
			this.nodoDerivada2 = this.djep.simplify(diff2);
			segundaD = this.djep.toString(this.nodoDerivada2);
	
		}catch(Exception e) {
			System.out.println("Error");
		}
		
		int i = 0;
		int itera = 100;
		String original = this.funcion;
		String primeraDerv = primeraD;
		String segundaDerv = segundaD;
		
		ArrayList<Double> xi = new ArrayList<Double>();
		ArrayList<Double> ea = new ArrayList<Double>();
		ArrayList<Double> raices = new ArrayList<Double>();

		while(i<=itera) {
			
			String funcionP = "x-" + "((" + original + ")*(" + primeraDerv + "))" + "/" + "((" + primeraDerv + ")^2-" + "(" + original + ")*(" + segundaDerv + "))";

			try {
				jep = new JEP();
				this.jep.addStandardFunctions();
				this.jep.addStandardConstants();
				this.jep.addComplex();
				
				if(i==0) {
					this.jep.addVariable("x", this.puntoInicial);
				}else {
					this.jep.addVariable("x", xi.get(i));
				}
				
				this.jep.parseExpression(funcionP);
				double r = this.jep.getValue();
				double eAprox = 0.0;
				
				
				try {
					if(xi.isEmpty()) {
						xi.add(this.puntoInicial);
						ea.add(0.00);
						xi.add(r);
						eAprox = Math.abs((xi.get(i+1) - xi.get(i)) / (xi.get(i+1)))*100;
						DecimalFormat df = new DecimalFormat("0.00");
						String e = df.format(eAprox);
						if(Double.parseDouble(e) <= this.error) {
							break;
						}else {
							ea.add(Double.parseDouble(e));
							i++;
						}
					}else {
						xi.add(r);
						eAprox = Math.abs((xi.get(i+1) - xi.get(i)) / (xi.get(i+1)))*100;
						DecimalFormat df = new DecimalFormat("0.00");
						String e = df.format(eAprox);
						if(Double.parseDouble(e) <= this.error) {
							ea.add(Double.parseDouble(e));
							break;
						}else {
							ea.add(Double.parseDouble(e));
							i++;
						}
					}
		
				}catch(Exception e) {
					System.out.println("No se puede dividir entre cero.");
					break;
				}

			}catch(Exception e) {
				System.out.println("Error del programa");
				break;
			}
		}
		
		raices.addAll(xi);
		raices.addAll(ea);
		
		return raices;
	}
	
	
	

}
