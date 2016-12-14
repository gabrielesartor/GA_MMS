/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

import java.util.Random;

public class Algorithm
{
  /* Here is our number of task */
  private  int          chrom_length; // Alleles per chromosome
  /* for us this one is maybe the same than the previous */
  private  int          gene_number;  // Number of genes in every chromosome
  /* I do not see what it is for */
  private  int          gene_length;  // Number of bits per gene

  /* Number of machines */
  private  int          machinesNum;

  private  int          popsize;      // Number of individuals in the population

  /* pc is between  0.7 and 0.9 we have to laucnh 50 time each and make test to see the best result */
  /* for pm is the same than pc jsut above */
  private  double pc, pm;      // Probability of applying crossover and mutation

  private  Problem       problem;     // The problem being solved
  private  Population    pop;         // The population

  /**/
  private  static Random r;           // Source for random values in this class

  /* don't see what is it */
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
    this.pop = new Population(popsize,chrom_length,m);// Create initial population
    this.r             = new Random();
    this.aux_indiv     = new Individual(chrom_length,m);

    for(int i=0;i<popsize;i++)
    pop.set_fitness(i,problem.evaluateStep(pop.get_ith(i)));
    pop.compute_stats();
  }

  // BINARY TOURNAMENT
  /* with method choose the individual with the greatest fitness */
  public Individual select_tournament() throws Exception
  {
    int p1, p2;

    p1 = (int)(r.nextDouble()*
               (double)popsize + 0.5); // Round and then trunc to int

    /* to be sure we will not have ungound value */
    if(p1>popsize-1) p1=popsize-1;

    do
    {  p2 = (int)(r.nextDouble()*
                  (double)popsize + 0.5);  // Round and then trunc to int
      /* to be sure we will not have ungound value */
      if(p2>popsize-1) p2=popsize-1;
    }

    while (p1==p2);
    /* We return the one with the greatest fitness*/
    if (pop.get_ith(p1).get_fitness()>pop.get_ith(p2).get_fitness())
    return pop.get_ith(p1);
    else
    return pop.get_ith(p2);
  }

  /* maybe we can make an update and keep the one with more fitness */
  // SINGLE POINT CROSSOVER - ONLY ONE CHILD IS CREATED (RANDOMLY DISCARD
  // DE OTHER)
  /* I don't find the signification of SPX  */
  public Individual SPX (Individual p1, Individual p2)
  {
    int       rand;

    rand = (int)(r.nextDouble()*
                 (double)chrom_length-1+0.5); // From 0 to L-1 rounded

    /* to be sure we will not have ungound value */
    if(rand>chrom_length-1) rand=chrom_length-1;

    /* improvement we can return the one with more fitnes but it is maybe part of the algorithm */
    if(r.nextDouble()>pc)  // If no crossover then randomly returns one parent
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
  public Individual mutate(Individual p1)
  {
    int alelle=0;
    Random r = new Random();

    aux_indiv.assign(p1);

    for(int i=0; i<chrom_length; i++)
    if (r.nextDouble()<=pm)  // Check mutation bit by bit...
    {
      aux_indiv.set_allele(i,r.nextInt(machinesNum));
    }

    return aux_indiv;

  }

  // REPLACEMENT - THE WORST INDIVIDUAL IS ALWAYS DISCARDED
  public void replace(Individual new_indiv) throws Exception
  {
    pop.set_ith(pop.get_worstp(),new_indiv);
    //pop.compute_stats();                  // Recompute avg, best, worst, etc.
  }

  // EVALUATE THE FITNESS OF AN INDIVIDUAL
  private double evaluateStep(Individual indiv)
  {
    return problem.evaluateStep(indiv);
  }

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
