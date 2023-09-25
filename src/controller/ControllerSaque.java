package controller;

import java.util.concurrent.Semaphore;

public class ControllerSaque extends Thread{

	public int Saldo;
	public int ValorMovimentado;
	public int TipoTransacao;
	public int idConta;
	Semaphore semaforoSaque;
	Semaphore semaforoDeposito;
	
	public ControllerSaque(int Saldo, int ValorMovimentado, int TipoTransicao, int idConta, Semaphore semaforo1, Semaphore semaforo2) {
		this.Saldo = Saldo;
		this.ValorMovimentado = ValorMovimentado;
		this.TipoTransacao = TipoTransicao;
		this.idConta = idConta;
		this.semaforoSaque = semaforo1;
		this.semaforoDeposito = semaforo2;
	}
	
	public void run() {
		Transacao();
	}
	
	private void Transacao() {
		if(TipoTransacao == 1) { // Saque
			SemaforoSaque();
		} else {// Deposito
			SemaforoDeposito();
		}
	}
	
	private void SemaforoSaque() {
		try {
			semaforoSaque.acquire();
			Operacao();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			semaforoSaque.release();
		}
	}
	
	private void SemaforoDeposito() {
		try {
			semaforoDeposito.acquire();
			Operacao();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforoDeposito.release();
		}
		
	}
	
	private void Operacao() {
		int novoSaldo;
		if(TipoTransacao == 1) {
			System.out.println("Saldo Atual: R$" + Saldo +". Realizando saque de " + ValorMovimentado + " da conta " + idConta);
			novoSaldo = Saldo - ValorMovimentado;
		} else {
			System.out.println("Saldo Atual: R$" + Saldo +". Realizando depósito de " + ValorMovimentado + " para conta " + idConta);
			novoSaldo = Saldo + ValorMovimentado;
		}
		System.err.println("Transação Finalizada --> Novo saldo da conta " + idConta + " é R$" + novoSaldo);
	}

}
