/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

/*
Intelligent Systems - Problem Solving project
Authors:  Gabriele Sartor
          Morgan Gautherot 
*/

import java.util.Random;

/*
This abstract class aims to contain the most important parameters and methods useful for dealing with genetic algorithm.
*/


public abstract class Problem               
{

    protected int GL=1;               	// Gene lenth in binary
    protected int GN=1;               	// Gene number in one string
    protected int CL=GN*GL;           	// Chromosome length
    protected long fitness_counter;   	// Number of evaluations
    protected double target_fitness;  	// Target fitness value -MAXIMUM-
    protected boolean tf_known;       	
    protected static Random r = new Random();	// Random uniform variable

    //CONSTRUCTOR
    public Problem() {
        CL              = GN*GL;
        fitness_counter = 0;
        tf_known        = false;
        target_fitness  = -999999.9;
    }

    //SET and GET functions
    public int     get_geneL()           { return GL; }
    public int     get_geneN()           { return GN; }
    public void    set_geneL(int gl)     { GL = gl; CL=GN*GL; }
    public void    set_geneN(int gn)     { GN = gn; CL=GN*GL; }
    public long    get_fitness_counter() { return fitness_counter; }
    public double  get_target_fitness()  { return target_fitness;  }
    public boolean tf_known()            { return tf_known;        }

    public void    set_target_fitness(double tf) {
        target_fitness = tf;
        tf_known       = true;
    }


    /*
    Increment steps of the algorithm computed and return the fitness of the individual Indiv
    */
    public double evaluateStep(Individual Indiv) {
            fitness_counter++ ;
            return Evaluate(Indiv) ;
    }

    /*Calculates the fitness of the individual Indiv (to implement in the class which extends this class)*/
    public abstract double Evaluate(Individual Indiv) ;
}
// END OF CLASS: Problem
