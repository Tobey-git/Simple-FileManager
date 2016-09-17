package Managers;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_srcPath;
	private JButton button_srcFile;
	private JButton button_srcDir;
	private JTextField textField_dst;
	private JButton button_dst;
	private JTextField textField_dstName;
	private JLabel label_name;
	private JTextArea textArea_result;
	private JButton button_delete;
	private JButton button_zip;
	private JButton button_unzip;
	private JButton button_copy;
	private JButton button_rename;
	private JButton button_create;
	private JButton button_list;
	private JButton button_clear;
	private JTextField textField_srcName;
	private FileDialog Choose_File;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_srcPath = new JTextField();
		textField_srcPath.setBounds(10, 58, 117, 21);
		contentPane.add(textField_srcPath);
		textField_srcPath.setColumns(10);
		
		button_srcFile = new JButton("选择文件");
		button_srcFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Choose_File = new FileDialog(MainUI.this,"选择文件",FileDialog.LOAD);
				Choose_File.setBounds(0,100,400,400);
				Choose_File.setVisible(true);
				Choose_File.addWindowListener(new WindowAdapter(){  
					public void windowClosing(WindowEvent e){ 
						Choose_File.setVisible(false);
						super.windowClosing(e);
					}
				});
				textField_srcPath.setText(Choose_File.getDirectory());
				textField_srcName.setText(Choose_File.getFile());
			}
		});

		button_srcFile.setBounds(10, 25, 117, 23);
		contentPane.add(button_srcFile);
		
		button_srcDir = new JButton("选择目录");
		button_srcDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//选择目录
				  JFileChooser jfc = new JFileChooser();
				  jfc.setDialogTitle("选择目录");
				  jfc.setDialogType(JFileChooser.OPEN_DIALOG);
				  jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				  int res = jfc.showOpenDialog(null);
				  if (res == JFileChooser.APPROVE_OPTION) {
					   File dir = jfc.getSelectedFile();
					   textField_srcPath.setText(dir.getAbsolutePath());
					   textField_srcName.setText("");
				  }
			}
		});

		button_srcDir.setBounds(130, 25, 100, 23);
		contentPane.add(button_srcDir);
		
		textField_dst = new JTextField();
		textField_dst.setColumns(10);
		textField_dst.setBounds(131, 91, 99, 21);
		contentPane.add(textField_dst);
		
		button_dst = new JButton("选择存储路径");
		button_dst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//选择目录
				  JFileChooser jfc = new JFileChooser();
				  jfc.setDialogTitle("选择存储路径");
				  jfc.setDialogType(JFileChooser.OPEN_DIALOG);
				  jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				  int res = jfc.showOpenDialog(null);
				  if (res == JFileChooser.APPROVE_OPTION) {
					   File dir = jfc.getSelectedFile();
					   textField_dst.setText(dir.getAbsolutePath());
				  }
			}
		});

		button_dst.setBounds(10, 90, 117, 23);
		contentPane.add(button_dst);
		
		textField_dstName = new JTextField();
		textField_dstName.setColumns(10);
		textField_dstName.setBounds(131, 121, 99, 21);
		contentPane.add(textField_dstName);
		
		label_name = new JLabel("输入文件|目录名:");
		label_name.setHorizontalAlignment(SwingConstants.RIGHT);
		label_name.setBounds(10, 120, 111, 23);
		contentPane.add(label_name);
		
		textArea_result = new JTextArea();
		textArea_result.setLineWrap(true);
		textArea_result.setWrapStyleWord(true);
		textArea_result.setEditable(false);
		textArea_result.setBounds(10, 152, 220, 175);
//		JScrollPane jsp = new JScrollPane(textArea_result);
		contentPane.add(textArea_result);
		
		button_delete = new JButton("删除");
		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				Dir_FileDeleter dir_fileDeleter=new Dir_FileDeleter();
				String result = dir_fileDeleter.Deleting(textField_srcPath.getText(),textField_srcName.getText());
				textArea_result.append(result + "\n");
				//将文本框中的路径及目录名清空
				clean_input();
			}
		});

		button_delete.setBounds(240, 26, 93, 23);
		contentPane.add(button_delete);
		
		button_zip = new JButton("压缩");
		button_zip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				Dir_FileZipper Dir_FileZipper=new Dir_FileZipper();
				String result;
				try {
					result = Dir_FileZipper.compress(textField_srcPath.getText() + textField_srcName.getText(), 
								textField_dst.getText() + File.separator + textField_dstName.getText());
					textArea_result.append(result + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//将文本框中的路径及目录名清空
				clean_input();
			}
		});

		button_zip.setBounds(240, 58, 93, 23);
		contentPane.add(button_zip);
		
		button_unzip = new JButton("解压缩");
		button_unzip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				Dir_FileUnzipper Dir_FileUnzipper=new Dir_FileUnzipper();
				String result;
				try {
					result = Dir_FileUnzipper.decompression(textField_srcPath.getText() + textField_srcName.getText(),
								textField_dst.getText() );
					
					textArea_result.append(result + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//将文本框中的路径及目录名清空
				clean_input();
			}
		});

		button_unzip.setBounds(240, 89, 93, 23);
		contentPane.add(button_unzip);
		
		button_copy = new JButton("复制");
		button_copy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				Dir_FileCopyer dir_fileCopyer=new Dir_FileCopyer();
				try {
					String result = dir_fileCopyer.Copying(textField_srcPath.getText(), textField_srcName.getText(), textField_dst.getText());
					textArea_result.append(result + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				clean_input();
			}
		});
		
		button_copy.setBounds(240, 122, 93, 23);
		contentPane.add(button_copy);
		
		button_rename = new JButton("重命名");
		button_rename.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				Dir_FileRenamer dir_fileRenamer=new Dir_FileRenamer();
				String result = dir_fileRenamer.Renaming(textField_srcPath.getText(), textField_srcName.getText(), textField_dstName.getText());
				textArea_result.append(result + "\n");
				clean_input();
			}
		});

		button_rename.setBounds(240, 154, 93, 23);
		contentPane.add(button_rename);
		
		button_create = new JButton("新建目录");
		button_create.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				//创建新目录
				DirCreater dirCreater=new DirCreater();
				String result = dirCreater.Creating(textField_dst.getText(),textField_dstName.getText());
				textArea_result.append(result + "\n");
//				setText(result + "\n");
				//将文本框中的路径及目录名清空
				clean_input();
			}
		});

		button_create.setBounds(240, 187, 93, 23);
		contentPane.add(button_create);
		
		button_list = new JButton("目录内容");
		button_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_output();
				FileLister fileLister=new FileLister();
				String result = fileLister.Listing(textField_srcPath.getText(), textField_srcName.getText());
				textArea_result.append(result + "\n");
				clean_input();
			}
		});
		
		button_list.setBounds(240, 220, 93, 23);
		contentPane.add(button_list);
		
		button_clear = new JButton("Clear");
		button_clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clean_input();
				clean_output();
			}
		});
		
		button_clear.setBounds(240, 253, 93, 23);
		contentPane.add(button_clear);
		
		textField_srcName = new JTextField();
		textField_srcName.setColumns(10);
		textField_srcName.setBounds(131, 60, 99, 21);
		contentPane.add(textField_srcName);

	}
	
	public void clean_input() {

		//清空文本框中输入的内容
		textField_srcPath.setText("");
		textField_srcName.setText("");
		textField_dst.setText("");
		textField_dstName.setText("");
	}
	
	public void clean_output() {
		//清空结果显示框内容
		textArea_result.setText("");
	}
}
