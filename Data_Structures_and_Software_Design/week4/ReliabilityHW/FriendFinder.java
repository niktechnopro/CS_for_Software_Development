import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*
 * SD2x Homework #10
 * Modify the method below so that it uses defensive programming.
 * Please be sure not to change the method signature!
 */

public class FriendFinder {

    protected ClassesDataSource classesDataSource;
    protected StudentsDataSource studentsDataSource;

    public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
        classesDataSource = cds;
        studentsDataSource = sds;
    }


    public Set<String> findClassmates(Student theStudent) {

        if (theStudent == null) {
            throw new IllegalArgumentException("Student is null");
        }

        String name = theStudent.getName();
        if (name == null) {
            throw new IllegalArgumentException("Student name is null");
        }

        // find the classes that this student is taking
        if (classesDataSource == null) {
            throw new IllegalStateException("ClassesDataSource is null.");
        }

        List<String> myClasses = classesDataSource.getClasses(name);

        if (myClasses == null) return null;

        Set<String> classmates = new HashSet<String>();

        // use the classes to find the names of the students
        if (studentsDataSource == null) {
            throw new IllegalStateException ("StudentsDataSource is null.");
        }
        for (String myClass : myClasses) {
            // list all the students in the class
            List<Student> students = studentsDataSource.getStudents(myClass);
            if (students == null) continue;

            for (Student student : students) {
                if (student == null || student.getName() == null) continue;

                // find the other classes that they're taking
                List<String> theirClasses = classesDataSource.getClasses(student.getName());
                if (theirClasses == null) continue;

                // see if all of the classes that they're taking are the same as the ones this student is taking
                boolean same = true;
                for (String c : myClasses) {

                    if (theirClasses.contains(c) == false) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    if (student.getName().equals(name) == false && classmates.contains(student.getName()) == false)
                        classmates.add(student.getName());
                }
            }

        }

        if (classmates.isEmpty()) {
            return null;
        }
        else return classmates;
    }


}