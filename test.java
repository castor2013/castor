
package lyapunov;

public class test 
{
    static final double sigma=30;
    static final double theta=1;
    static final double mu=2;
    static final double sigma2=sigma*sigma;
    static final double nf = 2.0 / Math.sqrt(Math.PI);
    static Lms myLms = new Lms();

    public  double myErf(double x){
	double res;
	res=myLms.erfJ(x);
	return res;
    }

    public  double phi(double r,double t)
    {
	double res,x;
	double sr,st;
	double d;

	// scaled norm
	sr = r / sigma;
	// scaled time
	st = Math.abs(t) * theta ; 
	// temporary value
	d = 1.0/Math.sqrt(2.0 + st);
	x= sr*d;
	if (x>1e-6)
	    {
		res = myErf(x) / sr;
	    }
	else
	    {
		res= nf * d;
	    }
	/*******************************************/
	return res;
    }


    public static void main(String args) {
	test mytest = new test();
 
	double r,res;
	for (int i=0;i<100;i++)
	    {
		r=0.01+0.1*i;
		res=mytest.phi(r,1);
		System.out.println("r= " + r + " phi= " + res);
	    }


    }



}