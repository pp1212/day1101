package com.sist.note;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.awt.event.KeyListener;


//"�θ޴�" ������,����,������ ������ �� �̺�Ʈó���� ���Ͽ� ActionListener�������̽��� �����ϵ��� ��
public class SistNote extends JFrame implements ActionListener, KeyListener{
	
	String fileName = "���� ����";
	
	//�̹� ���ϸ��� ���� ��� ����� �ʰ� �� ���Ͽ� �����ϵ��� �ϱ� ���Ͽ� File��ü�� �ɹ������� ����
	File file;	//=null ���ص� �⺻���� null�� ��
	
	//�޸����� �Է� �� ����� ���Ͽ� "������"�� ������ �Է��� �� �ִ� JTextArea�� �ɹ������� ����
	JTextArea jta;
	
	//������ �����̸��� ����� �����̸��� �����ϱ� ���� JFileChooser�� �ɹ������� ����
	JFileChooser jfc;
	
	//�޸����� ������ ����� ������ �ִ��� ���¸� �����ϱ� ���� ������ ����
	boolean isNew;
	
	//�����ڿ��� JTextArea ��ü�� �����Ͽ� �����ӿ� ����
	public SistNote() {
		jta = new JTextArea();
		
		//isNew�� �⺻���� false ����
		isNew = false;
		
		//JFileChooser ��ü�� ����
		jfc = new JFileChooser("c:/myData");
		
		//add(jta);
		//�ؽ�Ʈ����� �ٷ� �����ӿ� ������
		//ȭ���� ��� ���ڵ��� �� ����
		//�׷��� �ؽ�Ʈ����� �ٷ� ���� �ʰ�
		//��ũ���� �ڵ����� ������ִ� JScrollPane���� ���μ� �����ӿ� ��ƾ� ��
		//�����ӿ� ���� �� ->JScrollPane
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
		//�޴������ ũ�� ���ϱ�
		//�޴��ٸ� ���� ��ü ����
		JMenuBar jmb = new JMenuBar();
		
		//
		JMenu mn_file = new JMenu("����");
		
		//
		JMenuItem file_new = new JMenuItem("������");
		JMenuItem file_open = new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		
		//"�θ޴�" ������,����,������ "�ָ޴�" ���Ͽ� ����
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		//
		jmb.add(mn_file);
		
		//�����ӿ� �޴��ٸ� ����
		setJMenuBar(jmb);
		
		//��ġ�� �θ޴� ������� ���Ŀ� ����
		//"�θ޴�" ������,����,���忡 ���Ͽ� �޴��� ������ �� � ���� �ϵ��� �̺�Ʈ�� ���
		//�Ű������� �̺�Ʈó����簴ü�� �����ؾ� �ϴµ�, �� Ŭ����(SistNote) �ڽ��� ó���ϵ��� �ϱ� ���Ͽ�
		//this�� ����-> ���� �����ϳ� -> ���� �Ѵ�
		file_new.addActionListener( this );
		file_open.addActionListener( this );
		file_save.addActionListener( this );
		
		//�ؽ�Ʈ����� Ű�̺�Ʈ�� ���
		jta.addKeyListener(this);
		
		//�������� ũ�⸦ �����ϰ� �������� �����ֵ��� ����
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
		
		
		/*â�� ó���� ��������  ȭ�鿡 3�ʵ��� "�ȳ��ϼ���" ���
		 getText�� �ݴ�
		 
		jta.setText("�ȳ��ϼ���");
		try {
			Thread.sleep(3000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		jta.setText("");
		*/
	}

	//�θ޴� ������ �� �޼ҵ尡 ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�޴� �߿� ���� ���������� �ľ��ϱ� ���Ͽ�
		//ActionEvent�� �޼ҵ� �߿� getActionCommand �޼ҵ带 �̿��Ͽ�
		//������ �޴��� "����"�� ������
		String cmd = e.getActionCommand();
		
		//������ �޴��� ���� ������ ��ó���� �ϵ��� ��
		if(cmd.equals("������")) {
			
			if(isNew == true) {		//����� ������ ���� �� ������ ����
				int re = JOptionPane.showConfirmDialog(this,"�����Ͻðڽ��ϱ�?");
				System.out.println("re:"+re);
				//re:0	:������ �ϰ� ������ ó��
				//re:1	:������ ���ϰ� ������ ó��
				//re:2	:������ ���
				
				switch(re) {
					case 0:saveFile();
					case 1:
						//�ؽ�Ʈ����� �����ϰ� �����
						jta.setText("");
						fileName = "�������";
						setTitle(fileName);
						isNew = false;
						file = null;
						break;
					case 2:return;
				}
			}else {		//����� ������ ���� �� ������ ����
				jta.setText("");
				fileName = "�������";
				setTitle(fileName);
				isNew = false;
				file = null;
			}
			
			
			
		}else if(cmd.equals("����")) {
			
			try {
				//"����" �޴��� ������ ��� �ִ� � ������ ������� �����ϱ� ���� ���̾�α� ���
				int re = jfc.showOpenDialog(this);
				
				//���̾�α׷��� "���"�� ������ �ʰ� "����"�� ������ �� ������ ������ �о���� ó���� �ϵ��� ��
				if(re == 0) {
					//���̾�α׿��� ������ ���������� ������
					//����� ������ �ɹ����� file�� ������
					file = jfc.getSelectedFile();	//�ɹ������� ����
						
						//�� ������ ������ ��ǻ�͸޸𸮷� �о���̱� ���� ��Ʈ�� ����
						FileReader fr = new FileReader(file);
						
						//������ ��� ������ ��� ���� ���ڿ� ������ ����
						//���Ϸκ��� �ѱ��ھ� �о�ͼ� �����ϱ� ���Ͽ� ""���� �ʱ�ȭ
						String data = "";
						
						//���Ϸ� ���� �ѱ��ھ� �б� ���� ���� ����
						int ch;
						
						//�ݺ����� �̿��Ͽ� ������ ���� �ƴҵ��� �ѱ��ھ� �о� ����
						while(true) {
							
							//���Ϸκ��� �ѱ��ھ� �о����
							//���ڿ� �ش��ϴ� �������� ��ȯ ��
							//���� ���̻� �о���� ������ ���� ������ ���� �����ϸ� -1�� ��ȯ
							ch = fr.read();
							
							//���Ϸκ��� ���̻� �о���� ������ ������ �ݺ����� Ż��
							if(ch == -1) {
								break;
							}
							
							//������ ���� �ƴ϶�� �о���� ������ ���ڸ� �ٽ� ���ڷ� ��ȯ�Ͽ�
							//���ڿ� ���� data�� ���� ��
							data = data + (char)ch;
						}	
						//������ ������ ��� �о���� �������� while�� Ż����
						//while�� Ż���������� ������ ��� ������ String ���� data�� ����� ����
						//data�� ������ �ؽ�Ʈ����� ����
						jta.setText(data);
						
						//������ �о�Դٰ� �޽��� ���
						JOptionPane.showMessageDialog(this, "������ �о�Խ��ϴ�.");
						
						//�о�� ������ ������ ��� �ִ� File ��ü�κ��� ���ϸ��� �̾Ƽ� �ɹ�����  fileName�� ����
						fileName = file.getName();
						//String []arr = fileName.split(".");
						//fileName = arr[0];
						fileName = fileName.substring(0,fileName.indexOf("."));
						
						//���ϸ��� �������� ����ǥ���ٷ� ����
						setTitle(fileName);
				}	
			} catch (Exception ex) {
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}
			
			isNew = false;
			
		}else if(cmd.equals("����")) {
			saveFile();
			
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		isNew = true;
	}
	
	public void saveFile() {
		//"����"�޴��� ������ ��� � �����̸����� ������ ������ �����ϵ��� ���̾�α� ���
		//�Ű����� this�� �� ���̾�α׸� � ������������ ��� ������ ����
		//���� �� ������ SistNote�� ����� �ǹ̷� ��ü�ڽ��� �ǹ��ϴ� this ����
		//showSavwDialog���� ����ڴ� ������ ���� ���� �ְ� ��Ҹ� ���� ���� ����
		//��Ҹ� �����µ� ���� �����ϴ� ��ɾ ó���ϸ� ���
		//�׷��� �� �޼ҵ�� ������ ������ 0�� ��ȯ�ϰ� ��Ҹ� ������ 1�� ��ȯ
		//�� ��ȯ�ϴ� ���� ������ ��� �� ���� ���� �״��� ��ó���� �ٸ��� �� �� ����
		
		//�̹� ������ ���ٸ� ������ �����ϱ� ���� ���̾�α׸� ����
		//�̹� ������ �ִٸ� �� �̸����� �����ϵ��� ��
		
		int re = 0;
		//�̹� �����̸��� �ִ� ���¿���(File�� null�� �ƴ� ����)
		//������ ������ ������ �����ؾ� ��
		//�׷��� ���� �� ó�� ������ �����ϸ� File�� null���¿��� "����"�� ������
		//������ �����ϴ� ���̾�α׿��� "���"�� ������ �ʰ� "����"�� ������ ���� �����ϵ��� ó���ؾ� ��
		//�̰��� ���ÿ� ó���ϱ� ���Ͽ� re�� �⺻���� 0�� ����
		//�׷��� �̹� ������ ������ ������ if�� ������ �ʾƼ� re�� �״�� 0�� �����ϰ� ����
		//���� ������ ���� ���¸� ������ if�� ������
		//����� ���̾�α׿��� "���"�� ������ re=1�� ��
		//�׷��� �ڿ� ����ó���� ����
		
		
		if(file == null) {
			re = jfc.showSaveDialog(this);	
			if(re == 0) {
				file = jfc.getSelectedFile();
			}
		}
		
		
		//"�����ϱ�"���̾�α׿��� "����"�� ������ �� ���������� ���� ��ɾ���� �����ϵ��� ��
		if(re == 0) {
			//���̾�α׿��� ������ ������ ������ �о� ��
			File file = jfc.getSelectedFile();
			
			//�� ������ ���Ϸ� �ؽ�Ʈ����� ������ ������ ����ϱ� ���� ��Ʈ���� ����
			//���ڴ����� ����� ���� Writer�� �ļ��� FileWriter�� �̿�
			//����°� ���õ� ��� �����ڿ� �޼ҵ���� ���ܸ� �����ϰ� ����
			//�� ���ܵ��� RunTimeException�� �ļյ��� �ƴϱ� ������
			//����ڰ� �ݵ�� ����ó�� �ؾ� ��
			try {
				FileWriter fw = new FileWriter(file);
				
				//�ؽ�Ʈ�������� ������ ������ �о�ͼ� ���Ϸ� ��� ��
				fw.write( jta.getText() );
				
				//������ ����� �������Ƿ� ������ �ݾ���
				fw.close();
				
				//��������� �˷��� ������ �ֿܼ� ������� ���� ���̾�α׸� ���
				JOptionPane.showMessageDialog(this, "���Ϸ� �����Ͽ����ϴ�.");
				
				//������ ������ ������ ���� �ִ� ��ü File file�� ���� �����̸��� �̾ƿͼ�
				//�ɹ����� fileName�� ����
				//fileName = file.getName();
				//�޸�.txt
				//.���� �и��Ͽ� ���ϸ� �̾ƿ�
				fileName = file.getName();
				//String []arr = fileName.split(".");
				//fileName = arr[0];
				//split�޼ҵ�� .���� �и� �Ұ���
				fileName = fileName.substring(0,fileName.indexOf("."));
				
				//���ϸ��� ����ǥ���ٿ� ����
				setTitle(fileName);
				
			}catch (Exception ex) {	//���ܺ����̸��� �̺�Ʈ�����̸��� �ٸ��� ��������� ��
				System.out.println("���ܹ߻�:"+ex.getMessage());	
				//���ܰ� �߻��ϸ� ���ܸ޽����� ����Ͽ�
				//�޽����� �ľ��Ͽ� ������ �ذ��ϵ��� ��
			}
			
			isNew = false;
		}
	}
}

