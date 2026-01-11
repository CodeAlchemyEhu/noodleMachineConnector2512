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

## 📝 New task for behavioral design patterns p.1


### 🧠 Task – Order Price Calculation
Extend the noodle ordering system to **calculate the final order price** dynamically, depending on:

- noodle type
- region
- applied discount rules

Use **stratagy** pattern


#### ✔ Description

Each noodle order must be able to calculate its **base price** and then apply **one discount strategy**.


#### ✔ Regional Noodle Price Table

#### ☕ Base Noodle Prices (USD)

| Region         | Ramen | Spaghetti | Chow Mein |
|----------------|-------|-----------|-----------|
| **Italy** 🇮🇹    | $3.00 | $4.50     | $5.00     |
| **India** 🇮🇳   | $2.80 | $2.20     | $3.80     |


#### 🍯 Topping Prices (USD)

| Topping | Price |
|---------|-------|
| Sesame  | $0.50 |
| Tofu    | $0.90 |
| Chili   | $0.65 |

- Toppings can be combined
- Each topping adds its price to the base noodle price

#### ✔ Discount Strategies

Only **one discount** may be applied per order.

| Discount Type       | Rule |
|---------------------|------|
| **None**            | No discount |
| **Student** 🎓      | 20% off total price |
| **Loyalty Card** 💳 | 10% off total price |



#### ✔ Example Usage

```
student ramen sesame tofu

none spaghetti
```

### 🧠 Task – Order Processing Pipeline

#### 🎯 Goal
Refactor the order processing logic into a **step-by-step processing pipeline** where each step is responsible for **exactly one concern**.

Use **Chain of Responsibility** pattern

#### ✔ Description

Processing a noodle order involves multiple sequential actions, such as (examples):

- parsing the input string
- identifying noodle type
- applying toppings
- applying discount rules

### 🧠 Task – Noodle Machine Connector States

### 🎯 Goal
Enhance the `NoodleMachineConnector` to behave differently depending on its **internal state**, simulating a real-world unstable external device.

The connector must automatically switch between states based on **successes and failures** during operation.

Use **state** pattern


#### ✔ Description

The Noodle Machine Connector must operate in **three distinct states**:

1. **OPEN**
2. **CLOSED**
3. **SEMI-CLOSED**

Each state defines how the connector reacts to incoming noodle preparation requests.

#### ✔ State Definitions & Rules

##### 🟢 OPEN State
- Normal operating mode
- All requests are executed normally
- If **2 exceptions occur processing**:
   - the connector switches to **CLOSED** state

##### 🔴 CLOSED State
- Protective mode
- The connector **ignores the next 5 incoming calls**
- Ignored calls:
   - must not reach the real Noodle Machine
- After 5 ignored calls:
   - the connector switches to **SEMI-CLOSED** state

##### 🟡 SEMI-CLOSED State
- Testing mode
- The connector allows **exactly one request** to pass through
- If the request:
   - **succeeds** → switch to **OPEN**
   - **fails** → switch back to **CLOSED**
