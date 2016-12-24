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
This class aims to show main algorithms to deal with genetic algorithms.
*/

public class Algorithm
{

  private  int          chrom_length; // Alleles per chromosome(in this case the number of jobs we want to assing)

  private  int          gene_number;  // Number of genes in every chromosome

  private  int          gene_length;  // Number of bits per gene

  private  int          machinesNum;  // Number of machines to perform jobs

  private  int          popsize;      // Number of individuals in the population


  private  double pc, pm;      // Probability of applying crossover and mutation

  private  Problem       problem;     // The problem being solved
  private  Population    pop;         // The population

  private  static Random r;           // Source for random values in this class

  private  Individual aux_indiv;  // Internal auxiliar individual being computed


  // CONSTRUCTOR
  public Algorithm(Problem p, int popsize, int gn, int gl, double pc, double pm, int m)
  throws Exception
  {
    this.gene_number   = gn;
    this.gene_length   = gl;
    this.chrom_length  = gn*gl;
    this.popsize       = popsize;
    this.pc            = pc;
    this.pm            = pm;
    this.problem       = p;
    this.machinesNum   = m;
    this.pop = new Population(popsize,chrom_length,m);  // Create initial population
    this.r             = new Random();                  
    this.aux_indiv     = new Individual(chrom_length,m);// Create a new individual

    for(int i=0;i<popsize;i++)
    pop.set_fitness(i,problem.evaluateStep(pop.get_ith(i)));  //Compute the fitness for each individual of the population
    pop.compute_stats();  //Print of the best and worst fitnesses of the population
  }


  // BINARY TOURNAMENT
  /*
  Selects two random individuals from the population and return the individual which has the best fitness
  */
  public Individual select_tournament() throws Exception
  {
    int p1, p2;

    p1 = (int)(r.nextDouble()*
               (double)popsize + 0.5); 

    if(p1>popsize-1) p1=popsize-1;

    do
    {  p2 = (int)(r.nextDouble()*
                  (double)popsize + 0.5);  
      if(p2>popsize-1) p2=popsize-1;
    }

    while (p1==p2);

    if (pop.get_ith(p1).get_fitness()<pop.get_ith(p2).get_fitness())
    return pop.get_ith(p1);
    else
    return pop.get_ith(p2);
  }


  // SINGLE POINT CROSSOVER 
  /*
  Takes two individuals and apply the crossover in a random point.
  The first part will be composed by the first individual and the rest by the second one
  */
  public Individual SPX (Individual p1, Individual p2)
  {
    int       rand;

    rand = (int)(r.nextDouble()*
                 (double)chrom_length-1+0.5); 

    if(rand>chrom_length-1) rand=chrom_length-1;

    if(r.nextDouble()>pc)  
    return r.nextDouble()>0.5?p1:p2;

    // Copy CHROMOSOME 1
    for (int i=0; i<rand; i++)
    {
      aux_indiv.set_allele(i,p1.get_allele(i));
    }
    // Copy CHROMOSOME 2
    for (int i=rand; i<chrom_length; i++)
    {
      aux_indiv.set_allele(i,p2.get_allele(i));
    }

    return aux_indiv;
  }


  // MUTATE A int CHROMOSOME
  /*
  For each allele we change its value with a random one with probability pm
  */
  public Individual mutate(Individual p1)
  {
    int alelle=0;
    Random r = new Random();

    aux_indiv.assign(p1);

    for(int i=0; i<chrom_length; i++)
    if (r.nextDouble()<=pm) { 
      aux_indiv.set_allele(i,r.nextInt(machinesNum));
    }

    return aux_indiv;

  }

  // REPLACEMENT - THE WORST INDIVIDUAL IS ALWAYS DISCARDED
  public void replace(Individual new_indiv) throws Exception
  {
    pop.set_ith(pop.get_worstp(),new_indiv);
  }

  // EVALUATE THE FITNESS OF AN INDIVIDUAL
  private double evaluateStep(Individual indiv)
  {
    return problem.evaluateStep(indiv);
  }

  /*
  Create a new individual exploiting crossover and mutation. 
  Then it replaces the worst individual of the population.
  */
  public void go_one_step() throws Exception
  {
    aux_indiv.assign( SPX(select_tournament(),select_tournament()) );
    aux_indiv.set_fitness(problem.evaluateStep(mutate(aux_indiv)));
    replace(aux_indiv);
  }


  public Individual get_solution() throws Exception
  {
    return pop.get_ith(pop.get_bestp());// The better individual is the solution
  }


  public int    get_worstp() { return pop.get_worstp(); }
  public int    get_bestp()  { return pop.get_bestp();  }
  public double get_worstf() { return pop.get_worstf(); }
  public double get_avgf()   { return pop.get_avgf();   }
  public double get_bestf()  { return pop.get_bestf();  }
  public double get_BESTF()  { return pop.get_BESTF();  }

  
  public Individual get_ith(int index) throws Exception
  {
    return pop.get_ith(index);
  }

  public void set_ith(int index, Individual indiv) throws Exception
  {
    pop.set_ith(index,indiv);
  }
}
// END OF CLASS: Algorithm
