cd lyapunov_home
g++ -fPIC -I /usr/java/jdk1.6.0_11/include/ -I /usr/java/jdk1.6.0_11/include/linux/ -I /home/daniel/JAVA/ATC_GLOBAL/lyapunov_home/GMRES -shared -c lyapunov_Lms.c
g++ -fPIC -I /home/daniel/JAVA/ATC_GLOBAL/lyapunov_home/ODE_SOLVER -shared -c Lyapunov.c
export LD_LIBRARY_PATH=`pwd`:/opt/intel/mkl/10.0.1.014/lib/32
g++ -shared -o liblyapunov_Lms.so -Bsymbolic lyapunov_Lms.o Lyapunov.o  /home/daniel/JAVA/ATC_GLOBAL/lyapunov_home/ODE_SOLVER/libode.a /home/daniel/JAVA/ATC_GLOBAL/lyapunov_home/GMRES/libgmres.a -L /opt/intel/mkl/10.0.1.014/lib/32 -lguide -lmkl -lmkl_lapack -lm -lstdc++
cd ..
time java  -Xmx1012M -cp .:je.jar Control