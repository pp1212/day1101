package com.sist.echo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TCPChatClient extends JFrame implements ActionListener{

	//대화내용을 출력할 텍스트에리어를 맴버변수로 만듬
	JTextArea jta;
	
	//내가 대화내용을 입력할 텍스트필드를 맴버변수로 만듬
	JTextField jtf;
	
	//입출력 스트림을 맴버변수로 만듬
	InputStream is;
	OutputStream os;
	
	public TCPChatClient() {
		
		
		
		
		
		//멤버변수 텍스트에리어와 텍스트필드를 생성
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		//전송을 위한 버튼 생성
		JButton btn = new JButton("전송");
		
		//버튼에 이벤트 등록 -> 버튼 눌리면 actionperformed로 감
		btn.addActionListener(this);
		
		//텍스트필드와 버튼을 담기 위한 패널 생성
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		//텍스트에리어를 스크롤팬으로 감싸기
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임의 가운데에 텍스트에리어를 감싸고 있는 스크롤팬을 담기
		add(jsp,BorderLayout.CENTER);
		
		//텍스트필드와 버튼을 담고 있는 패널을 프레임의 아래에 담기
		add(p,BorderLayout.SOUTH);
		
		//프레임 크기 설정
		setSize(800,600);
		
		//프레임이 화면에 보이도록 설정
		setVisible(true);
		
		
		try {
			
			//통신을 위하여 서버에 접속을 요청
			Socket socket = new Socket("192.168.35.67",9003);
			
			//입출력을 위한 스트림을 생성, 여기에서 객체를 생성해버리면 다른 곳에서 사용할 수 없으니까 맴버변수로
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//서버가 보내오는 데이터를 계속하여 받기 위한 쓰레드 클래스를 만듬
		//innerClass 바깥에 있는 클래스를 Outter클래스
		//이너클래스는 마치 아우터클래스 맴버처럼 동작
		//아우터클래스의 멤버네 자유롭게 접근
		class ClientThread extends Thread{
			byte []data = new byte[100];
			public void run() {
				while(true) {
					try {
						//서버가 보내온 데이터를 수신함
						is.read(data);
						
						//수신한 데이터를 문자열로 만듬
						String msg = new String(data);
						
						//수신한 문자열을 텍스트에리어에 추가
						jta.append(msg.trim()+"\n");
						
					}catch (Exception e) {
						System.out.println("예외발생:"+e.getMessage());
					}
					
				}
			}//end run
		}//end innerClass
		
		//서버로부터 계속하여 수신된 메시지를 받기 위한 쓰레드 객체를 생성하고 가동
		ClientThread ct = new ClientThread();
		ct.start();
		
	}//end 생성자
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPChatClient();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//사용자가 입력한 텍스트필드의 내용을 서버로 보냄
			//OutputStream void	write(byte[] b) 메소드 이용
			//가져와서 byte로 바꿔줌
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//계속 내보내야 하니까 close 안 함
			
			//메세지를 전송하고 다음 메세지 입력을 위하여 텍스트필드를 깨끗이 지움
			jtf.setText("");
			
			
		} catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}
	}

}
