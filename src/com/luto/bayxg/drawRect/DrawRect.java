package com.luto.bayxg.drawRect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.luto.bayxg.common.Constant;
import com.luto.bayxg.distinguish.DistinguishTP;
import com.luto.bayxg.search.Search;
/**
 * ��ͼ��
 * @author luto
 *
 */
public class DrawRect extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//	 ����һ��С�Ĵ���,�����ť��������
	JButton button;
	public DrawRect(){
		setVisible(true);
		setLayout(new FlowLayout());
		setBounds(1000, 600, 100, 100);
		setResizable(false);
		button = new JButton(Constant.BTNNAME);
		add(button);
		button.addActionListener(new ActionListener(){
			//	�������ť��new һ��ScreenFrame�����ÿɼ���
			public void actionPerformed(ActionEvent e){
				ScreenFrame sf = new ScreenFrame();
				sf.setAlwaysOnTop(true);
				sf.setUndecorated(true);
				sf.setVisible(true);
			}
		});
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
class ScreenFrame extends JFrame{
	private static final long serialVersionUID = 2L;
	
	//	����һ��ȫ���Ĵ��ڣ���ȫ����ͼ�����JFrame�Ĵ����ϣ��Թ���������
	Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
	ScreenFrame(){
		//	���ô�С����ȫ��
		setSize(di);
		//	���ش˴���� contentPane���� 
		getContentPane().add(new DrawRect());
	}

	class DrawRect extends JPanel implements MouseMotionListener, MouseListener{
		private static final long serialVersionUID = 3L;
		//	��ȫ����ͼ�����JPanel �ϣ� ����ͨ��new DrawRect�����JPanel������JPanel����ȫ��ͼ��
		int sx, sy, ex, ey;
		File file = null;	
		BufferedImage image, getImage;
		boolean flag = true;
		public DrawRect(){
			try{
				//	��ȡȫ��ͼ�����ݣ����ظ�image
				Robot robot = new Robot();
				image = robot.createScreenCapture(new Rectangle(0, 0, di.width, di.height));
			}catch(Exception e){
				throw new RuntimeException(Constant.DRAWERR);
			}
			//	��� ����¼�
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		//	��дpaintComponent��ͨ��repaint ��ʾ���������ķ�Χ
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, di.width, di.height, this);
			g.setColor(new Color(0, 115, 219));
			if(sx < ex && sy < ey){
				//	���½�
				g.drawRect(sx, sy, ex - sx, ey - sy);
			}else{
				//	���Ͻ�
				g.drawRect(ex, ey, sx - ex, sy - ey);
			}
		}
		//	����Ϊ����¼�,ͨ����������¼�����,ʵϰ�Ը�����
		@Override
		public void mouseClicked(MouseEvent e){
			//	�����
			if(e.getButton() == MouseEvent.BUTTON3){
				//	�Ҽ��˳�����
				System.exit(0);
			}else if(e.getClickCount() == 2){
				//	˫������
				try{
					cutScreens();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e){
			if(flag){
				sx = e.getX();
				sy = e.getY();
			}
		}
		@Override
		public void mouseReleased(MouseEvent e){
			flag = false;
		}
		@Override
		public void mouseEntered(MouseEvent e){
			
		}
		@Override
		public void mouseExited(MouseEvent e){
			
		}
		//����ƶ��У�ͨ��repaint ����Ҫ�����ķ�Χ
		@Override
		public void mouseDragged(MouseEvent e){
			ex = e.getX();
			ey = e.getY();
			repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		//	�Զ����������
		private void cutScreens() throws Exception{
			Robot ro = new Robot();
			if(sx < ex && sy < ey){
				//	���½�
				getImage = ro.createScreenCapture(new Rectangle(sx, sy,	ex - sx, ey - sy));
			}else{
				//	���Ͻ�
				getImage = ro.createScreenCapture(new Rectangle(ex, ey,	sx - ex, sy - ey));
			}
			//	�˴�Ϊ��ͼƬ���浽����,�ٶ�orc���Խ��ܶ����Ƶ�ͼƬ����,���в���Ҫ����ͼƬ,
			//	������Խ�ͼ����,�鿴��ͼ����,��ȡ����ע��()
			String name =Constant.getImgName();
			file = new File(Constant.SAVEIMGADR, name);
			ImageIO.write(getImage,Constant.IMGSUFFIX, file);
			//����ʶͼapi,��ȡͼƬ����������
			String code = DistinguishTP.getCode(getImage);
			//�������,��������
			Search.openDefaultBrowser(code);
			System.exit(0);
		}
	}
}
