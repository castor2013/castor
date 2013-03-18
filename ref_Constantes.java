package Traffic;

public class Constantes{


    // constante de conversion
    public static double NM_TO_METER = 1852.0;
    public static double KT_TO_MS = 1852.0 / 3600.0;
    public static double FEET_TO_METER = 0.3048006096012;
    public static double FL_TO_METER = 30.48006096012;
    public static double FTM_TO_MS = 0.3048006096012/ 60.0;

    public static double NORM_H = 5;//norme horizontale en nautique
    public static double NORM_V = 10;//norme verticale en FL
    public static double METER_TO_NORMH = 1/(NORM_H*NM_TO_METER);

    public static double METER_TO_NORMV = 1/(NORM_V*FL_TO_METER);

    public static double MPS_TO_NORMHPM = 60/(NORM_H*NM_TO_METER);
    public static double MPS_TO_NORMVPM = 60/(NORM_V*FL_TO_METER);
    public static double FL_TO_NORM = 1/(NORM_V*FL_TO_METER);
    public static double FTM_TO_NORMPM = 1/(NORM_V);


    public static int dimension=3;


    public static int horizonTrajectographiqueInf = 60;  //en secondes
    public static int horizonTrajectographiqueSup = 60;  //en secondes


    public static int horizonGeometriqueInf =5;
    public static int horizonGeometriqueSup =5;


    public static double radius = 2; //(attenuation proximity, convergence)en norme de separation 
    public static double alpha = 0.1; 

    public static double voisinageKoenig=10; // en norme de separation 

    //parametetres cluster*/
    public static int nbMaxSegments   =  connections.CastorConnect.MAX_SEGMENTS_CLUSTER_PACKET;
    public static double seuilClusterTempo = 10.0;  /* horizon temporel de prise en compte des conflits en minutes*/
    public static double seuilClusterDist = 1.0;  /* distance minimum indusant un conflit en norm de separation*/

}
