package com.gsn.engine.mercurry;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

public class Ping {
	public interface IPingListener {
		void onPing(long time);
	}

	private IPingListener listener;
	private String host;
	private int port;
	private byte[] pingByte;

	public Ping(String host, int port, IPingListener listener) {
		this.listener = listener;
		this.host = host;
		this.port = port;
		ByteBuffer pingBuf = ByteBuffer.allocate(2);
		pingBuf.clear();
		pingBuf.putShort((short) 65535);
		pingBuf.flip();
		pingByte = pingBuf.array();
	}
	private long kq = -1;
	private boolean isTimeout;
	
	private long ping(int timeout) throws Exception{
		kq = -1;
		long time = System.currentTimeMillis();
		Socket echoSocket = null;		
		try {
			echoSocket = new Socket();
			InetAddress addr = InetAddress.getByName(host);		    
		    SocketAddress sockaddr = new InetSocketAddress(addr, port);
			echoSocket.connect(sockaddr, timeout);													
			kq = (System.currentTimeMillis() - time);			
			echoSocket.close();
		} catch (Exception e) {
			//e.printStackTrace();
			kq = -1;
			System.out.println(" loi roi : " + e.getMessage());
			System.out.println(" ko ping dc time : " + (System.currentTimeMillis() - time));
		}		
		if (!isTimeout)
			if (listener != null)
				listener.onPing(kq);
		return kq;
	}
	Timer timerPing = new Timer();
	public void stop(){
		timerPing.cancel();		
	}
	
	public void loopPing(final int period, final int timeout){						

		timerPing.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ping...");
				try {
					ping(timeout);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("llllllllllloi me no roi ne");
					e.printStackTrace();
					
				}
			}
		}, 0, period);
	}
	
	static long last = System.currentTimeMillis();
	
	public static void main(String[] args) throws InterruptedException {		
		Ping p = new Ping("10.198.48.114", 443, new IPingListener() {
			
			@Override
			public void onPing(long time) {
				// TODO Auto-generated method stub
										
				System.out.println("ping : " + time);
				
			}
		});
		p.loopPing(1000, 1000);		
	}
}
