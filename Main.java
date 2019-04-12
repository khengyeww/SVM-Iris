import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// データを読み込む
		ReadInputData linearData = new ReadInputData("lineardata.txt", "linear");
		linearData.run();
		// 学習させたいデータの指定
		double[][] trainingData1 = linearData.getDataTable();

		ReadInputData nonlinearData = new ReadInputData("nonlineardata.txt", "nonlinear");
		nonlinearData.run();
		double[][] trainingData2 = nonlinearData.getDataTable();

		Perceptron perceptron = new Perceptron(0.5, 10000, trainingData1);

		SupVM linearSVM = new SupVM(trainingData1, "linear");
		SupVM nonlinearSVM = new SupVM(trainingData2, "nonlinear");

		System.out.println("\n========================================");
		System.out.println("Perceptron:\n");
		perceptron.learn();

		System.out.println("\n========================================");
		System.out.println("Linear SVM:\n");
		linearSVM.learn();

		System.out.println("\n========================================");
		System.out.println("Nonlinear SVM:\n");
		nonlinearSVM.learn();
	}

}
