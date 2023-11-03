package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
class Student {
    private String name;
    private List<Double> grades;
    private String specialty;


    public <E> Student(String name, List<E> grades, String specialty) {
        this.name = name;
        this.grades = (List<Double>) grades;
        this.specialty = specialty;
    }

    public double getAverageGrade() {
        return grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public String getName() {
        return this.name;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Student> students = createStudentsList(); // Создаем список студентов
        List<Student> filteredStudents = students.stream()
                .filter(student -> student.getSpecialty().equals("Информатика")) // Фильтруем по специальности "Информатика"
                .filter(student -> student.getAverageGrade() > 4.5) // Фильтруем по среднему баллу выше 4.5
                .sorted(Comparator.comparing(Student::getAverageGrade).reversed()) // Сортируем по убыванию среднего балла
                .limit(5) // Берем первые 5 студентов
                .collect(Collectors.toList());
        filteredStudents.forEach(student -> System.out.println(student.getName()));
    }
    private static List<Student> createStudentsList() {
        List<Student> students = List.of(
                new Student("Иван", List.of(4.8, 4.9, 5.0), "Информатика"),
                new Student("Петр", List.of(4.5, 4.6, 4.7), "Математика"),
                new Student("Алексей", List.of(4.0, 4.2, 4.4), "Информатика"),
                new Student("Мария", List.of(4.7, 4.8, 4.9), "Информатика"),
                new Student("Елена", List.of(3.9, 4.1, 4.3), "Информатика"),
                new Student("Дмитрий", List.of(4.6, 4.7, 4.8), "Информатика"),
                new Student("Ольга", List.of(4.3, 4.4, 4.5), "Математика")
        );
        return students;
    }
}