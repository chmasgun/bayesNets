import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BayesNetsMain {


	private final static int numIter=100000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (
			BufferedReader br=new BufferedReader(new FileReader("net1.txt"));
			BufferedReader pr=new BufferedReader(new FileReader("prob1.txt"));
			BufferedReader user=new BufferedReader(new InputStreamReader(System.in));){

			int size=Integer.parseInt(br.readLine());
			BayesNet mynet=new BayesNet(br,pr,size,numIter);
			mynet.runConsec();
//			double[] trueData=new double[size];
//			int cnt=0;
//			while(cnt<numIter){
//				boolean[] res=mynet.runNet();
//				for(int i=0; i<res.length;i++){
//					if(res[i]){
//						trueData[i]++;
//					}
//				}
//				//mynet.printRunResults();
//				cnt++;
//			}
//			System.out.println();
//
//			for(int i=0; i<size;i++){
//				System.out.println(trueData[i]/numIter);
//			}






		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
