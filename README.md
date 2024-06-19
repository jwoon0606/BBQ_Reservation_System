# :meat_on_bone: BBQ Reservation System
<p align="center">
<img src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/MainFrame.png" height="500" width="400">
</p>

## 1. Project Description
- This is a BBQ seat reservation management program for Handong University.
- Users can make reservations for their desired dates.
- Users can view existing reservations.

## 2. Project Members
|                                Minsuk Lee (22100504)                                 |                               Jongwoon Nam (22000220)                                |
|:-----------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------:|
| <img src="https://github.com/Glen02lee.png" alt="Profile Image" width="200" height="200"> | <img src="https://github.com/jwoon0606.png" alt="Profile Image" width="200" height="200"> |
|                     [@Glen02lee](https://github.com/Glen02lee)                      |                     [@jwoon0606](https://github.com/jwoon0606)                      |

## 3. Development Environment
- Front-end: Java GUI (AWT, Swing)
- Back-end: Java
- Version Control: GitHub

## 4. Development Period
### Timeline
- Overall development period: May 15, 2024 - June 6, 2024
- GUI implementation: May 15, 2024 - June 6, 2024
- Feature implementation: May 15, 2024 - June 1, 2024

### Project Management
- Regular offline meetings were held for project discussion and updates.

## 5. UML Diagram of Classes and Interfaces
*(The UML diagram would be included here.)*

## 6. User's Guide

### [Select Date]
- Users can choose the desired month from the displayed calendar.
  + Use the "<" ">" buttons to navigate through months.

|                                                  Month Navigation                                                  |
|:---------------------------------------------------------------------------------------------------------------:|
| <img width="532" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/MainFrame.png"> |

- Users can select the desired date.
  + Dates can be selected with a mouse click.
  + A reservation window will pop up.

|                                                   Date Selection                                                   |                                                          Reservation Window                                                           |
|:----------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|
| <img width="532" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ChooseDate.png"> | <img width="532" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ChooseDateReservation.png"> |

<br>

### [Reservation]
- Users can make reservations for the selected date.
- Some required information must be provided.
  + Name, phone number, seat number, and time are required.

|                                                         Reservation Success                                                        |
|:----------------------------------------------------------------------------------------------------------------------------------:|
| <img width="535" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ReservationSuccess.png"> |

- The name field cannot contain numbers.

|                                                               Reservation Error (1)                                                               |
|:--------------------------------------------------------------------------------------------------------------------------------------------------:|
| <img width="535" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ReservationError_NameContainsNumber.png"> |

- The phone number must follow the format 010-0000-0000.

|                                                                Reservation Error (2)                                                                |
|:----------------------------------------------------------------------------------------------------------------------------------------------------:|
| <img width="535" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ReservationError_PhoneNumberForm.png"> |

- Reservations cannot be made if there is already a reservation for the same seat at the same time.

|                                                                Reservation Error (3)                                                                |
|:---------------------------------------------------------------------------------------------------------------------------------------------------:|
| <img width="535" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ReservationError_SeatDuplication.png"> |
<br>

### [View Reservation]
- Users can view current reservations by clicking the "View Reservation" button.

|                                                     View Reservations                                                      |
|:---------------------------------------------------------------------------------------------------------------------:|
| <img width="535" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/ViewReservation.png"> |
<br>

### [File I/O]
- Reservation details are saved in the "reservations.txt" file.

|                                                         File I/O                                                         |
|:----------------------------------------------------------------------------------------------------------------------:|
| <img width="600" src="https://github.com/jwoon0606/JavaProgrammingProject/blob/main/screenshots/reservations.txt.png"> |
<br>

## 7. Reflections
### Jongwoon Nam (jwoon0606)
- Collaboration Experience
  + Gained proficiency in version control and communication through GitHub by working on a project with others.
- Java GUI Implementation
  + Gained experience using AWT and Swing classes.

### Minsuk Lee (Glen02lee)
- Reflections
  + [Insert personal reflections here]