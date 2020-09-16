package org.mycontrib.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class ProcessApiApp {

	public static void main(String[] args) {
		//startNodePad();
		//destroyProcessById();
		//startAndDestroyProcess();
		startAndWaitingProcess();
		//displayingProcess();
	}
	
	public static String processHandleAsString(ProcessHandle ph) {
		return "pid="+ph.pid()+" - "+ph.info().toString();
	}
	
	public static void displayingProcess() {
		ProcessHandle.allProcesses().forEach(ph -> System.out.println(processHandleAsString(ph)));
	}
	
	public static void startNodePad() {
		ProcessBuilder builder = new ProcessBuilder("notepad.exe");//de java.lang depuis java 1.5
		try {
			Process process = builder.start();
			System.out.println("pid of process=" + process.pid());
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public static void destroyProcessById() {
		long pid=Long.parseLong(JOptionPane.showInputDialog(null, "pid"));
		Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
		optionalProcessHandle.ifPresent(processHandle -> processHandle.destroy());
	}
	
	public static void startAndDestroyProcess() {
		//ProcessBuilder builder = new ProcessBuilder("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		ProcessBuilder builder = new ProcessBuilder("notepad.exe");
		try {
			Process process = builder.start();
			System.out.println("pid of process=" + process.pid());
			Thread.sleep(5000); //5000ms
			process.destroy();
			if (process.isAlive()) {
				System.out.println("process still alive ...");
			    process.destroyForcibly(); //a utiliser que si process.destroy() ne suffit pas
			}else {
				System.out.println("process is no more alive ...");
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	
	public static void startAndWaitingProcess() {
		ProcessBuilder builder = new ProcessBuilder();
		/*
		final String javaHome = "C:\\Program Files\\Java\\jdk-11.0.4" ; // " " need around directory name with space 
		final String javaCmdeWithQuote = "\"" + javaHome+"\\bin\\java.exe" +"\"";
		//final String cmde = javaCmdeWithQuote + " -version";
		final String cmde = javaCmdeWithQuote+" -cp .\\target\\myProcessApp.jar org.mycontrib.tp.MySubProcess";
		System.out.println("cmde="+cmde);
		//builder.command("cmd.exe", "/c", cmde); // ok , "/c" = terminate after this run
		*/
		//NB: .\\target\\myProcessApp.jar will be ready after mvn install or ...
		builder.command("java","-cp",".\\target\\myProcessApp.jar", "org.mycontrib.tp.MySubProcess");//ok with java in PATH
		
		boolean isStopped = false;
		try {
			Process process = builder.start();
			System.out.println("pid of process=" + process.pid());
			BufferedReader outputReaderOfSubProcess = new BufferedReader(new InputStreamReader(process.getInputStream()));
			System.out.println("output of process (first line) =" + outputReaderOfSubProcess.readLine());
			isStopped=process.waitFor(3, TimeUnit.SECONDS);
			if(isStopped) {
				System.out.println("process is terminated");
			}else {
			   System.out.println("process not terminated after 3s");
			   isStopped=process.waitFor(3, TimeUnit.SECONDS);//re-wait
			}
			if (isStopped) {
				System.out.println("process is stopped with exit value="+process.exitValue());
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

}
