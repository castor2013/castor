/usr/java/jdk1.7.0_11/bin/javac SOLVERS/SolverQR.java
/usr/java/jdk1.7.0_11/bin/javah SOLVERS.SolverQR
cd SOLVERS
gcc -fPIC -I /usr/java/jdk1.7.0_11/include/ -I /usr/java/jdk1.7.0_11/include/linux/ -c SOLVERS_SolverQR.c
export LD_LIBRARY_PATH=`pwd`:/opt/intel/mkl/10.0.011/lib/em64t/
ld -shared -o libSOLVERS_SolverQR.so -Bsymbolic SOLVERS_SolverQR.o  -L /opt/intel/mkl/10.0.011/lib/em64t -lguide -lmkl -lmkl_lapack -lm 
cd ..
time /usr/java/jdk1.7.0_11/bin/java -cp . SOLVERS/SolverQR
