# University Student Record and Campus Route Management System

## Project Overview and Objective

This is a Java console application built for a Data Structures and Algorithms course assignment. It manages university student records and campus navigation routes by implementing six core data structures **from scratch** (without using `java.util.LinkedList`, `Stack`, `Queue`, or `HashMap` for those structures):

- Singly Linked List — primary student record store
- Stack — audit log of add/update/delete actions
- Queue — student service desk requests (FIFO)
- Binary Search Tree — students sorted by ID
- Hash Table (chaining) — fast student lookup by ID
- Undirected Graph (adjacency list) — campus locations and roads, with BFS/DFS

A coordinating service keeps the linked list, BST, and hash table synchronised on every student CRUD operation.

## How to Compile and Run

Requirements: JDK 8 or later (`javac` / `java` on your PATH).

From the project root (`DS System`):

```bash
javac -d out src/main/model/*.java src/main/datastructures/*.java src/main/service/*.java src/main/util/*.java src/main/Main.java
java -cp out Main
```

On Windows PowerShell you can use the same commands.

## Project Structure

```
src/main/
  Main.java
  model/Student.java
  model/Location.java
  model/Action.java
  model/ServiceRequest.java
  datastructures/StudentLinkedList.java
  datastructures/ActionStack.java
  datastructures/ServiceQueue.java
  datastructures/StudentBST.java
  datastructures/StudentHashTable.java
  datastructures/CampusGraph.java
  service/StudentRecordService.java
  util/InputValidator.java
README.md
```

## Data Structures and Where They Are Used

| Structure | Class | Used for |
|-----------|--------|----------|
| Singly Linked List | `StudentLinkedList` | Authoritative student store; menu 1–4 |
| Stack | `ActionStack` | Log of every add/update/delete; menu 7 |
| Queue | `ServiceQueue` | Service desk FIFO; menus 5–6 |
| Binary Search Tree | `StudentBST` | Sorted display by student ID; menu 8 |
| Hash Table | `StudentHashTable` | O(1) average search by ID; menu 9 |
| Graph | `CampusGraph` | Locations/roads + BFS/DFS; menus 10–15 |

`StudentRecordService` updates the linked list, BST, hash table, and action stack together on every student mutation.

## Menu Option Reference

1. Add Student Record  
2. Update Student Record  
3. Delete Student Record  
4. Display All Records using Linked List  
5. Add Service Request to Queue  
6. Process Next Service Request  
7. Display Recent Actions using Stack  
8. Display Students using BST  
9. Search Student using Hashing  
10. Add Campus Location  
11. Remove Campus Location  
12. Add Campus Connection/Road  
13. Remove Campus Connection/Road  
14. Display Campus Connections  
15. Traverse Campus Locations using BFS or DFS (ask user which)  
16. Exit  

Input validation rejects duplicate student IDs, missing IDs on update/delete/search, marks outside 0–100, non-numeric input, duplicate location names, edges to unknown locations, and empty queue/stack operations (with friendly messages).

## Group Member Names

- Member 1: AM. Fathima Hanoof
- Member 2: AM. Fathima Rusna
- Member 3: UF. Sharafa
- Member 4: AM. Fathima Jesira 

## Student IDs

- Member 1: 23DA2-0530
- Member 2: 23DA2-0492
- Member 3: 23DA2-0529
- Member 4: 23DA2-0653

## Assigned Responsibilities

- Member 1: Graph implementation, campus locations, connections, and BFS/DFS traversal.
- Member 2: BST/AVL tree implementation and hashing/search functionality.
- Member 3: Linked list implementation and student-record management. 
- Member 4: Stack and queue implementation and related operations. 
- All Members: Integration, validation, testing, debugging, documentation, GitHub collaboration, 
and completion of the entire project. 

## Individual Contributions

- Member 1: managed the project and led the group of workers. The graph, campus locations, connections, BFS, and DFS were implemented.I did and helped with documentation, testing, integration, troubleshooting, GitHub collaboration and final project validation.
- Member 2: implemented the hashing/search and BST/AVL trees. helped with testing, integration, validation, debugging and GitHub collaboration.
- Member 3: implemented the place student record management and linked lists. helped with documentation, testing, validation, GitHub collaboration and debugging.
- Member 4: The queue and stack structures and operations were implemented into reality. helped with debugging, testing, validation, GitHub collaboration and integration.

## Collaboration

This project was developed using GitHub version control with commit,
branches, and pull requests.