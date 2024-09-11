package view;
import java.util.concurrent.Semaphore;
import controller.*;

public class Main
{
	

	public static void main(String[] args)
	{
		
		
		Semaphore pegarPedra = new Semaphore(1);
		
		Semaphore pegarTocha = new Semaphore(1);
		
		Semaphore pegarPorta = new Semaphore(1);
		
		Cavaleiro.monsters = new boolean[4];
		Cavaleiro.monsters[(int)(Math.random()*4)] = true;
		Cavaleiro.portas = new boolean[4];
		
		for(int i = 0;i < 4;i++) {
			Cavaleiro c = new Cavaleiro(i+1, pegarTocha, pegarPedra, pegarPorta);
			
			c.start();
			}

	}
}