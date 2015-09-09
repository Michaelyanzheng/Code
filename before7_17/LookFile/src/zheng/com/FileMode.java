package zheng.com;

import java.io.File;

public class FileMode {
	
	private File file;
	
	public FileMode(File f){
		this.file = f;
	}
	
	public File getFile(){
		return this.file;
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s", file.isDirectory()?"Dir":"File",file.getName());
	}
	

}
