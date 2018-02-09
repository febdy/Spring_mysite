package com.javaex.vo;

public class FileVo {
	private int no;
	private String orgName;
	private String exName;
	private String saveName;
	private String saveDir;
	private String filePath;
	private long fileSize;
	private String regDate;
	private int userNo;
	private String userName;

	FileVo() {

	}

	public FileVo(int no, String orgName, String exName, String saveName, String saveDir, String filePath,
			long fileSize, String regDate, int userNo, String userName) {
		super();
		this.no = no;
		this.orgName = orgName;
		this.exName = exName;
		this.saveName = saveName;
		this.saveDir = saveDir;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.regDate = regDate;
		this.userNo = userNo;
		this.userName = userName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "FileVo [no=" + no + ", orgName=" + orgName + ", exName=" + exName + ", saveName=" + saveName
				+ ", saveDir=" + saveDir + ", filePath=" + filePath + ", fileSize=" + fileSize + ", regDate=" + regDate
				+ ", userNo=" + userNo + ", userName=" + userName + "]";
	}

}
