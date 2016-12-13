import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class BayesNet {


	private int numStates=0;
	private State[] data;
	private String[] name;
	private int numIter;




	public BayesNet(BufferedReader br,BufferedReader pr,BufferedReader user,int size,int iter) throws NumberFormatException, IOException{
		numIter=iter;

		data=new State[size];
		String read=br.readLine();
		while(read!=null){
			data[numStates]=new State(numStates,read,pr.readLine());
			numStates++;
			read=br.readLine();
		}
		name=new String[numStates];
		for(int i=0; i<numStates;i++){
			System.out.print(i+"th state is probability of: ");
			String inp=user.readLine();
			name[i]=inp;
		}
	}
	
	public BayesNet(BufferedReader br,BufferedReader pr,int size,int iter) throws NumberFormatException, IOException{
		numIter=iter;

		data=new State[size];
		String read=br.readLine();
		while(read!=null){
			data[numStates]=new State(numStates,read,pr.readLine());
			numStates++;
			read=br.readLine();
		}
		
	}
	
	public int getNumStates(){
		return numStates;
	}



	public boolean[] runNet(){
		boolean[] result=new boolean[numStates];
		for(int state=0; state<numStates;state++){
			int[] parents=data[state].getParents();
			int tableInd=0;
			for(int i=0; i<parents.length;i++){
				tableInd*=2;
				if(data[parents[i]].getRunVal()){
					tableInd++;
				}
			}
			double[] table=data[state].getProbTable();
			double prob=table[tableInd];
			Random rand=new Random();
			if(prob>rand.nextDouble()){
				data[state].setRunVal(true);
				result[state]=true;
			}else{
				data[state].setRunVal(false);
				result[state]=false;
			}
		}
		return result;
	}

	public void printRunResults(){
		System.out.println();
		for(int state=0; state<numStates;state++){
			System.out.print(data[state].getRunVal()+" ");
		}	
	}

	public void runConsec(){
		double[] tests=new double[numStates];
		int cnt=0;
		while(cnt<numIter){
			boolean[] t=runNet();
			for(int i=0; i<t.length;i++){
				if(t[i]){
					tests[i]++;
				}
			}
			cnt++;
		}
		if(name==null){
			name=new String[numStates];
			for(int i=0; i<numStates;i++){
				name[i]=i+"";
			}
		}
		for(int i=0; i<numStates;i++){
			System.out.println("Probability of "+name[i] +" is:"+tests[i]/numIter);
		}
	}
}
