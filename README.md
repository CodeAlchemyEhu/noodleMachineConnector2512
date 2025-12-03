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
