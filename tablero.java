
public class tablero {
	private int[][] tablero;				
	private static int N=3;					
	private static int K=12;						
	private static int S=15;						
	private int[] acuF;				
	private int[] acuC;				
	private boolean[] usados;			

	
	public tablero () {
		tablero = new int[N][N];
		this.acuF = new int[N];
		this.acuC = new int[N];
		usados = new boolean[K];
		for (int i = 0; i < K; i++) {
			usados[i]=false;
		}
	}
	
	public void solucionar(){
		if(this.backtracking(0)){
			this.imprimir();
		}else{
			System.out.println("Sin solucion");
		}
	}
	
	public void imprimir(){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println();
		}
	}
	
	public boolean esSolucion(){
		boolean aux = true;
		for (int i = 0; i < N; i++){
			if (this.acuF[i] != S){
				aux = false;
			}
		}
		if (aux){
			for (int j = 0; j < N; j++){
				if (this.acuC[j] != S){
					aux = false;
				}
			}
		}
		return aux;
	}
	
	public boolean poda(int celda, int fil, int col){
		if ((this.acuC[col] > S) || (this.acuF[fil] > S)){
			return false;
		}
		if (celda/N == N-1){
			if (this.acuC[col]!=S){
				return false;
			}
		}
		if (celda%N == N-1){
			if (this.acuF[fil]!=S){
				return false;
			}
		}
		return true;
		
	}
	
	public boolean backtracking(int celda){
		int fil = celda/N;
		int col = celda%N;
		if(celda == N*N -1){
			if(esSolucion()){
				return true;
			}
		}
		
		for (int i = 0; i < K; i++) {
			if(!usados[i]){
				this.usados[i]=true;
				this.tablero[fil][col]=i+1;
				this.acuC[col] += i+1;
				this.acuF[fil] += i+1;
				if(poda(celda,fil,col)&&(this.backtracking(celda+1))){
					return true;
				}else{
					usados[i]=false;								
					this.tablero[fil][col] = 0;								 
					this.acuF[fil] -= i+1;							 
					this.acuC[col] -= i+1;	
				}
			}
			
		}
		return false;
	}

}
