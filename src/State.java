import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class State {

	private boolean isRoot=false;
	private int[] parents;
	private int[] childs=new int[3];
	private int numChilds;
	private String name;
	private int id;
	private double[] probTable;

	// After run
	private boolean runVal;
	


	public State(int id,String parents,String probs){
		this.id=id;
		name=id+"";
		numChilds=0;
		if(parents.equals("")){
			this.parents=new int[0];
			isRoot=true;
		}else{
			String store=parents;
			int arrSize=0;
			StringTokenizer token=new StringTokenizer(parents);
			
			while(token.hasMoreTokens()){
				arrSize++;
				token.nextToken(" ");
			}
			this.parents=new int[arrSize];
			arrSize=0;
			token=new StringTokenizer(store," ");
			
			while(token.hasMoreTokens()){
				this.parents[arrSize]=Integer.parseInt(token.nextToken());
				arrSize++;
			}
			

		}
		

		makeProbTable(probs);
	}

	public State(String name,int id,int[] parents,String probs){
		this.id=id;
		this.name=name;
		numChilds=0;
		this.parents=parents;
		if(parents.length==0){
			isRoot=true;
		}
		makeProbTable(probs);
	}

	public void setChild(State chld){
		childs[numChilds]=chld.getId();
		numChilds++;
		if(numChilds==childs.length){
			int[] store=new int[numChilds];
			for(int i=0;i<numChilds;i++){
				store[i]=childs[i];
			}
			childs=new int[numChilds+3];
			for(int i=0;i<numChilds;i++){
				childs[i]=store[i];
			}
		}

	}
	public int getId(){
		return id;
	}
	public int[] getParents(){
		return parents;
	}
	public double[] getProbTable(){
		return probTable;
	}
	public void setRunVal(boolean val){
		runVal=val;
	}
	public boolean getRunVal(){
		return runVal;
	}
	

	private void makeProbTable(String probs){
		try {
			int length=(int) Math.pow(2, parents.length);
			if(!isRoot){
				System.out.print(id+"th state, for parents: ");
				for(int i=0;i<parents.length;i++){
					System.out.print(parents[i]+" ");
				}
				System.out.println();
			}

			probTable=new double[length];
			
			StringTokenizer token=new StringTokenizer(probs," ");
			int cnt=0;
			while(cnt<length){
				if(length==1){
					System.out.print("Root "+name +" occurence probability: ");
				}else{
					System.out.print(tableEntry(cnt)+": ");	
				}
				
				double val=Double.parseDouble(token.nextToken());
			
				probTable[cnt]=val;
				System.out.println(val);
				cnt++;


			}




		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	private String tableEntry(int num){
		String result="";
		while(num>0){
			//peopleIn= peopleIn==0? 0: peopleIn-1;
			result= num%2==1? "1"+result:"0"+result;

			num=num/2;
		}
		while(result.length()!=parents.length){
			result="0"+result;
		}
		return result;
	}

}
