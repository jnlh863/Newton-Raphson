package newtonraphson;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.JEP;


public class nr1 {
	
	private String funcion = "";
	private double puntoInicial = 0.0;
	private double error = 0.0;
	
	private double resultado = 0.0;

	String primeraD = "";
	String segundaD = "";
	

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
	
	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}


	public double iteracionesraiz() {
	
		try {
			
			this.djep = new DJep();
			this.djep.addStandardFunctions();
			this.djep.addStandardConstants();
			this.djep.addComplex();
			this.djep.setAllowAssignment(true);
			this.djep.setAllowUndeclared(true);
			this.djep.setImplicitMul(true);
			this.djep.addStandardDiffRules();
			
			this.nodeFuncion = this.djep.parse(funcion);
			
			Node diff = this.djep.differentiate(nodeFuncion, "x");
		
			this.nodoDerivada = this.djep.simplify(diff);
			
			primeraD = this.djep.toString(this.nodoDerivada);
	
		}catch(Exception e) {
			System.out.println("Error");
		}
		
		String original = this.funcion;
		String primeraDerv = primeraD;
		String funcionP = "x-" + original + "/" +  primeraDerv;
		
		jep = new JEP();
		this.jep.addStandardFunctions();
		this.jep.addStandardConstants();
		this.jep.addComplex();
		this.jep.addVariable("x", this.puntoInicial);
		this.jep.parseExpression(funcionP);
		this.resultado = this.jep.getValue();
		double r = this.resultado;
		return r;

	}


}
