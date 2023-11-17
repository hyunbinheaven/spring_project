package com.food.exp.dto;

import org.apache.ibatis.type.Alias;

@Alias("FileDTO")
public class FileDTO {

    String uploadFileName;  
    String uploadFilePath;  
    int rev_no;
    String rst_id;

	public FileDTO(String uploadFileName, String uploadFilePath, int rev_no, String rst_id) {
		this.uploadFileName = uploadFileName;
		this.uploadFilePath = uploadFilePath;
		this.rev_no = rev_no;
		this.rst_id = rst_id;	
	}
    public FileDTO() {  }

    public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFilePath() {
        return uploadFilePath;
    }
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

    public int getRev_no() {
        return rev_no;
    }
	public void setRev_no(int rev_no) {
		this.rev_no = rev_no;
	}

	public String getRst_id() {
		return rst_id;
	}

	public void setRst_id(String rst_id) {
		this.rst_id = rst_id;
	}

	@Override
	public String toString() {
		return "FileDTO [uploadFileName=" + uploadFileName + ", uploadFilePath=" + uploadFilePath + ", rev_no=" + rev_no
				+ ", rst_id=" + rst_id + "]";
	}

	// 빌더 클래스
    public static class Builder {
        private String uploadFileName;
        private String uploadFilePath;
        private int rev_no;

        public Builder uploadFileName(String uploadFileName) {
            this.uploadFileName = uploadFileName;
            return this;
        }

        public Builder uploadFilePath(String uploadFilePath) {
            this.uploadFilePath = uploadFilePath;
            return this;
        }

        public Builder rev_no(int rev_no) {
            this.rev_no = rev_no;
            return this;
        }

        // FileDTO 객체를 빌드하고 반환합니다.
        public FileDTO build() {
            FileDTO fileDTO = new FileDTO();
            fileDTO.uploadFileName = this.uploadFileName;
            fileDTO.uploadFilePath = this.uploadFilePath;
            fileDTO.rev_no = this.rev_no;
            return fileDTO;
        }
    }

    // 빌더 인스턴스를 생성하는 정적 메서드를 제공합니다.
    public static Builder builder() {
        return new Builder();
    }
}