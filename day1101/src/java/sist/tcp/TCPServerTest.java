package java.sist.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerTest {

	public static void main(String[] args) {
		
		Random r = new Random();
		
		
		try {
			//1.���������� ����
			ServerSocket server = new ServerSocket(9001);
			
			//2.Ŭ���̾�Ʈ�� ������ ���� �����·� ��ٷ��� ��
			while(true) {
				
				//3.Ŭ���̾�Ʈ�� ������ ����
				//�� �޼ҵ�� ������ ��û�� Ŭ���̾�Ʈ ����� �ϱ� ���� ���ϰ�ü�� ��ȯ
				//�̷��� ������ �����ͼ����̶� ��
				Socket socket = server.accept();
				System.out.println("Ŭ���̾�Ʈ�� �����Ͽ����ϴ�.");
				
				//4.�����͸� �ְ� ���� ��Ʈ���� ����
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				//5.��Ʈ���� ���� �����͸� �ְ����
				//��û�� Ŭ���̾�Ʈ���� 10���� ������ ������ ����� ���������� ��
				for(int i=1;i<=10;i++) {
					
					//0���� 100������ ������ ����
					int n = r.nextInt(100);
					
					System.out.println("������ ���� �ϳ��� ��������ϴ�."+n);
					
					//0.2�� ���ð�
					Thread.sleep(200);
					
					//�� ������ Ŭ���̾�Ʈ���� ���
					os.write(n);
					
					
				}
				
				System.out.println("Ŭ���̾�Ʈ���� �����͸� ��� �����½��ϴ�.");
				
				
				//6.����ߴ� �ڿ����� �ݾ���
				is.close();
				os.close();
				socket.close();
				
			}
			
			
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}

	}

}
