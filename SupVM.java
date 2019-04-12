
public class SupVM {

	private double[] weights = {0.0, 0.0}; // 重み
	private double[][] trainData; 	// データ配列
	private String linearOrNonlinear = "";
	private int patternNum = 0;

	private double[] alpha;
	private double[] deltaAlpha;
	private double[][] D;
	private static final double GAMMA = 0.01;

	SupVM(double[][] trainData, String decide) {
		this.trainData = trainData;
		this.linearOrNonlinear = decide;

		if(linearOrNonlinear.equals("linear"))
			this.patternNum = 8;
		else if(linearOrNonlinear.equals("nonlinear"))
			this.patternNum = 4;

		alpha = new double[patternNum];
		deltaAlpha = new double[patternNum];
		D = new double[patternNum][patternNum];
	}

	public void init(){
		for(int i = 0; i < patternNum;i++){
			alpha[i] = 0;
		}

		for(int i = 0; i < patternNum; i++){
			for(int j = 0; j < patternNum; j++){
				if(linearOrNonlinear.equals("linear"))
					D[i][j] = trainData[i][2] * trainData[j][2] * ((trainData[i][0] * trainData[j][0]) + (trainData[i][1] * trainData[j][1]));
				else if(linearOrNonlinear.equals("nonlinear")) {
					double kernel = (trainData[i][0] * trainData[j][0]) + (trainData[i][1] * trainData[j][1]) + 1;
					D[i][j] = trainData[i][2] * trainData[j][2] * kernel * kernel;
				}
			}
		}
	}

	public void train(){

		init();

		for(int z = 0; z < patternNum; z++) {
			do{
				for(int i = 0; i < patternNum; i++){
					double[] E = new double[patternNum];

					for(int j = 0; j < patternNum; j++){
						E[i] += alpha[j] * D[i][j];
					}

					double max1 = GAMMA * (1 - E[i]);
					double max2 = -1 * alpha[i];
					if(max1 >= max2){
						deltaAlpha[i] = max1;
					}
					else {
						deltaAlpha[i] = max2;
					}

					alpha[i] += deltaAlpha[i];
				}

			}while(deltaAlpha[z] > 0.000000000000001 || deltaAlpha[z] < -0.000000000000001);
		}
	}

	public void weightCalc(){
		for(int i = 0; i < patternNum; i++){
			weights[0] += alpha[i] * trainData[i][2] * trainData[i][0];
			weights[1] += alpha[i] * trainData[i][2] * trainData[i][1];
		}
	}

	public void learn(){
		train();

		for(int i = 0; i < patternNum; i++){
			System.out.println("Alpha " + (i + 1) + ": " + alpha[i]);
		}

		if(linearOrNonlinear.equals("linear")) {
			weightCalc();
			System.out.println("Weight 1: " + weights[0] + "\nWeight 2: " + weights[1]);
		}

		System.out.println("========================================");
	}

}
