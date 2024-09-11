package controller;
import java.util.concurrent.Semaphore;



public class Cavaleiro extends Thread 
{
	
	Semaphore pegarPedra;
	Semaphore pegarTocha;
	Semaphore pegarPorta; //CRIANDO OS 3 SEMAFOROS QUE IRÃO DETERMINAR O RESULTADO
	
	
	public static boolean monsters[];  // UM MONSTRO TIPO BOOLEANO PARA CONDIÇÃO FALSO OU VERDADEIRO
	public static boolean portas[]; // PORTAS BOOLEANAS COM FALSO OU VERDADEIRO;
	int velocidade = 2; //VELOCIDADE DOS CAVALEIROS DE 2 M ;
	int id; // ID DOS CAVALEIROS
	int percorrido; //DISTANCIA TOTAL PERCORRIDA
	static boolean tocha;
	static boolean pedra;


	
	// private static final ThreadLocal<Boolean> threadHasItem = ThreadLocal.withInitial(() -> false);
	 
	 
	 
	
	public Cavaleiro(int id, Semaphore pegarPedra, Semaphore pegarTocha, Semaphore pegarPorta)
	{
		this.id = id;  //ATRIBUINDO os atributos para os objetos
		this.pegarPedra = pegarPedra;
		this.pegarPorta = pegarPorta;
		this.pegarTocha = pegarTocha;
		
	}
	
	public void run() //INICIANDO AS THREADS 
	{
		
		
		while(percorrido <= 2000) //CONDICIONAL DE PARADA CASO TERMINE O TAMANHO DO MAPA DE 2000M
		{
			percorrido = percorrido + (int)(Math.random()*3) + velocidade; 
			
			
			
			
			if(percorrido >= 500 && !tocha ) // !threadHasItem.get()
				
				
				
			{
				try
				{
					pegarTocha.acquire();
					velocidade = velocidade + 2;
					System.out.println("O Cavaleiro "+id+" pegou a tocha.");
					tocha = true;
					
					
					
					
					// threadHasItem.set(true);
					
					
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			if (percorrido >=1500 && !pedra ) //!threadHasItem.get()
			{
				try 
				{
					pegarPedra.acquire();
					velocidade = velocidade + 2;
					System.out.println("O Cavaleiro "+id+" pegou a pedra.");
					pedra = true;
				//	threadHasItem.set(true);
					
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
			
			System.out.println("O Cavaleiro "+id+ " percorreu "+percorrido+ " KM.");
			
			try
			{
				sleep(50);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		
		
		int indicePortas = (int)(Math.random()*4);
		
		while(portas[indicePortas])

			
		indicePortas = (int)(Math.random()*4);
		
		try {
			pegarPorta.acquire();
			portas[indicePortas] = true;
			System.out.println("O Cavaleiro "+id+ " pegou a porta "+(indicePortas+1)+ " e " +(monsters[indicePortas] ? " Saiu com segurança " : " Foi devorado"));
		} catch(InterruptedException e)
		{
			e.printStackTrace();
		}finally
		{
			pegarPorta.release();
		}
	}

}
