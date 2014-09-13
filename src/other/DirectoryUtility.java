package other;


import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class DirectoryUtility {
    
    public boolean createDir(File file) throws IOException{
        boolean success = file.mkdir();
        System.out.println(file.getCanonicalPath() + " Create. ==> " + success);
        return success;
    }
    
    public boolean deleteDir(File file) throws IOException{
        boolean success = file.delete();
        System.out.println(file.getCanonicalPath() + " Delete. ==> " + success);
        return success;
    }
    
     public File getFileAndCreateDir(String pathFile) throws Exception {
        StringTokenizer st = new StringTokenizer(pathFile.trim(),"/");
        String path = "";
        File f = null;        
        //System.out.println("Token "+st.countTokens());
        int countToken = st.countTokens(); 
        for(int i=0; st.hasMoreTokens(); i++){
            if (i < countToken-1) {
                path += "/" + st.nextToken();
                f = new File(path);
                if (!f.exists()) 
                    createDir(f);                
                //System.out.println(i + " , " + path);
            }else{
                st.nextToken();
            }
        }               
        File file = new File(pathFile);        
        //System.out.println(file.getCanonicalPath() + " === " + file.exists());
        return file;
    }
     
     public Vector<File> getFilesInDirectory(String path){
        Vector<File> file = new Vector<File>();
        try{
            File pathName = new File(path);
            System.out.println("Path "+pathName.getPath());
            String[] fileNames = pathName.list();
            for(int i=0; i < fileNames.length;i++){
                File f = new File(pathName.getPath(),fileNames[i]);
                if(f.isDirectory()){
                    
                }else if(f.isFile()){
                    file.add(f.getCanonicalFile());
                    //System.out.println(f.getCanonicalPath());
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage()+" getFileInDi..");
            e.printStackTrace();
        }
        return file;
    }
     
     
     public Vector<File> getAllFilesInDirectory(String path){
         files  = new Vector<File>();
         findAllFilesInDirectory(path);
         return files;
     }
     private Vector<File> files;
     private void findAllFilesInDirectory(String path){
        try{
            File pathName = new File(path);
            String[] fileNames = pathName.list();
            for(int i=0; i < fileNames.length;i++){
                File f = new File(pathName.getPath(),fileNames[i]);
                if(f.isDirectory()){
                    findAllFilesInDirectory(f.getPath());
                }else if(f.isFile()){
                    files.add(f.getCanonicalFile());
                    //System.out.println(f.getCanonicalPath());
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "findAllFiles... "+e.getMessage());
            e.printStackTrace();
        }
    }
}
