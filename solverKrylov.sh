/usr/java/jdk1.7.0_11/bin/javac SOLVERS/SolverKrylov.java
/usr/java/jdk1.7.0_11/bin/javah SOLVERS.SolverKrylov
cd SOLVERS
g++ -fPIC -I /usr/java/jdk1.7.0_11/include/ -I /usr/java/jdk1.7.0_11/include/linux/ -I /home/delahaye/SOFT/JAVA/NEW_ATC_GLOBAL/SOLVER/GMRES -shared -c SOLVERS_SolverKrylov.c
export LD_LIBRARY_PATH=`pwd`:/opt/intel/mkl/10.0.011/lib/em64t
g++ -shared -o libSOLVERS_SolverKrylov.so -Bsymbolic SOLVERS_SolverKrylov.o /home/delahaye/SOFT/JAVA/NEW_ATC_GLOBAL/SOLVERS/GMRES/libgmres.a -L /opt/intel/mkl/10.0.011/lib/em64t -lguide -lmkl -lmkl_lapack -lm -lstdc++
cd ..
/usr/java/jdk1.7.0_11/bin/java -cp . SOLVERS/SolverKrylov
