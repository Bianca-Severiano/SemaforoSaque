package view;

import java.util.concurrent.Semaphore;

import controller.ControllerSaque;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforoSaque = new Semaphore(1);
		Semaphore semaforoDeposito = new Semaphore(1);
		int saldo;
		int valorMovimentado;
		int tipoTransacao;
		for (int i = 0; i < 5; i++) {
			tipoTransacao = (int)((Math.random() * 2) + 1);
			saldo = (int)((Math.random() * 1001) + 500);
			valorMovimentado =  (int)((Math.random()* 401) + 100);
			Thread t = new ControllerSaque(saldo, valorMovimentado, tipoTransacao, i, semaforoSaque, semaforoDeposito);
			t.start();
		}

	}

}
