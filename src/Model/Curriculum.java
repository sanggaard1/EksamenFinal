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
}
