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
 * 截图类
 * @author luto
 *
 */
public class DrawRect extends JFrame{
	private static final long serialVersionUID = 1L;
	
	//	 创建一个小的窗口,点击按钮来截屏。
	JButton button;
	public DrawRect(){
		setVisible(true);
		setLayout(new FlowLayout());
		setBounds(1000, 600, 100, 100);
		setResizable(false);
		button = new JButton(Constant.BTNNAME);
		add(button);
		button.addActionListener(new ActionListener(){
			//	鼠标点击按钮，new 一个ScreenFrame，设置可见，
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
	
	//	创建一个全屏的窗口，将全屏的图像放在JFrame的窗口上，以供来截屏。
	Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
	ScreenFrame(){
		//	设置大小，即全屏
		setSize(di);
		//	返回此窗体的 contentPane对象 
		getContentPane().add(new DrawRect());
	}

	class DrawRect extends JPanel implements MouseMotionListener, MouseListener{
		private static final long serialVersionUID = 3L;
		//	将全屏的图像放在JPanel 上， 可以通过new DrawRect来获得JPanel，并且JPanel上有全屏图像
		int sx, sy, ex, ey;
		File file = null;	
		BufferedImage image, getImage;
		boolean flag = true;
		public DrawRect(){
			try{
				//	获取全屏图像数据，返回给image
				Robot robot = new Robot();
				image = robot.createScreenCapture(new Rectangle(0, 0, di.width, di.height));
			}catch(Exception e){
				throw new RuntimeException(Constant.DRAWERR);
			}
			//	添加 鼠标活动事件
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		//	重写paintComponent，通过repaint 显示出来截屏的范围
		@Override
		public void paintComponent(Graphics g){
			g.drawImage(image, 0, 0, di.width, di.height, this);
			g.setColor(new Color(0, 115, 219));
			if(sx < ex && sy < ey){
				//	右下角
				g.drawRect(sx, sy, ex - sx, ey - sy);
			}else{
				//	左上角
				g.drawRect(ex, ey, sx - ex, sy - ey);
			}
		}
		//	以下为鼠标事件,通过重新鼠标事件方法,实习性格内容
		@Override
		public void mouseClicked(MouseEvent e){
			//	鼠标点击
			if(e.getButton() == MouseEvent.BUTTON3){
				//	右键退出程序
				System.exit(0);
			}else if(e.getClickCount() == 2){
				//	双击截屏
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
		//鼠标移动中，通过repaint 画出要截屏的范围
		@Override
		public void mouseDragged(MouseEvent e){
			ex = e.getX();
			ey = e.getY();
			repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		//	自定义截屏函数
		private void cutScreens() throws Exception{
			Robot ro = new Robot();
			if(sx < ex && sy < ey){
				//	右下角
				getImage = ro.createScreenCapture(new Rectangle(sx, sy,	ex - sx, ey - sy));
			}else{
				//	左上角
				getImage = ro.createScreenCapture(new Rectangle(ex, ey,	sx - ex, sy - ey));
			}
			//	此处为将图片保存到本地,百度orc可以接受二进制的图片数据,所有不需要保存图片,
			//	如需测试截图功能,查看截图内容,可取消此注释()
			String name =Constant.getImgName();
			file = new File(Constant.SAVEIMGADR, name);
			ImageIO.write(getImage,Constant.IMGSUFFIX, file);
			//调用识图api,获取图片中文字内容
			String code = DistinguishTP.getCode(getImage);
			//打开浏览器,搜索内容
			Search.openDefaultBrowser(code);
			System.exit(0);
		}
	}
}
