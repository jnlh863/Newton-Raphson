package newtonraphson;

import org.lsmp.djep.djep.DJep;
import java.lang.Math;
import java.util.ArrayList;
import org.nfunk.jep.Node;
import org.nfunk.jep.JEP;

public class nr1{
	
	private String funcion = "";
	private double puntoInicial = 0.0;
	private double error = 0.0;

	String primeraD = "";
	JEP jep;
	DJep djep;
	Node nodeFuncion;
	Node nodoDerivada;
	
	public nr1() {
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

	public ArrayList<Double> newtonRaphson() {
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
	
		}catch(Exception e) {
			System.out.println("Error");
		}
		
		int i = 0;
		int itera = 100;
		String original = this.funcion;
		String primeraDerv = primeraD;
		ArrayList<Double> xi = new ArrayList<Double>();
		ArrayList<Double> ea = new ArrayList<Double>();
		ArrayList<Double> raices = new ArrayList<Double>();

		while(i<=itera) {
			
			String funcionP = "x-" + "(" + original + ")" + "/" +  "(" + primeraDerv + ")";

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
						ea.add(0.0);
						xi.add(this.puntoInicial);
						xi.add(r);	
						eAprox = Math.abs((xi.get(i+1) - xi.get(i)) / (xi.get(i+1)))*100;
						double truncado = Math.floor(eAprox* 10) / 10;
						if(this.error != truncado) {
							ea.add(truncado);
							i++;
							continue;
						}else {
							break;
						}
					}

					
					xi.add(r);
					eAprox = Math.abs((xi.get(i+1) - xi.get(i)) / (xi.get(i+1)))*100;
					double truncado = Math.floor(eAprox* 10) / 10;
					if(this.error != truncado) {
						ea.add(truncado);
						i++;
					}else {
						ea.add(truncado);
						break;
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
