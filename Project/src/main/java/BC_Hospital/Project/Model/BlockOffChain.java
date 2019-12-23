package BC_Hospital.Project.Model;

import java.sql.Blob;

import javax.persistence.*;

// Pojo (plain old Java object) là class đại diện cho một Table

@Entity // Đánh dấu đây là một Entity, chịu sự quản lý của Hibernate
@Table(name = "offchain") //Entity này đại diện cho table BlockOffChain trong db
public class BlockOffChain {

	@Id // Đánh dấu biến ở dưới là primary key của table này
	@Column(name = "hashfile", unique = true)
	private String hashfile;
	
	// Trường data ở dưới đại diện cho cột data của table trong database
	@Column(name = "file", unique = true, columnDefinition = "MEDIUMBLOB")
	private Blob file;
	
	public BlockOffChain() {}
	
	public BlockOffChain(String hashfile, Blob file) {
		this.hashfile = hashfile;
		this.file = file;
	}

	public String getHashfile() {
		return hashfile;
	}

	public void setHashfile(String hashfile) {
		this.hashfile = hashfile;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

}
