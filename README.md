# ❌⭕ Tic Tac Toe — Java Swing

![Status](https://img.shields.io/badge/status-complete-brightgreen)
![Version](https://img.shields.io/badge/version-1.0-blue)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A classic **Tic Tac Toe** game implemented in **Java 8 + Swing**, featuring two gameplay modes:

- **Player vs Player (PvP)**
- **Player vs AI (PvAI)** using the **Minimax algorithm**

The project demonstrates clean **MVC architecture**, separation of concerns, and basic game AI logic.

---

## 🚀 Features

- ✔️ Two game modes: **PvP** and **PvAI**
- ✔️ AI powered by **Minimax** (perfect play)
- ✔️ GUI built with **Java Swing**
- ✔️ Clear MVC structure
- ✔️ Win/draw detection
- ✔️ Unit tests (JUnit + AssertJ + Mockito)

---

## 🧱 Architecture (MVC)

### **Model**
- `Model.java` — game state (board, current player)
- `Player.java` — player representation
- `GameStatusChecker.java` — win/draw detection logic

### **View**
- `StartView.java` — start menu
- `BoardView.java` — 3×3 game board UI

### **Controller**
- `StartController.java` — handles mode selection
- `BoardController.java` — shared board logic
- `BoardControllerInPvPMode.java` — PvP controller
- `BoardControllerInPvAIMode.java` — PvAI controller
- `AIController.java` — AI move handling
- `AIMinimax.java` — Minimax algorithm implementation

---

## 🧠 AI — Minimax Algorithm

The AI uses a classic **Minimax** strategy:

- Evaluates all possible moves  
- Simulates outcomes  
- Chooses the optimal move  
- Plays **perfectly** (cannot be beaten)

This makes the AI a great demonstration of deterministic game-tree search.

---

## 📁 Project Structure

```
src/
 ├── main/
 │   └── java/com/bielinski/ticTacToe/
 │       ├── AIController.java
 │       ├── AIMinimax.java
 │       ├── BoardController.java
 │       ├── BoardControllerInPvAIMode.java
 │       ├── BoardControllerInPvPMode.java
 │       ├── BoardView.java
 │       ├── GameMode.java
 │       ├── GameStatusChecker.java
 │       ├── Model.java
 │       ├── Player.java
 │       ├── StartController.java
 │       └── StartView.java
 └── test/
     └── java/com/bielinski/ticTacToe/
         ├── AIMinimaxTest.java
         ├── GameStatusCheckerTest.java
         └── PlayerTest.java
```

---

## 🛠️ Technologies Used

- **Java 8**
- **Swing**
- **Maven**
- **JUnit 4**
- **AssertJ**
- **Mockito**

---

## ▶️ How to Run

### **Clone the repository**
```bash
git clone https://github.com/BielinskiLukasz/ticTacToe.git
cd ticTacToe
```

### **Build the project**
```bash
mvn clean install
```

### **Run the game**
```bash
mvn exec:java -Dexec.mainClass="com.bielinski.Main"
```

Or run `Main.java` directly from your IDE.

---

## 🧪 Running Tests

```bash
mvn test
```

Includes tests for:

- AI logic (`AIMinimaxTest`)
- Win/draw detection (`GameStatusCheckerTest`)
- Player model (`PlayerTest`)

---

## 📌 Project Status

The project is **complete** and stable.  
Serves as a demonstration of Swing UI, MVC, and Minimax-based AI.

---

## 🤝 Contributing

Ideas and improvements are welcome.  
Possible extensions:

- Difficulty levels for AI  
- Animated UI  
- Larger board sizes (5×5, 10×10)  
- Score tracking  

---

## 📄 License

Released under the **MIT License**.
