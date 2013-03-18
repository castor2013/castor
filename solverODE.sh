/usr/java/jdk1.7.0_11/bin/javac SOLVERS/SolverODE.java
/usr/java/jdk1.7.0_11/bin/javah SOLVERS.SolverODE
cd SOLVERS
g++ -fPIC -I  /home/delahaye/SOFT/JAVA/NEW_ATC_GLOBAL/SOLVERS/ODE_SOLVER -shared -c LyapunovODE.c
g++ -fPIC -I /usr/java/jdk1.7.0_11/include/ -I /usr/java/jdk1.7.0_11/include/linux/ -shared -c SOLVERS_SolverODE.c
export LD_LIBRARY_PATH=`pwd`:/opt/intel/mkl/10.0.011/lib/em64t
g++ -shared -o libSOLVERS_SolverODE.so -Bsymbolic SOLVERS_SolverODE.o LyapunovODE.o  /home/delahaye/SOFT/JAVA/NEW_ATC_GLOBAL/SOLVERS/ODE_SOLVER/libode.a  -lm -lstdc++
cd ..
/usr/java/jdk1.7.0_11/bin/java  -Xmx1012M -cp . SOLVERS/SolverODE
