package tcpsm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import cdc.CDC;


class Acceptor implements Runnable{
	
	private static Acceptor instance;
	private static List<Socket> clientFdList;
	
	final int port = 1234;
	private CDC cdc;
	private Vector<String> ipTable;
	private ServerSocket listenFd;
	private List <Thread> clientHandler;
	
	
	public static Acceptor getInstance() {
		if (instance == null) {
			try {
				instance = new Acceptor();
				clientFdList = new LinkedList<Socket>();
			} catch (IOException e) {
				return null;
			}
		}
		return instance;
	}
	
	Acceptor()  throws IOException{
		this.ipTable = new Vector<String>();
		this.listenFd = new ServerSocket(port);

	}
	
	Vector<String> getIPTable() {
		assert instance != null: "class Acceptor.getIPTable() :need to new acceptor instance";
		assert ipTable  != null: "class Acceptor.getIPTable() :ipTable is null";
		return ipTable;
	}
	

	@Override
	public void run() {
		while(true) {
			try {
				assert listenFd != null: "class Acceptor.run() :listenFd is null";
				assert ipTable  != null: "class Acceptor.run() :ipTable is null";
				
				Socket acceptFd = listenFd.accept();
				clientFdList.add(acceptFd);
				ipTable.addElement(acceptFd.getRemoteSocketAddress().toString());
				clientHandler.add(new Thread(new ServerThread(acceptFd,cdc)));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}

}

