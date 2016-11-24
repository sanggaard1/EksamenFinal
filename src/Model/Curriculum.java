package Model;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Curriculum {

    String school;
    String education;
    int semester;
    int curriculumID;

    public Curriculum() {

    }
    //Constructor
    public Curriculum(String school, String education, int semester, int curriculumID) {
        this.school = school;
        this.education = education;
        this.semester = semester;
        this.curriculumID = curriculumID;
    }
    public Curriculum(String school, String education, int semester) {
        this.school = school;
        this.education = education;
        this.semester = semester;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }
}