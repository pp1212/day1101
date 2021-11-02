//java UDPSender 192.168.0.44 �ȳ�
//java UDPSender 192.168.0.44 �ݰ����ϴ�
//192.168.0.44 -> [0], �ݰ����ϴ� -> [1]



package com.sist.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
	
	public static void main(String[] args) {
		
		String ip = args[0];
		String msg = args[1];
		
		
		
		//UDP��Ŀ��� �����͸� �ְ� �ޱ� ���ؼ��� DatagramSocket�� �ʿ�
		//DatagramSocket(int port)
		
		try {
			DatagramSocket socket = new DatagramSocket(9002);
			
			//�������� �ּ� ip�� ���� InetAddress��ü ����
			InetAddress addr = InetAddress.getByName(ip);
			
			//�����ϰ��� �ϴ� ���ڿ� msg�� byte�� �迭�� ����
			byte[]data = msg.getBytes();
			
			//UDP����� ���������۴��� ��Ŷ�� ����
			DatagramPacket packet = new DatagramPacket(data, data.length, addr,9002);
			
			//DatagramSocketŬ������ send�޼ҵ带 ���� ������ ����
			socket.send(packet);
			
			//������ �ݾ���
			socket.close();
			
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
	}
}
