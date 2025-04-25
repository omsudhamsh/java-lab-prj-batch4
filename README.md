# Online Exam Registration System

A Java-based console application for managing exam registrations for students and administrative tasks for admins.

## Features

### For Students
- Register for exams
- View available exams
- Check registration status
- View exam results

### For Administrators
- Create and manage exams
- View registered students
- Update exam details
- Generate reports

## Getting Started

### Prerequisites
- Java JDK 17 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/online-exam-registration.git
   ```
2. Navigate to the project directory
   ```bash
   cd online-exam-registration
   ```
3. Compile the project
   ```bash
   javac -d bin online_exam_registration/*.java model/*.java util/*.java
   ```
4. Run the application
   ```bash
   java -cp bin Main
   ```

## Project Structure
```
online_exam_registration/
├── model/
│   ├── User.java
│   ├── Student.java
│   ├── Admin.java
│   └── Exam.java
├── util/
│   └── InputUtil.java
└── Main.java
```

## Usage
1. Start the application
2. Choose your role (Student or Admin)
3. Enter your credentials
4. Use the menu options to perform various actions

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
- Thanks to all contributors who have helped with this project
- Inspired by the need for efficient exam registration systems