# ❌⭕ Tic Tac Toe — Java Swing

![Status](https://img.shields.io/badge/status-finished-brightgreen)
![Version](https://img.shields.io/badge/version-1.0-blue)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A classic **Tic Tac Toe** game implemented in **Java 8 + Swing**, featuring two gameplay modes:

- **Player vs Player (PvP)**
- **Player vs AI (PvAI)** using a **custom Minimax-based algorithm**

The project demonstrates clean **MVC architecture**, separation of concerns, and a fully deterministic AI opponent.

---

## 🚀 Features

- ✔️ Two game modes: **PvP** and **PvAI**
- ✔️ AI powered by a **custom Minimax implementation**
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
- `AIController.java` — integrates AI decisions
- `AIMinimax.java` — Minimax algorithm implementation

---

## 🧠 How the AI Works — Custom Minimax Implementation

The AI opponent uses a **custom Minimax-based algorithm** implemented in `AIMinimax.java`.  
Although inspired by the classic Minimax approach, this version includes several unique design choices tailored to the project.

### 🎯 Board Representation

The board is stored as a **1D array of 9 integers**:

- `0` → empty field  
- `1` → move made by **Player X**  
- `-1` → move made by **AI (Player O)**  

This simplifies copying and simulating board states.

---

### 🔍 Overview of the Algorithm

When it's the AI’s turn, the method:

```java
AIMinimax.chooseFieldForAI(int[] boardFields)
```

evaluates **every empty field** and assigns it a score.  
The AI then selects the field with the **highest score**.

The scoring process is recursive and explores all possible future moves until the board is full.

---

### 🧮 Scoring Logic

#### **1. Terminal states**
If the simulated board results in a win:

```
score = (MAX_DEPTH - depth + 1)
```

- If the **current player is X**, the score is **positive**  
- If the **current player is O (AI)**, the score is **negative**

This means:

- **Faster wins are better**
- **Slower losses are less bad**

#### **2. Occupied fields**
Illegal moves receive a strong penalty:

```
-99
```

This ensures the AI never selects an invalid field.

#### **3. Recursive evaluation**
For each empty field:

- The algorithm simulates placing the current player's mark  
- Recursively evaluates the resulting board  
- Switches the player (`Player.X` ↔ `Player.O`)  
- Increases recursion depth  

#### **4. Minimizing vs maximizing**
Your implementation uses an inversion compared to classic Minimax:

- When **Player X** is the current player → algorithm **minimizes**  
- When **AI (Player O)** is the current player → algorithm **maximizes**  

This works because the sign of the score is flipped depending on the winner.

---

### 🧠 Choosing the Best Move

After all fields are scored, the AI selects the **index with the highest score**:

```java
AIMinimax.findBestFieldIndex(int[] scores)
```

If multiple fields have the same score:

- The **center field** (index 4) is preferred  
- Otherwise, the **first matching field** is chosen  

This gives the AI a natural preference for strong opening moves.

---

### 🧩 Summary of AI Behavior

Your AI:

- Explores all possible game states (full-depth search)
- Evaluates wins/losses with depth-based scoring
- Penalizes illegal moves
- Alternates minimizing/maximizing depending on the simulated player
- Prefers the center when scores are equal
- Plays **perfectly** — cannot be beaten

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
