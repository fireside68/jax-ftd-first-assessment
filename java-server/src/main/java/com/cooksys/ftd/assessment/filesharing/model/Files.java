package com.cooksys.ftd.assessment.filesharing.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Files {
	
	private int fileID;
	private String filepath;
	private byte[] fileContents;
	public Files() {
		super();
	}
	public Files(int fileID, String filepath, byte[] fileContents) {
		this.fileID = fileID;
		this.filepath = filepath;
		this.fileContents = fileContents;
	}
	@XmlAttribute
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	@XmlElement
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@XmlElement
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fileContents);
		result = prime * result + fileID;
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Files other = (Files) obj;
		if (!Arrays.equals(fileContents, other.fileContents))
			return false;
		if (fileID != other.fileID)
			return false;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Files [fileID=" + fileID + ", filepath=" + filepath + ", fileContents=" + Arrays.toString(fileContents)
				+ "]";
	}
	
	
}
