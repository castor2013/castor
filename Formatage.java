/*  This file is part of CASTOR.

    CASTOR is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CASTOR.  If not, see <http://www.gnu.org/licenses/

*/


import java.io.*;
import java.util.*;
import java.sql.Time;


public class Formatage {

    protected static double DEG_TO_RAD = Math.PI/180;
    protected static double FEET_TO_METER = 0.3048;
    protected static double KT_TO_MS = 1852.0 / 3600.0;
    protected static double FTM_TO_MS = 0.3048 / 60.0;
    protected static double FL_TO_METER = 30.48006096012;
    protected static double NM_TO_METER = 1852.0;

    public void opasCastor(String nomGen)
	{
	    String nomFichierOpas=nomGen+".OPAS";
	    String nomFichierCastor=nomGen+".CASTOR";
	    try {

		FileReader file = new FileReader(nomFichierOpas);
		BufferedReader fileInput = new BufferedReader(file);
		String ligne,buffer;
		int alti,speed,ftpm;
		double latDg,lonDg,altiFt,heading,sec;

		FileWriter file2 = new FileWriter(nomFichierCastor);
		PrintWriter fileOutput = new PrintWriter(file2);
		String text;
		int type=1; //plot
		long id,time;
		double latRd,lonRd,altiM,speedMs,vX,vY,vZ;

		int num,cpt=1;
		while ((ligne = fileInput.readLine()) != null)
		{
		    // cpt++;
		    StringTokenizer tokenizer = new StringTokenizer(ligne);
		    //lecture des champs de la ligne OPAS
		    time=Long.parseLong(tokenizer.nextToken());
		    id=Long.parseLong(tokenizer.nextToken());
		    latDg=Double.parseDouble(tokenizer.nextToken());
		    lonDg=Double.parseDouble(tokenizer.nextToken());
		    altiFt=Integer.parseInt(tokenizer.nextToken());
		    heading=Double.parseDouble(tokenizer.nextToken());
		    speed=Integer.parseInt(tokenizer.nextToken());
		    ftpm=Integer.parseInt(tokenizer.nextToken());

		    
		    //conversion des unites
		    latRd=latDg*DEG_TO_RAD;
		    lonRd=lonDg*DEG_TO_RAD;
		    altiM=altiFt*FEET_TO_METER;
		    speedMs=KT_TO_MS*(double)(speed);
		    vX=speedMs*Math.sin(heading*DEG_TO_RAD);
		    vY=speedMs*Math.cos(heading*DEG_TO_RAD);
		    vZ=FTM_TO_MS*(double)(ftpm);
		    //ecriture dans le nouveau fichier
		    fileOutput.print(type + " ");
		    fileOutput.print(id + " ");
		    fileOutput.print(time + " ");
		    fileOutput.print(latRd + " ");
		    fileOutput.print(lonRd + " ");
		    fileOutput.print(altiM + " ");
		    fileOutput.print(vX + " ");
		    fileOutput.print(vY + " ");
		    fileOutput.print(vZ + "\n");
		}
		fileInput.close();
		fileOutput.close();
	    } catch(IOException ioe) {
	    	System.err.println("Erreur fichier");
	    }
	}

    public void csvCastor(String nomGen)
	{
	    String nomFichierOpas=nomGen+".csv";
	    String nomFichierCastor=nomGen+".CASTOR";
	    try {

		FileReader file = new FileReader(nomFichierOpas);
		BufferedReader fileInput = new BufferedReader(file);
		String ligne,buffer;
		int alti;
		double latDg,lonDg,altiFt,speed,heading,ftpm,sec;

		FileWriter file2 = new FileWriter(nomFichierCastor);
		PrintWriter fileOutput = new PrintWriter(file2);
		String text;
		int type=1; //plot
		long id,time;
		double latRd,lonRd,altiM,speedMs,vX,vY,vZ;
		String[] tab;
		String[] tabLigne;

		int num,cpt=0;
		while ((ligne = fileInput.readLine()) != null)
		{
		    if (cpt>0)
			{
			    tabLigne=ligne.split(";");

			    buffer=tabLigne[0];
			    tab=buffer.split(":");
			    time=Long.parseLong(tab[0])*3600 + Long.parseLong(tab[1])*60 + Long.parseLong(tab[2]);


			    buffer=tabLigne[1];
			    id=Long.parseLong(buffer);


			    buffer=tabLigne[17];
			    tab=buffer.split(":");
			    latDg=((double)Long.parseLong(tab[0])*3600 + Long.parseLong(tab[1])*60 + Long.parseLong(tab[2]))/3600;;

			    buffer=tabLigne[18];
			    tab=buffer.split(":");
			    lonDg=((double)Long.parseLong(tab[0])*3600 + Long.parseLong(tab[1])*60 + Long.parseLong(tab[2]))/3600;;


			    buffer=tabLigne[3];
			    altiFt=Integer.parseInt(buffer);

			    buffer=tabLigne[7];
			    heading=Double.parseDouble(buffer);

			    buffer=tabLigne[6];
			    speed=Double.parseDouble(buffer);

			    buffer=tabLigne[8];
			    ftpm=Double.parseDouble(buffer);

		    
			    //conversion des unites
			    latRd=latDg*DEG_TO_RAD;
			    lonRd=lonDg*DEG_TO_RAD;
			    altiM=altiFt*FEET_TO_METER;
			    speedMs=KT_TO_MS*(double)(speed);
			    vX=speedMs*Math.sin(heading*DEG_TO_RAD);
			    vY=speedMs*Math.cos(heading*DEG_TO_RAD);
			    vZ=FTM_TO_MS*(double)(ftpm);
			    //ecriture dans le nouveau fichier
			    fileOutput.print(type + " ");
			    fileOutput.print(id + " ");
			    fileOutput.print(time + " ");
			    fileOutput.print(latRd + " ");
			    fileOutput.print(lonRd + " ");
			    fileOutput.print(altiM + " ");
			    fileOutput.print(vX + " ");
			    fileOutput.print(vY + " ");
			    fileOutput.print(vZ + "\n");
			}
		    cpt++;
		}
		fileInput.close();
		fileOutput.close();
	    } catch(IOException ioe) {
	    	System.err.println("Erreur fichier");
	    }
	}

    public void simRadCastor(String nomGen)
	{
	    String nomFichierSimRad=nomGen+".SIMRAD_TRAJ";
	    String nomFichierCastor=nomGen+".CASTOR";
	    try {

		FileReader file = new FileReader(nomFichierSimRad);
		BufferedReader fileInput = new BufferedReader(file);
		String ligne;
		int alti;
		double x,y,z,vX,vY,vZ;

		FileWriter file2 = new FileWriter(nomFichierCastor);
		PrintWriter fileOutput = new PrintWriter(file2);
		String text;
		int type=1; //plot
		long id,time;
		double latRd,lonRd;
		String[] tab;
		String[] tabLigne;

		LambertAzimuthal projection = new LambertAzimuthal(47*Math.PI/180.0,0);

		int num,cpt=0;
		while ((ligne = fileInput.readLine()) != null)
		{
		    if (cpt>=0)
			{
			    StringTokenizer tokenizer = new StringTokenizer(ligne);

			    id=Long.parseLong(tokenizer.nextToken());
			    time=Long.parseLong(tokenizer.nextToken());
			    x=Double.parseDouble(tokenizer.nextToken());
			    y=Double.parseDouble(tokenizer.nextToken());
			    z=Double.parseDouble(tokenizer.nextToken());
			    vX=Double.parseDouble(tokenizer.nextToken());
			    vY=Double.parseDouble(tokenizer.nextToken());
			    vZ=Double.parseDouble(tokenizer.nextToken());


			    //conversion des unites
			    double[] latLong=projection.inverse(x,y);

			    latRd=latLong[0];
			    lonRd=latLong[1];

			    //ecriture dans le nouveau fichier
			    fileOutput.print(type + " ");
			    fileOutput.print(id + " ");
			    fileOutput.print(time + " ");
			    fileOutput.print(latRd + " ");
			    fileOutput.print(lonRd + " ");
			    fileOutput.print(z + " ");
			    fileOutput.print(vX + " ");
			    fileOutput.print(vY + " ");
			    fileOutput.print(vZ + "\n");
			}
		    cpt++;
		}
		fileInput.close();
		fileOutput.close();
	    } catch(IOException ioe) {
	    	System.err.println("Erreur fichier");
	    }
	}

    public void husniCastor(String nomGen)
	{
	    String nomFichierData=nomGen;
	    double[] pos = new double[3];
	    double[] speed = new double[3];
	    String nomFichierHusni=nomGen+".txt";
	    String nomFichierCastor=nomGen+".CASTOR";
	    LambertAzimuthal projection = new LambertAzimuthal(47*Math.PI/180.0,0);

	    try {
		FileReader file = new FileReader(nomFichierHusni);
		BufferedReader fileInput = new BufferedReader(file);
		FileWriter file2 = new FileWriter(nomFichierCastor);
		PrintWriter fileOutput = new PrintWriter(file2);

		String ligne;
		long id,time;
		double x,y,z,vX,vY,vZ;
		double latRd,lonRd;
		int type=1; 
		int cpt=0;
		while ((ligne = fileInput.readLine()) != null)
		{
		    StringTokenizer tokenizer = new StringTokenizer(ligne);
		    time=Long.parseLong(tokenizer.nextToken());
		    id=Long.parseLong(tokenizer.nextToken());
		    x=Double.parseDouble(tokenizer.nextToken())*NM_TO_METER;
		    y=Double.parseDouble(tokenizer.nextToken())*NM_TO_METER;
		    z=Double.parseDouble(tokenizer.nextToken())*FL_TO_METER;
		    vX=Double.parseDouble(tokenizer.nextToken())*1852;
		    vY=Double.parseDouble(tokenizer.nextToken())*1852;
		    vZ=Double.parseDouble(tokenizer.nextToken())*FTM_TO_MS;

		    //conversion des unites
		    double[] latLong=projection.inverse(x,y);

		    latRd=latLong[0];
		    lonRd=latLong[1];

		    //ecriture dans le nouveau fichier
		    fileOutput.print(type + " ");
		    fileOutput.print(id + " ");
		    fileOutput.print(time + " ");
		    fileOutput.print(latRd + " ");
		    fileOutput.print(lonRd + " ");
		    fileOutput.print(z + " ");
		    fileOutput.print(vX + " ");
		    fileOutput.print(vY + " ");
		    fileOutput.print(vZ + "\n");
		}
		fileInput.close();
		fileOutput.close();
	    } catch(IOException ioe) {
	    	System.err.println("Erreur fichier");
	    }
	}


    public void test(String nomGen)
	{
	    String nomFichierSimRad=nomGen+".SIMRAD_TRAJ";
	    String nomFichierCastor=nomGen+".txt";
	    try {

		FileReader file = new FileReader(nomFichierSimRad);
		BufferedReader fileInput = new BufferedReader(file);
		String ligne;
		int alti;
		double x,y,z,vX,vY,vZ;
		float fx,fy,fz,fvX,fvY,fvZ;

		FileWriter file2 = new FileWriter(nomFichierCastor);
		PrintWriter fileOutput = new PrintWriter(file2);
		String text;
		int type=1; //plot
		long id,time;
		String[] tab;
		String[] tabLigne;


		int num,cpt=0;
		while ((ligne = fileInput.readLine()) != null)
		{
		    if (cpt>=0)
			{
			    StringTokenizer tokenizer = new StringTokenizer(ligne);

			    id=Long.parseLong(tokenizer.nextToken());
			    time=Long.parseLong(tokenizer.nextToken());
			    x=Double.parseDouble(tokenizer.nextToken());
			    y=Double.parseDouble(tokenizer.nextToken());
			    z=Double.parseDouble(tokenizer.nextToken());
			    vX=Double.parseDouble(tokenizer.nextToken());
			    vY=Double.parseDouble(tokenizer.nextToken());
			    vZ=Double.parseDouble(tokenizer.nextToken());

			    x=x/1852;
			    y=y/1852;
			    vX=vX/1852;
			    vY=vY/1852;

			    fx=(float)x;
			    fy=(float)y;
			    fz=(float)z;
			    fvX=(float)vX;
			    fvY=(float)vY;
			    fvZ=(float)vZ;



			    //ecriture dans le nouveau fichier
			    fileOutput.print(time + " ");
			    fileOutput.print(id + " ");
			    fileOutput.print(fx + " ");
			    fileOutput.print(fy + " ");
			    fileOutput.print(fz + " ");
			    fileOutput.print(fvX + " ");
			    fileOutput.print(fvY + " ");
			    fileOutput.print(fvZ + "\n");
			}
		    cpt++;
		}
		fileInput.close();
		fileOutput.close();
	    } catch(IOException ioe) {
	    	System.err.println("Erreur fichier");
	    }
	}


    public static void main(String[] args) {

	Formatage myFormatage= new Formatage();
	myFormatage.test("TEST");
    }


}


