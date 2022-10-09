import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mynotepad extends JFrame  implements ActionListener
{
    JMenuBar menubar=new JMenuBar();
    JMenu file=new JMenu("file");
    JMenu edit=new JMenu("edit");
    JMenu help=new JMenu("help");

JMenuItem newFile=new JMenuItem("New");
    JMenuItem openFile=new JMenuItem("open");
    JMenuItem saveFile=new JMenuItem("save");
    JMenuItem print=new JMenuItem("print");
    JMenuItem exit=new JMenuItem("exit");

    JMenuItem cut =new JMenuItem("cut");
    JMenuItem copy=new JMenuItem("copy");
    JMenuItem paste=new JMenuItem("paste");
    JMenuItem selectall= new JMenuItem("selectall");

JTextArea textArea=new JTextArea();




     mynotepad(){
        setTitle("cool notepad");
        setBounds(100,100,700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
   JScrollPane scrollpane=new JScrollPane(textArea);

textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
textArea.setLineWrap(true);
textArea.setWrapStyleWord(true);
        add(scrollpane);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        paste.addActionListener(this);


        setVisible(true);

    }


    public static void main(String[]args)
    {

        new mynotepad();
    }


    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equalsIgnoreCase("New")){textArea.setText(null);}
        else if(e.getActionCommand().equalsIgnoreCase("open")){

            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action=fileChooser.showOpenDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader,null);
                }catch (IOException ep){
                    ep.printStackTrace();
                }

            }

        }
        else if(e.getActionCommand().equalsIgnoreCase("save")){


            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action=fileChooser.showSaveDialog(null);
if(action!=JFileChooser.APPROVE_OPTION){ return;}
else {
    String fileNAME=fileChooser.getSelectedFile().getAbsolutePath().toString();
    if(fileNAME.contains(".txt"))
        fileNAME=fileNAME+".txt";
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNAME));
        textArea.write(writer);
    }catch (IOException ep){
        ep.printStackTrace();
    }
}


        }
        else if(e.getActionCommand().equalsIgnoreCase("print")){
            try {
                textArea.print();
            }
            catch (PrinterException ex){
                Logger.getLogger(mynotepad.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        else if(e.getActionCommand().equalsIgnoreCase("exit")){
            System.exit(0);
        }
        else if(e.getActionCommand().equalsIgnoreCase("cut")){

            textArea.cut();

        }
        else if(e.getActionCommand().equalsIgnoreCase("copy")){
            textArea.copy();
        }
        else if(e.getActionCommand().equalsIgnoreCase("paste")){
            textArea.paste();
        }
        else if(e.getActionCommand().equalsIgnoreCase("select All")){
            textArea.selectAll();
        }


    }

}

