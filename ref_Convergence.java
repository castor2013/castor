package complexity;

import java.util.ArrayList;
import Traffic.*;


public class Convergence implements Indicator {
    int dim=Constantes.dimension;
    double alpha;
    double radius;
    double l;
    Jama.Matrix X      = new Jama.Matrix(dim,1);
    Jama.Matrix V      = new Jama.Matrix(dim,1);
    Jama.Matrix Xn     = new Jama.Matrix(dim,1);
    Jama.Matrix Vn     = new Jama.Matrix(dim,1);


    /*********************************************************************************************/
    /*                                 Constructor   KolmoNLin                                   */
    /*********************************************************************************************/

    public Convergence() {
	alpha = Constantes.alpha;
	radius = Constantes.radius; //en norme de separation
	l= -alpha / radius;
    }/*KolmoNLin*/


    /*********************************************************************************************/
    /*                                 value                                                     */
    /*********************************************************************************************/

    public double computeValue(ArrayList plotSet) {


	double convergence=0.0;
	Plot plotI,plotJ;
	double dx,dy,dz;
	double dvx,dvy,dvz;
	double r,div;

	System.out.println("Calcul de la valeur de convergence ...start");

	for(int i = 0; i < plotSet.size(); i++)
	    {
		plotI=(Plot)plotSet.get(i);
		for(int j = 0; j < plotSet.size(); j++)
		    {
			plotJ=(Plot)plotSet.get(j);
			dx=plotI.getXPos()-plotJ.getXPos();
			dy=plotI.getYPos()-plotJ.getYPos();
			dz=plotI.getZPos()-plotJ.getZPos();
			r=Math.sqrt(dx*dx+dy*dy+dz*dz);
			dvx=plotI.getVX()-plotJ.getVX();
			dvy=plotI.getVY()-plotJ.getVY();
			dvz=plotI.getVZ()-plotJ.getVZ();
			div=(dx*dvx+dy*dvy+dz*dvz)/r;
			if (div<0)
			    {
				convergence=convergence-div*Math.exp(l * r);
			    }/*if*/
		    }/*for*/
	    }/*for*/
	System.out.println("Calcul de la valeur de convergence ...end");
	return convergence;
    }



public  void computeMap(ArrayList plotSet, MapComplexity map) {

        double convergence=0.0;
        double dx,dy,dz;
        double dvx,dvy,dvz;

	double div,r;
	Plot plotI,plotJ;

	double[] buffer=new double[plotSet.size()];

	System.out.println("Calcul de la carte convergence ....start");




	for(int i = 0; i < plotSet.size(); i++)
	    {
		plotI=(Plot)plotSet.get(i);
		convergence=0;
		for(int j = 0; j < plotSet.size(); j++)
		    {
			plotJ=(Plot)plotSet.get(j);
			dx=plotI.getXPos()-plotJ.getXPos();
			dy=plotI.getYPos()-plotJ.getYPos();
			dz=plotI.getZPos()-plotJ.getZPos();
			r=Math.sqrt(dx*dx+dy*dy+dz*dz);
			dvx=plotI.getVX()-plotJ.getVX();
			dvy=plotI.getVY()-plotJ.getVY();
			dvz=plotI.getVZ()-plotJ.getVZ();
			div=(dx*dvx+dy*dvy+dz*dvz)/r;
			if (div<0)
			    {
				convergence=convergence-div*Math.exp(l * r);
			    }/*if*/
		    }/*for*/
		 buffer[i]=convergence;
	    }/*for*/

        for (int i=0;i<map.nbX;i++)
        {
          for (int j=0;j<map.nbY;j++)
          {
           for (int k=0;k<map.nbZ;k++)
           {
	       // initiate trajectory.
	       X.set(0,0,((double)i) * map.stepX + map.xMin);
	       X.set(1,0,((double)j) * map.stepY + map.yMin);
	       X.set(2,0,((double)k) * map.stepZ + map.zMin);
	       convergence=0.0;
	       for(int m = 0; m < plotSet.size(); m++)
		   {
		       plotI=(Plot)plotSet.get(m);

		       dx = X.get(0,0)-plotI.getXPos();
		       dy = X.get(1,0)-plotI.getYPos();
		       dz = X.get(2,0)-plotI.getZPos();
		       r=Math.sqrt(dx*dx+dy*dy+dz*dz);
		       convergence=convergence + buffer[m]*Math.exp(l * r);
		   }/*for*/
	       map._MapField[i][j][k].setMetric(convergence);
	   } // end k loop
	  } // end j loop
	} // end i loop
	System.out.println("Calcul de la carte convergence....end");
    }
}

