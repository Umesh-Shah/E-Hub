package VO;

import java.io.Serializable;

public class Exam implements Serializable{

		  private String examName;
		  private String examDes;
		  private Long exam_id;
		  private Long marks;
		  private Long duration;
		  private Long no_ques;

		  public void setNo_ques(Long no_ques) {
			this.no_ques = no_ques;
		}
		public String getExamName() {
			return examName;
		}
		public Long getNo_ques() {
			return no_ques;
		}
		public void setExamName(String examName) {
			this.examName = examName;
		}
		public String getExamDes() {
			return examDes;
		}
		public void setExamDes(String examDes) {
			this.examDes = examDes;
		}
		public Long getExam_id() {
			return exam_id;
		}
		public void setExam_id(Long exam_id) {
			this.exam_id = exam_id;
		}
		public Long getMarks() {
			return marks;
		}
		public void setMarks(Long marks) {
			this.marks = marks;
		}
		public Long getDuration() {
			return duration;
		}
		public void setDuration(Long duration) {
			this.duration = duration;
		}
		
		}
