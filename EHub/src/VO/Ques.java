package VO;

import java.io.Serializable;

public class Ques implements Serializable{
	
	  private String ques;
	  private Exam exam_id; 
	  private long quesid;
	  private String ans1;
	  private String ans2;
	  private String ans3;
	  private String ans4;
	  private String correctAns;
	  private long marks;
	  
	  public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public Exam getExam_id() {
		return exam_id;
	}
	public void setExam_id(Exam exam_id) {
		this.exam_id = exam_id;
	}
	public long getQuesid() {
		return quesid;
	}
	public void setQuesid(long quesid) {
		this.quesid = quesid;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public String getAns4() {
		return ans4;
	}
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	public long getMarks() {
		return marks;
	}
	public void setMarks(long marks) {
		this.marks = marks;
	}
	
}