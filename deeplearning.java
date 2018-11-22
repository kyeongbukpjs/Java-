


public class deeplearning {


	
	public static void main(String[] args) {
		Preprocess preprocessor = new Preprocess();
		NeuralElement neural = new NeuralElement();
		
		int[][] data =preprocessor.readData();
		double[][] x = preprocessor.getX(data);
		int[][] y = preprocessor.getY(data);
		double[][] Synapse = neural.InitializeSynapse();
		
		

		for(int i = 0; i< 10000 ; i++) {
			double[][] output = neural.matmul(x,Synapse);
			
			Synapse = neural.UpdateSynapse(Synapse, output, x, y, 0.00003);
			
			if(i%20==0) {
				double[][] TestOutput = neural.matmul(x,Synapse);
				
				int correct = 0;
				for(int k = 0; k<x.length;k++) {
					if(neural.argmax(y[k])==neural.argmax(TestOutput[k]))
					correct += 1;
		
				}
				double accuracy = correct/((double)(x.length)) ;
				System.out.printf("학습횟수 : %-8d 정확도: %f\n",i,accuracy);
				
				
			}
			
			
			
		}
		
		
	}
	
}
