package com.medical.common;

import java.io.File;
import java.io.FileFilter;

public class FilesFilter implements FileFilter {

	private String extension;

	public FilesFilter(String extension) {
		this.setExtension(extension);
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return false;
		}
		String name = file.getName();
		int index = name.lastIndexOf(".");
		if (index == -1) {
			return false;
		} else if (index == name.length() - 1) {
			return false;
		} else {
			name = name.toLowerCase();
			return extension.equals(name.substring(index + 1));
		}
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
}
