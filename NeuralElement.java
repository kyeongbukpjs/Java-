
public class NeuralElement {
			

	public static double[][] InitializeSynapse(){
		double[][] Synapse = new double[64][10];
		
		for (int i = 0; i < 64; i++) {
			for(int k = 0; k <10; k++) {
				Synapse[i][k] = Math.random()*2-1;
			}
		
		}
		return Synapse;
	}
	
	
	
	public static double[][] matmul(double[][] A, double[][] B) {

        int A_row = A.length;
        int A_columns = A[0].length;
        int B_rows = B.length;
        int B_columns = B[0].length;

        if (A_columns != B_rows) {
            throw new IllegalArgumentException("A:Rows: " + A_columns + " did not match B:Columns " + B_rows + ".");
        }

        double[][] C = new double[A_row][B_columns];
        for (int i = 0; i < A_row; i++) {
            for (int j = 0; j < B_columns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < A_row; i++) { 
            for (int j = 0; j < B_columns; j++) { 
                for (int k = 0; k < A_columns; k++) { 
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
	
	public static double[][] UpdateSynapse(double[][] Synapse, double[][] net, double[][] x, int[][] y, double learningRate){
		double[][] UpdateSynapse = new double[64][10];
		double[][] yMinusNet = new double[y.length][10];
		double[][] xTransposed = new double[64][x.length];
		for(int i = 0;i < 64; i++)
	    {
	        for(int j = 0; j < x.length;j++)
	        {
	            xTransposed[i][j] = x[j][i];
	        }
	    }
		
		for(int i = 0;i < y.length; i++)
	    {
	        for(int j = 0; j < 10;j++)
	        {
	            yMinusNet[i][j] = y[i][j]-net[i][j];
	        }
	    }
		
		
		UpdateSynapse = matmul(xTransposed,yMinusNet);
		
		for(int i = 0;i < 64; i++)
	    {
	        for(int j = 0; j < 10;j++)
	        {
	            UpdateSynapse[i][j] = Synapse[i][j]+UpdateSynapse[i][j]*learningRate;
	        }
	    }
		
		
		return UpdateSynapse;
	}
	public static int argmax (double [] elems)
    {
      int bestIdx = -1;
      double max = -1000000000;
      for (int i = 0; i < elems.length; i++) {
        double elem = elems[i];
        if (elem > max) {
          max = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
	
	public static int argmax (int [] elems)
    {
      int bestIdx = -1;
      int max = 0;
      for (int i = 0; i < elems.length; i++) {
        int elem = elems[i];
        if (elem > max) {
          max = elem;
          bestIdx = i;
        }
      }
      return bestIdx;
    }
}
