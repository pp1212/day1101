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
			//1.서버소켓을 생성
			ServerSocket server = new ServerSocket(9001);
			
			//2.클라이언트의 접속을 무한 대기상태로 기다려야 함
			while(true) {
				
				//3.클라이언트의 접속을 수락
				//이 메소드는 연결을 요청한 클라이언트 통신을 하기 위한 소켓객체를 반환
				//이러한 소켓을 데이터소켓이라 함
				Socket socket = server.accept();
				System.out.println("클라이언트가 접속하였습니다.");
				
				//4.데이터를 주고 받을 스트림을 생성
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				//5.스트림을 통해 데이터를 주고받음
				//요청한 클라이언트한테 10개의 정수를 난수로 만들어 내보내도록 함
				for(int i=1;i<=10;i++) {
					
					//0에서 100사이의 난수를 만듬
					int n = r.nextInt(100);
					
					System.out.println("서버가 난수 하나를 만들었습니다."+n);
					
					//0.2초 대기시간
					Thread.sleep(200);
					
					//그 난수를 클라이언트한테 출력
					os.write(n);
					
					
				}
				
				System.out.println("클라이언트에게 데이터를 모두 내보냈습니다.");
				
				
				//6.사용했던 자원들을 닫아줌
				is.close();
				os.close();
				socket.close();
				
			}
			
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}

	}

}
