import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInputData {

	private String fileName = "";
	private static BufferedReader reader = null;
	private String linearOrNonlinear = "";
	private int patternNum = 0;
	private static final int DATANUM = 3;
	private double[][] linearData;
	private double[][] nonLinearData;

	public ReadInputData(String filename, String decide) {
		this.fileName = filename;
		this.linearOrNonlinear = decide;

		if(linearOrNonlinear.equals("linear"))
			patternNum = 8;
		else if(linearOrNonlinear.equals("nonlinear"))
			patternNum = 4;

		linearData = new double[patternNum][DATANUM];
		nonLinearData = new double[patternNum][DATANUM];
	}

	/* データの読み込み */
	public void run() throws IOException {
		reader = new BufferedReader(new FileReader(fileName));

		String read;
		int i = 0;
		double input = 0.0;
		while ((read = reader.readLine()) != null && read.length() != 0) {
			String[] splited = read.split(",");
			for (String part : splited) {

				if(part.equals("class-1"))
					part = "1";
				else if(part.equals("class-2"))
					part = "-1";
				else
					;

				input = Double.parseDouble(part);
				loadToTable(input, i);
				i++;

				// System.out.println(input);
			}
		}

		reader.close();

		/*
		 * 確認のために、クラスごとの表示
		 *
		for(int a = 0; a<patternNum; a++){
			for(int b = 0; b<DATANUM; b++){
				System.out.print(linearData[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println("End of Linear Data");
		for(int a = 0; a<patternNum; a++){
			for(int b = 0; b<DATANUM; b++){
				System.out.print(nonLinearData[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println("End of Nonlinear Data");
		//*/
	}

	/* 読み込んだデータを配列に入れる */
	public void loadToTable(double inputData, int g) {

		int row = (g / DATANUM) % patternNum;
		int column = g % DATANUM;

		if(linearOrNonlinear.equals("linear"))
			linearData[row][column] = inputData;
		else if(linearOrNonlinear.equals("nonlinear"))
			nonLinearData[row][column] = inputData;
	}

	public double[][] getDataTable(){
		double[][] newTable = {};

		if(linearOrNonlinear.equals("linear"))
			newTable = linearData;
		else if(linearOrNonlinear.equals("nonlinear"))
			newTable = nonLinearData;

		return newTable;
	}

}