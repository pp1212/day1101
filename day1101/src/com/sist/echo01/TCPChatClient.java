package com.sist.echo01;

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

public class TCPChatClient extends JFrame implements ActionListener,Runnable{

	//��ȭ������ ����� �ؽ�Ʈ����� �ɹ������� ����
	JTextArea jta;
	
	//���� ��ȭ������ �Է��� �ؽ�Ʈ�ʵ带 �ɹ������� ����
	JTextField jtf;
	
	//����� ��Ʈ���� �ɹ������� ����
	InputStream is;
	OutputStream os;
	
	public TCPChatClient() {
		
		
		
		
		
		//������� �ؽ�Ʈ������� �ؽ�Ʈ�ʵ带 ����
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		//������ ���� ��ư ����
		JButton btn = new JButton("����");
		
		//��ư�� �̺�Ʈ ��� -> ��ư ������ actionperformed�� ��
		btn.addActionListener(this);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� ���� �г� ����
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		//�ؽ�Ʈ����� ��ũ�������� ���α�
		JScrollPane jsp = new JScrollPane(jta);
		
		//�������� ����� �ؽ�Ʈ����� ���ΰ� �ִ� ��ũ������ ���
		add(jsp,BorderLayout.CENTER);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� �ִ� �г��� �������� �Ʒ��� ���
		add(p,BorderLayout.SOUTH);
		
		//������ ũ�� ����
		setSize(800,600);
		
		//�������� ȭ�鿡 ���̵��� ����
		setVisible(true);
		
		
		try {
			
			//����� ���Ͽ� ������ ������ ��û
			Socket socket = new Socket("192.168.35.67",9003);
			
			//������� ���� ��Ʈ���� ����, ���⿡�� ��ü�� �����ع����� �ٸ� ������ ����� �� �����ϱ� �ɹ�������
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Thread t = new Thread(this);
		t.start();
		//->�̷��� �ؾ� run ������
		
		
	}//end ������
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPChatClient();
		//1.main start
		//2.������ start
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//����ڰ� �Է��� �ؽ�Ʈ�ʵ��� ������ ������ ����
			//OutputStream void	write(byte[] b) �޼ҵ� �̿�
			//�����ͼ� byte�� �ٲ���
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//��� �������� �ϴϱ� close �� ��
			
			//�޼����� �����ϰ� ���� �޼��� �Է��� ���Ͽ� �ؽ�Ʈ�ʵ带 ������ ����
			jtf.setText("");
			
			
		} catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}
	}

	
	//�����κ��� ���ŵ� �����͸� ��� �޵��� ��
	//�������� Ŭ���̾�Ʈ�� ���� �� ����(Ŭ���̾�Ʈ) �� ���ص� �ٸ� Ŭ���̾�Ʈ�� ���� ��� �޵���
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		byte []data = new byte[100];
		while(true) {
			try {
				is.read(data);
				String msg = new String(data);
				msg = msg.trim();
				jta.append(msg+"\n");
				
			}catch (Exception e) {
				// TODO: handle exception
			}//end catch
		}//end while
	}//end run

}