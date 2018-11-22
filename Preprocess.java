import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Preprocess {
	//파일을 읽어올 때 쓰는 클래스
	
	public static int[][] readData() { //데이터를 읽어와서 2차원 array의 input을 만들어주는 역할을 하는 함수.
		int input[][] = new int[5620][65];
		
		try {
			File file = new File("./data.txt");
			FileReader filereader = new FileReader(file);
		    BufferedReader bufReader = new BufferedReader(filereader);
		    String line = "";
		    
		    
		    int k = 0;
		    while((line = bufReader.readLine())!=null) {
		    	String[] array;
		    	array = line.split(",");
		    	int Line[] = new int[array.length];
		    	int i = 0;
		    	for(String num :array) {
		    		Line[i] = Integer.parseInt(num);
		    		i+=1;
		    	}input[k] = Line;
		    	k +=1;
		    }
		    bufReader.close();
		}catch (FileNotFoundException e) {
		}catch(IOException e) {
			System.out.println(e);
		}
		return input;
	}
	
	
	public static int[][] getY(int[][] data){
		int[][] y = new int[data.length][10];
		
		for (int i = 0; i < data.length; i++) {
			for(int j=0; j <10;j++) {
				if(j ==data[i][64])
				y[i][j] = 1;
				else y[i][j] =0;
			}     
		}
		return y;
	}
	
	
	public static double[][] getX(int[][] data){
		double[][] x = new double[data.length][64];
		
		for (int i = 0; i < data.length; i++) {
			for(int k = 0; k <64; k++) {
				x[i][k] = data[i][k]/16.0;
			}
		
			}
		return x;
	}
	
}

