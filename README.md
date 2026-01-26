# 🍜 Noodle Machine Middleware

## 🎯 Goal
Design and implement a **middleware** between the `OrderDesk` (front-end) and the `NoodleMachine` interface.  
The middleware must translate meal orders into preparation commands for the noodle machine.

---

## 🧠 Task Description

Implement a middleware layer (the `NoodleMachineController`) that connects the `OrderDesk` and the `NoodleMachine`.

1. The controller must **interpret each noodle dish type** and send the correct preparation command to the noodle machine.

2. The configuration for each dish (e.g. ramen, spaghetti, chow mein) should not be hard-coded directly in the controller.

3. Use **creational design patterns** to build a flexible and extensible design.

4. Each noodle type should define:

   - **Noodle mass (g)**
   - **Water volume (ml)**
   - **Broth volume (ml)**
   - **Vegetable mass (g)**

5. Send the command to the machine in the format:
   ```
   "<noodleMass>g <water>ml <broth>ml <vegetableMass>g"
   ```

7. Each region-specific factory must return versions of noodle dishes with **region-dependent ingredient values**  
   (e.g. Japan = rich broth ramen, Italy = less broth, more noodles, etc.).

---

## 🌍 Regional Recipe Table

| Region  | Ramen (N/W/B/V) | Spaghetti (N/W/B/V) | Chow Mein (N/W/B/V) | Notes |
|----------|----------------|--------------------|--------------------|--------|
| **Italy** 🇮🇹   | 100g / 250ml / 150ml / 40g | 150g / 100ml / 50ml / 30g | 120g / 150ml / 40ml / 30g | Classic pasta balance |
| **India** 🇮🇳   | 120g / 300ml / 150ml / 70g | 140g / 120ml / 60ml / 60g | 130g / 100ml / 70ml / 80g | Spicy & colorful |

---

## 📝 New task for structural design patterns

### ✔ 1. Add Toppings Support
Extend the coffee order logic so a user can request toppings, for example:
```
ramen sesame tofu
```
The list of toppings:
- **Sesame**
- **Tofu**
- **Chili**

Toppings can be combined

### ✔ 2. Maintain Backward Compatibility

Old connector: must still work exactly as before and support topping functionality

New connector: must fully support toppings

Your implementation must ensure the system can work with either connector without breaking existing functionality.

### ✔ 3. ☕ NewNoodleMachineConnector – Overview

`NewNoodleMachineConnector` is a connector class that simulates communication with a noodle machine device.
It implements the `NoodleMachineV55` interface and provides a controlled workflow for interacting with the machine.

Typical lifecycle:
```
1. getToken()
2. openSession(token)
3. makeNoodle(token, session, "120g 400ml 250ml 50g chili")
4. closeSession(token, session)
```
The connector supports the following operations:

**Requesting a token** – retrieves a unique authentication token for connector.

**Opening a session** – establishes a session using the provided token.

**Preparing noodle** – performs a simulated noodle preparation within an active session.

**Closing the session** – gracefully ends the active session.

Additionally, the connector implements strict validation rules to ensure proper usage:

Only one session can be open at any time

This behavior mimics real-world external device integrations where authentication, session control, and state validation are required.

---

## 📝 New task for behavioral design patterns p.2

### 🧠 New Task – Order Events & Listeners (Observer Pattern)
#### 🎯 Goal

Extend the ordering system, so it can react to order completion events without changing the existing order processing logic.

**Use the Observer (Listener) design pattern.**

#### 🧩 Core Idea

When an order is successfully prepared, or fails during processing the system must notify registered listeners.

#### ✔ Required Listener Implementations

##### 📚 1. OrderHistoryListener

Responsibility:
Store the history of all processed orders. History must include both successful and failed orders.

##### 🔔 2. NotificationListener

Responsibility:
Simulate user notifications. Output to console is enough.

## 🧠 Task – Order History Processing (Visitor Pattern)

### 🎯 Goal
Extend the order history subsystem so that **multiple independent operations** can be performed on stored order history **without modifying the order or history classes**.

Use the **Visitor design pattern**.

### 🧩 Core Idea

The system already stores a list of processed orders in `OrderHistory`.

New requirements appear:
- calculate statistics
- generate reports

Instead of:
- adding methods to `Order`
- or adding `if` / `switch` logic in `OrderHistory`

use the **Visitor pattern** to add new behaviors **without changing existing classes**.

### ✔ Required Visitor Implementations
#### 📊 1. StatisticsVisitor

Responsibility:

count total orders

count successful vs failed orders

Discounts usage

Calculate total revenue

Average order price

Anything that seems interesting to you


#### 📝 2. ReportVisitor

Responsibility:

generate a human-readable report from order history

Example output:

```
Orders processed: 42
Successful: 38
Failed: 4

Topping usage: 
-- // -- : 12
-- // -- : 4
```

📌 Notes:

Output to console or string is sufficient
