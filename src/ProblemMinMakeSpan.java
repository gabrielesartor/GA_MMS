/*
Intelligent Systems - Problem Solving project
Authors:  Gabriele Sartor
          Morgan Gautherot 
*/

/*
This class is used to initialize the Minimum MakeSpan problem parameters and implement the calculation of the fitness for this particular problem.
*/

public class ProblemMinMakeSpan extends Problem{
    float [][]jobsMatrix;
    
    //CONSTRUCTOR
    public ProblemMinMakeSpan() {
      throw new IllegalArgumentException("Problem in the initialization of the problem. \nDid you put the name of the input file?");
    }
    
    //CONSTRUCTOR
    //Use input_file which is the file containing data of the problem
    public ProblemMinMakeSpan(String input_file) {
      try{
        jobsMatrix = ReadTasks.matrixFromFile(input_file);
      }catch(Exception e){ System.out.println("Error IO"); }
    }

    /*
    Return the fitness of "Indiv" calculated for this particular problem
    */
    public double Evaluate(Individual Indiv) {
      return MinMakeSpan(Indiv) ;
    }


  
  /*
  Return the fitness of "Indiv" for MMS problem
  */
  private double MinMakeSpan(Individual indiv)
  {
    double f[] = new double[16];
    double max = 0.0;
     
    for(int i=0; i<CL; i++) {
      f[(indiv.get_chromosome()).get_allele(i)]  += jobsMatrix[i][(indiv.get_chromosome()).get_allele(i)];
    }
    
    for(int i=0; i<16; i++) {
      if(max<f[i])
        max = f[i];
    }

    return -max;
  }

}
