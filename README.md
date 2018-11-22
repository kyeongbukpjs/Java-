# java로만 인공신경망 구현해보기



<b>only java neural network</b>는 java를 이용해 구현한 인공신경망을 이용해 학습하는 숫자 이미지 classifier입니다.


**Table of Contents**

- [Basic structure](#basic-structure)
- [Basic Class](#basic-class)
	- [NeuralElement](#NeuralElement)
	- [Preprocess](#Preprocess)
	- [deeplearning](#deeplearning)
- [usage/customize](#customize)


### Basic structure

```java
for(int i = 0; i< 10000 ; i++) {
	double[][] output = neural.matmul(x,Synapse);
	
	Synapse = neural.UpdateSynapse(Synapse, output, x, y, 0.00003);
}
```

핵심이 되는 구조는 deeplearning.java의 클래스입니다.

deeplearning.java의 main함수는 위 코드와 같이, 정해진 학습 횟수(위의 경우 10000번)동안 뉴럴네트워크의 아웃풋을 계산한 뒤 이를 바탕으로 Synapse(Weight)를 업데이트를 반복해주는 형태입니다.

```java
if(i%20==0) {
	double[][] TestOutput = neural.matmul(x,Synapse);
	
	int correct = 0;
	for(int k = 0; k<x.length;k++) {
		if(neural.argmax(y[k])==neural.argmax(TestOutput[k]))
		correct += 1;

	}
	double accuracy = correct/((double)(x.length)) ;
	System.out.printf("학습횟수: %-8d 정확도: %f\n",i,accuracy);
```

또한 진행중인 학습결과를 확인할 수 있도록, 일정 횟수(위의 경우 20번)의 학습이 진행된 후에, 정확도를 출력합니다.


### Basic Class

<b>only java neural network</b>의 main 메서드가 위치하는 class는 deeplearning.java이고, 추가적으로 사용되는 객체는
Preprocess.class와 NeuralElement.class입니다. 

#### NeuralElement

다음은 NeuralElement 클래스 내부의 메서드들입니다.

- public static double[][] InitializeSynapse()
- public static double[][] matmul(double[][] A, double[][] B)
- public static double[][] UpdateSynapse(double[][] Synapse, double[][] net, double[][] x, int[][] y, double learningRate)
- public static int argmax (double [] elems)

위 메서드들은 각각 가중치 최초 초기화, 행렬곱 연산, GradientDescent 알고리즘을 적용한 가중치 업데이트, argmax 값 계산을 하는데에 사용됩니다. 
| 메서드명 | 매개변수 | 리턴 값 | 설명 |
| ---- | ---- | -------- | ----------- |
| InitializeSynapse | - | 이차원 행렬 Synapse | 학습 이터레이션을 돌기 전, 가중치 값들이 기록된 행렬을 초기화시켜주는 메서드 입니다. |
| matmul | 이차원 행렬 A,B | 이차원 행렬 C (A 행렬곱 B의 결과) | 행렬곱 연산의 결과를 리턴해주는 메서드 입니다. |
|  UpdateSynapse | 이차원 행렬 Synapse, net, x, y, 상수 learning rate | 이차원 행렬 Synapse (updated) | Gradient Descent 알고리즘에 의해서 결정된 가중치로 업데이트해줍니다.  |
|argmax|int[] or double[]|int|가장 값이 큰 요소의 인덱스를 리턴해줍니다.

#### Preprocess

다음은 파일을 읽어올 때 사용되는 Preprocess 클래스 내부의 메서드들입니다.

- public static int[][] readData()
- public static int[][] getY(int[][] data)
- public static double[][] getX(int[][] data)

위 메서드들은 각각 텍스트형식의 데이터에서 값을 읽어오는 것, label 데이터를 전처리해주는 것, feature를 전처리해주는 것 입니다.
| 메서드명 | 매개변수 | 리턴 값 | 설명 |
| ---- | ---- | -------- | ----------- |
|readData | - | 텍스트 파일에서 읽어온 숫자들로 구성된 이차원 행렬 | data.txt에서 읽어들인 텍스트 파일을 이차원 행렬의 형태로 리턴합니다. |
| getY |readData의 아웃풋 |이차원 행렬 y| 읽어들인 데이터로부터 라벨 값의 행렬을 만듭니다.|
|   getX | readData의 아웃풋|이차원 행렬 x |  읽어들인 데이터로 부터 feature 값의 행렬을 만듭니다.|


#### deaplearning

main 메서드가 포함된 클래스로, NeuralElement 클래스와 Preprocess 클래스의 객체를 생성하고, 이들을 이용하여 아웃풋을 내고 실질적으로 학습이 진행되는 클래스입니다.

## customize

커스터마이징이 가능한 파라미터들은 아래와 같습니다.

- learning rate - deeplearning.java
- train iteration - deeplearning.java
- data scale rate - Preprocess.java