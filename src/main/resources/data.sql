-- 🔥 1. COLLAGE (Parent)
INSERT INTO collage (enstablishedyear, id, code, emailclg, name) VALUES
(1941, 1, 'DTU001', 'info@dtu.ac.in', 'Delhi Technical University'),
(1961, 2, 'IITD002', 'contact@iitd.ac.in', 'Indian Institute of Technology Delhi'),
(1983, 3, 'NSUT003', 'info@nsut.ac.in', 'Netaji Subhas University'),
(1969, 4, 'JNU004', 'admin@jnu.ac.in', 'Jawaharlal Nehru University');
-- 🔥 2. TEACHER (Parent)
INSERT INTO teacher (id, name, email, spacelization, collage_id) VALUES
    (1, 'Amit Sharma', 'amit.sharma@gmail.com', 'Computer Science', 1),
    (2, 'Priya Verma', 'priya.verma@gmail.com', 'Mathematics', 1),
    (3, 'Rahul Singh', 'rahul.singh@gmail.com', 'Physics', 2),
    (4, 'Neha Gupta', 'neha.gupta@gmail.com', 'Chemistry', 2),
    (5, 'Sandeep Yadav', 'sandeep.yadav@gmail.com', 'Mechanical', 3);


-- 🔥 3. COURSE (depends on teacher + collage)
INSERT INTO course (id, name, course_code, teacher_id, collage_id) VALUES
(101, 'Java', 'J101', 1, 1),
(102, 'Python', 'P102', 2, 2),
(103, 'C++', 'C103', 3, 3),
(104, 'Mechanical', 'M104', 1, 1);


-- 🔥 4. STUDENT (depends on course + collage)
INSERT INTO student (id, name, email, phone_number, branch, course_id, collage_id) VALUES
(1, 'Ankit Jain', 'ankit.jain@example.com', '9876543215', 'CSE', 101, 1),
(2, 'Simran Kaur', 'simran.kaur@example.com', '9876543216', 'IT', 102, 2),
(3, 'Vikas Yadav', 'vikas.yadav@example.com', '9876543217', 'ECE', 103, 3),
(4, 'Pooja Sharma', 'pooja.sharma@example.com', '9876543218', 'ME', 104, 1),
(5, 'Karan Malhotra', 'karan.malhotra@example.com', '9876543219', 'CE', 101, 2);


-- 🔥 5. ATTENDANCE (depends on student)
INSERT INTO attendance (id, student_id, rollnumber, status) VALUES
(1, '1', 'R001', 'PRESENT'),
(2, '2', 'R002', 'ABSENT'),
(3, '3', 'R003', 'PRESENT');


-- 🔥 6. MANY-TO-MANY (JOIN TABLE)
INSERT INTO attendance_course (attendance_id, course_id) VALUES
(1, 101),
(1, 102),
(2, 102),
(3, 103);