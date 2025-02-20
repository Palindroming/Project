## 🚀 **What is Aspect-Oriented Programming (AOP)**?
### 📌 **What is AOP?
AOP (**Aspect-Oriented Programming**) is a programming technique that separates and manages common features (logging, authentication, transactions, performance measurement, etc.) that are used repeatedly regardless of business logic.

✅ **Applying AOP means:** **.  
Setting common functions to run automatically before or after certain methods are executed  
👉 Setting up common functions to run automatically before or after certain methods are executed.  
👉 Use annotations like `@Before`, `@After`, `@Around`, etc.

---]

## 🎯 **Why use AOP?
### **Problems (if you develop without AOP)** **.
For example, let's say you need the ability to log all controller methods before they are executed.

✅ **Developing without AOP (inefficient way)** ** You could use
```java
public class UserController {
    public void getUser() {
        System.out.println(“[LOG] getUser() executed”); // You have to add it for every method yourself
        // Perform business logic
    }

    public void saveUser() {
        System.out.println(“[LOG] saveUser() executed”);
        // perform business logic
    }
}
```
- Problem: You have to manually log every method, which leads to code duplication and makes maintenance difficult.
- Solution: AOP allows us to separate the logging function and run it automatically.

---.

## 🔥 **What happens when you apply AOP?
### ✅ **After applying AOP**.
```java
@Aspect
@Component
public class LoggingAspect {

    @Before(“execution(* com.example.controller..*(..))”) // before executing all methods of the controller
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(“[LOG] ‘ + joinPoint.getSignature().getName() + ’ Executed”);
    }
}
```
✅ **Result of applying AOP**.
```java
UserController.getUser(); // automatically print “[LOG] getUser executed” when executed
UserController.saveUser(); // automatically print “[LOG] saveUser executed” when executed
```
🚀 **You don't need to put the log in the method, it will be executed automatically!  
🚀 **Code is cleaner as business logic and common functionality are separated!**

---]

## 📌 **Core features of AOP**.
| Annotations | Description |
|-----------|----------------|
| `@Before` | Execute specific logic **before** a method executes |
| `@After` | Execute specific logic **after** method execution |
| `@AfterReturning` | Execute specific logic **after** the method has executed normally |
| `@AfterThrowing` | Execute when an exception is thrown during method execution
| `@Around` | Run both **before** and **after** method execution (most powerful feature) |

---]

## 🎯 **Conclusion**
✅ **What is AOP enforcement?  
👉 Setting common, recurring functions to run automatically **before or after a specific method execution**.  
It's the automatic execution of common functions without the developer having to manually put logic into each method.

✅ **Why should you use AOP?
- **To eliminate repetitive code and make maintenance easier**.
- To manage common functionality (logging, security, transaction management, etc.) in one place
- To make code more readable by separating business logic from common functionality.

Now you know why you need AOP and what “applying AOP” is! 🚀 I hope you have a better understanding!  
If you have any further questions, please ask! 😊🔥

Translated with DeepL.com (free version)