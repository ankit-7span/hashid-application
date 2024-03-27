package com.spingboot.hashid.service;

import com.spingboot.hashid.entity.Enrollment;
import com.spingboot.hashid.entity.Student;
import com.spingboot.hashid.models.StudentCourseDetailsDTO;
import com.spingboot.hashid.request.StudentRequestDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private EntityManager entityManager;

    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }

    public StudentCourseDetailsDTO getStudentCourseDetails(Long studentId) {
        List<Enrollment> enrollments = entityManager.createQuery(
                        "select e from Enrollment e join fetch e.student join fetch e.course where e.student.id = :studentId",
                        Enrollment.class)
                .setParameter("studentId", studentId)
                .getResultList();

        int totalCoursesEnrolled = enrollments.size();
        int totalCreditsEnrolled = enrollments.stream().mapToInt(e -> e.getCourse().getCredits()).sum();

        Student student = entityManager.find(Student.class, studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }

        return new StudentCourseDetailsDTO(studentId, student.getName(), (long) totalCoursesEnrolled,(long) totalCreditsEnrolled);
    }

    public StudentCourseDetailsDTO getStudentCourseDetailsByFunction(Long studentId) {
        List<Object[]> resultList = entityManager.createNativeQuery(
                "select * from student.get_student_course_details(:studentId)")
                .setParameter("studentId",studentId).getResultList();

        if (resultList.isEmpty()) {
            throw new IllegalArgumentException("No data found for student with ID: " + studentId);
        }

        Object[] result = resultList.get(0);
        Long retrievedStudentId = (Long) result[0];
        String studentName = (String) result[1];
        Long totalCoursesEnrolled = (Long) result[2];
        Long totalCreditsEnrolled = (Long) result[3];

        return new StudentCourseDetailsDTO(retrievedStudentId, studentName, totalCoursesEnrolled, totalCreditsEnrolled);
    }

    public void saveStudent(StudentRequestDto studentRequestDto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new Student(null, studentRequestDto.name(), studentRequestDto.email()));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to save student: " + e.getMessage());
        }
    }

    public void updateStudent(StudentRequestDto studentRequestDto, Long studentId) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, studentId);
            if (student != null) {
                student.setEmail(studentRequestDto.email());
                student.setName(studentRequestDto.name());
                entityManager.merge(student);
                entityManager.getTransaction().commit();
            } else {
                throw new RuntimeException("Student not found with ID: " + studentId);
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to update student: " + e.getMessage());
        }
    }

    public void removeStudent(Long id) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                entityManager.remove(student);
                entityManager.getTransaction().commit();
            } else {
                throw new RuntimeException("Student not found with ID: " + id);
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to remove student: " + e.getMessage());
        }
    }
}
