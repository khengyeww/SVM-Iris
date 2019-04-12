
public class Perceptron {

	private double[] weights = {0.0, 0.1, 0.1}; // 重み
	private double[][] trainData; 	// データ配列
	private double learningRate; 	// 学習率 (p)
	private int counter;			// 学習の上限回数

	Perceptron(double learningRate, int counter, double[][] trainData) {
		this.learningRate = learningRate;
		this.counter = counter;
		this.trainData = trainData;
	}

	/* データの学習 */
	public void train() {
		System.out.println(".....Training Data.....");
		for (int i = 0; i < counter; i++) {
			int weightError = 0;

			// 行ごとにデータ学習
			for (double[] row : trainData) {
				double label = row[row.length - 1];
				double predictedLabel = checkIfAboveLine(row);

				// 誤識別が生じた場合に重みベクトルの修正
				if (predictedLabel > label) {
					weightError++;
					decreaseWeightVector(row);
				}
				else if (predictedLabel < label) {
					weightError++;
					increaseWeightVector(row);
				}
			}

			// 誤識別が起こらない場合、学習終了
			if(weightError == 0) {
				System.out.println("\nSuccess!\nIt's linearly separable!");
				break;
			}
			// 設定された回数内に収束できないので、終了する
			if(i == counter - 1) {
				System.out.println("\nFailed to learn within limited counter...");
				System.out.println("It's non-linearly separable...");
			}
		}
	}

	/* 重みベクトルの減少 */
	private void decreaseWeightVector(double[] dataPoint) {
		weights[0] -= 1 * learningRate;
		for (int i = 0; i < dataPoint.length - 1; i++) {
			weights[i+1] -= dataPoint[i] * learningRate;
		}
	}

	/* 重みベクトルの増加 */
	private void increaseWeightVector(double[] dataPoint) {
		weights[0] += 1 * learningRate;
		for (int i = 0; i < dataPoint.length - 1; i++) {
			weights[i+1] += dataPoint[i] * learningRate;
		}
	}

	/* g(x) > 0 or g(x) < 0 の判断  */
	private double checkIfAboveLine(double[] dataPoint) {
		double result = 0;
		result += 1 * weights[0];
		for (int i = 0; i < dataPoint.length - 1; i++){
			result += dataPoint[i] * weights[i+1];
		}
		if (result > 0.0) {
			return 1.0;
		}
		else {
			return -1.0;
		}
	}

	/* 最終重みベクトルの表示 */
	public void getWeights() {
		System.out.println("\nFinal weights:");
		for(int i = 0; i < weights.length; i++)
			System.out.println("Weight " + i + ": " + weights[i]);
		System.out.println("========================================");
	}

	public void learn(){
		train();
		getWeights();
	}

}
