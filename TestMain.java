public class TestMain{
    public static void main(String args[])
    {
	Formatage format = new Formatage();
	String[] tab;
	String buffer=args[0];
	tab=buffer.split("[.]");

	if (tab[1].equals("OPAS")) format.opasCastor(tab[0]);
	if (tab[1].equals("csv")) format.csvCastor(tab[0]);
	if (tab[1].equals("SIMRAD_TRAJ")) format.simRadCastor(tab[0]);

    }//fin methode main
}
